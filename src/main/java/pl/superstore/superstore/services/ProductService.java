package pl.superstore.superstore.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.superstore.superstore.dto.BucketDto;
import pl.superstore.superstore.dto.ProductMenu;
import pl.superstore.superstore.interfaces.Producer;
import pl.superstore.superstore.models.Bucket;
import pl.superstore.superstore.models.Product;
import pl.superstore.superstore.repos.ProductRepo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <strong>ProductService</strong> class<br>
 * This class manages all logic of {@link Bucket} and {@link Product}
 * <br>
 * @author Karol Melak
 * @since 1.0
 */
@Service
@AllArgsConstructor
@NoArgsConstructor
public class ProductService implements Producer
{
    @Autowired
    private ProductRepo productRepo;

    private Bucket bucket;

    @Override
    public int showTheNumberOfBucketItems()
    {
        return bucket.getNumberOfItems();
    }

    @Override
    public BigDecimal showBucketAmount()
    {
        return bucket.getAmount();
    }

    @Override
    public List<BucketDto> showBucket()
    {
        return bucket.getPurchases();
    }

    @Override
    public void addToBucket(long id)
    {
        Product chosenProduct = productRepo.findById(id).orElse(new Product());
        bucket.addNewItem(chosenProduct);
    }

    @Override
    public void removeFromBucket(int index)
    {
        bucket.removeItem(index);
    }

    @Override
    public int addNewProduct(Product product)
    {
        if(product != null)
        {
            productRepo.save(product);
            return 1;
        }
        return 0;
    }

    @Override
    public int removeProduct(long id)
    {
        if(productRepo.existsById(id))
        {
            productRepo.deleteById(id);
            return 1;
        }
        return 0;
    }

    /**
     * This method generates the list of all products
     * stored in database and cuts the list to smaller
     * pieces of 5 elements. It helps to display all
     * data in more suitable format on web browser
     * @param number
     * @return Product sublist of 5 objects
     */
    @Override
    public List<ProductMenu> getOnePage(int number)
    {
       if(productRepo.count() == 0)
       {
           System.out.println("Empty repo!");
           return null;
       }

        List<ProductMenu> products = productRepo.getAllProductsForMenu();
        List<List<ProductMenu>> subPages = new ArrayList<>();

        for(int i = 0; i < products.size(); i += 5)
        {
            subPages.add(products.subList(i, Math.min(i + 5, products.size())));
        }
        return subPages.get(number);
    }

    @Override
    public Product getById(long id)
    {
        Product product = productRepo.findById(id).orElse(new Product());
        return product;
    }

    @Override
    public List<Product> getAll()
    {
        return productRepo.findAll();
    }
}
