package pl.superstore.superstore.controllers;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.superstore.superstore.dto.UserDto;
import pl.superstore.superstore.services.UserService;


@RestController
@RequestMapping("/user")
@AllArgsConstructor
@NoArgsConstructor
public class UserController
{
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public ResponseEntity<Void> createUser()
    {
        UserDto userDto = new UserDto();
        userDto.setName("Test");
        userDto.setLastName("LastTest");
        userDto.setMail("karol@kar.pl");
        userDto.setPassword("password");
        userDto.setPhoneNumber("");
        userService.addNewUser(userDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}