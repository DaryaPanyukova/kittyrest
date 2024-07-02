package service;

import abstraction.CatClient;
import abstraction.CatService;
import dto.CatDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ComponentScan(basePackages = {"client", "config.kafka.producer"})
public class CatServiceImpl implements CatService {
    private final CatClient catClient;
    private final KafkaTemplate<String, Object> catKafka;

    @Autowired
    public CatServiceImpl(CatClient catClient, @Qualifier("CatKafkaTemplate") KafkaTemplate<String, Object> catKafka) {
        this.catClient = catClient;
        this.catKafka = catKafka;
    }

    @Override
    public void createCat(CatDto catDto) {
        catKafka.send("create_cat", catDto);
    }

    @Override
    public void updateCat(CatDto catDto) {
        catKafka.send("update_cat", catDto);
    }

    @Override
    public void deleteCat(Long id) {
        catKafka.send("delete_cat", new CatDto(id));
    }

    @Override
    public ResponseEntity<List<CatDto>> getAllCats() {
        return catClient.getAllCats();
    }

    @Override
    public ResponseEntity<CatDto> findCatById(Long catId) {
        return catClient.findCatById(catId);
    }

    @Override
    public ResponseEntity<List<CatDto>> getCatFriends(Long catId) {
        return catClient.getCatFriends(catId);
    }

    @Override
    public void makeFriends(Long firstCatId, Long secondCatId) {
        catKafka.send("make_friends", List.of(firstCatId, secondCatId));
    }

    @Override
    public void breakFriendship(Long firstCatId, Long secondCatId) {
        catKafka.send("break_friendship", List.of(firstCatId, secondCatId));
    }

    @Override
    public ResponseEntity<List<CatDto>> getCatsByOwnerId(Long ownerId) {
        return catClient.getCatsByOwnerId(ownerId);
    }
}
