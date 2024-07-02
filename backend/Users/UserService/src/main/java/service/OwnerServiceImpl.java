package service;

import abstraction.OwnerClient;
import abstraction.OwnerService;
import dto.OwnerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ComponentScan(basePackages = {"client", "config.kafka.producer"})
public class OwnerServiceImpl implements OwnerService {
    private final OwnerClient ownerClient;
    private final KafkaTemplate<String, Object> ownerKafka;

    @Autowired
    public OwnerServiceImpl(OwnerClient ownerClient,
                            @Qualifier("OwnerKafkaTemplate") KafkaTemplate<String, Object> ownerKafka) {
        this.ownerClient = ownerClient;
        this.ownerKafka = ownerKafka;
    }

    @Override
    public void createOwner(OwnerDto ownerDto) {
        ownerKafka.send("create_owner", ownerDto);
    }

    @Override
    public void updateOwner(OwnerDto ownerDto) {
        ownerKafka.send("update_owner", ownerDto);
    }

    @Override
    public void deleteOwner(Long id) {
        ownerKafka.send("delete_owner", id);
    }

    @Override
    public ResponseEntity<List<OwnerDto>> getAllOwners() {
        return ownerClient.getAllOwners();
    }

    @Override
    public ResponseEntity<OwnerDto> findOwnerById(Long ownerId) {
        return ownerClient.findOwnerById(ownerId);
    }
}
