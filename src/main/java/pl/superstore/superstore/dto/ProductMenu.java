package pl.superstore.superstore.dto;

import lombok.Data;
import pl.superstore.superstore.models.Category;

import java.math.BigDecimal;


@Data
public class ProductMenu
{
    private long id;
    private String name;
    private String imageUrl;
    private BigDecimal price;
    private String category;

    public ProductMenu(long id, String name, String imageUrl, BigDecimal price, Category category)
    {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
        this.category = category.getCategory();
    }
}
