package controller;

import abstraction.AccessControlService;
import abstraction.CatService;
import dto.CatDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RequestMapping("/api/cats")
@ComponentScan(basePackages = "ru.daryapanyukova.backend.service.abstraction")
public class CatController {
    private final CatService catService;

    private final AccessControlService accessControlService;

    @Autowired
    public CatController(CatService catService, AccessControlService accessControlService) {
        this.catService = catService;
        this.accessControlService = accessControlService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    public void createCat(@RequestBody CatDto catDto) {
        catService.createCat(catDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or @accessControlService.isCurrentUserOwnerOfCat(#id, authentication" +
                  ".name)")
    public void updateCat(@RequestBody CatDto catDto) {
        catService.updateCat(catDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or @accessControlService.isCurrentUserOwnerOfCat(#id, authentication" +
                  ".name)")
    public void deleteCat(@PathVariable(name = "id") Long id) {
        catService.deleteCat(id);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<CatDto>> getAllCats() {
        return catService.getAllCats();
    }

    @GetMapping("/{catId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or @accessControlService.isCurrentUserOwnerOfCat(#catId, authentication" +
                  ".name)")
    public ResponseEntity<CatDto> findCatById(@PathVariable(name = "catId") Long catId) {
        return catService.findCatById(catId);
    }

    @GetMapping("/{catId}/friends")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or @accessControlService.isCurrentUserOwnerOfCat(#catId, authentication" +
                  ".name)")
    public ResponseEntity<List<CatDto>> getCatFriends(@PathVariable(name = "catId") Long catId) {
        return catService.getCatFriends(catId);
    }

    @PostMapping("/{firstCatId}/friends/{secondCatId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or @accessControlService.isCurrentUserOwnerOfCat(#firstCatId, " +
                  "authentication.name)")
    public ResponseEntity<Void> makeFriends(@PathVariable(name = "firstCatId") Long firstCatId,
                                            @PathVariable(name = "secondCatId") Long secondCatId) {
        catService.makeFriends(firstCatId, secondCatId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{firstCatId}/friends/{secondCatId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or @accessControlService.isCurrentUserOwnerOfCat(#firstCatId, " +
                  "authentication.name)")
    public ResponseEntity<Void> breakFriendship(@PathVariable(name = "firstCatId") Long firstCatId,
                                                @PathVariable(name = "secondCatId") Long secondCatId) {
        catService.breakFriendship(firstCatId, secondCatId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/owner/{ownerId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or @accessControlService.isCurrentUser(#ownerId, authentication.name)")
    public ResponseEntity<List<CatDto>> getCatsByOwnerId(@PathVariable(name = "ownerId") Long ownerId) {
        return catService.getCatsByOwnerId(ownerId);
    }
}
