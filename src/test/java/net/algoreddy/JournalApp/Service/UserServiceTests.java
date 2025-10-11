package net.algoreddy.JournalApp.Service;

import net.algoreddy.JournalApp.entity.User;
import net.algoreddy.JournalApp.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    // you can actually diable a test by writing the @Disable annotation
    // instead of using the csvsource as we are not inputting the csv better to use other value like value source
    // we can pass the differnt types of arguments by declaring wheather they are the strings , int or any data type value
    // you can create the custom notes using the annotation @ArugumentsSource() --> custom made
    @ParameterizedTest
    @ArgumentsSource(UserArugmentsProvider.class)
    public void testSaveNewUser(User user) {
        assertTrue(userService.saveNewUser(user));
    }


    @ParameterizedTest
    @CsvSource({"1 ,1 , 2", "2 ,10, 12 ", "3 ,3 , 9"})
    public void test(int a, int b, int expect) {
        assertEquals(expect, a + b);
    }
   //@beforeall --> this annotation helps in executing the test before all the tests

   //@BeforeEach ==> this anno --> makes sures it's runs before the  (each )each test

    //@AfterEach --> after completing the each test

    //@AfterALL --> after all the test are complete then  it will run

}
