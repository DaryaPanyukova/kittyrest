package kafka;

import abstraction.CatService;
import dto.CatDto;
import mapper.CatMapper;
import model.CatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Component
@ComponentScan(basePackages = {"kafka"})
public class KafkaCatService {
    private final CatService catService;

    @Autowired
    public KafkaCatService(CatService catService) {
        this.catService = catService;
    }

    @KafkaListener(topics = "create_cat", groupId = "createCatGroup", containerFactory =
            "catConcurrentKafkaListenerContainerFactory")
    public void createCat(@RequestBody CatDto cat) {
        catService.createCat(CatMapper.dtoToModel(cat));
    }

    @KafkaListener(topics = "update_cat", groupId = "updateCatGroup", containerFactory =
            "catConcurrentKafkaListenerContainerFactory")
    public void updateCat(@RequestBody CatDto cat) {
        CatModel catModel = CatMapper.dtoToModel(cat);
        catService.updateCat(catModel);
    }

    @KafkaListener(topics = "delete_cat", groupId = "deleteCatGroup", containerFactory =
            "catConcurrentKafkaListenerContainerFactory")
    public void deleteCat(@PathVariable(name = "id") Long id) {
        catService.deleteCat(id);
    }


    @KafkaListener(topics = "make_friends", groupId = "makeFriendsCatGroup", containerFactory =
            "catConcurrentKafkaListenerContainerFactory")
    public ResponseEntity<Void> makeFriends(@Payload Map<String, Long> payload) {
        catService.makeFriends(payload.get("firstCatId"), payload.get("secondCatId"));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @KafkaListener(topics = "break_friendship", groupId = "breakFriendshipCatGroup", containerFactory =
            "catConcurrentKafkaListenerContainerFactory")
    public ResponseEntity<Void> breakFriendship(@PathVariable Long firstCatId, @PathVariable Long secondCatId) {
        catService.breakFriendship(firstCatId, secondCatId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
