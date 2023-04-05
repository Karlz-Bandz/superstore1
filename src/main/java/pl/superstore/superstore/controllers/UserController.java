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
        userDto.setMail("karole@kar.pl");
        userDto.setPassword("password");
        userDto.setPhoneNumber("");
        userService.addNewUser(userDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/register/a")
    public ResponseEntity<Void> createAdmin()
    {
        UserDto userDto = new UserDto();
        userDto.setName("KArol");
        userDto.setLastName("LastTest");
        userDto.setMail("kaarolee@kar.pl");
        userDto.setPassword("kaarol123");
        userDto.setPhoneNumber("505 829 237");
        userService.addNewAdmin(userDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
