
package ddsheet.dao;

import ddsheet.domain.User;
import java.util.ArrayList;
import java.sql.*;

public class SqlUserDao implements UserDao {
    
    private ArrayList<User> users;
    private String url = "jdbc:sqlite:myDB.sqlite";
    
    public SqlUserDao() {
        users = new ArrayList();
        init();
    }
    
    private void init() {
        System.out.println("Connection established");
        createTable();
        load();
    }
    
    private Connection connect() throws SQLException {
        Connection conn = DriverManager.getConnection(url);
        return conn;
    }
    
    //creates new table if it doesn't exist
    private void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (\n"  
                + " id integer PRIMARY KEY,\n"  
                + " username text NOT NULL,\n"  
                + " password text NOT NULL\n"  
                + ");";
        try {
            Connection c = connect();
            Statement stmt = c.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("table created successfully");
    }
    
    //loads users to the arraylist
    private void load() {
        String sql = "SELECT * FROM users";
        
        try {
            Connection c = connect(); 
            Statement stmt = c.createStatement(); 
            ResultSet rs = stmt.executeQuery(sql); 
            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                users.add(new User(username, password));
            }
            
        } catch (SQLException e) {  
            System.out.println(e.getMessage());
        }
        System.out.println("loading successful");
    }
    
    
    @Override
    public void create(User user) {
        users.add(user);
        String username = user.getUsername();
        String password = user.getPassword();
        String sql = "INSERT INTO users (" + username + "," + password + ")";
        try (Connection c = connect();) {
            PreparedStatement pstmt = c.prepareStatement(sql); 
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
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
}
