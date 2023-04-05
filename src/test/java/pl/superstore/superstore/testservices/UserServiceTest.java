package pl.superstore.superstore.testservices;

import org.junit.jupiter.api.Assertions;
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
import pl.superstore.superstore.models.User;
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
    public void changeThePassword_Test()
    {
        UserDto userDtoTest = new UserDto("Mateusz", "Last", "pass",
                "mail@mail.com", "");
        UserDto userDtoTest2 = new UserDto("Mateusz", "Last", "pass",
                "mail2@mail.com", "");

        userServiceTest.addNewUser(userDtoTest);
        userServiceTest.addNewUser(userDtoTest2);

        boolean changePassTest1 = userServiceTest.changeThePassword("mail@mail.com", "pass");
        boolean changePassTest2 = userServiceTest.changeThePassword("mail@mail.com", "pass2");
        List<User> usersTest = userServiceTest.getAllUsers();
        String changedPass = usersTest.get(0).getPassword();

        boolean expectedPassTest1 = false;
        boolean expectedPassTest2 = true;
        int expectedLengthTest = 2;
        String expectedChangedPass = "pass2";

        Assertions.assertEquals(changePassTest1, expectedPassTest1);
        Assertions.assertEquals(changePassTest2, expectedPassTest2);
        Assertions.assertEquals(usersTest.size(), expectedLengthTest);
        Assertions.assertTrue(passwordEncoderTest.matches(expectedChangedPass, changedPass));

        userServiceTest.deleteAll();
    }

    @Test
    public void deleteUserById_Test()
    {
        UserDto userDtoTest = new UserDto("Mateusz", "Last", "pass",
                "mail@mail.com", "");
        UserDto userDtoTest2 = new UserDto("Pazdan", "Last", "pass",
                "mail2@mail.com", "");
        UserDto userDtoTest3 = new UserDto("PApryk", "Last", "pass",
                "mail3@mail.com", "");

        userServiceTest.addNewUser(userDtoTest);
        userServiceTest.addNewUser(userDtoTest2);
        userServiceTest.addNewUser(userDtoTest3);

        List<User> users = userServiceTest.getAllUsers();

        boolean testSolution1 = userServiceTest.deleteUserBYId(50);
        boolean testSolution2 = userServiceTest.deleteUserBYId(users.get(1).getId());
        boolean testSolution3 = userServiceTest.deleteUserBYId(users.get(1).getId());

        int expectedSize = 2;
        int solutionSizeTest = userServiceTest.getAllUsersNames().size();

        Assertions.assertEquals(testSolution1, false);
        Assertions.assertEquals(testSolution2, true);
        Assertions.assertEquals(testSolution3, false);
        Assertions.assertEquals(solutionSizeTest, expectedSize);

        userServiceTest.deleteAll();
    }

    @Test
    public void getAllUsersNames_Test()
    {
        UserDto userDtoTest = new UserDto("Mateusz", "Last", "pass",
                "mail@mail.com", "");
        UserDto userDtoTest2 = new UserDto("Pazdan", "Last", "pass",
                "mail2@mail.com", "");
        UserDto userDtoTest3 = new UserDto("PApryk", "Last", "pass",
                "mail3@mail.com", "");

        userServiceTest.addNewUser(userDtoTest);
        userServiceTest.addNewUser(userDtoTest2);
        userServiceTest.addNewUser(userDtoTest3);
        List<String> testNames = userServiceTest.getAllUsersNames();

        Assertions.assertEquals(testNames.size(), 3);

        userServiceTest.deleteAll();
    }

    @Test
    public void addNewUser_Test()
    {
        UserDto userDtoTest = new UserDto("Mateusz", "Last", "pass",
                "mail@mail.com", "");
        UserDto userDtoTest2 = new UserDto("Marek", "Last", "pass",
                "mail@mail.com", "");
        UserDto userDtoTest3 = new UserDto("Marek", "Last", "pass",
                "maildiffer@mail.com", "");

        boolean checkTest1 = userServiceTest.addNewUser(userDtoTest);
        boolean checkTest2 = userServiceTest.addNewUser(userDtoTest2);
        boolean checkTest3 = userServiceTest.addNewUser(userDtoTest3);

        List<User> users = userServiceTest.getAllUsers();

        Assertions.assertEquals(users.get(0).getName(), "Mateusz");
        Assertions.assertEquals(users.get(1).getName(), "Marek");
        Assertions.assertEquals(users.size(), 2);
        Assertions.assertEquals(checkTest1, true);
        Assertions.assertEquals(checkTest2, false);
        Assertions.assertEquals(checkTest3, true);

        userServiceTest.deleteAll();
    }

    @Test
    public void addNewAdmin_Test()
    {
        UserDto userDtoTest = new UserDto("Mateusz", "Last", "pass",
                "mail@mail.com", "");
        UserDto userDtoTest2 = new UserDto("Marek", "Last", "pass",
                "mail@mail.com", "");
        UserDto userDtoTest3 = new UserDto("Marek", "Last", "pass",
                "maildiffer@mail.com", "");

        boolean checkTest1 = userServiceTest.addNewAdmin(userDtoTest);
        boolean checkTest2 = userServiceTest.addNewAdmin(userDtoTest2);
        boolean checkTest3 = userServiceTest.addNewAdmin(userDtoTest3);

        List<User> users = userServiceTest.getAllUsers();

        Assertions.assertEquals(users.get(0).getName(), "Mateusz");
        Assertions.assertEquals(users.get(1).getName(), "Marek");
        Assertions.assertEquals(users.size(), 2);
        Assertions.assertEquals(checkTest1, true);
        Assertions.assertEquals(checkTest2, false);
        Assertions.assertEquals(checkTest3, true);

        userServiceTest.deleteAll();
    }
}
