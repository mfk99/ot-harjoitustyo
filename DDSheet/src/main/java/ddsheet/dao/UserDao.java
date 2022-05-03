
package ddsheet.dao;

import ddsheet.domain.User;
import java.util.List;

public interface UserDao {
    
    void create(User user);
    User findByUsername(String username);
    List<User> getAll();
    
}
