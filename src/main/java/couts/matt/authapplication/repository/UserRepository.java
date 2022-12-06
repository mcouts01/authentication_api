package couts.matt.authapplication.repository;

import couts.matt.authapplication.entity.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query("SELECT * FROM AUTHAPP.user WHERE user_id = :userid")
    User findByID(@Param("userid") Integer userID);

    @Query("SELECT * FROM AUTHAPP.user WHERE auth_id = :authid")
    Optional<User> findByAuthID(@Param("authid") String userID);
}
