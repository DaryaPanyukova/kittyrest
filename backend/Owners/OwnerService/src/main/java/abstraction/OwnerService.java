package abstraction;

import model.OwnerModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface OwnerService {
    OwnerModel createOwner(OwnerModel ownerModel);

    OwnerModel updateOwner(OwnerModel ownerModel);

    boolean deleteOwner(Long id);

    List<OwnerModel> getAllOwners();

    Optional<OwnerModel> findOwnerById(Long ownerId);
}
