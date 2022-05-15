/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ddsheet.dao;

import ddsheet.domain.User;
import ddsheet.domain.Character;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

/**
 *
 * @author matti
 */
public class FileCharacterDaoTest {
    
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();
    
    public FileCharacterDaoTest() {
    }
    
    File userFile;
    File characterFile;
    UserDao userDao;
    CharacterDao characterDao;
    
    @Before
    public void setUp() throws Exception {
        userFile = testFolder.newFile("testfile_users.txt");
        characterFile = testFolder.newFile("testfile_characters.txt");
        
        try (FileWriter writer = new FileWriter(userFile.getAbsolutePath())) {
            writer.write("Sly;Sly\n");
        }
        
        try (FileWriter writer = new FileWriter(characterFile.getAbsolutePath())) {
            writer.write("Sly;Bentley;-;-;-;10;10;10;10;10;10\n");
        }
        userDao = new FileUserDao(userFile.getAbsolutePath());
        characterDao = new FileCharacterDao(userDao, characterFile.getAbsolutePath());
    }

    @After
    public void tearDown() {
        userFile.delete();
        characterFile.delete();
    }
    
    @Test
    public void createdCharacterCanBeFound() {
        Character character = new Character("test", new User("test","test"));
        characterDao.create(character);
        List <Character> characters = characterDao.getAll();
        boolean value = characters.contains(character);
        assertEquals(true, value);
    }
    
    @Test
    public void saveAndLoadWork() throws Exception{
        Character character = new Character("test", new User("test","test"));
        characterDao.create(character);
        characterDao.save();
        characterDao = new FileCharacterDao(userDao, characterFile.getAbsolutePath());
        List <Character> characters = characterDao.getAll();
        boolean value = false;
        for (Character cha : characters) {
            String name=cha.getStringValues()[0];
            if (name.equals("test")) value=true;
        }
        assertEquals(true, value);
    }
}
