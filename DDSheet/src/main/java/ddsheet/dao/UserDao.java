
package ddsheet.dao;

import ddsheet.domain.User;
import java.util.List;

/**
 * An interface that has the responsibilty of 
 * saving the user information into the database
 * @author matti
 */
public interface UserDao {
    /**
     * Adds a new user to the database
     * @param user The user which will be added
     */
    void create(User user);
    /**
     * Finds a specific user by their username
     * @param username The given username
     * @return The User represantation of the user
     */
    User findByUsername(String username);
    /**
     * A method that returns a list of all users in the database
     * @return A list of all users in the database
     */
    List<User> getAll();
    
}
