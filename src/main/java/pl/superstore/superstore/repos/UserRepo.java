package pl.superstore.superstore.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.superstore.superstore.dto.ProductMenu;
import pl.superstore.superstore.models.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long>
{
    @Query("SELECT u.name FROM User u")
    List<String> getAllUsersNames();

    Optional<User> findByMail(String mail);
}
