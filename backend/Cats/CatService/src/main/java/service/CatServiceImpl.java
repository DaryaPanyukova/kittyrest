package service;

import abstraction.CatService;
import entity.CatEntity;
import mapper.CatMapper;
import model.CatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import repository.CatRepository;

import java.util.List;
import java.util.Optional;

@Service
@ComponentScan(basePackages = "repository")
public class CatServiceImpl implements CatService {

    private final CatRepository catRepository;

    @Autowired
    public CatServiceImpl(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    @Override
    public CatModel createCat(CatModel catModel) {
        var catEntity = CatMapper.modelToEntity(catModel);
        return CatMapper.entityToModel(catRepository.save(catEntity));
    }


    @Override
    public boolean updateCat(CatModel catModel) {
        Optional<CatEntity> existingCat = catRepository.findById(catModel.getId());

        if (existingCat.isPresent()) {
            CatEntity updatedCat = CatMapper.modelToEntity(catModel);
            updatedCat.setId(existingCat.get().getId());
            updatedCat.setOwnerId(existingCat.get().getOwnerId());
            updatedCat.setFriends(existingCat.get().getFriends());

            catRepository.save(updatedCat);
            return true;
        } else {
            return false;
        }
    }


    @Override
    public boolean deleteCat(Long catId) {
        if (catRepository.existsById(catId)) {
            catRepository.deleteById(catId);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<CatModel> getAllCats() {
        return CatMapper.entitiesToModels(catRepository.findAll());
    }

    @Override
    public Optional<CatModel> findCatById(Long catId) {
        return catRepository.findById(catId).map(CatMapper::entityToModel);
    }

    @Override
    public List<CatModel> getCatFriends(Long catId) {
        var catEntity = catRepository.findById(catId).orElseThrow(() -> new RuntimeException("Cat not found"));
        return CatMapper.entitiesToModels(catEntity.getFriends());
    }

    @Override
    public void makeFriends(Long firstCatId, Long secondCatId) {
        var firstCat = catRepository.findById(firstCatId).orElseThrow(() -> new RuntimeException("Cat not found"));
        var secondCat = catRepository.findById(secondCatId).orElseThrow(() -> new RuntimeException("Cat not found"));

        firstCat.getFriends().add(secondCat);
        secondCat.getFriends().add(firstCat);

        catRepository.addFriendToCat(firstCatId, firstCat.getFriends());
        catRepository.addFriendToCat(secondCatId, secondCat.getFriends());
    }

    @Override
    public void breakFriendship(Long firstCatId, Long secondCatId) {
        var firstCat = catRepository.findById(firstCatId).orElseThrow(() -> new RuntimeException("Cat not found"));
        var secondCat = catRepository.findById(secondCatId).orElseThrow(() -> new RuntimeException("Cat not found"));

        firstCat.getFriends().remove(secondCat);
        secondCat.getFriends().remove(firstCat);

        catRepository.addFriendToCat(firstCatId, firstCat.getFriends());
        catRepository.addFriendToCat(secondCatId, secondCat.getFriends());
    }

    @Override
    public List<CatModel> getCatsByOwnerId(Long ownerId) {
        return CatMapper.entitiesToModels(catRepository.getCatsByOwnerId(ownerId));
    }
}