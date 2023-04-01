package pl.superstore.superstore.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.superstore.superstore.models.Category;
import pl.superstore.superstore.models.Product;
import pl.superstore.superstore.repos.ProductRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService
{
    ProductRepo productRepo;

    /**
     * This method generates the list of all products
     * stored in database and cuts the list to smaller
     * pieces of 5 elements. It helps to display all
     * data in more suitable format on web browser
     * @param number
     * @return Product sublist of 5 objects
     */
    public List<Product> getOnePage(int number)
    {
        List<Product> products = productRepo.findAll();
        List<List<Product>> subPages = new ArrayList<>();

        for(int i = 0; i < products.size(); i += 5)
        {
            subPages.add(products.subList(i, Math.min(i + 5, products.size())));
        }
        return subPages.get(number);
    }

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
