package pl.superstore.superstore.testservices;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.superstore.superstore.dto.BucketDto;
import pl.superstore.superstore.dto.ProductMenu;
import pl.superstore.superstore.models.Bucket;
import pl.superstore.superstore.models.Category;
import pl.superstore.superstore.models.Product;
import pl.superstore.superstore.repos.ProductRepo;
import pl.superstore.superstore.services.ProductService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductServiceTest
{
    @Autowired
    private ProductRepo productRepo;


    private Bucket testBucket = new Bucket();

    /**
     * This method creates a fake database for the testing purpose
     */
    @BeforeAll
    void setup() {
        List<Product> products = Arrays.asList(
                new Product("Test1", "DescriptionTest",Category.KERATIN, BigDecimal.valueOf(10.55),"/rr/jj.jpg"),
                new Product("Test2", "DescriptionTest",Category.KERATIN, BigDecimal.valueOf(10.55),"/rr/jj.jpg"),
                new Product("Test3", "DescriptionTest",Category.SETS, BigDecimal.valueOf(10.55),"/rr/jj.jpg"),
                new Product("Test4", "DescriptionTest",Category.KERATIN, BigDecimal.valueOf(10.55),"/rr/jj.jpg"),
                new Product("Test5", "DescriptionTest",Category.SHAMPOOS, BigDecimal.valueOf(10.55),"/rr/jj.jpg"),
                new Product("Test6", "DescriptionTest",Category.KERATIN, BigDecimal.valueOf(10.55),"/rr/jj.jpg"),
                new Product("Test7", "DescriptionTest",Category.OILS, BigDecimal.valueOf(10.55),"/rr/jj.jpg")
        );
        productRepo.saveAll(products);
    }

    @Test
    public void addToBucket_Test()
    {
        ProductService productService = new ProductService(productRepo, testBucket);

        productService.addToBucket(2);
        productService.addToBucket(4);
        productService.addToBucket(1);

        List<BucketDto> testBucketDto = productService.showBucket();

        System.out.println("\nINSIDE THE BUCKET\n" + testBucketDto.toString() + "\n\n");

        assertEquals(testBucketDto.size(), 3);

    }

    @Test
    public void addNewProduct_Test()
    {
        ProductService productService = new ProductService(productRepo, testBucket);

        Product newProductTest1 = new Product("Test8", "DescriptionTest",Category.OILS, BigDecimal.valueOf(10.55),"/rr/jj.jpg");
        Product newProductTest2 = new Product("Test9", "DescriptionTest",Category.OILS, BigDecimal.valueOf(10.55),"/rr/jj.jpg");

       int returnedTest1 = productService.addNewProduct(newProductTest1);
       int returnedTest2 = productService.addNewProduct(newProductTest2);

       Product addedProductTest = productService.getById(8).orElse(new Product());

       assertEquals(returnedTest1, 1);
       assertEquals(returnedTest2, 1);
       assertEquals(addedProductTest.getName(), "Test8");
    }

    @Test
    public void getById_Test()
    {
        ProductService productService = new ProductService(productRepo,testBucket);

        Optional<Product> returnedProductTest1 = productService.getById(2);
        Optional<Product> returnedProductTest2 = productService.getById(3);

        Product productTest1 = returnedProductTest1.orElse(new Product());
        Product productTest2 = returnedProductTest2.orElse(new Product());

              assertEquals(productTest1.getId(), 2);
              assertEquals(productTest1.getName(), "Test2");
              assertEquals(productTest2.getId(), 3);
              assertEquals(productTest2.getName(), "Test3");
    }

    @Test
    public void getOnePage_Test()
    {
        ProductService productService = new ProductService(productRepo, testBucket);

        List<ProductMenu> page1Test = productService.getOnePage(0);
        List<ProductMenu> page2Test = productService.getOnePage(1);

        List<ProductMenu> expected = Arrays.asList(
                new ProductMenu(1L, "Test1", "/rr/jj.jpg", BigDecimal.valueOf(10.55), Category.KERATIN),
                new ProductMenu(2L, "Test2", "/rr/jj.jpg", BigDecimal.valueOf(10.55), Category.KERATIN),
                new ProductMenu(3L, "Test3", "/rr/jj.jpg", BigDecimal.valueOf(10.55), Category.SETS),
                new ProductMenu(4L, "Test4", "/rr/jj.jpg", BigDecimal.valueOf(10.55), Category.KERATIN),
                new ProductMenu(5L, "Test5", "/rr/jj.jpg", BigDecimal.valueOf(10.55), Category.SHAMPOOS)
        );
        List<ProductMenu> expected2 = Arrays.asList(
                new ProductMenu(6L, "Test6", "/rr/jj.jpg", BigDecimal.valueOf(10.55), Category.KERATIN),
                new ProductMenu(7L, "Test7", "/rr/jj.jpg", BigDecimal.valueOf(10.55), Category.OILS)
        );

        assertEquals(expected, page1Test);
        assertEquals(expected2, page2Test);
    }
}
