package dev.conductor.dataservices.restcontrollers;

import dev.conductor.dataservices.dto.ApplicationUserPassword;
import dev.conductor.dataservices.entities.ApplicationUser;
import dev.conductor.dataservices.service.ApplicationUserService;
import dev.conductor.dataservices.service.exceptions.UsernameAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@RestController
@RequestMapping("/users")
public class UserController extends BaseController {

    @Autowired
    private ApplicationUserService userService;

    @PostMapping
    public ApplicationUser signUp(
            @RequestBody ApplicationUser user,
            Principal principal
    ) {
        if (userService.count() != 0){
            if (user.getAdmin()
                    && (this.getAuthenticatedUser(principal) == null
                    || !this.getAuthenticatedUser(principal).getAdmin())
            ){
                throw new ResponseStatusException(
                        HttpStatus.UNPROCESSABLE_ENTITY,
                        "Cannot create admin user whilst not being an admin"
                );
            }
        }

        try{
            userService.createUser(user);
        } catch (UsernameAlreadyExistsException e) {
            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_ENTITY,
                    "Username already taken"
            );
        }

        user.setPassword(null);
        return user;
    }

    @PostMapping("/{id}")
    public ApplicationUser updateUser(
            @RequestBody ApplicationUser user,
            Principal principal
    ) {
        if (this.getAuthenticatedUser(principal) == null
                || (!this.getAuthenticatedUser(principal).getAdmin()
                && !user.getUsername().equals(principal.getName())
        )) {
            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_ENTITY,
                    "Not authenticated to update user"
            );
        }

        ApplicationUser originalUser = userService.findById(user.getId());

        user.setPassword(originalUser.getPassword());
        userService.save(user);

        user.setPassword(null);
        return user;
    }

    @PostMapping("/{id}/password")
    public void updateUser(
            @RequestBody ApplicationUserPassword password,
            @PathVariable String id,
            Principal principal
    ) {
        ApplicationUser authenticatedUser = this.getAuthenticatedUser(principal);

        if (authenticatedUser == null
                || (!authenticatedUser.getId().equals(id)
                && !this.getAuthenticatedUser(principal).getAdmin())
        ) {
            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_ENTITY,
                    "Not authenticated to update user"
            );
        }

        ApplicationUser user = userService.findById(id);

        userService.changePassword(user, password.getPassword());
    }

    @GetMapping("/{id}")
    public ApplicationUser updateUser(@PathVariable String id, Principal principal) {

        ApplicationUser user = userService.findById(id);

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        if (this.getAuthenticatedUser(principal) == null
                || (!this.getAuthenticatedUser(principal).getAdmin()
                && !user.getUsername().equals(principal.getName())
        )) {
            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_ENTITY,
                    "Not authenticated to update user"
            );
        }

        userService.save(user);
        user.setPassword(null);

        return user;
    }
}
