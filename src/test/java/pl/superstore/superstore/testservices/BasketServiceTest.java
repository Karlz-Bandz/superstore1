package pl.superstore.superstore.testservices;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.superstore.superstore.dto.BucketDto;
import pl.superstore.superstore.models.Category;
import pl.superstore.superstore.models.Product;
import pl.superstore.superstore.repos.ProductRepo;
import pl.superstore.superstore.services.BasketService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BasketServiceTest
{
    @Autowired
    private ProductRepo productRepo;

    /**
     * This method creates a fake database for the testing purpose
     */
    @BeforeAll
    void setup() {
        List<Product> products = Arrays.asList(
                new Product("Test1", "DescriptionTest", Category.KERATIN, BigDecimal.valueOf(10.55),"/rr/jj.jpg"),
                new Product("Test2", "DescriptionTest",Category.KERATIN, BigDecimal.valueOf(10.55),"/rr/jj.jpg"),
                new Product("Test3", "DescriptionTest",Category.SETS, BigDecimal.valueOf(10.55),"/rr/jj.jpg"),
                new Product("Test4", "DescriptionTest",Category.KERATIN, BigDecimal.valueOf(10.55),"/rr/jj.jpg"),
                new Product("Test5", "DescriptionTest",Category.SHAMPOOS, BigDecimal.valueOf(10.55),"/rr/jj.jpg"),
                new Product("Test6", "DescriptionTest",Category.KERATIN, BigDecimal.valueOf(10.55),"/rr/jj.jpg"),
                new Product("Test7", "DescriptionTest",Category.OILS, BigDecimal.valueOf(10.55),"/rr/jj.jpg"),
                new Product("Test8", "DescriptionTest",Category.OILS, BigDecimal.valueOf(10.55),"/rr/jj.jpg")
        );
        productRepo.saveAll(products);
    }

    @Test
    public void removeFromBucket_Test()
    {
        BasketService basketServiceTest = new BasketService(productRepo);

        basketServiceTest.removeFromBasket(3);
        basketServiceTest.addToBasket(2);
        basketServiceTest.addToBasket(2);
        basketServiceTest.addToBasket(1);
        basketServiceTest.addToBasket(3);
        basketServiceTest.addToBasket(4);
        basketServiceTest.addToBasket(5);

        List<BucketDto> testList = basketServiceTest.showBasket();
        int testLength = testList.size();
        int testNumberOfItems = basketServiceTest.showTheNumberOfBasketItems();
        BigDecimal testAmount = basketServiceTest.showBasketAmount();

        assertEquals(testLength, 6);
        assertEquals(testAmount.compareTo(BigDecimal.valueOf(63.3)), 0);
        assertEquals(testNumberOfItems, 6);

        //Here try to remove item which is not exist
        basketServiceTest.removeFromBasket(6);

        testList = basketServiceTest.showBasket();
        testLength = testList.size();
        testAmount = basketServiceTest.showBasketAmount();
        testNumberOfItems = basketServiceTest.showTheNumberOfBasketItems();

        //And check if data inside the bucket wasn't changed if
        //is not test is passed
        assertEquals(testLength, 6);
        assertEquals(testAmount.compareTo(BigDecimal.valueOf(63.3)), 0);
        assertEquals(testNumberOfItems, 6);

        //Now removing real item and check all changes
        basketServiceTest.removeFromBasket(3);

        testList = basketServiceTest.showBasket();
        testLength = testList.size();
        testAmount = basketServiceTest.showBasketAmount();
        testNumberOfItems = basketServiceTest.showTheNumberOfBasketItems();

        assertEquals(testLength, 5);
        assertEquals(testAmount.compareTo(BigDecimal.valueOf(52.75)), 0);
        assertEquals(testNumberOfItems, 5);
    }

    @Test
    public void addToBucket_Test()
    {
        BasketService basketServiceTest = new BasketService(productRepo);

        basketServiceTest.addToBasket(2);
        basketServiceTest.addToBasket(2);
        basketServiceTest.addToBasket(1);

        List<BucketDto> testBucketDto = basketServiceTest.showBasket();

        BigDecimal testAmount = basketServiceTest.showBasketAmount();
        int testNumberOfItems = basketServiceTest.showTheNumberOfBasketItems();

        System.out.println("\nINSIDE THE BUCKET\n" + testBucketDto.toString() + "\n\n");

        assertEquals(testBucketDto.size(), 3);
        assertEquals(testNumberOfItems, 3);
        assertEquals(testAmount.compareTo(BigDecimal.valueOf(31.65)), 0);

        //Clear the bucket
        basketServiceTest.removeFromBasket(2);
        basketServiceTest.removeFromBasket(1);
        basketServiceTest.removeFromBasket(0);

    }
}
