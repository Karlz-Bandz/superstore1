package pl.superstore.superstore.interfaces;

import pl.superstore.superstore.dto.BucketDto;
import pl.superstore.superstore.dto.ProductMenu;
import pl.superstore.superstore.models.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface Producer
{
     int addNewProduct(Product product);

     int removeProduct(long id);

     List<ProductMenu> getOnePage(int number);

     Product getById(long id);

     int getNumberOfProducts();

     List<Product> getAll();
}
