package dev.conductor.centra.repository;

import dev.conductor.centra.entities.Settings;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SettingsRepository extends MongoRepository<Settings, String> {
    Settings findOneByKey(String key);
}
