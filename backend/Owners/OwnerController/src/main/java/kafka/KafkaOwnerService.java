package kafka;

import abstraction.OwnerService;
import dto.OwnerDto;
import mapper.OwnerMapper;
import model.OwnerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@ComponentScan(basePackages = {"kafka"})
public class KafkaOwnerService {
    private final OwnerService ownerService;
    private final KafkaTemplate<String, OwnerDto> kafkaTemplate;

    @Autowired
    public KafkaOwnerService(OwnerService ownerService, KafkaTemplate<String, OwnerDto> kafkaTemplate) {
        this.ownerService = ownerService;
        this.kafkaTemplate = kafkaTemplate;
    }


    /*  @KafkaListener(topics = "create_owner", groupId = "createOwnerGroup", containerFactory =
              "ownerConcurrentKafkaListenerContainerFactory")
      @SendTo("owner_response")
      public OwnerDto createOwner(@RequestBody OwnerDto ownerDto) {
          OwnerModel ownerModel = OwnerMapper.dtoToModel(ownerDto);
          OwnerModel createdOwnerModel = ownerService.createOwner(ownerModel);
          return OwnerMapper.modelToDto(createdOwnerModel);
      }*/
    @KafkaListener(topics = "create_owner", groupId = "createOwnerGroup", containerFactory =
            "ownerConcurrentKafkaListenerContainerFactory")
    public void createOwner(OwnerDto ownerDto) {
        OwnerModel ownerModel = OwnerMapper.dtoToModel(ownerDto);
        OwnerModel createdOwner = ownerService.createOwner(ownerModel);
        OwnerDto createdOwnerDto = OwnerMapper.modelToDto(createdOwner);
        kafkaTemplate.send("create_owner_response", createdOwnerDto);
    }

    @KafkaListener(topics = "update_owner", groupId = "updateOwnerGroup", containerFactory =
            "ownerConcurrentKafkaListenerContainerFactory")
    public void updateOwner(@RequestBody OwnerDto ownerDto) {
        OwnerModel ownerModel = OwnerMapper.dtoToModel(ownerDto);
        ownerService.updateOwner(ownerModel);
    }

    @KafkaListener(topics = "delete_owner", groupId = "deleteOwnerGroup", containerFactory =
            "ownerConcurrentKafkaListenerContainerFactory")
    public void deleteOwner(@PathVariable(name = "id") Long id) {
        ownerService.deleteOwner(id);
    }
}
