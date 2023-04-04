package pl.superstore.superstore.testservices;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.superstore.superstore.dto.UserDto;
import pl.superstore.superstore.models.Role;
import pl.superstore.superstore.repos.RoleRepo;
import pl.superstore.superstore.repos.UserRepo;
import pl.superstore.superstore.services.UserService;

import java.util.Arrays;
import java.util.List;


@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceTest
{
    @Autowired
    private UserRepo userRepoTest;

    @Autowired
    private RoleRepo roleRepoTest;

    @Autowired
    private PasswordEncoder passwordEncoderTest;

    @Test
    public void addNewUser_Test()
    {
        UserService userServiceTest = new UserService(userRepoTest, roleRepoTest, passwordEncoderTest);
        UserDto userDtoTest = new UserDto("Mateusz", "Last", "pass",
                "mail@mail.com", "");

        userServiceTest.addNewUser(userDtoTest);
    }
}
