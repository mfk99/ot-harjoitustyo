
package ddsheet.dao;

import ddsheet.domain.Character;
import java.util.ArrayList;

public class SqlCharacterDao implements CharacterDao {
    
    ArrayList<Character> characters;
    
    public SqlCharacterDao() {
        characters = new ArrayList();
        //load the characters
    }
    
    @Override
    public void create(Character character) {
        characters.add(character);
    }
    
    @Override
    public ArrayList<Character> getAll() {
        return characters;
    }
    
    public void save() {
        //save characters to database
    }
}
