
package ddsheet.domain;

import java.util.ArrayList;

/**
 * A class represantation of program users
 * @author matti
 */
public class User {
    
    private String username;
    private String password;
    private ArrayList<Character> characters;
    
    /**
     * Creates a new user and allocates them a list for their characters
     * @param username The user's username
     * @param password The user's password
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.characters = new ArrayList<>();
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public ArrayList<Character> getCharacters() {
        return this.characters;
    }
    
}
