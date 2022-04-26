
package ddsheet.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DDSheetServiceTest {
    
    public DDSheetServiceTest() {
    }
    
    DDSheetService ddsheetService;
    
    @Before
    public void setUp() {
        ddsheetService=new DDSheetService();
    }

    @Test
    public void userCreationAllowed() {
        String returnValue= ddsheetService.attemptCreateUser("test", "test");
        assertEquals("Account successfully created!", returnValue);
    }
    
    @Test
    public void userCreationNotAllowedWithIneligiblePassword() {
        String returnValue= ddsheetService.attemptCreateUser("test", "te");
        assertEquals("Password must be at least 3 characters long", returnValue);
    }
    
    @Test
    public void userCreationNotAllowedWithIneligibleUsername() {
        String returnValue= ddsheetService.attemptCreateUser("te", "test");
        assertEquals("Username must be at least 3 characters long", returnValue);
    }
    
    @Test
    public void userCreationNotAllowedWithExistingUsername() {
        ddsheetService.attemptCreateUser("test", "test");
        String returnValue= ddsheetService.attemptCreateUser("test", "test");
        assertEquals("Username already taken", returnValue);
    }
    
    @Test
    public void loginAllowedWithCorrectCredentials() {
        ddsheetService.attemptCreateUser("test", "test");
        String returnValue= ddsheetService.attemptLogIn("test", "test");
        assertEquals("Login successful!", returnValue);
    }
    
    @Test
    public void loginNotAllowedWithNonexistentUsername() {
        String returnValue= ddsheetService.attemptLogIn("test", "");
        assertEquals("There is no account with that username", returnValue);
    }
    
    @Test
    public void loginNotAllowedWithIncorrectPassword() {
        ddsheetService.attemptCreateUser("test", "test");
        String returnValue= ddsheetService.attemptLogIn("test", "");
        assertEquals("Incorrect password", returnValue);
    }
    
    @Test
    public void loginSetsLoggedUserCorrectly() {
        ddsheetService.attemptCreateUser("test", "test");
        ddsheetService.attemptLogIn("test", "test");
        String returnValue=ddsheetService.getUser().getUsername();
        assertEquals("test", returnValue);
    }
    
    @Test
    public void loginOutSetsUserToNull() {
        ddsheetService.attemptCreateUser("test", "test");
        ddsheetService.attemptLogIn("test", "test");
        ddsheetService.logOut();
        assertEquals(null, ddsheetService.getUser());
    }
    
    @Test
    public void characterCreationNotAllowedWithNoCharacters() {
        ddsheetService.attemptCreateUser("test", "test");
        ddsheetService.attemptLogIn("test", "test");
        String returnValue=ddsheetService.addCharacter("");
        assertEquals("Name must be at least one character long", returnValue);
    }
    
    @Test
    public void characterCreationAllowed() {
        ddsheetService.attemptCreateUser("test", "test");
        ddsheetService.attemptLogIn("test", "test");
        String returnValue=ddsheetService.addCharacter("test");
        assertEquals("Character creation successful!", returnValue);
    }
    
    @Test
    public void characterCreationAddsToUsersArrayList() {
        ddsheetService.attemptCreateUser("test", "test");
        ddsheetService.attemptLogIn("test", "test");
        ddsheetService.addCharacter("test");
        int returnValue=ddsheetService.getUser().getCharacters().size();
        assertEquals(1, returnValue);
    }
    
    @Test
    public void attemptIntValueChangeDoesntAcceptNegatives() {
        Character test=new Character("");
        ddsheetService.setCharacter(test);
        boolean returnValue=ddsheetService.attemptIntValueChange(0, "-1");
        assertEquals(false, returnValue);
    }
    
    @Test
    public void attemptIntValueChangeAcceptsCorrectValue() {
        Character test=new Character("");
        ddsheetService.setCharacter(test);
        boolean returnValue=ddsheetService.attemptIntValueChange(0, "20");
        assertEquals(true, returnValue);
    }
    
    @Test
    public void attemptStringValueChangeDoesntAcceptNullName() {
        Character test=new Character("");
        ddsheetService.setCharacter(test);
        boolean returnValue=ddsheetService.attemptStringValueChange(0, "");
        assertEquals(false, returnValue);
    }
    
    @Test
    public void attemptStringValueChangeAcceptsCorrectValue() {
        Character test=new Character("");
        ddsheetService.setCharacter(test);
        boolean returnValue=ddsheetService.attemptStringValueChange(0, "name");
        assertEquals(true, returnValue);
    }
    
    @Test
    public void getCharacterReturnsCurrentCharacter() {
        Character test=new Character("");
        ddsheetService.setCharacter(test);
        Character returnValue = ddsheetService.getCharacter();
        assertEquals(test, returnValue);
    }
    
    @Test
    public void clearCharacterSetsCurrentCharacterToNull() {
        Character test=new Character("");
        ddsheetService.setCharacter(test);
        ddsheetService.clearCharacter();
        Character returnValue = ddsheetService.getCharacter();
        assertEquals(null, returnValue);
    }
}