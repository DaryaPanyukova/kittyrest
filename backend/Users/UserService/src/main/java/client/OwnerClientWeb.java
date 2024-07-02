package client;

import abstraction.OwnerClient;
import dto.OwnerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class OwnerClientWeb implements OwnerClient {
    private final WebClient webClientOwner;

    @Autowired
    public OwnerClientWeb(@Qualifier("WebOwnerClientConfiguration") WebClient webClientOwner) {
        this.webClientOwner = webClientOwner;
    }

    @Override
    public ResponseEntity<List<OwnerDto>> getAllOwners() {
        List<OwnerDto> owners = webClientOwner.get()
                .uri("")
                .retrieve()
                .bodyToFlux(OwnerDto.class)
                .collectList()
                .block();
        return new ResponseEntity<>(owners, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<OwnerDto> findOwnerById(Long id) {
        OwnerDto owner = webClientOwner.get().uri("/{id}", id).retrieve().bodyToMono(OwnerDto.class).block();
        return new ResponseEntity<>(owner, HttpStatus.OK);
    }
}
