package mapper;

import dto.CatDto;
import entity.CatEntity;
import enums.CatColor;
import model.CatModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CatMapper {
    public static CatModel entityToModel(CatEntity entity) {
        if (entity == null) {
            return null;
        }
        List<CatModel> friendModels = new ArrayList<>();

        for (var friend : entity.getFriends()) {
            friendModels.add(new CatModel(friend.getId(),
                    friend.getName(),
                    friend.getBirthDate(),
                    friend.getBreed(),
                    friend.getColor(),
                    friend.getImage(),
                    friend.getOwnerId(),
                    Collections.emptyList()));
        }

        return new CatModel(entity.getId(),
                entity.getName(),
                entity.getBirthDate(),
                entity.getBreed(),
                entity.getColor(),
                entity.getImage(),
                entity.getOwnerId(),
                friendModels);
    }

    public static CatEntity modelToEntity(CatModel model) {
        if (model == null) {
            return null;
        }
        ArrayList<CatEntity> friendEntities = new ArrayList<>();

        for (var friend : model.getFriends()) {
            var friendEntity = new CatEntity(friend.getName(),
                    friend.getBirthDate(),
                    friend.getBreed(),
                    friend.getColor(),
                    friend.getImage(),
                    friend.getOwnerId(),
                    new ArrayList<>());
            if (friend.getId() != null) {
                friendEntity.setId(friend.getId());
            }
            friendEntities.add(friendEntity);
        }

        CatEntity entity = new CatEntity(model.getName(),
                model.getBirthDate(),
                model.getBreed(),
                model.getColor(),
                model.getImage(),
                model.getOwnerId(),
                friendEntities);
        if (model.getId() != null) {
            entity.setId(model.getId());
        }
        return entity;
    }

    public static List<CatModel> entitiesToModels(List<CatEntity> entities) {
        List<CatModel> models = new ArrayList<>();
        for (CatEntity entity : entities) {
            models.add(entityToModel(entity));
        }
        return models;
    }

    public static List<CatEntity> modelsToEntities(List<CatModel> models) {
        List<CatEntity> entities = new ArrayList<>();
        for (CatModel model : models) {
            entities.add(modelToEntity(model));
        }
        return entities;
    }

    public static CatDto modelToDto(CatModel catModel) {
        if (catModel == null) {
            return null;
        }

        List<CatDto> friendDtos = new ArrayList<>();

        for (var friend : catModel.getFriends()) {
            friendDtos.add(new CatDto(friend.getId(),
                    friend.getName(),
                    friend.getBirthDate(),
                    friend.getBreed(),
                    friend.getColor().name(),
                    friend.getImage(),
                    null,
                    Collections.emptyList()));
        }

        return new CatDto(catModel.getId(),
                catModel.getName(),
                catModel.getBirthDate(),
                catModel.getBreed(),
                catModel.getColor().name(),
                catModel.getImage(),
                catModel.getOwnerId(),
                friendDtos);
    }

    public static CatModel dtoToModel(CatDto catDto) {
        if (catDto == null) {
            return null;
        }

        List<CatModel> friendModels = new ArrayList<>();

        for (var friend : catDto.friends()) {
            friendModels.add(new CatModel(friend.id(),
                    friend.name(),
                    friend.birthDate(),
                    friend.breed(),
                    CatColor.valueOf(friend.color()),
                    friend.image(),
                    null,
                    Collections.emptyList()));
        }

        return new CatModel(catDto.id(),
                catDto.name(),
                catDto.birthDate(),
                catDto.breed(),
                CatColor.valueOf(catDto.color()),
                catDto.image(),
                catDto.ownerId(),
                friendModels);
    }

    public static List<CatDto> modelsToDtos(List<CatModel> catModels) {
        return catModels.stream().map(CatMapper::modelToDto).collect(Collectors.toList());
    }

    public static List<CatModel> dtosToModels(List<CatDto> catRequestBodies) {
        return catRequestBodies.stream().map(CatMapper::dtoToModel).collect(Collectors.toList());
    }
}