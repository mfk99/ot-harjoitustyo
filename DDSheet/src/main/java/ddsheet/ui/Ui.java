
package ddsheet.ui;

import ddsheet.domain.User;
import ddsheet.domain.Character;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Ui extends Application{
    
    //hashmapin käyttö on väliaikaista, myöhemmin vaihdetaan sqliteen
    final private HashMap <String, User> USERS=new HashMap();
    String currentUser="";
    boolean loggedIn=false;
    
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
        Scene loginView=new Scene(composition);
        
        //create user scene
        
        VBox createUserInformation=new VBox();
        HBox createUserButtons=new HBox();
        TextField createUserusernameField=new TextField();
        TextField createUserPasswordField=new TextField();
        Label createUserPrompt=new Label();
        Button createUserButton=new Button("Create user");
        Button loginViewButton=new Button("Back");
        
        createUserButtons.getChildren().addAll(createUserButton, loginViewButton);
        
        createUserInformation.getChildren().addAll(new Label("Username:"), createUserusernameField, 
                new Label("Password"), createUserPasswordField, createUserPrompt, createUserButtons);
        
        Scene createUserView=new Scene(createUserInformation);
        
        //user characters scene
        
        VBox userCharacters=new VBox();
        
        Button logOutButton=new Button ("Log out");
        GridPane grid=new GridPane();
        Button createCharacterViewButton=new Button ("Create new character");
        userCharacters.getChildren().addAll(logOutButton, grid, createCharacterViewButton);
        if (loggedIn) {
            User user=USERS.get(currentUser);
            grid=buildCharacterGrid(user);
            userCharacters.getChildren().set(1, grid);
        }
        
        Scene userCharactersView=new Scene(userCharacters);
        
        //character creation view
        
        VBox characterCreation=new VBox();
        Label createCharacterPrompt=new Label();
        TextField characterName=new TextField();
        Button createCharacterButton=new Button("Create character");
        Button usercharactersViewBtton=new Button("Back");
        characterCreation.getChildren().addAll(createCharacterPrompt, 
                characterName, createCharacterButton, usercharactersViewBtton);
        Scene characterCreationView=new Scene(characterCreation);
        
        //button functionalities
        
        createUserViewButton.setOnAction(e-> {
            window.setScene(createUserView);
        });
        logInButton.setOnAction(e-> {
            prompt.setText(attemptLogIn(loginUsernameField.getText(), loginPasswordField.getText()));
            if (prompt.getText().equals("Login successful!")) {
                window.setScene(userCharactersView);
            }
        });
        
        createUserButton.setOnAction(e-> {
            createUserPrompt.setText(attemptCreateUser(createUserusernameField.getText(), createUserPasswordField.getText()));
        });
        loginViewButton.setOnAction(e-> {
            window.setScene(loginView);
        });
        
        logOutButton.setOnAction(e-> {
            loggedIn=false;
            window.setScene(loginView);
        });
        createCharacterViewButton.setOnAction(e-> {
            window.setScene(characterCreationView);
        });
        
        usercharactersViewBtton.setOnAction(e-> {
            userCharacters.getChildren().set(1, buildCharacterGrid(USERS.get(currentUser)));
            window.setScene(userCharactersView);
        });
        createCharacterButton.setOnAction(e-> {
            createCharacterPrompt.setText(attemptAddCharacter(characterName.getText()));
        });
        
        window.setScene(loginView);
        window.show();
    }
    
    public String attemptCreateUser(String username, String password) {
        if (existingUsername(username)) {
            return "Username already taken";
        }
        if (!usernameLongEnough(username)) {
            return "Username must be at least 3 characters long";
        }
        if (!passwordLongEnough(password)) {
            return "Password must be at least 3 characters long";
        }
        USERS.put(username, new User(username, password));
        return "Account successfully created!";
    }
    
    public String attemptLogIn(String username, String password) {
        if (!existingUsername(username)) {
            return "There is no account with that username";
        }
        if (!correctPassword(username, password)) {
            return "Incorrect password";
        }
        loggedIn=true;
        currentUser=username;
        return ("Login successful!");
    }
    
    private boolean existingUsername(String username) {
        return USERS.containsKey(username);
    }
    
    private boolean correctPassword(String username, String password) {
        User user=USERS.get(username);
        return (password.equals(user.getPassword()));
    }
    
    private boolean usernameLongEnough(String username){
        return (3<=username.length());
    }
    
    private boolean passwordLongEnough(String password){
        return (3<=password.length());
    }
    
    private GridPane buildCharacterGrid(User user) {
        
        GridPane charactersGrid=new GridPane();
        ArrayList<Character> characters=user.getCharacters();
        for (int i=0; i<characters.size(); i++) {
            Character character=characters.get(i);
            charactersGrid.add(new Label(character.getName()), 0, i);
//            charactersGrid.add(new Label(character.getCharacterClass()), i, 1);
//            charactersGrid.add(new Label(character.getAlignment()), i, 2);
//            Button viewCharacter=new Button("View");
//            charactersGrid.add(viewCharacter, i, 3);
//            Button modifyCharacter=new Button("Modify");
//            charactersGrid.add(modifyCharacter, i, 4);
//            Button deleteCharacter=new Button("Delete");
//            charactersGrid.add(modifyCharacter, i, 5);
        }
//        
//        modifyCharacter.setOnAction(e-> {
//            //TODO
//        });
//        
//        deleteCharacter.setOnAction(e-> {
//            //TODO
//        });
        
        return charactersGrid;
    }
    
    private String attemptAddCharacter(String name) {
        if (characterNameLongEnough(name)) {
            USERS.get(currentUser).getCharacters().add(new Character(name));
            return "Character creation successful!";
        }
        return "Name must be at least one character long";
    }
    
    private boolean characterNameLongEnough(String name) {
        return 0<name.length();
    }
    
}
