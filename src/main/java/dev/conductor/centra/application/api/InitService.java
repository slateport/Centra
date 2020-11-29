package dev.conductor.centra.application.api;

import dev.conductor.centra.domain.init.dto.InitDTO;

import java.security.Principal;

public interface InitService {
    InitDTO getInit(Principal principal);
}
