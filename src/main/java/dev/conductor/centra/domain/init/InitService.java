package dev.conductor.centra.domain.init;

import dev.conductor.centra.domain.init.dto.InitDTO;

import java.security.Principal;

public interface InitService {
    InitDTO getInit(Principal principal);
}
