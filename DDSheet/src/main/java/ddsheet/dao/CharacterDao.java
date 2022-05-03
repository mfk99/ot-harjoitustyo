
package ddsheet.dao;

import ddsheet.domain.Character;
import java.util.List;

/**
 * An interface that has the responsibilty of 
 * saving the character information into the database
 * @author matti
 */
public interface CharacterDao {
    /**
     * Adds a new character to the database
     * @param character The character which will be added
     */
    void create(Character character);
    /**
     * Returns a list of all characters in the database
     * @return A list of all the characters in the database
     */
    List<Character> getAll();
    
}
