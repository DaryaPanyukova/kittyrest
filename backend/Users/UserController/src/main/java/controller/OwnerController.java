package controller;

import abstraction.AccessControlService;
import abstraction.OwnerService;
import dto.OwnerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/owners")
@ComponentScan(basePackages = "service.abstraction")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class OwnerController {

    private final OwnerService ownerService;

    private final AccessControlService accessControlService;

    @Autowired
    public OwnerController(OwnerService ownerService, AccessControlService accessControlService) {
        this.ownerService = ownerService;
        this.accessControlService = accessControlService;
    }

    @PostMapping
    @PreAuthorize("isAnonymous() or hasAuthority('ROLE_ADMIN')")
    public void createOwner(@RequestBody OwnerDto ownerDto) {
        ownerService.createOwner(ownerDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize(
            "hasAuthority('ROLE_ADMIN') or @accessControlService.isCurrentUser(#ownerDto.id, authentication" + ".name)")
    public void updateOwner(@RequestBody OwnerDto ownerDto) {
        ownerService.updateOwner(ownerDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or @accessControlService.isCurrentUser(#id, authentication.name)")
    public void deleteOwner(@PathVariable(name = "id") Long id) {
        ownerService.deleteOwner(id);
    }

    @GetMapping
    public ResponseEntity<List<OwnerDto>> getAllOwners() {
        return ownerService.getAllOwners();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or @accessControlService.isCurrentUser(#id, authentication.name)")
    public ResponseEntity<OwnerDto> findOwnerById(@PathVariable(name = "id") Long id) {
        return ownerService.findOwnerById(id);
    }
}
