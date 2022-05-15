/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ddsheet.domain;

import ddsheet.dao.CharacterDao;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author matti
 */
public class FakeCharacterDao implements CharacterDao {
    
    ArrayList<Character> characters=new ArrayList<>();
    
    public FakeCharacterDao() {
        characters.add(new Character("Sly Cooper", new User ("Sly", "Sly")));
    }
    
    @Override
    public void create (Character character) {
        characters.add(character);
    }
    
    @Override
    public List<Character> getAll() {
        return characters;
    }
    
    @Override
    public void save() {
        
    }
}
