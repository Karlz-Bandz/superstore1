package pl.superstore.superstore.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.superstore.superstore.models.Product;

public interface ProductRepo extends JpaRepository<Product, Long>
{
}
