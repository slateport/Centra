package dev.conductor.centra.infrastructure.rest.controllers;

import dev.conductor.centra.domain.applicationUser.entiity.ApplicationUser;
import dev.conductor.centra.domain.applicationUser.ApplicationUserService;
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

    protected Boolean isAdmin(Principal principal) {
        return applicationUserService.isAdmin(principal);
    }
}
