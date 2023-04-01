package pl.superstore.superstore.interfaces;

import pl.superstore.superstore.dto.ProductMenu;
import pl.superstore.superstore.models.Product;

import java.util.List;
import java.util.Optional;

public interface Producter
{

     int addNewProduct(Product product);

     List<ProductMenu> getOnePage(int number);

     Optional<Product> getById(long id);
}
