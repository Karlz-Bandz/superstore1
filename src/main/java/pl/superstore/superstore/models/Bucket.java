package pl.superstore.superstore.models;

import lombok.Getter;
import pl.superstore.superstore.dto.BucketDto;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Bucket
{
    private List<BucketDto> purchases;

    public Bucket()
    {
        purchases = new ArrayList<>();
    }

    public void addNewItem(Product product)
    {
        BucketDto bucketDto = new BucketDto(product.getId(), product.getName(), product.getPrice());
        purchases.add(bucketDto);
    }
}
