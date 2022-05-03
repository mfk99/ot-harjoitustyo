
package ddsheet.dao;

import ddsheet.domain.Character;
import java.util.List;

public interface CharacterDao {
    
    void create(Character character);
    List<Character> getAll();
    
}
