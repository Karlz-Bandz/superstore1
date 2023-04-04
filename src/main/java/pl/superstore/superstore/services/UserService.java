package pl.superstore.superstore.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.superstore.superstore.dto.UserDto;
import pl.superstore.superstore.models.Role;
import pl.superstore.superstore.models.User;
import pl.superstore.superstore.repos.RoleRepo;
import pl.superstore.superstore.repos.UserRepo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService
{
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void addNewUser(UserDto userDto)
    {
        User user = new User();
        user.setName(userDto.getName());
        user.setLastName(userDto.getLastName());
        user.setMail(userDto.getMail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role role = roleRepo.findByRole("USER").get();
        user.setRoles(Collections.singletonList(role));

        userRepo.save(user);
    }
}
