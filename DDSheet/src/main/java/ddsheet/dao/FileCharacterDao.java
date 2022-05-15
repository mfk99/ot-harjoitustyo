
package ddsheet.dao;

import ddsheet.domain.Character;
import ddsheet.domain.User;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileCharacterDao implements CharacterDao {
    
    private UserDao users;
    private String characterFile;
    ArrayList<Character> characters;
    
    public FileCharacterDao(UserDao users, String characterFile) throws Exception {
        this.users = users;
        this.characterFile = characterFile;
        characters = new ArrayList();
        try {
            load();
        } catch (Exception ex) {
            FileWriter writer = new FileWriter(new File(characterFile));
            writer.close();
        }
    }
    
    private void load() throws Exception {
        Scanner scanner = new Scanner(new File(characterFile));
        while (scanner.hasNextLine()) {
            String[] values = scanner.nextLine().split(";");
            User owner = users.findByUsername(values[0]);
            Character character = new Character(values[1], owner);
            for (int i = 1; i < 4; i++) {
                character.setStringValues(i, values[i + 1]);
            }
            for (int i = 0; i < 6; i++) {
                int value = Integer.valueOf(values[5 + i]);
                character.setIntValues(i, value);
            }
            characters.add(character);
            owner.getCharacters().add(character);
        }
    }
    
    @Override
    public void create(Character character) {
        characters.add(character);
    }
    
    @Override
    public ArrayList<Character> getAll() {
        return characters;
    }
    
    public void save() {
        try (FileWriter writer = new FileWriter(new File(characterFile))) {
            for (Character character : characters) {
                String[] stringValues = character.getStringValues();
                int[] intValues = character.getIntValues();
                writer.write(character.getOwner().getUsername() + ";"
                        + stringValues[0] + ";" + stringValues[1] + ";"
                        + stringValues[2] + ";" + stringValues[3]
                        + ";" + intValues[0] + ";" + intValues[1]
                        + ";" + intValues[2] + ";" + intValues[3]
                        + ";" + intValues[4] + ";" + intValues[5]
                        + "\n");
            }
        } catch (Exception ex) {
        }
    }
}
