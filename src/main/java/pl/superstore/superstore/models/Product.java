package pl.superstore.superstore.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

/**
 * <strong>Product</strong> class<br>
 * This is a model of all products occurring on
 * the superstore. All products are stored in database.
 */
@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, length = 2000)
    private String description;
    @Enumerated(EnumType.ORDINAL)
    private Category category;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private String imageUrl;

    public Product(String name, String description, Category category, BigDecimal price, String imageUrl)
    {
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.imageUrl = imageUrl;
    }


}