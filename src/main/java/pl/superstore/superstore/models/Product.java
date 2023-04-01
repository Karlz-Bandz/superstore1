package pl.superstore.superstore.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * <strong>Product</strong> class<br>
 * This is a model of all products occurring on
 * the superstore. All products are stored in database.
 */
@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Enumerated(EnumType.ORDINAL)
    private Category category;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private String imageUrl;
}