/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ddsheet.domain;

import ddsheet.dao.UserDao;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author matti
 */
public class FakeUserDao implements UserDao{
    
    ArrayList<User> users=new ArrayList<>();
    
    public FakeUserDao() {
    }
    
    @Override
    public void create (User user) {
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
    public List<User> getAll() {
        return users;
    }
    
    @Override
    public void save() {
        
    }
}
