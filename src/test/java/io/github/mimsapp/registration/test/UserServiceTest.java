package io.github.mimsapp.registration.test;

import io.github.mimsapp.registration.app.AppInitializer;
import io.github.mimsapp.registration.app.domain.User;
import io.github.mimsapp.registration.app.exception.ValidationException;
import io.github.mimsapp.registration.app.service.UserService;
import io.github.mimsapp.registration.app.util.ErrorMessage;
import io.github.mimsapp.registration.test.util.UserFactory;
import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = "/application-test.properties")
@SpringBootTest(classes = AppInitializer.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test(expected = Test.None.class)
    public void simpleRegistration() {

        User user = UserFactory.getUser("087788423137", "Khamim",
                "Fathoni", "13-07-1991", "M",
                "mimsapp@gmail.com");
        String exceptionMessage = null;

        try {
            userService.save(user);
        } catch (ValidationException e) {
            exceptionMessage = e.getMessage();
        }

        assertNull(exceptionMessage);
    }

    @Test
    public void avoidDoubleMobileNumberRegistrations() {
        User user1 = UserFactory.getUser("085259756074", "Khamim",
                "Fathoni", "13-07-1991", "M",
                "mims@gmail.com");

        User user2 = UserFactory.getUser("085259756074", "Khamim",
                "Fathoni", "13-07-1991", "M",
                "mims2@gmail.com");
        String exceptionMessage = null;
        try {
            userService.save(user1);
            userService.save(user2);
        } catch (ValidationException e) {
            exceptionMessage = e.getMessage();
        }

        assertNotNull(exceptionMessage);
        assertEquals(exceptionMessage, ErrorMessage.MOBILE_NUMBER_EXISTS);
    }

    @Test
    public void avoidDoubleEmailRegistrations() {
        User user1 = UserFactory.getUser("085259756075", "Khamim",
                "Fathoni", "13-07-1991", "M",
                "khamim@gmail.com");

        User user2 = UserFactory.getUser("085259756076", "Khamim",
                "Fathoni", "13-07-1991", "M",
                "khamim@gmail.com");
        String exceptionMessage = null;
        try {
            userService.save(user1);
            userService.save(user2);
        } catch (ValidationException e) {
            exceptionMessage = e.getMessage();
        }

        assertNotNull(exceptionMessage);
        assertEquals(exceptionMessage, ErrorMessage.EMAIL_EXISTS);
    }

    @Test
    public void avoidInvalidEmailRegistration() {
        User user = UserFactory.getUser("085259756077", "Khamim",
                "Fathoni", "13-07-1991", "M",
                "khamim#gmailcom");

        String exceptionMessage = null;

        try {
            userService.save(user);
        } catch (ValidationException e) {
            exceptionMessage = e.getMessage();
        }

        assertEquals(exceptionMessage, ErrorMessage.EMAIL_INVALID);
    }

    @Test
    public void avoidInvalidMobileNumberRegistration() {
        User user = UserFactory.getUser("015259756077", "Khamim",
                "Fathoni", "13-07-1991", "M",
                "khamim1@gmailcom");

        String exceptionMessage = null;

        try {
            userService.save(user);
        } catch (ValidationException e) {
            exceptionMessage = e.getMessage();
        }

        assertEquals(exceptionMessage, ErrorMessage.MOBILE_NUMBER_INVALID);
    }

}
