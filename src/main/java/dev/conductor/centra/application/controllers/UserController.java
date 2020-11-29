package dev.conductor.centra.application.controllers;

import dev.conductor.centra.domain.applicationUser.dto.ApplicationUserPasswordDTO;
import dev.conductor.centra.domain.applicationUser.dto.UserLiteDTO;
import dev.conductor.centra.domain.applicationUser.entiity.ApplicationUser;
import dev.conductor.centra.application.api.ApplicationUserService;
import dev.conductor.centra.domain.applicationUser.exceptions.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController extends BaseController {

    @Autowired
    private ApplicationUserService userService;

    @PostMapping
    public ApplicationUser signUp(
            @RequestBody ApplicationUser user,
            Principal principal
    ) {
        if (user.getAdmin()
                && (this.getAuthenticatedUser(principal) == null
                || !this.getAuthenticatedUser(principal).getAdmin())
        ){
            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_ENTITY,
                    "Cannot create admin user whilst not being an admin"
            );
        }

        try{
            userService.createUser(user);
        } catch (UserAlreadyExistsException e) {
            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_ENTITY,
                    e.getMessage()
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
            @RequestBody ApplicationUserPasswordDTO password,
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
    public ApplicationUser getUser(@PathVariable String id) {

        ApplicationUser user = userService.findById(id);

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        userService.save(user);
        user.setPassword(null);

        return user;
    }

    @GetMapping("/lite")
    public List<UserLiteDTO> getAllUserLite() {
        return userService.findAllLite();
    }
}
