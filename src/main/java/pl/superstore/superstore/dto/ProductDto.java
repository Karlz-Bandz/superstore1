package pl.superstore.superstore.dto;

import lombok.Builder;
import lombok.Data;
import pl.superstore.superstore.models.Category;

import java.math.BigDecimal;

@Builder
@Data
public class ProductDto
{
    private String name;
    private String description;
    private Category category;
    private BigDecimal price;
    private String imageUrl;
}
