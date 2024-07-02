package abstraction;

import org.springframework.stereotype.Service;

@Service
public interface AccessControlService {
    boolean isCurrentUser(Long ownerId, String username);

    boolean isCurrentUserOwnerOfCat(Long catId, String username);
}
