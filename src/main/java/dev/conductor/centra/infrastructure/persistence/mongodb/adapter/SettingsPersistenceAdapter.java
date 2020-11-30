package dev.conductor.centra.infrastructure.persistence.mongodb.adapter;

import dev.conductor.centra.domain.settings.entity.Settings;
import dev.conductor.centra.domain.settings.spi.SettingsPersistencePort;
import dev.conductor.centra.infrastructure.persistence.mongodb.entity.SettingsEntity;
import dev.conductor.centra.infrastructure.persistence.mongodb.repository.SettingsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class SettingsPersistenceAdapter implements SettingsPersistencePort {

    private final SettingsRepository repository;
    private final ModelMapper modelMapper;

    @Autowired
    public SettingsPersistenceAdapter(SettingsRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Settings findOneByKey(String key) {
        return modelMapper.map(repository.findOneByKey(key), Settings.class);
    }

    @Override
    public List<Settings> findAll() {
        return repository.findAll()
                .stream()
                .map(settingsEntity -> modelMapper.map(settingsEntity, Settings.class))
                .collect(Collectors.toList());
    }

    @Override
    public Settings save(Settings settings) {
        return modelMapper.map(modelMapper.map(settings, SettingsEntity.class), Settings.class);
    }
}
