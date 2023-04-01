package pl.superstore.superstore.testservices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.superstore.superstore.dto.ProductMenu;
import pl.superstore.superstore.models.Category;
import pl.superstore.superstore.models.Product;
import pl.superstore.superstore.repos.ProductRepo;
import pl.superstore.superstore.services.ProductService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ProductServiceTest
{
    @Autowired
    private ProductRepo productRepo;

    @BeforeEach
    public void setup() {
        List<Product> products = Arrays.asList(
                new Product("Test1", "DescriptionTest",Category.KERATIN, BigDecimal.valueOf(10.55),"/rr/jj.jpg"),
                new Product("Test2", "DescriptionTest",Category.KERATIN, BigDecimal.valueOf(10.55),"/rr/jj.jpg"),
                new Product("Test3", "DescriptionTest",Category.KERATIN, BigDecimal.valueOf(10.55),"/rr/jj.jpg"),
                new Product("Test4", "DescriptionTest",Category.KERATIN, BigDecimal.valueOf(10.55),"/rr/jj.jpg"),
                new Product("Test5", "DescriptionTest",Category.KERATIN, BigDecimal.valueOf(10.55),"/rr/jj.jpg"),
                new Product("Test6", "DescriptionTest",Category.KERATIN, BigDecimal.valueOf(10.55),"/rr/jj.jpg"),
                new Product("Test7", "DescriptionTest",Category.KERATIN, BigDecimal.valueOf(10.55),"/rr/jj.jpg")
        );
        productRepo.saveAll(products);
    }

    @Test
    public void getOnePage_Test() {
        // Arrange
        ProductService productService = new ProductService(productRepo);

        // Act
        List<ProductMenu> result = productService.getOnePage(0);

        // Assert
        List<ProductMenu> expected = Arrays.asList(
                new ProductMenu(1L, "Test1", "/rr/jj.jpg", BigDecimal.valueOf(10.55), Category.KERATIN),
                new ProductMenu(2L, "Test2", "/rr/jj.jpg", BigDecimal.valueOf(10.55), Category.KERATIN),
                new ProductMenu(3L, "Test3", "/rr/jj.jpg", BigDecimal.valueOf(10.55), Category.KERATIN),
                new ProductMenu(4L, "Test4", "/rr/jj.jpg", BigDecimal.valueOf(10.55), Category.KERATIN),
                new ProductMenu(5L, "Test5", "/rr/jj.jpg", BigDecimal.valueOf(10.55), Category.KERATIN)
        );
        assertEquals(expected, result);
    }
}
