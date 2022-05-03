
package ddsheet.domain;
/**
 * A class representation of the user's characters
 * @author matti
 */
public class Character {
    
    //contains name, race, class, alignment
    private String[] stringValues;
    /*contains strength, dexterity, constitution, 
    intelligence, wisdom and charisma */
    private int[] intValues;
    
    /**
     * Creates a new character, sets string values apart 
     * from the name to - and integer values to 10
     * @param name The given name for the character
     */
    public Character(String name) {
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
    
//    Additional setters and getters for string values, 
//    currently not in use
//    
//    public void setName(String value) {
//        this.stringValues[0] = value;
//        
//    }
//    public String getName() {
//        return this.stringValues[0];
//    }
//    
//    public void setRace(String value) {
//        this.stringValues[1] = value;
//    }
//    public String getRace() {
//        return this.stringValues[1];
//    }
//    
//    public void setCharacterClass(String value) {
//        this.stringValues[2] = value;
//    }
//    public String getCharacterClass() {
//        return this.stringValues[2];
//    }
//    
//    public void setAlignment(String value) {
//        this.stringValues[3] = value;
//    }
//    public String getAlignment() {
//        return this.stringValues[3];
//    }
    
//    Additional setters and getters for integer values, 
//    currently not in use
//    
//    public void setStrength(int value) {
//        this.abilityScores[0]=value;;
//    }
//    
//    public int getStrength() {
//        return this.abilityScores[0];
//    }
//    
//    public void setDexteririty(int value) {
//        this.abilityScores[1]=value;
//    }
//    
//    public int getDexterity() {
//        return this.abilityScores[1];
//    }
//    
//    public void setConstitution(int value) {
//        this.abilityScores[2]=value;
//    }
//    
//    public int getConstitution() {
//        return this.abilityScores[2];
//    }
//    
//    public void setIntelligence(int value) {
//        this.abilityScores[3]=value;
//    }
//    
//    public int getIntelligence() {
//        return this.abilityScores[3];
//    }
//    
//    public void setWisdom(int value) {
//        this.abilityScores[4]=value;
//    }
//    
//    public int getWisdom() {
//        return this.abilityScores[4];
//    }
//    
//    public void setCharisma(int value) {
//        this.abilityScores[5]=value;
//    }
//    
//    public int getCharisma() {
//        return this.abilityScores[5];
//    }
}
