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
import pl.superstore.superstore.services.UserService;

import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceTest
{
    @Autowired
    private PasswordEncoder passwordEncoderTest;

    @Autowired
    private UserService userServiceTest;

    @BeforeAll
    void setup()
    {
        Role roleUserTest = new Role("USER");
        Role roleAdminTest = new Role("ADMIN");
        userServiceTest.addRole(roleUserTest);
        userServiceTest.addRole(roleAdminTest);
    }

    @Test
    public void getAllUsersNames_Test()
    {
        UserDto userDtoTest = new UserDto("Mateusz", "Last", "pass",
                "mail@mail.com", "");
        UserDto userDtoTest2 = new UserDto("Pazdan", "Last", "pass",
                "mail@mail.com", "");
        UserDto userDtoTest3 = new UserDto("PApryk", "Last", "pass",
                "mail@mail.com", "");

        userServiceTest.addNewUser(userDtoTest);
        userServiceTest.addNewUser(userDtoTest2);
        userServiceTest.addNewUser(userDtoTest3);
        List<String> testNames = userServiceTest.getAllUsersNames();

        for (String x: testNames)
        {
            System.out.println(x);
        }
    }

    @Test
    public void addNewUser_Test()
    {
        UserDto userDtoTest = new UserDto("Mateusz", "Last", "pass",
                "mail@mail.com", "");
        UserDto userDtoTest2 = new UserDto("Mateusz", "Last", "pass",
                "mail@mail.com", "");

        userServiceTest.addNewUser(userDtoTest);
        userServiceTest.addNewUser(userDtoTest2);
    }
}