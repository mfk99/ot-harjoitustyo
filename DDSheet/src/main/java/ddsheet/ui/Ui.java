
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

public class Ui extends Application {
    
    private DDSheetService ddsheetService;
    
    private Stage window;
    
    private Scene loginScene;
    private Scene createUserScene;
    private Scene userCharactersScene;
    private Scene createCharacterScene;
    private Scene inspectCharacterScene;
    private Scene modifyCharacterScene;
    private Scene modifyAttributeScene;
    
    private GridPane characterGrid;
    private GridPane statisticsGrid;
    private GridPane modificationGrid;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void init() {
        ddsheetService = new DDSheetService();
    }
    
    @Override
    public void start(Stage stage) {
        
        window=stage;
        
        //login view
        
        HBox loginButtons = new HBox();
        Button logInButton = new Button("Log in");
        Button createUserViewButton = new Button("Create new user");
        loginButtons.getChildren().addAll(logInButton, createUserViewButton);
        loginButtons.setSpacing(10);
        
        VBox loginInformation = new VBox();
        TextField loginUsernameField = new TextField();
        TextField loginPasswordField = new TextField();
        Label prompt = new Label();
        loginInformation.getChildren().addAll(
                new Label("Username:"), loginUsernameField, new Label("Password"), 
                loginPasswordField, prompt, loginButtons);
        loginInformation.setSpacing(10);
        FlowPane composition = new FlowPane(loginInformation);
        
        logInButton.setOnAction(e-> {
            prompt.setText(ddsheetService.attemptLogIn(loginUsernameField.getText(), loginPasswordField.getText()));
            if (prompt.getText().equals("Login successful!")) {
                updateUserCharactersGrid(ddsheetService.getUser(), window);
                window.setScene(userCharactersScene);
            }
        });
        createUserViewButton.setOnAction(e-> {
            window.setScene(createUserScene);
        });
        
        loginScene = new Scene(composition, 400, 200);
        
        //create user view
        
        VBox createUserInformation = new VBox();
        HBox createUserButtons = new HBox();
        TextField createUserusernameField = new TextField();
        TextField createUserPasswordField = new TextField();
        Label createUserPrompt = new Label();
        Button createUserButton = new Button("Create user");
        Button loginViewButton = new Button("Back");
        
        createUserButtons.getChildren().addAll(createUserButton, loginViewButton);
        createUserInformation.getChildren().addAll(new Label("Username:"), createUserusernameField, 
                new Label("Password"), createUserPasswordField, createUserPrompt, createUserButtons);
        
        createUserButton.setOnAction(e-> {
            createUserPrompt.setText(ddsheetService.attemptCreateUser(createUserusernameField.getText(), createUserPasswordField.getText()));
        });
        loginViewButton.setOnAction(e-> {
            window.setScene(loginScene);
        });
        
        createUserScene = new Scene(createUserInformation, 400, 200);
        
        //user characters view
        
        VBox userCharacters = new VBox();
        
        Button logOutButton = new Button("Log out");
        characterGrid = new GridPane();
        Button createCharacterViewButton = new Button("Create new character");
        userCharacters.getChildren().addAll(logOutButton, characterGrid, createCharacterViewButton);
        
        logOutButton.setOnAction(e-> {
            ddsheetService.logOut();
            window.setScene(loginScene);
        });
        createCharacterViewButton.setOnAction(e-> {
            window.setScene(createCharacterScene);
        });
        
        userCharactersScene = new Scene(userCharacters, 400, 200);
        
        //character creation view
        
        VBox characterCreation = new VBox();
        Label createCharacterPrompt = new Label();
        TextField characterName = new TextField();
        Button createCharacterButton = new Button("Create character");
        Button usercharactersViewButton = new Button("Back");
        characterCreation.getChildren().addAll(createCharacterPrompt, 
                characterName, createCharacterButton, usercharactersViewButton);
        
        usercharactersViewButton.setOnAction(e-> {
            updateUserCharactersGrid(ddsheetService.getUser(), window);
            window.setScene(userCharactersScene);
        });
        createCharacterButton.setOnAction(e-> {
            createCharacterPrompt.setText(ddsheetService.addCharacter(characterName.getText()));
        });
        
        createCharacterScene = new Scene(characterCreation, 400, 200);
        
        //character inspection view
        
        VBox inspectionView=new VBox();
        
        HBox inspectionViewButtons=new HBox();
        Button inspectionViewBackButton=new Button("Back");
        Button toModifyViewButton=new Button("Modify");
        inspectionViewButtons.getChildren().addAll(inspectionViewBackButton, 
                toModifyViewButton);
        
        statisticsGrid=new GridPane();
        inspectionView.getChildren().addAll(inspectionViewButtons, statisticsGrid);
        
        inspectionViewBackButton.setOnAction(e-> {
            ddsheetService.clearCharacter();
            window.setScene(userCharactersScene);
        });
        toModifyViewButton.setOnAction(e-> {
            updateModificationViewGrid();
            window.setScene(modifyCharacterScene);
        });
        
        inspectCharacterScene=new Scene(inspectionView, 400, 350);
        
        //character modifying view
        
        VBox modificationView=new VBox();
        HBox modificationViewButtons=new HBox();
        Button modificationViewBackButton=new Button("Back");
        Button toInspectViewButton=new Button("View");
        modificationViewButtons.getChildren().addAll(modificationViewBackButton, toInspectViewButton);
        modificationGrid=new GridPane();
        modificationView.getChildren().addAll(modificationViewButtons, modificationGrid);
        
        modificationViewBackButton.setOnAction(e-> {
            ddsheetService.clearCharacter();
            window.setScene(userCharactersScene);
        });
        toInspectViewButton.setOnAction(e-> {
            updateInspectionViewGrid();
            window.setScene(inspectCharacterScene);
        });
        
        modifyCharacterScene=new Scene(modificationView, 400, 350);
        
        //attribute modifying view
        
        modifyAttributeScene=new Scene(new Label(), 400, 300);
        
        //TODO
        
        window.setScene(loginScene);
        window.show();
    }
    
