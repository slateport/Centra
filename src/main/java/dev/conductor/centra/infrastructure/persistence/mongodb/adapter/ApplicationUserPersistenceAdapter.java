package dev.conductor.centra.infrastructure.persistence.mongodb.adapter;

import dev.conductor.centra.domain.applicationUser.entiity.ApplicationUser;
import dev.conductor.centra.domain.applicationUser.spi.ApplicationUserPersistencePort;
import dev.conductor.centra.infrastructure.persistence.mongodb.entity.ApplicationUserEntity;
import dev.conductor.centra.infrastructure.persistence.mongodb.repository.ApplicationUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ApplicationUserPersistenceAdapter implements ApplicationUserPersistencePort {

    private final ModelMapper modelMapper;
    private final ApplicationUserRepository repository;

    @Autowired
    public ApplicationUserPersistenceAdapter(ApplicationUserRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;

        this.configureMapper();
    }

    @Override
    public ApplicationUser findByUsername(String username) {
        ApplicationUserEntity entity = repository.findByUsername(username);

        if (entity != null) {
            return modelMapper.map(entity, ApplicationUser.class);
        }

        return null;
    }

    @Override
    public List<ApplicationUser> findAll() {
        List<ApplicationUser> result = new ArrayList<>();

        for(ApplicationUserEntity entity: repository.findAll()){
            result.add(modelMapper.map(entity, ApplicationUser.class));
        }

        return result;
    }

    @Override
    public ApplicationUser findByEmailAddress(String emailAddress) {
        ApplicationUserEntity entity = repository.findByEmailAddress(emailAddress);

        if (entity != null) {
            return modelMapper.map(entity, ApplicationUser.class);
        }

        return null;
    }

    @Override
    public ApplicationUser findById(String id) {
        Optional<ApplicationUserEntity> entity = repository.findById(id);

        return entity.map(
                    applicationUserEntity -> modelMapper.map(applicationUserEntity, ApplicationUser.class)
                ).orElse(null);

    }

    @Override
    public ApplicationUser save(ApplicationUser user) {
        ApplicationUserEntity entity = modelMapper.map(user, ApplicationUserEntity.class);
        repository.save(entity);

        return modelMapper.map(entity, ApplicationUser.class);
    }

    private void configureMapper(){
//        this.modelMapper.typeMap(ApplicationUserEntity.class, ApplicationUser.class)
//                .addMapping()
    }
}
