package pl.superstore.superstore.testservices;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import pl.superstore.superstore.models.Category;
import pl.superstore.superstore.models.Product;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
public class ProductIntegrationTest
{

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Container
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:13")
            .withDatabaseName("prop")
            .withUsername("postgres")
            .withPassword("pass")
            .withExposedPorts(5432);

    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url",
                () -> String.format("jdbc:postgresql://localhost:%d/prop", postgreSQLContainer.getFirstMappedPort()));
        registry.add("spring.datasource.username", () -> "postgres");
        registry.add("spring.datasource.password", () -> "pass");
    }

    private Product getProduct()
    {
        return Product.builder()
                .name("Szampon")
                .category(Category.DYES)
                .imageUrl("/url/test")
                .price(BigDecimal.valueOf(120.50))
                .description("Test")
                .build();
    }

    @Test
    public void AddNewProduct_Test() throws Exception
    {

        Product productTest = getProduct();

        String requestStringTest = objectMapper.writeValueAsString(productTest);



        mockMvc.perform(MockMvcRequestBuilders.post("product/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestStringTest))
                .andExpect(status().isCreated());

    }



}
