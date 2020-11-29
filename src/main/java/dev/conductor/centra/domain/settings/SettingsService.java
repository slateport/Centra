package dev.conductor.centra.domain.settings;

import dev.conductor.centra.domain.settings.entity.Settings;

import java.util.List;

public interface SettingsService {

    Settings getSettingsByName(SettingsEnum name);
    List<Settings> findAll();
    Settings getDefaultByName(SettingsEnum name);
    Settings save(Settings settings);
}
