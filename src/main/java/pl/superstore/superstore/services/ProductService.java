package pl.superstore.superstore.services;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.superstore.superstore.models.Product;
import pl.superstore.superstore.repos.ProductRepo;

import java.util.Optional;

@Service
@NoArgsConstructor
public class ProductService
{

    @Autowired
    ProductRepo productRepo;


    public Optional<Product> getById(long id)
    {
        Optional<Product> product = productRepo.findById(id);
        return product ;
    }
}
