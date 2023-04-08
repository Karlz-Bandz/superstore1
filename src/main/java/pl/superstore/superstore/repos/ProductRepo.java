package pl.superstore.superstore.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.superstore.superstore.dto.ProductMenu;
import pl.superstore.superstore.models.Product;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long>
{
    @Query("SELECT new pl.superstore.superstore.dto.ProductMenu(u.id, u.name, u.imageUrl, u.price, u.category) FROM Product u")
    List<ProductMenu> getAllProductsForMenu();

    @Query("SELECT COUNT(*) FROM Product u")
    int getNumberOfProducts();
}