    private void updateUserCharactersGrid(User user, Stage window) {
        
        characterGrid.getChildren().clear();
        characterGrid.add(new Label ("----------"), 0, 0);
        ArrayList<Character> characters = user.getCharacters();
        for (int i = 0; i < characters.size(); i++) {
            Character character = characters.get(i);
            int index=i*2;
            Label characterName = new Label(character.getName());
            
            Button viewCharacter = new Button("View");
            viewCharacter.setOnAction(e-> {
                ddsheetService.setCharacter(character);
                updateInspectionViewGrid();
                window.setScene(inspectCharacterScene);
            });
            
            Button modifyCharacter = new Button("Modify");
            modifyCharacter.setOnAction(e-> {
                ddsheetService.setCharacter(character);
                updateModificationViewGrid();
                window.setScene(modifyCharacterScene);
            });
            
            Button deleteCharacter = new Button("Delete");
            deleteCharacter.setOnAction(e-> {
                user.getCharacters().remove(character);
                updateUserCharactersGrid(user, window);
            });
            
            characterGrid.addRow(index+1, characterName, viewCharacter, 
                    modifyCharacter, deleteCharacter);
            characterGrid.add(new Label("----------"), 0, index+2);
        }
    }
    
    private void updateInspectionViewGrid() {
        Character character = ddsheetService.getCharacter();
        statisticsGrid.getChildren().clear();
        statisticsGrid.setHgap(10);
        statisticsGrid.setVgap(15);
        statisticsGrid.addColumn(0, new Label ("Name: "), new Label ("Race: "), 
                new Label ("Class: "), new Label ("Alignment: "), 
                new Label ("Strength"), new Label ("Dexterity"), 
                new Label ("Constitution"), new Label ("Intelligence"), 
                new Label ("Wisdom"), new Label ("Charisma"));
        
        statisticsGrid.add(new Label(character.getName()), 1, 0);
        statisticsGrid.add(new Label(character.getRace()), 1, 1);
        statisticsGrid.add(new Label(character.getCharacterClass()), 1, 2);
        statisticsGrid.add(new Label(character.getAlignment()), 1, 3);
        
        int [] stats=character.getIntValues();
        for (int i=0; i<stats.length; i++) {
            statisticsGrid.add(new Label(String.valueOf(stats[i])), 1, (i+4));
        }
    }
    
