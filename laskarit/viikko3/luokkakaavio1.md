```mermaid
classDiagram
    Monopoly "1" --> "2" Dice
    Monopoly "1" --> "1" Board
    Board "1" --> "40" Space
    Space "1" --> "1" Space
    Monopoly "1" --> "2..8" Player
    Player "1" --> "1" Token
    Token "*" ..> "1" Space
    
    class Monopoly 
    class Dice
    class Player
    class Board 
    class Space 
    class Token 
