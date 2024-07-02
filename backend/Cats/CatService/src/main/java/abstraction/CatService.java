package abstraction;


import model.CatModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CatService {
    CatModel createCat(CatModel catModel);

    boolean updateCat(CatModel catModel);

    boolean deleteCat(Long id);

    List<CatModel> getAllCats();

    Optional<CatModel> findCatById(Long catId);

    List<CatModel> getCatFriends(Long catId);

    void makeFriends(Long firstCatId, Long secondCatId);

    void breakFriendship(Long firstCatId, Long secondCatId);

    List<CatModel> getCatsByOwnerId(Long ownerId);
}
