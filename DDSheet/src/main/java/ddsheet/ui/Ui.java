
package ddsheet.ui;

import java.util.HashMap;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Ui extends Application{
    
    //hashmapin käyttö on väliaikaista, myöhemmin vaihdetaan sqliteen
    private HashMap <String, String> USERS=new HashMap();
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void init() {
        //TODO
    }
    
    @Override
    public void start(Stage window) {
        
        //login scene
        
        HBox loginButtons=new HBox();
        Button logInButton=new Button("Log in");
        Button createUserViewButton=new Button("Create new user");
        loginButtons.getChildren().addAll(logInButton, createUserViewButton);
        loginButtons.setSpacing(10);
        
        VBox loginInformation=new VBox();
        TextField loginUsernameField=new TextField();
        TextField loginPasswordField=new TextField();
        Label prompt=new Label();
        loginInformation.getChildren().addAll(
                new Label("Username:"), loginUsernameField, new Label("Password"), 
                loginPasswordField, prompt, loginButtons);
        loginInformation.setSpacing(10);
        
        
        FlowPane composition=new FlowPane(loginInformation);
        Scene loginScene=new Scene(composition);
        
        //create user scene
        
        VBox createUserInformation=new VBox();
        HBox createUserButtons=new HBox();
        TextField createUserusernameField=new TextField();
        TextField createUserPasswordField=new TextField();
        Label createUserPrompt=new Label();
        Button createUserButton=new Button("Create user");
        Button createUserBackButton=new Button("Back");
        
        createUserButtons.getChildren().addAll(createUserButton, createUserBackButton);
        
        createUserInformation.getChildren().addAll(new Label("Username:"), createUserusernameField, 
                new Label("Password"), createUserPasswordField, createUserPrompt, createUserButtons);
        
        Scene createUserView=new Scene(createUserInformation);
        
        //button functionalities
        
        createUserViewButton.setOnAction(e-> {
            window.setScene(createUserView);
        });
        logInButton.setOnAction(e-> {
            prompt.setText(attemptLogIn(loginUsernameField.getText(), loginPasswordField.getText()));
        });
        createUserButton.setOnAction(e-> {
            createUserPrompt.setText(attemptCreateUser(createUserusernameField.getText(), createUserPasswordField.getText()));
        });
        createUserBackButton.setOnAction(e-> {
            window.setScene(loginScene);
        });
        
        window.setScene(loginScene);
        window.show();
    }
    
    public String attemptCreateUser(String username, String password) {
        if (existingUsername(username)) {
            return "Username already taken";
        }
        if (!passwordEligible(password)) {
            return "Password must be at least 3 characters long";
        }
        USERS.put(username, password);
        return "Account successfully created!";
    }
    
    public String attemptLogIn(String username, String password) {
        if (!existingUsername(username)) {
            return "There is no account with that username";
        }
        if (!correctPassword(username, password)) {
            return "Incorrect password";
        }
        return ("Login successful!");
    }
    
    private boolean existingUsername(String username) {
        return USERS.containsKey(username);
    }
    
    private boolean correctPassword(String username, String password) {
        String truePassword=USERS.get(username);
        return (truePassword.equals(password));
    }
    
    private boolean passwordEligible(String password){
        return (3<=password.length());
    }
}
