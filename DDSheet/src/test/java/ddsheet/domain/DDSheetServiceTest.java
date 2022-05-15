
package ddsheet.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DDSheetServiceTest {
    
    public DDSheetServiceTest() {
    }
    
    User testUser;
    
    DDSheetService ddsheetService;
    
    @Before
    public void setUp() throws Exception {
        FakeUserDao userDao = new FakeUserDao();
        FakeCharacterDao characterDao= new FakeCharacterDao();
        ddsheetService=new DDSheetService(userDao, characterDao);
    }

    @Test
    public void userCreationAllowed() {
        String returnValue= ddsheetService.createUser("test", "test");
        assertEquals("Account successfully created!", returnValue);
    }
    
    @Test
    public void userCreationNotAllowedWithIneligiblePassword() {
        String returnValue= ddsheetService.createUser("test", "te");
        assertEquals("Password must be at least 3 characters long", returnValue);
    }
    
    @Test
    public void userCreationNotAllowedWithIneligibleUsername() {
        String returnValue= ddsheetService.createUser("te", "test");
        assertEquals("Username must be at least 3 characters long", returnValue);
    }
    
    @Test
    public void userCreationNotAllowedWithExistingUsername() {
        ddsheetService.createUser("test", "test");
        String returnValue= ddsheetService.createUser("test", "test");
        assertEquals("Username already taken", returnValue);
    }
    
    @Test
    public void loginAllowedWithCorrectCredentials() {
        ddsheetService.createUser("test", "test");
        String returnValue= ddsheetService.logIn("test", "test");
        assertEquals("Login successful!", returnValue);
    }
    
    @Test
    public void loginNotAllowedWithNonexistentUsername() {
        String returnValue= ddsheetService.logIn("test", "");
        assertEquals("There is no account with that username", returnValue);
    }
    
    @Test
    public void loginNotAllowedWithIncorrectPassword() {
        ddsheetService.createUser("test", "test");
        String returnValue= ddsheetService.logIn("test", "");
        assertEquals("Incorrect password", returnValue);
    }
    
    @Test
    public void loginSetsLoggedUserCorrectly() {
        ddsheetService.createUser("test", "test");
        ddsheetService.logIn("test", "test");
        String returnValue=ddsheetService.getUser().getUsername();
        assertEquals("test", returnValue);
    }
    
    @Test
    public void loginOutSetsUserToNull() {
        ddsheetService.createUser("test", "test");
        ddsheetService.logIn("test", "test");
        ddsheetService.logOut();
        assertEquals(null, ddsheetService.getUser());
    }
    
    @Test
    public void characterCreationNotAllowedWithNoCharacters() {
        ddsheetService.createUser("test", "test");
        ddsheetService.logIn("test", "test");
        String returnValue=ddsheetService.addCharacter("");
        assertEquals("Name must be at least one character long", returnValue);
    }
    
    @Test
    public void characterCreationAllowed() {
        ddsheetService.createUser("test", "test");
        ddsheetService.logIn("test", "test");
        String returnValue=ddsheetService.addCharacter("test");
        assertEquals("Character creation successful!", returnValue);
    }
    
    @Test
    public void characterCreationAddsToUsersArrayList() {
        ddsheetService.createUser("test", "test");
        ddsheetService.logIn("test", "test");
        ddsheetService.addCharacter("test");
        int returnValue=ddsheetService.getUser().getCharacters().size();
        assertEquals(1, returnValue);
    }
    
    @Test
    public void attemptIntValueChangeDoesntAcceptNegatives() {
        Character test=new Character("", testUser);
        ddsheetService.setCharacter(test);
        boolean returnValue=ddsheetService.changeIntValue(0, "-1");
        assertEquals(false, returnValue);
    }
    
    @Test
    public void attemptIntValueChangeAcceptsCorrectValue() {
        Character test=new Character("", testUser);
        ddsheetService.setCharacter(test);
        boolean returnValue=ddsheetService.changeIntValue(0, "20");
        assertEquals(true, returnValue);
    }
    
    @Test
    public void attemptStringValueChangeDoesntAcceptNullName() {
        Character test=new Character("", testUser);
        ddsheetService.setCharacter(test);
        boolean returnValue=ddsheetService.changeStringValue(0, "");
        assertEquals(false, returnValue);
    }
    
    @Test
    public void attemptStringValueChangeAcceptsCorrectValue() {
        Character test=new Character("", testUser);
        ddsheetService.setCharacter(test);
        boolean returnValue=ddsheetService.changeStringValue(0, "name");
        assertEquals(true, returnValue);
    }
    
    @Test
    public void getCharacterReturnsCurrentCharacter() {
        Character test=new Character("", testUser);
        ddsheetService.setCharacter(test);
        Character returnValue = ddsheetService.getCharacter();
        assertEquals(test, returnValue);
    }
    
    @Test
    public void clearCharacterSetsCurrentCharacterToNull() {
        Character test=new Character("", testUser);
        ddsheetService.setCharacter(test);
        ddsheetService.clearCharacter();
        Character returnValue = ddsheetService.getCharacter();
        assertEquals(null, returnValue);
    }
}