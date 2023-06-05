package pl.superstore.superstore.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.superstore.superstore.services.BasketService;

@RestController
@RequestMapping("/basket")
@AllArgsConstructor
public class BasketController
{

    BasketService basketService;

    @GetMapping("/number")
    public ResponseEntity<Integer> getNumberOfItems()
    {
        return new ResponseEntity<>(basketService.showTheNumberOfBasketItems(), HttpStatus.OK);
    }
}
