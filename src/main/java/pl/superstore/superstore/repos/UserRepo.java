package pl.superstore.superstore.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.superstore.superstore.models.User;

public interface UserRepo extends JpaRepository<User, Long>
{
}
