package service;

import abstraction.OwnerService;
import entity.OwnerEntity;
import jakarta.persistence.EntityNotFoundException;
import mapper.OwnerMapper;
import model.OwnerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import repository.OwnerRepository;

import java.util.List;
import java.util.Optional;


@Service
@ComponentScan(basePackages = "repository")
public class OwnerServiceImpl implements OwnerService {
    private final OwnerRepository ownerRepository;

    @Autowired
    public OwnerServiceImpl(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public OwnerModel createOwner(OwnerModel ownerModel) {
        OwnerEntity ownerEntity = OwnerMapper.modelToEntity(ownerModel);
        ownerRepository.save(ownerEntity);
        return OwnerMapper.entityToModel(ownerEntity);
    }

    @Override
    public OwnerModel updateOwner(OwnerModel ownerModel) {
        OwnerEntity oldEntity = ownerRepository.findById(ownerModel.getId())
                .orElseThrow(() -> new EntityNotFoundException("Owner not found with id: " + ownerModel.getId()));

        OwnerEntity newEntity = OwnerMapper.modelToEntity(ownerModel);
        newEntity.setId(oldEntity.getId());
        ownerRepository.save(newEntity);
        return OwnerMapper.entityToModel(newEntity);
    }

    @Override
    public boolean deleteOwner(Long ownerId) {
        if (ownerRepository.existsById(ownerId)) {
            ownerRepository.deleteById(ownerId);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<OwnerModel> getAllOwners() {
        List<OwnerEntity> ownerEntities = ownerRepository.findAll();
        return OwnerMapper.entitiesToModels(ownerEntities);
    }

    @Override
    public Optional<OwnerModel> findOwnerById(Long ownerId) {
        Optional<OwnerEntity> ownerEntityOptional = ownerRepository.findById(ownerId);
        return ownerEntityOptional.map(OwnerMapper::entityToModel);
    }
}