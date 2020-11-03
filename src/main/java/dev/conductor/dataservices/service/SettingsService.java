package dev.conductor.dataservices.service;

import dev.conductor.dataservices.config.SettingsEnum;
import dev.conductor.dataservices.entities.Settings;

import java.util.List;

public interface SettingsService {

    Settings getSettingsByName(SettingsEnum name);
    List<Settings> findAll();
    Settings getDefaultByName(SettingsEnum name);
    Settings save(Settings settings);
}
