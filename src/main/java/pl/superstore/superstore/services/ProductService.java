package pl.superstore.superstore.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import pl.superstore.superstore.dto.ProductMenu;
import pl.superstore.superstore.interfaces.Producter;
import pl.superstore.superstore.models.Product;
import pl.superstore.superstore.repos.ProductRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ProductService implements Producter
{

    private ProductRepo productRepo;

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
        List<ProductMenu> products = productRepo.getAllProductsForMenu();
        List<List<ProductMenu>> subPages = new ArrayList<>();

        for(int i = 0; i < products.size(); i += 5)
        {
            subPages.add(products.subList(i, Math.min(i + 5, products.size())));
        }
        return subPages.get(number);
    }

    @Override
    public Optional<Product> getById(long id)
    {
        return productRepo.findById(id);
    }
}
