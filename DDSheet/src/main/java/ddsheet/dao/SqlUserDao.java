
package ddsheet.dao;

import ddsheet.domain.User;
import java.util.ArrayList;
import java.sql.*;

public class SqlUserDao implements UserDao {
    
    private ArrayList<User> users;
    
    public SqlUserDao() {
        users=new ArrayList();
        init();
    }
    
    private void init() {
        Connection conn=connect();
        System.out.println("Connection established");
        createTable(conn);
        load(conn);
    }
    
    //connects to/creates database
    private Connection connect() {
        try {
            
            String url = "jdbc:sqlite:C:/data/database.db";
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    //creates new table if it doesn't exist
    private void createTable(Connection conn){
        String sql = "CREATE TABLE IF NOT EXISTS users (\n"  
                + " id integer PRIMARY KEY,\n"  
                + " username text NOT NULL,\n"  
                + " password text NOT NULL\n"  
                + ");";
        try {
            Statement stmt=conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("table created successfully");
    }
    
    //loads users to the arraylist
    private void load(Connection conn) {
        String sql = "SELECT * FROM users";
        
        try {
            Statement stmt  = conn.createStatement();  
            ResultSet rs    = stmt.executeQuery(sql); 
            while (rs.next()) {
                String username=rs.getString("username");
                String password=rs.getString("password");
                users.add(new User(username, password));
            }
            
        } catch (SQLException e) {  
            System.out.println(e.getMessage());
        }
        System.out.println("loading successful");
    }
    
    public void insert(User user, Connection conn) {
        String username=user.getUsername();
        String password=user.getPassword();
        String sql= "INSERT INTO users ("+username+","+password+")";
        try {
             PreparedStatement pstmt = conn.prepareStatement(sql); 
             pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
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
    public ArrayList<User> getAll() {
        return users;
    }
}
