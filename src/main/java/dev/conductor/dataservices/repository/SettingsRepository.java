package dev.conductor.dataservices.repository;

import dev.conductor.dataservices.entities.Settings;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SettingsRepository extends MongoRepository<Settings, String> {
    Settings findOneByKey(String key);
}
