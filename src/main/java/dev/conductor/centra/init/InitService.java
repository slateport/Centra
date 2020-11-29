package dev.conductor.centra.init;

import java.security.Principal;

public interface InitService {
    InitDTO getInit(Principal principal);
}
