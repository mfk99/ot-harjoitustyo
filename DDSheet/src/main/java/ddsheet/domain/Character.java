
package ddsheet.domain;

public class Character {
    
    //contains name, race, class, alignment
    private String[] stringValues;
    
    //contains strength, dexterity, constitution, intelligence, wisdom and charisma
    private int[] intValues;
    
    public Character(String name) {
        stringValues = new String[4];
        stringValues[0]=name;
        intValues = new int[6];
    }
    
    //string values
    
    public void setStringValues(int index, String value) {
        stringValues[index]=value;
    }
    
    public String[] getStringValues() {
        return stringValues;
    }
    
    public void setName(String value) {
        this.stringValues[0] = value;
        
    }
    public String getName() {
        return this.stringValues[0];
    }
    
    public void setRace(String value) {
        this.stringValues[1] = value;
    }
    public String getRace() {
        return this.stringValues[1];
    }
    
    public void setCharacterClass(String value) {
        this.stringValues[2] = value;
    }
    public String getCharacterClass() {
        return this.stringValues[2];
    }
    
    public void setAlignment(String value) {
        this.stringValues[3] = value;
    }
    public String getAlignment() {
        return this.stringValues[3];
    }
    
    //int values
    
    public void setIntValues(int index, int value) {
        this.intValues[index]=value;
    }
    
    public int[] getIntValues() {
        return intValues;
    }
    
    //these aren't necessary but might make the code easier to read
    
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
