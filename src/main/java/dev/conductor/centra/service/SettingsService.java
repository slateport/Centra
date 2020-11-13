package dev.conductor.centra.service;

import dev.conductor.centra.config.SettingsEnum;
import dev.conductor.centra.entities.Settings;

import java.util.List;

public interface SettingsService {

    Settings getSettingsByName(SettingsEnum name);
    List<Settings> findAll();
    Settings getDefaultByName(SettingsEnum name);
    Settings save(Settings settings);
}
