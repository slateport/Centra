package dev.conductor.dataservices.restcontrollers;

import dev.conductor.dataservices.entities.ApplicationUser;
import dev.conductor.dataservices.service.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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


        userService.createUser(user);
        return user;
    }
}
