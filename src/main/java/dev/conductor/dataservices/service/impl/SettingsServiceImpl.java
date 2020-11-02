package dev.conductor.dataservices.service.impl;

import dev.conductor.dataservices.config.DefualtSettingsEnum;
import dev.conductor.dataservices.config.SettingsEnum;
import dev.conductor.dataservices.entities.Settings;
import dev.conductor.dataservices.repository.SettingsRepository;
import dev.conductor.dataservices.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SettingsServiceImpl implements SettingsService {

    @Autowired private SettingsRepository settingsRepository;

    @Override
    public Settings getSettingsByName(SettingsEnum name) {
        return settingsRepository.findOneByKey(name.toString());
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
}
