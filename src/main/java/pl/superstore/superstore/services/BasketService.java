package pl.superstore.superstore.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.superstore.superstore.dto.BucketDto;
import pl.superstore.superstore.interfaces.BasketManager;
import pl.superstore.superstore.models.Basket;
import pl.superstore.superstore.models.Product;
import pl.superstore.superstore.repos.ProductRepo;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class BasketService implements BasketManager
{
    @Autowired
    protected ProductRepo productRepo;

    protected Basket basket = new Basket();

    public BasketService(ProductRepo productRepo)
    {
        this.productRepo = productRepo;
    }

    public int showTheNumberOfBasketItems()
    {
        return basket.getNumberOfItems();
    }

    @Override
    public BigDecimal showBasketAmount()
    {
        return basket.getAmount();
    }

    @Override
    public List<BucketDto> showBasket()
    {
        return basket.getPurchases();
    }

    @Override
    public void addToBasket(long id)
    {
        Product chosenProduct = productRepo.findById(id).orElse(new Product());
        basket.addNewItem(chosenProduct);
    }

    @Override
    public void removeFromBasket(int index)
    {
        basket.removeItem(index);
    }
}
