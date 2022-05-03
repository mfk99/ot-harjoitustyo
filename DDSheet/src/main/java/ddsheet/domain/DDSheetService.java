
package ddsheet.domain;

import ddsheet.dao.CharacterDao;
import ddsheet.dao.SqlCharacterDao;
import ddsheet.dao.SqlUserDao;
import ddsheet.dao.UserDao;
import java.util.HashMap;
import java.util.List;

/**
 * Separates logical functions from the UI-class
 * @author matti
 */
public class DDSheetService {

    final private HashMap<String, User> users = new HashMap();
    private User loggedUser;
    private Character currentCharacter;
    private UserDao userDao;
    private CharacterDao characterDao;

    public DDSheetService() {
//        userDao=new SqlUserDao();
//        characterDao=new SqlCharacterDao();
//        List<User> usersList=userDao.getAll();
//        for (User user: usersList) {
//            users.put(user.getUsername(), user);
//        }
    }

    /** 
     * Attempts to create a new user
     * 
     * @param username The given username for the user
     * @param password The given password for the user
     * 
     * @return A string represantation of the outcome
     */
    public String createUser(String username, String password) {
        if (existingUsername(username)) {
            return "Username already taken";
        }
        if (!usernameLongEnough(username)) {
            return "Username must be at least 3 characters long";
        }
        if (!passwordLongEnough(password)) {
            return "Password must be at least 3 characters long";
        }
        User newUser = new User(username, password);
        users.put(username, newUser);
//        userDao.create(newUser);
        return "Account successfully created!";
    }

    /**
     * Attempts to log in with the given credentials
     * 
     * @param username The given username for the user
     * @param password The given password for the user
     * 
     * @return A string represantation of the outcome
     */
    public String logIn(String username, String password) {
        if (!existingUsername(username)) {
            return "There is no account with that username";
        }
        if (!correctPassword(username, password)) {
            return "Incorrect password";
        }
        loggedUser = users.get(username);
        return ("Login successful!");
    }

    /**
     * Attempts to change a string value for a character
     * 
     * @param index Specifies which index will be modified
     * @param value The value which will be set to the index
     * @return The success of the change
     */
    public boolean changeStringValue(int index, String value) {
        if (value.length() == 0) {
            return false;
        }
        currentCharacter.setStringValues(index, value);
        return true;
    }
    
    /**
     * Attempts to change an int value for a character
     * 
     * @param index Specifies which index will be modified
     * @param value The value which will be set to the index
     * @return The success of the change
     */
    public boolean changeIntValue(int index, String value) {
        int intValue;
        try {
            intValue = Integer.valueOf(value);
        } catch (Exception e) {
            return false;
        }
        if (intValue < 0 || intValue > 20) {
            return false;
        }
        currentCharacter.setIntValues(index, intValue);
        return true;
    }
    
    public void logOut() {
        loggedUser = null;
    }

    /**
     * Checks whether a user already has the username
     * 
     * @param username The username
     * 
     * @return The resulting truth value
     */
    public boolean existingUsername(String username) {
        return users.containsKey(username);
    }

    /**
     * Checks whether the password matches the correct one
     * 
     * @param username The username
     * @param password The passwword
     * 
     * @return The resulting truth value
     */
    public boolean correctPassword(String username, String password) {
        User user = users.get(username);
        return (password.equals(user.getPassword()));
    }

    /**
     * Checks whether the username is long enough
     * 
     * @param username The given username
     * 
     * @return The resulting truth value
     */
    public boolean usernameLongEnough(String username) {
        return (3 <= username.length());
    }

    /**
     * Checks whether the password is long enough
     * 
     * @param password The given username
     * 
     * @return The resulting truth value
     */
    public boolean passwordLongEnough(String password) {
        return (3 <= password.length());
    }

    /**
     * Attempts to create a new character
     * 
     * @param name The given name
     * 
     * @return A string represantation of the result
     */
    public String addCharacter(String name) {
        if (characterNameLongEnough(name)) {
            loggedUser.getCharacters().add(new Character(name));
            return "Character creation successful!";
        }
        return "Name must be at least one character long";
    }

    /**
     * Checks whether the character's name is long enough
     * 
     * @param name The given username
     * 
     * @return The resulting truth value
     */
    public boolean characterNameLongEnough(String name) {
        return 0 < name.length();
    }

    public User getUser() {
        return loggedUser;
    }

    public void setCharacter(Character character) {
        currentCharacter = character;
    }
    
    /**
     * Sets the current character to null
     */
    public void clearCharacter() {
        currentCharacter = null;
    }
    
    public Character getCharacter() {
        return currentCharacter;
    }
}
