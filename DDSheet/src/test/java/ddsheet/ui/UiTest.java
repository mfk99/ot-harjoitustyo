package ddsheet.ui;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UiTest {
    
    public UiTest() {
    }
    
    Ui ui;
    
    @Before
    public void setUp() {
        ui=new Ui();
    }

    @Test
    public void userCreationAllowed() {
        String returnValue= ui.attemptCreateUser("test", "test");
        assertEquals("Account successfully created!", returnValue);
    }
    
    @Test
    public void userCreationNotAllowedWithIneligiblePassword() {
        String returnValue= ui.attemptCreateUser("test", "te");
        assertEquals("Password must be at least 3 characters long", returnValue);
    }
    
    @Test
    public void userCreationNotAllowedWithIneligibleUsername() {
        String returnValue= ui.attemptCreateUser("te", "test");
        assertEquals("Username must be at least 3 characters long", returnValue);
    }
    
    @Test
    public void userCreationNotAllowedWithExistingUsername() {
        ui.attemptCreateUser("test", "test");
        String returnValue= ui.attemptCreateUser("test", "test");
        assertEquals("Username already taken", returnValue);
    }
    
    @Test
    public void loginAllowedWithCorrectCredentials() {
        ui.attemptCreateUser("test", "test");
        String returnValue= ui.attemptLogIn("test", "test");
        assertEquals("Login successful!", returnValue);
    }
    
    @Test
    public void loginNotAllowedWithNonexistentUsername() {
        String returnValue= ui.attemptLogIn("test", "");
        assertEquals("There is no account with that username", returnValue);
    }
    
    @Test
    public void loginNotAllowedWithIncorrectPassword() {
        ui.attemptCreateUser("test", "test");
        String returnValue= ui.attemptLogIn("test", "");
        assertEquals("Incorrect password", returnValue);
    }
}