```mermaid
classDiagram
    Monopoly "1" --> "2" Dice
    Monopoly "1" --> "1" Board
    Board "1" --> "40" Space
    Space "1" --> "1" Space
    Monopoly "1" --> "2..8" Player
    Player "1" --> "1" Token
    Token "1" ..> "1" Space
    Space "1" --> "1" Type
    Type "1" --> "1" Action
    Card "1" --> "1" Action
    
    class Monopoly {
    +Space start
    +Space Jail
    }
    class Dice
    class Player
    Player : +int money
    class Board 
    class Space {
    +Type type
    +Player owner
    +int houses
    +boolean hotel
    }
    class Card
    Card : -boolean community
    Card : -boolean chance
    Card : +action Action
    class Action
    class Token 
    class Type {
    +String type
    +action Action
    }
