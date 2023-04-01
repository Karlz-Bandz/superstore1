package pl.superstore.superstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class BucketDto
{
    private long id;
    private String name;
    private BigDecimal price;
}
