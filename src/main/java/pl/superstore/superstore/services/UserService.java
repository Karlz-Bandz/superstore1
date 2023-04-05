package pl.superstore.superstore.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.superstore.superstore.dto.UserDto;
import pl.superstore.superstore.interfaces.UserManager;
import pl.superstore.superstore.models.Role;
import pl.superstore.superstore.models.User;
import pl.superstore.superstore.repos.RoleRepo;
import pl.superstore.superstore.repos.UserRepo;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserService implements UserManager
{
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean addNewUser(UserDto userDto)
    {
        User foundUser = userRepo.findByMail(userDto.getMail()).orElse(new User());

        if(foundUser.getMail() != null)
        {
            System.out.println("Mail already exists!");
            return false;
        }else{

            Role role = roleRepo.findByRole("USER").orElseThrow(() -> new RuntimeException("Role not found"));
            User user = new User();
            user.setName(userDto.getName());
            user.setLastName(userDto.getLastName());
            user.setMail(userDto.getMail());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setRoles(Collections.singletonList(role));

        userRepo.save(user);

        return true;
        }
    }

    @Override
    public boolean deleteUserBYId(long id)
    {
        if(userRepo.existsById(id))
        {
            userRepo.deleteById(id);
            return true;
        }
        else
        return false;
    }

    @Override
    public boolean changeThePassword(String mail, String newPass)
    {
        User user = userRepo.findByMail(mail).orElseThrow(() -> new RuntimeException("Mail not exists!"));
        boolean checkCurrentPass = passwordEncoder.matches(newPass, user.getPassword());

        if(checkCurrentPass)
        {
            System.out.println("Provided the current password!");
            return false;
        }else
        {
            user.setPassword(passwordEncoder.encode(newPass));
            userRepo.save(user);
            return true;
        }
    }

    @Override
    public List<String> getAllUsersNames()
    {
       return userRepo.getAllUsersNames();
    }

    @Override
    public void addRole(Role role)
    {
       roleRepo.save(role);
    }

    /**
     * Only for testing purposes
     */
    public void deleteAll()
    {
        userRepo.deleteAll();
    }
    public List<User> getAllUsers()
    {
        return userRepo.findAll();
    }
}
