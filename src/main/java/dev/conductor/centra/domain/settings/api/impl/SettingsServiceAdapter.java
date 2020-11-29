package dev.conductor.centra.domain.settings.api.impl;

import dev.conductor.centra.domain.settings.api.SettingsService;
import dev.conductor.centra.domain.settings.DefualtSettingsEnum;
import dev.conductor.centra.domain.settings.SettingsEnum;
import dev.conductor.centra.domain.settings.entity.Settings;
import dev.conductor.centra.infrastructure.persistence.mongodb.SettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SettingsServiceAdapter implements SettingsService {

    @Autowired private SettingsRepository settingsRepository;

    @Override
    public Settings getSettingsByName(SettingsEnum name) {
        return settingsRepository.findOneByKey(name.name());
    }

    @Override
    public List<Settings> findAll() {
        return settingsRepository.findAll();
    }

    @Override
    public Settings getDefaultByName(SettingsEnum name) {
        DefualtSettingsEnum defaultSettings = DefualtSettingsEnum.valueOf(name.name());

        return new Settings(name.name(), defaultSettings.toString());
    }

    @Override
    public Settings save(Settings settings) {
        return settingsRepository.save(settings);
    }
}
