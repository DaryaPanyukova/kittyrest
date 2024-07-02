package abstraction;

import dto.OwnerDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface OwnerClient {

    ResponseEntity<List<OwnerDto>> getAllOwners();

    ResponseEntity<OwnerDto> findOwnerById(@PathVariable Long id);
}
