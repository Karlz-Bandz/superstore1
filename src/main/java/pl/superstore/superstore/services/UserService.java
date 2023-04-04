package pl.superstore.superstore.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.superstore.superstore.dto.UserDto;
import pl.superstore.superstore.models.Role;
import pl.superstore.superstore.models.User;
import pl.superstore.superstore.repos.RoleRepo;
import pl.superstore.superstore.repos.UserRepo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
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
        Role role = roleRepo.findByRole("USER").orElseThrow(() -> new RuntimeException("Role not found"));
        User user = new User();
        user.setName(userDto.getName());
        user.setLastName(userDto.getLastName());
        user.setMail(userDto.getMail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(Collections.singletonList(role));

        userRepo.save(user);
    }

   public List<String> getAllUsersNames()
   {
       return userRepo.getAllUsersNames();
   }

   public void addRole(Role role)
   {
       roleRepo.save(role);
   }
}
