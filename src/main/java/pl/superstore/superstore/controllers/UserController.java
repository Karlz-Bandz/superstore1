package pl.superstore.superstore.controllers;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
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
    private UserService userService;

    @GetMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id)
    {
        userService.deleteUserBYId(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/register")
    public ResponseEntity<Void> createUser()
    {
        for(int i = 0; i < 3; i++)
        {
            UserDto userDto = new UserDto();
            userDto.setName("Test");
            userDto.setLastName("LastTest"+i);
            userDto.setMail("karole"+i+"@kar.pl");
            userDto.setPassword("password");
            userDto.setPhoneNumber("");
            userService.addNewUser(userDto);
        }

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
