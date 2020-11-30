package dev.conductor.centra.infrastructure.persistence.mongodb.repository;

import dev.conductor.centra.domain.settings.entity.Settings;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SettingsRepository extends MongoRepository<Settings, String> {
    Settings findOneByKey(String key);
}
