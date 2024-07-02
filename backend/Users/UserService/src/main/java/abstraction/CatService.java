package abstraction;

import dto.CatDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CatService {
    void createCat(CatDto catDto);

    void updateCat(CatDto catDto);

    void deleteCat(Long id);

    ResponseEntity<List<CatDto>> getAllCats();

    ResponseEntity<CatDto> findCatById(Long catId);

    ResponseEntity<List<CatDto>> getCatFriends(Long catId);

    void makeFriends(Long firstCatId, Long secondCatId);

    void breakFriendship(Long firstCatId, Long secondCatId);

    ResponseEntity<List<CatDto>> getCatsByOwnerId(Long ownerId);
}
