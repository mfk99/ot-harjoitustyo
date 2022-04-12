
package ddsheet.domain;

public class Character {
    
    private String name;
    private String characterClass;
    private String race;
    private String alignment;
    
    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;
    
    public Character(String name) {
        this.name = name;
    }
    
    //string values
    
    public void setName(String value) {
        this.name = value;
    }
    
    public String getName() {
        return this.name;
    }
    
//    public void setCharacterClass(String value) {
//        this.characterClass = value;
//    }
//    
//    public String getCharacterClass() {
//        return this.characterClass;
//    }
//    
//    public void setRace(String value) {
//        this.race = value;
//    }
//    
//    public String getRace() {
//        return this.race;
//    }
//    
//    public void setAlignment(String value) {
//        this.alignment = value;
//    }
//    
//    public String getAlignment() {
//        return this.alignment;
//    }
//    
//    //int values
//    
//    public void setStrength(int value) {
//        this.strength = value;
//    }
//    
//    public int getStrength() {
//        return this.strength;
//    }
//    
//    public void setDexteririty(int value) {
//        this.dexterity = value;
//    }
//    
//    public int getDexterity() {
//        return this.dexterity;
//    }
//    
//    public void setConstitution(int value) {
//        this.constitution = value;
//    }
//    
//    public int getConstitution() {
//        return this.constitution;
//    }
//    
//    public void setIntelligence(int value) {
//        this.intelligence = value;
//    }
//    
//    public int getIntelligence() {
//        return this.intelligence;
//    }
//    
//    public void setWisdom(int value) {
//        this.wisdom = value;
//    }
//    
//    public int getWisdom() {
//        return this.wisdom;
//    }
//    
//    public void setCharisma(int value) {
//        this.charisma = value;
//    }
//    
//    public int getCharisma() {
//        return this.charisma;
//    }
}
