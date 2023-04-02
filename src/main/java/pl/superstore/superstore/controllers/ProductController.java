package pl.superstore.superstore.controllers;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.superstore.superstore.dto.ProductMenu;
import pl.superstore.superstore.models.Product;
import pl.superstore.superstore.services.ProductService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/product")
@NoArgsConstructor
public class ProductController
{
    @Autowired
    private ProductService productService;

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
}