    private void updateModificationViewGrid() {
        Character character = ddsheetService.getCharacter();
        modificationGrid.getChildren().clear();
        modificationGrid.setHgap(10);
        modificationGrid.setVgap(7);
        
        Button nameButton = new Button("Name: ");
        Button raceButton = new Button("Race: ");
        Button classButton = new Button("Class: ");
        Button alignmentButton = new Button("Alignment: ");
        Button strengthButton = new Button("Strength");
        Button dexterityButton = new Button("Dexterity");
        Button constitutionButton = new Button("Constitution");
        Button intelligenceButton = new Button("Intelligence");
        Button wisdomButton = new Button("Wisdom");
        Button charismaButton = new Button("Charisma");
        modificationGrid.addColumn(0, nameButton, raceButton, 
                classButton, alignmentButton, 
                strengthButton, dexterityButton, 
                constitutionButton, intelligenceButton, 
                wisdomButton, charismaButton);
        
        modificationGrid.add(new Label(character.getName()), 1, 0);
        modificationGrid.add(new Label(character.getRace()), 1, 1);
        modificationGrid.add(new Label(character.getCharacterClass()), 1, 2);
        modificationGrid.add(new Label(character.getAlignment()), 1, 3);
        
        int [] stats=character.getIntValues();
        for (int i=0; i<stats.length; i++) {
            modificationGrid.add(new Label(String.valueOf(stats[i])), 1, (i+4));
        }
        
        nameButton.setOnAction(e-> {
            buildModificationView(0, "name");
            window.setScene(modifyAttributeScene);
        });
        raceButton.setOnAction(e-> {
            buildModificationView(1, "race");
            window.setScene(modifyAttributeScene);
        });
        classButton.setOnAction(e-> {
            buildModificationView(2, "class");
            window.setScene(modifyAttributeScene);
        });
        alignmentButton.setOnAction(e-> {
            buildModificationView(3, "alignment");
            window.setScene(modifyAttributeScene);
        });
        
        strengthButton.setOnAction(e-> {
            buildModificationView(0, "strength");
            window.setScene(modifyAttributeScene);
        });
        dexterityButton.setOnAction(e-> {
            buildModificationView(1, "dexterity");
            window.setScene(modifyAttributeScene);
        });
        constitutionButton.setOnAction(e-> {
            buildModificationView(2, "constitution");
            window.setScene(modifyAttributeScene);
        });
        intelligenceButton.setOnAction(e-> {
            buildModificationView(3, "intelligence");
            window.setScene(modifyAttributeScene);
        });
        wisdomButton.setOnAction(e-> {
            buildModificationView(4, "wisdom");
            window.setScene(modifyAttributeScene);
        });
        charismaButton.setOnAction(e-> {
            buildModificationView(5, "charisma");
            window.setScene(modifyAttributeScene);
        });
        
    }
    
    private void buildModificationView(int index, String value) {
        VBox vbox= new VBox();
        vbox.setSpacing(10);
        Button returnButton=new Button("Return");
        TextField newValue=new TextField();
        Label prompt=new Label("");
        Button intModificationButton=new Button("Modify");
        Button stringModificationButton=new Button("Modify");
        
        
        if (value.equals("name") || value.equals("race") || 
                value.equals("alignment") || value.equals("class")) {
            vbox.getChildren().addAll(returnButton, 
                    new Label ("Insert new "+value), 
                    newValue, prompt, stringModificationButton);
        }
        else if (value.equals("strength") || value.equals("dexterity") || 
                value.equals("constitution") || value.equals("wisdom") || 
                value.equals("intelligence") || value.equals("charisma")) {
            vbox.getChildren().addAll(returnButton, 
                    new Label ("Insert new "+value+" value"), 
                    newValue, prompt, intModificationButton);
        }
        
        returnButton.setOnAction(e-> {
            updateModificationViewGrid();
            window.setScene(modifyCharacterScene);
        });
        
        intModificationButton.setOnAction(e-> {
            boolean success=ddsheetService.attemptIntValueChange(index, newValue.getText());
            if (success) prompt.setText("Modification successful!");
            else prompt.setText("The value must be an integer between 0 and 99");
        });
        
        stringModificationButton.setOnAction(e-> {
            boolean success=ddsheetService.attemptStringValueChange(index, newValue.getText());
            if (success) prompt.setText("Modification successful!");
            else prompt.setText("The value must be at least one character long");
        });
        
        modifyAttributeScene=new Scene(vbox, 400, 300);
    }
}
