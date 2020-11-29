package dev.conductor.centra.application.api;

import dev.conductor.centra.domain.installation.dto.InstallationDTO;

public interface InstallService {
    void install(InstallationDTO installationDTO);
}
