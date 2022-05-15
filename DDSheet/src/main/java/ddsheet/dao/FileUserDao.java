
package ddsheet.dao;

import ddsheet.domain.User;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileUserDao implements UserDao {
    
    private ArrayList<User> users;
    private String userFile;
    
    public FileUserDao(String userFile) throws Exception {
        users = new ArrayList();
        this.userFile = userFile;
        try {
            load();
        } catch (Exception ex) {
            FileWriter writer = new FileWriter(new File(userFile));
            writer.close();
        }
    }
    
    /**
     * Loads existing users from memory file
     * @throws Exception 
     */
    private void load() throws Exception {
        Scanner scanner = new Scanner(new File(userFile));
        while (scanner.hasNextLine()) {
            String[] values = scanner.nextLine().split(";");
            users.add(new User(values[0], values[1]));
        }
    }
    
    @Override
    public void create(User user) {
        users.add(user);
    }
    
    @Override
    public User findByUsername(String username) {
        for (User user: users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
    
    @Override
    public ArrayList<User> getAll() {
        return users;
    }
    
    @Override
    public void save() {
        try (FileWriter writer = new FileWriter(new File(userFile))) {
            for (User user : users) {
                writer.write(user.getUsername() + ";" + user.getPassword() + "\n");
            }
        } catch (Exception ex) {
        }
    }
}
