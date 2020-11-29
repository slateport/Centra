package dev.conductor.centra.restcontrollers;

import dev.conductor.centra.init.InitDTO;
import dev.conductor.centra.init.InitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


@RestController
@RequestMapping("/api/init")
public class InitController {

    @Autowired
    private InitService initService;

    @GetMapping()
    public InitDTO getInit(Principal principal) {
        return initService.getInit(principal);
    }
}
