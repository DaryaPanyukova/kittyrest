package abstraction;

import dto.OwnerDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OwnerService {
    void createOwner(OwnerDto ownerModel);

    void updateOwner(OwnerDto ownerModel);

    void deleteOwner(Long id);

    ResponseEntity<List<OwnerDto>> getAllOwners();

    ResponseEntity<OwnerDto> findOwnerById(Long ownerId);
}
