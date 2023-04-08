package pl.superstore.superstore.controllers;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.superstore.superstore.dto.BucketDto;
import pl.superstore.superstore.dto.ID_Dto;
import pl.superstore.superstore.dto.ProductMenu;
import pl.superstore.superstore.models.Product;
import pl.superstore.superstore.services.BasketService;
import pl.superstore.superstore.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
@NoArgsConstructor
public class ProductController
{
    @Autowired
    private ProductService productService;

    @Autowired
    private BasketService basketService;

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAll()
    {
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/test/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable long id)
    {
        return new ResponseEntity<>(productService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/page/{number}")
    public ResponseEntity<List<ProductMenu>> getPage(@PathVariable int number)
    {
        return new ResponseEntity<>(productService.getOnePage(number), HttpStatus.OK);
    }

    @PostMapping("/basket/add")
    public ResponseEntity<Void> addToBucket(@RequestBody ID_Dto id_Dto)
    {
        basketService.addToBasket(id_Dto.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/basket/show")
    public ResponseEntity<List<BucketDto>> showBucket()
    {
        return new ResponseEntity<>(basketService.showBasket(),HttpStatus.OK);
    }
}
