
package ddsheet.domain;
/**
 * A class representation of the user's characters
 * @author matti
 */
public class Character {
    
    private User owner;
    //contains name, race, class, alignment
    private String[] stringValues;
    /*contains strength, dexterity, constitution, 
    intelligence, wisdom and charisma */
    private int[] intValues;
    
    /**
     * Creates a new character, sets string values apart 
     * from the name to - and integer values to 10
     * @param name The given name for the character
     * @param user The user who owns the character
     */
    public Character(String name, User user) {
        owner = user;
        stringValues = new String[4];
        stringValues[0] = name;
        for (int i = 1; i < 4; i++) {
            stringValues[i] = "-";
        }
        intValues = new int[6];
        for (int i = 0; i < 6; i++) {
            intValues[i] = 10;
        }
    }
    
    public void setStringValues(int index, String value) {
        stringValues[index] = value;
    }
    
    public String[] getStringValues() {
        return stringValues;
    }
    
    public void setIntValues(int index, int value) {
        this.intValues[index] = value;
    }
    
    public int[] getIntValues() {
        return intValues;
    }
    
    public User getOwner() {
        return owner;
    }
}
