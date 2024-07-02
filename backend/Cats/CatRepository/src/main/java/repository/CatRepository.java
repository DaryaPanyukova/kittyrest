package repository;

import entity.CatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatRepository extends JpaRepository<CatEntity, Long> {
    List<CatEntity> findAll();

    void deleteById(Long id);

    List<CatEntity> findAllByFriendsId(Long friendId);

    List<CatEntity> findAllByOwnerId(Long ownerId);

    List<CatEntity> getCatsByOwnerId(Long ownerId);

    @Modifying
    @Query("UPDATE CatEntity c SET c.friends = :newFriends WHERE c.id = :catId")
    void addFriendToCat(@Param("catId") Long catId, @Param("newFriends") List<CatEntity> newFriends);

    @Modifying
    @Query("UPDATE CatEntity c SET c.friends = :newFriends WHERE c.id = :catId")
    void removeFriendFromCat(@Param("catId") Long catId, @Param("newFriends") List<CatEntity> newFriends);
}
