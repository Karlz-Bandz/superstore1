package pl.superstore.superstore.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.superstore.superstore.models.Role;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role, Integer>
{
    Optional<Role> findByRole(String name);
}
