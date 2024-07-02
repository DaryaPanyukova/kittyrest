package mapper;

import dto.OwnerDto;
import entity.OwnerEntity;
import model.OwnerModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OwnerMapper {
    public static OwnerDto modelToDto(OwnerModel ownerModel) {
        if (ownerModel == null) {
            return null;
        }
        return new OwnerDto(ownerModel.getId(), ownerModel.getName(), ownerModel.getBirthDate());
    }

    public static OwnerModel dtoToModel(OwnerDto ownerDto) {
        if (ownerDto == null) {
            return null;
        }

        return new OwnerModel(ownerDto.getId(), ownerDto.getName(), ownerDto.getBirthDate());
    }

    public static List<OwnerDto> modelsToDtos(List<OwnerModel> ownerModels) {
        return ownerModels.stream().map(OwnerMapper::modelToDto).collect(Collectors.toList());
    }

    public static List<OwnerModel> dtosToModels(List<OwnerDto> ownerRequestBodies) {
        return ownerRequestBodies.stream().map(OwnerMapper::dtoToModel).collect(Collectors.toList());
    }

    public static OwnerModel entityToModel(OwnerEntity entity) {
        if (entity == null) {
            return null;
        }

        return new OwnerModel(entity.getId(), entity.getName(), entity.getBirthDate());
    }

    public static OwnerEntity modelToEntity(OwnerModel model) {
        if (model == null) {
            return null;
        }
        if (model.getId() == null) {
            return new OwnerEntity(model.getName(), model.getBirthDate());
        }
        return new OwnerEntity(model.getId(), model.getName(), model.getBirthDate());
    }

    public static List<OwnerModel> entitiesToModels(List<OwnerEntity> entities) {
        List<OwnerModel> models = new ArrayList<>();
        for (OwnerEntity entity : entities) {
            models.add(entityToModel(entity));
        }
        return models;
    }

    public static List<OwnerEntity> modelsToEntities(List<OwnerModel> models) {
        List<OwnerEntity> entities = new ArrayList<>();
        for (OwnerModel model : models) {
            entities.add(modelToEntity(model));
        }
        return entities;
    }
}