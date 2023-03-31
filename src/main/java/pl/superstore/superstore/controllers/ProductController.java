package pl.superstore.superstore.controllers;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.superstore.superstore.models.Product;
import pl.superstore.superstore.services.ProductService;

import java.util.Optional;

@Controller
@RequestMapping("/product")
@NoArgsConstructor
public class ProductController
{
    @Autowired
    ProductService productService;

    @GetMapping("/test")
    public ResponseEntity<Optional<Product>> getProduct()
    {
        return new ResponseEntity<Optional<Product>>(productService.getById(1), HttpStatus.OK);
    }
}
