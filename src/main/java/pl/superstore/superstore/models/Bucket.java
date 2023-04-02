package pl.superstore.superstore.models;

import lombok.Getter;
import pl.superstore.superstore.dto.BucketDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * <strong>Bucket</strong> class<br>
 * This class is responsible for storing data of
 * all products that we want to buy. Of course,
 * it is possible to manage the data for example
 * we can add new product or remove chosen product.
 */
@Getter
public class Bucket
{
    private List<BucketDto> purchases;

    private BigDecimal amount;

    private int numberOfItems;

    public Bucket()
    {
        purchases = new ArrayList<>();
        this.amount = new BigDecimal(0.00);
        this.numberOfItems = 0;
    }

    public void addNewItem(Product product)
    {
        BucketDto bucketDto = new BucketDto(product.getId(), product.getName(), product.getPrice());
        purchases.add(bucketDto);
        amount = amount.add(product.getPrice());
        this.numberOfItems++;
    }

    public void removeItem(int index)
    {
        int bucketLength = purchases.size();
        if(index <= bucketLength - 1 && index >= 0)
        {
            amount = amount.subtract(purchases.get(index).getPrice());
            this.numberOfItems--;
            purchases.remove(index);
        }else
        {
            System.out.println("Empty or not found!");
        }
    }
}
