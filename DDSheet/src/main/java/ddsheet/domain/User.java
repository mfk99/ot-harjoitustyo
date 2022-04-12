
package ddsheet.domain;

import java.util.ArrayList;

public class User {
    
    private String username;
    private String password;
    private ArrayList<Character> characters;
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        //arraylist vaihdetaan myöhemmin sqliteen
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
