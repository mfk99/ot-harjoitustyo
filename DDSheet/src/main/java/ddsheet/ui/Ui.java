
package ddsheet.ui;

import ddsheet.domain.User;
import ddsheet.domain.Character;
import ddsheet.domain.DDSheetService;
import java.util.ArrayList;
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
    
    private DDSheetService ddsheetService;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void init() {
        ddsheetService=new DDSheetService();
    }
    
    @Override
    public void start(Stage window) {
        
        //login view
        
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
        Scene loginView=new Scene(composition, 400, 200);
        
        //create user view
        
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
        
        Scene createUserView=new Scene(createUserInformation, 400, 200);
        
        createUserButton.setOnAction(e-> {
            createUserPrompt.setText(ddsheetService.attemptCreateUser(createUserusernameField.getText(), createUserPasswordField.getText()));
        });
        
        //user characters view
        
        VBox userCharacters=new VBox();
        
        Button logOutButton=new Button ("Log out");
        GridPane grid=new GridPane();
        Button createCharacterViewButton=new Button ("Create new character");
        userCharacters.getChildren().addAll(logOutButton, grid, createCharacterViewButton);
        
        Scene userCharactersView=new Scene(userCharacters, 400, 200);
        
        //character creation view
        
        VBox characterCreation=new VBox();
        Label createCharacterPrompt=new Label();
        TextField characterName=new TextField();
        Button createCharacterButton=new Button("Create character");
        Button usercharactersViewBtton=new Button("Back");
        characterCreation.getChildren().addAll(createCharacterPrompt, 
                characterName, createCharacterButton, usercharactersViewBtton);
        Scene characterCreationView=new Scene(characterCreation, 400, 200);
        
        //transitional button functionalities
        
        createUserViewButton.setOnAction(e-> {
            window.setScene(createUserView);
        });
        logInButton.setOnAction(e-> {
            prompt.setText(ddsheetService.attemptLogIn(loginUsernameField.getText(), loginPasswordField.getText()));
            if (prompt.getText().equals("Login successful!")) {
                userCharacters.getChildren().set(1, buildCharacterGrid(ddsheetService.getUser()));
                window.setScene(userCharactersView);
            }
        });
        
        
        loginViewButton.setOnAction(e-> {
            window.setScene(loginView);
        });
        
        logOutButton.setOnAction(e-> {
            ddsheetService.logOut();
            window.setScene(loginView);
        });
        createCharacterViewButton.setOnAction(e-> {
            window.setScene(characterCreationView);
        });
        
        usercharactersViewBtton.setOnAction(e-> {
            userCharacters.getChildren().set(1, buildCharacterGrid(ddsheetService.getUser()));
            window.setScene(userCharactersView);
        });
        createCharacterButton.setOnAction(e-> {
            createCharacterPrompt.setText(ddsheetService.addCharacter(characterName.getText()));
        });
        
        window.setScene(loginView);
        window.show();
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
}
