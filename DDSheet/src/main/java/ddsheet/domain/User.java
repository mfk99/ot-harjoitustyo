
package ddsheet.domain;

import java.util.ArrayList;

public class User {
    
    private String username;
    private String password;
    private ArrayList<Character> characters;
    
    public User(String username, String password) {
        this.username=username;
        this.password=password;
        //arraylist vaihdetaan myöhemmin sqliteen
        this.characters=new ArrayList<>();
    }
    
    public void addNewCharacter(String name) {
        characters.add(new Character(name));
    }
    
    public void deleteCharacter(String name) {
        for (int i=0; i<characters.size(); i++) {
            String characterName=characters.get(i).getName();
            if (name.equals(characterName)) {
                characters.remove(i);
                return;
            }
        }
    }
    
    public String getUsername () {
        return this.username;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public ArrayList<Character> getCharacters() {
        return this.characters;
    }
    
}
