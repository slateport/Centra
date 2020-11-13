package dev.conductor.centra.restcontrollers;

import dev.conductor.centra.entities.ApplicationUser;
import dev.conductor.centra.service.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Principal;

abstract class BaseController {

    @Autowired
    ApplicationUserService applicationUserService;

    protected ApplicationUser getAuthenticatedUser (Principal principal) {
        if (principal == null) {
            return null;
        }

        ApplicationUser user = applicationUserService.findByUsername(principal.getName());

        if (user == null || !user.getEnabled()) {
            return null;
        }

        return user;
    }
}
