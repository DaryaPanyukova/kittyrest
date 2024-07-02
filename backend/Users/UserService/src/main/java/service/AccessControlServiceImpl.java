package service;

import abstraction.AccessControlService;
import abstraction.CatService;
import dto.CatDto;
import entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.UserRepository;

import java.util.Objects;

@Service
public class AccessControlServiceImpl implements AccessControlService {

    private final CatService catService;

    private final UserRepository userRepository;

    @Autowired
    public AccessControlServiceImpl(CatService catService, UserRepository userRepository) {
        this.catService = catService;
        this.userRepository = userRepository;
    }

    @Override
    public boolean isCurrentUser(Long ownerId, String username) {
        UserEntity user = userRepository.findByUsername(username);

        return Objects.equals(user.getOwnerId(), ownerId);
    }

    @Override
    public boolean isCurrentUserOwnerOfCat(Long catId, String username) {
        UserEntity user = userRepository.findByUsername(username);

        CatDto cat = catService.findCatById(catId).getBody();
        return cat.ownerId().equals(user.getOwnerId());

    }
}
