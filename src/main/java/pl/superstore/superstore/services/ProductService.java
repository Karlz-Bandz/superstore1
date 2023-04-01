package pl.superstore.superstore.services;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.superstore.superstore.models.Category;
import pl.superstore.superstore.models.Product;
import pl.superstore.superstore.repos.ProductRepo;

import java.util.Optional;

@Service
@NoArgsConstructor
public class ProductService
{

    @Autowired
    ProductRepo productRepo;


    public String getById(long id)
    {
        StringBuilder builder = new StringBuilder();
        Optional<Product> product = productRepo.findById(id);
        if (product.isPresent())
        {
            Product p = product.get();
            Category cat = p.getCategory();
            builder.append(cat.getCategory());
        }

        return builder.toString();
    }
}
