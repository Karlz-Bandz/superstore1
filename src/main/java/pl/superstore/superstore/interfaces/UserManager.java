package pl.superstore.superstore.interfaces;

import pl.superstore.superstore.dto.UserDto;
import pl.superstore.superstore.models.Role;

import java.util.List;

public interface UserManager
{
    void addNewUser(UserDto userDto);

    boolean deleteUserBYId(long id);

    List<String> getAllUsersNames();

    void addRole(Role role);
}
