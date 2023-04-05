package pl.superstore.superstore.interfaces;

import pl.superstore.superstore.dto.UserDto;
import pl.superstore.superstore.models.Role;

import java.util.List;

public interface UserManager
{
    boolean addNewUser(UserDto userDto);

    boolean addNewAdmin(UserDto userDto);

    boolean deleteUserBYId(long id);

    boolean changeThePassword(String mail, String newPass);

    List<String> getAllUsersNames();

    void addRole(Role role);
}
