package abstraction;

import dto.CatDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface CatClient {
    ResponseEntity<List<CatDto>> getAllCats();

    ResponseEntity<CatDto> findCatById(@PathVariable Long catId);

    ResponseEntity<List<CatDto>> getCatFriends(@PathVariable Long catId);

    ResponseEntity<List<CatDto>> getCatsByOwnerId(@PathVariable Long ownerId);
}
