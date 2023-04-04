package pl.superstore.superstore.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.superstore.superstore.models.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>
{
}
