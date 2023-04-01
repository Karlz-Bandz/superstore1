package pl.superstore.superstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.superstore.superstore.models.Category;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class ProductMenu
{
    private long id;
    private String name;
    private String imageUrl;
    private BigDecimal price;
    private Category category;
}
