/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ddsheet.dao;

import ddsheet.domain.User;
import java.io.File;
import java.io.FileWriter;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import static org.junit.Assert.*;

/**
 *
 * @author matti
 */
public class FileUserDaoTest {
    
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();
    
    public FileUserDaoTest() {
    }
    
    File userFile;
    UserDao dao;
    
    @Before
    public void setUp() throws Exception {
        userFile = testFolder.newFile("testfile_users.txt");
        
        try (FileWriter writer = new FileWriter(userFile.getAbsolutePath())) {
            writer.write("Sly;Sly\n");
        }
        
        dao=new FileUserDao(userFile.getAbsolutePath());
    }

    @After
    public void tearDown() {
        userFile.delete();
    }
    
    @Test
    public void createdUserCanBeFound() {
        User user=new User("test", "test");
        dao.create(user);
        User foundUser=dao.findByUsername("test");
        assertEquals(user, foundUser);
    }
    
    @Test
    public void savedUserIsFound() {
        String username=dao.findByUsername("Sly").getUsername();
        assertEquals("Sly", username);
    }
}
