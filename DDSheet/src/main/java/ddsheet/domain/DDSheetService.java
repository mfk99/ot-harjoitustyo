
package ddsheet.domain;

import ddsheet.dao.CharacterDao;
import ddsheet.dao.SqlCharacterDao;
import ddsheet.dao.SqlUserDao;
import ddsheet.dao.UserDao;
import java.util.HashMap;
import java.util.List;

public class DDSheetService {

    final private HashMap<String, User> users = new HashMap();
    private User loggedUser;
    private Character currentCharacter;
    private UserDao userDao;
    private CharacterDao characterDao;

    public DDSheetService() {
        userDao=new SqlUserDao();
        characterDao=new SqlCharacterDao();
        List<User> usersList=userDao.getAll();
        for (User user: usersList) {
            users.put(user.getUsername(), user);
        }
    }

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
        User newUser=new User(username, password);
        users.put(username, newUser);
        userDao.create(newUser);
        return "Account successfully created!";
    }

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

    public boolean changeStringValue(int index, String value) {
        if (value.length() == 0) {
            return false;
        }
        currentCharacter.setStringValues(index, value);
        return true;
    }
    
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

    public boolean existingUsername(String username) {
        return users.containsKey(username);
    }

    public boolean correctPassword(String username, String password) {
        User user = users.get(username);
        return (password.equals(user.getPassword()));
    }

    public boolean usernameLongEnough(String username) {
        return (3 <= username.length());
    }

    public boolean passwordLongEnough(String password) {
        return (3 <= password.length());
    }

    public String addCharacter(String name) {
        if (characterNameLongEnough(name)) {
            loggedUser.getCharacters().add(new Character(name));
            return "Character creation successful!";
        }
        return "Name must be at least one character long";
    }

    public boolean characterNameLongEnough(String name) {
        return 0 < name.length();
    }

    public User getUser() {
        return loggedUser;
    }

    public void setCharacter(Character character) {
        currentCharacter = character;
    }
    
    public void clearCharacter() {
        currentCharacter = null;
    }
    
    public Character getCharacter() {
        return currentCharacter;
    }
}
