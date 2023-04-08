package pl.superstore.superstore.interfaces;

import pl.superstore.superstore.dto.BucketDto;

import java.math.BigDecimal;
import java.util.List;

public interface BasketManager
{
    int showTheNumberOfBasketItems();

    BigDecimal showBasketAmount();

    List<BucketDto> showBasket();

    void addToBasket(long id);

    void removeFromBasket(int index);
}
