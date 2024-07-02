package client;

import abstraction.CatClient;
import dto.CatDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@ComponentScan(basePackages = "config.web.client")
public class CatClientWeb implements CatClient {
    private final WebClient webClient;

    @Autowired
    public CatClientWeb(@Qualifier("WebCatClientConfiguration") WebClient webClient) {this.webClient = webClient;}

    @Override
    public ResponseEntity<List<CatDto>> getAllCats() {
        List<CatDto> cats = webClient.get().uri("").retrieve().bodyToFlux(CatDto.class).collectList().block();
        return new ResponseEntity<>(cats, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CatDto> findCatById(Long catId) {
        CatDto cat = webClient.get().uri("/{catId}", catId).retrieve().bodyToMono(CatDto.class).block();
        return new ResponseEntity<>(cat, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<CatDto>> getCatFriends(Long catId) {
        List<CatDto> catFriends = webClient.get()
                .uri("/{catId}/friends", catId)
                .retrieve()
                .bodyToFlux(CatDto.class)
                .collectList()
                .block();
        return new ResponseEntity<>(catFriends, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<CatDto>> getCatsByOwnerId(Long ownerId) {
        List<CatDto> catsByOwner = webClient.get()
                .uri("/owner/{ownerId}", ownerId)
                .retrieve()
                .bodyToFlux(CatDto.class)
                .collectList()
                .block();
        return new ResponseEntity<>(catsByOwner, HttpStatus.OK);
    }
}
