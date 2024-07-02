package controller;

import abstraction.OwnerService;
import dto.OwnerDto;
import mapper.OwnerMapper;
import model.OwnerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/owners")
@ComponentScan(basePackages = "service")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class OwnerController {

    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping
    public ResponseEntity<List<OwnerDto>> getAllOwners() {
        List<OwnerModel> ownerModels = ownerService.getAllOwners();
        return new ResponseEntity<>(OwnerMapper.modelsToDtos(ownerModels), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerDto> findOwnerById(@PathVariable Long id) {
        Optional<OwnerModel> ownerModelOptional = ownerService.findOwnerById(id);
        return ownerModelOptional.map(ownerModel -> new ResponseEntity<>(OwnerMapper.modelToDto(ownerModel),
                HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
