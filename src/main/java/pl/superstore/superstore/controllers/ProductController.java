package pl.superstore.superstore.controllers;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.superstore.superstore.dto.BucketDto;
import pl.superstore.superstore.dto.ID_Dto;
import pl.superstore.superstore.dto.ProductDto;
import pl.superstore.superstore.dto.ProductMenu;
import pl.superstore.superstore.models.Product;
import pl.superstore.superstore.services.BasketService;
import pl.superstore.superstore.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
@NoArgsConstructor
@AllArgsConstructor
public class ProductController
{

    private ProductService productService;

    private BasketService basketService;

    @PostMapping("/add")
    public ResponseEntity<Void> addProduct(@RequestBody ProductDto productDto)
    {
        Product product = Product.builder()
                .name(productDto.getName())
                .price(productDto.getPrice())
                .description(productDto.getDescription())
                .imageUrl(productDto.getImageUrl())
                .category(productDto.getCategory())
                .build();

        productService.addNewProduct(product);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/sum")
    public ResponseEntity<Integer> getSum()
    {
        return new ResponseEntity<>(productService.getNumberOfProducts(), HttpStatus.OK);
    }

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
