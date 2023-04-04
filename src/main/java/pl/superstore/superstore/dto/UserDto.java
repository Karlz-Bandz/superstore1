package pl.superstore.superstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto
{
    private String name;
    private String lastName;
    private String password;
    private String mail;
    private String phoneNumber;

    public UserDto(String name, String lastName, String password, String mail)
    {
        this.name = name;
        this.lastName = lastName;
        this.password = password;
        this.mail = mail;
    }
}
