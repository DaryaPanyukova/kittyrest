package controller;

import abstraction.CatService;
import dto.CatDto;
import mapper.CatMapper;
import model.CatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RequestMapping("/api/cats")
@ComponentScan(basePackages = "service")
public class CatController {

    private final CatService catService;

    @Autowired
    public CatController(CatService catService) {
        this.catService = catService;
    }

    @GetMapping
    public ResponseEntity<List<CatDto>> getAllCats() {
        List<CatModel> catModels = catService.getAllCats();
        return new ResponseEntity<>(CatMapper.modelsToDtos(catModels), HttpStatus.OK);
    }

    @GetMapping("/{catId}")
    public ResponseEntity<CatDto> findCatById(@PathVariable(name = "catId") Long catId) {
        Optional<CatModel> catModelOptional = catService.findCatById(catId);
        return catModelOptional.map(catModel -> new ResponseEntity<>(CatMapper.modelToDto(catModel), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{catId}/friends")
    public ResponseEntity<List<CatDto>> getCatFriends(@PathVariable(name = "catId") Long catId) {
        List<CatModel> catFriendModels = catService.getCatFriends(catId);
        return new ResponseEntity<>(CatMapper.modelsToDtos(catFriendModels), HttpStatus.OK);
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<CatDto>> getCatsByOwnerId(@PathVariable(name = "ownerId") Long ownerId) {
        List<CatModel> catModels = catService.getCatsByOwnerId(ownerId);
        return new ResponseEntity<>(CatMapper.modelsToDtos(catModels), HttpStatus.OK);
    }
}
