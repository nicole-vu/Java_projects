/**
 * Written by Nicole Vu - vu000166 and Gina Yi - yi000058
 * Board class creates the game board, places the boats, and allows user to fire or use powers
 */

public class Board {
    // Member variables declaration and initialization
    private Cell[][] board;
    private int gameMode;
    private int numBoat;
    private int boardLength;
    private Boat[] allBoats;
    private int powerPoints;
    private int numTurns;
    private boolean debugMode = false;

    // Constructor
    public Board(int gameMode) {
        this.gameMode = gameMode;
        createBoard();
    }

    // Getters and setters
    public int getNumBoat() {
        return numBoat;
    }
    public int getBoardLength() {
        return boardLength;
    }
    public int getPowerPoints(){
        return powerPoints;
    }
    public int getNumTurns(){
        return numTurns;
    }
    public void setDebugMode() {
        debugMode = true;
    }

    /** createBoard() method creates the game board using 2D array according to input gameMode
     * 1: "beginner", 2: "intermediate" or 3: "expert"
     * @return 2D cell array of all cells on the board
     */
    public Cell[][] createBoard() {
        if (gameMode == 1) {
            boardLength = 3;
            board = new Cell[3][3];
            numBoat = 1;
        }
        else if (gameMode == 2) {
            boardLength = 6;
            board = new Cell[6][6];
            numBoat = 3;
        }
        else if (gameMode == 3) {
            boardLength = 9;
            board = new Cell[9][9];
            numBoat = 5;
        }
        else {
            System.out.println("Mode not supported.");
            return null;
        }
        initBoardValues(board);
        placeBoats();
        return board;
    } // end createBoard()

    /** initBoardValue() method sets the initial values of the board to "-"
     * @param board the game board that we need to initialize
     */
    public void initBoardValues(Cell[][] board) {
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                board[y][x] = new Cell(y,x,'-');
            }
        }
    } // end initBoardValues()

    /** placeBoats() method creates a number of boats according to input mode
     * by calling the helper method createBoat()
     */
    public void placeBoats() {
        if (gameMode == 1) {
            // place 1 boat size 2
            numBoat = 1;
            powerPoints = 1;
            allBoats = new Boat[numBoat];
            allBoats[0] = createBoat(2, 3);
        }
        else if (gameMode == 2) {
            // place 3 boats size 2,3,4
            numBoat = 3;
            powerPoints = 3;
            allBoats = new Boat[numBoat];
            allBoats[0] = createBoat(2, 6);
            allBoats[1] = createBoat(3,6);
            allBoats[2] = createBoat(4, 6);
        }
        else if (gameMode == 3) {
            // place 5 boats size 2,3,3,4,5
            numBoat = 5;
            powerPoints = 5;
            allBoats = new Boat[numBoat];
            allBoats[0] = createBoat(2,9);
            allBoats[1] = createBoat(3,9);
            allBoats[2] = createBoat(3,9);
            allBoats[3] = createBoat(4,9);
            allBoats[4] = createBoat(5,9);
        }
        else {
            System.out.println("Mode not supported.");
        }
    } // end placeBoats()

    /** createBoat() method randomly creates a boat on an empty space on the board
     * @param boatLength length of the boat to be created
     * @param boardSize the size of the board based on game mode
     * @return Boat object
     */
    public Boat createBoat(int boatLength, int boardSize) {
        Boat newBoat = new Boat(boatLength);
        boolean isValid = false;
        int direction = (int)(2*Math.random()); // 0:vertical, 1:horizontal
        int initX = 0, initY = 0;

        if (direction == 0) {
            // Check vertical direction for space
            while (!isValid) {
                initX = (int)(boardSize*Math.random());
                initY = (int)((boardSize-boatLength+1)*Math.random());
                isValid = true;
                for (int i = 0; i < boatLength; i++) {
                    if (board[initY + i][initX].getStatus() != '-') {
                        isValid = false;
                        break;
                    }
                }
            }
            // Place ship vertically
            for (int i = 0; i < boatLength; i++) {
                newBoat.isHorizontal(false);
                newBoat.addBoat(new Cell( initY+i,initX, 'B'));
                board[initY+i][initX] = newBoat.getBoat()[i];
            }

        }
        else {
            // Check horizontal direction for space
            while (!isValid) {
                initX = (int)((boardSize-boatLength+1)*Math.random());
                initY = (int)(boardSize*Math.random());
                isValid = true;
                for (int i = 0; i < boatLength; i++) {
                    if (board[initY][initX + i].getStatus() != '-') {
                        isValid = false;
                        break;
                    }
                }
            }
            // Place ship horizontally
            if (isValid) {
                newBoat.isHorizontal(true);
                for (int i = 0; i < boatLength; i++) {
                    newBoat.addBoat(new Cell(initY,initX+i,'B'));
                    board[initY][initX+i] = newBoat.getBoat()[i];
                }
            }
        }
        return newBoat;
    } // end createBoat()

    /** display() method prints the board on terminal
     */
    public void display() {
        System.out.print("   ");
        for (int i = 0; i < board.length; i++) {
            System.out.print(i+ "  ");
        }
        System.out.println();
        for (int y = 0; y < board.length; y++) {
            System.out.print(y + "  ");
            for (int x = 0; x < board[y].length; x++) {
                if (board[y][x].getStatus() == 'B' && debugMode == false)  {
                    System.out.print("-  ");
                }
                else if (board[y][x].getStatus() == 'B' && debugMode == true)  {
//                    System.out.print("B  ")
                    for (int i = 0; i < allBoats.length; i++){
                        for (int j = 0; j < allBoats[i].getBoatLength(); j++)
                            if (board[y][x] == allBoats[i].getBoat()[j])
                            System.out.print(allBoats[i].getBoatLength() + "  ");
                    }
                }
                else {
                    System.out.print(board[y][x].getStatus() + "  ");
                }
            }
            System.out.println();
        }
    } // end display()

    /** fire() method receives x and y and hit if the coordinate is valid
     */
    public String fire(int x, int y) {
        numTurns++;
        if (x < 0 || x >= boardLength || y < 0 || y >= boardLength) {
            return "Penalty for input coordination out of bound. You lost 1 turn";
        }
        else if (board[y][x].getStatus() == '-') {
            board[y][x].setStatus('M');
            display();
            return "Miss";
        }
        else if (board[y][x].getStatus() == 'B') {
            board[y][x].setStatus('H');
            display();
            if (checkSinking(x,y)) {
                return "Boat sank!";
            }
            return "Hit";
        }
        else {
            display();
            return "Penalty for attacking the same cell twice. You lost 1 turn. ";
        }
    } // end fire

    /** checkSinking() method checks if the whole boat is hit
     * @return true if all the cells of the boat are hit
     */
    public boolean checkSinking(int x, int y) {
        for (int i = 0; i < allBoats.length; i++) {
            for (int j = 0; j < allBoats[i].getBoatLength(); j++) {
                if (x == allBoats[i].getBoat()[j].getCol() && y == allBoats[i].getBoat()[j].getRow()) {
                    if (allBoats[i].isSunk()) {
                        numBoat--;
                        return true;
                    }
                }
            }
        }
        return false;
    } // end checkSinking

    /** missile() method is a power that can hit an area of 3x3 cells
     * @return number of cell hit and ship sunk by the missile
     */
    public String missile(int x, int y){
        numTurns++;
        powerPoints--;
        int hitCount = 0;
        int shipSunk = 0;
        for (int i = (y-1); i < (y+2); i++) {
            for (int j = (x-1); j < (x+2); j++) {
                if (i >= 0 && i < boardLength && j >= 0 && j < boardLength) {
                    if (board[i][j].getStatus() == '-')
                        board[i][j].setStatus('M');
                    else if (board[i][j].getStatus() == 'B') {
                        board[i][j].setStatus('H');
                        hitCount++;
                        if (checkSinking(j,i)) {
                            shipSunk++;
                        }
                    }
                }
            }
        }
        display();
        return "Missile hit " + hitCount + " time(s), sunk " + shipSunk + " ship(s)";
    } // end missile()

    /** drone() method is a power that can scan a random row or column for ship
     * @return number of targets in a random row or column
     */
    public String drone(){
        numTurns++;
        powerPoints--;
        double rand = (Math.random()); //random decision of row or col; <.25 = row, <.25 = col
        int line = (int)((boardLength - 1) * Math.random()); //random decision of which row/col
        int count1 = 0; //counts how many boats are in row/col
        String rc = null; //indicates if drone() scanned row or col
        if (rand < .25) {
            rc = "row";
            for (int i = 0; i < boardLength; i++) {
                if (board[line][i].getStatus() == 'B' || board[line][i].getStatus() == 'H')
                    count1 += 1;
            }
        }
        else if (rand > .25) {
            rc = "column";
            for (int i = 0; i < boardLength; i++) {
                if (board[i][line].getStatus() == 'B' || board[i][line].getStatus() == 'H')
                    count1 += 1;
            }
        }
        return "Drone has scanned " + count1 + " target(s) in " + rc + " " + line;
    } // end drone()

    /** submarine() method is a power that sink the whole ship if one cell of it got hit
     * @return true if hit
     */
    public boolean submarine(int x, int y){
        numTurns++;
        powerPoints -= 1;
        if (board[y][x].getStatus() == 'B' || board[y][x].getStatus() == 'H') {
            for (int i = 0; i < allBoats.length; i++) {
                for (int j = 0; j < allBoats[i].getBoatLength(); j++) {
                    if ((x == allBoats[i].getBoat()[j].getCol()) && (y == allBoats[i].getBoat()[j].getRow())) {
                        for (int k = 0; k < allBoats[i].getBoatLength(); k++) {
                            board[allBoats[i].getBoat()[k].getRow()][allBoats[i].getBoat()[k].getCol()].setStatus('H');
                        }
                        numBoat--;
                        display();
                        return true;
                    }
                }
            }
        }
        board[y][x].setStatus('M');
        display();
        return false;
    } // end submarine()

    /** checkGameOver() method checks if all the boats have been sunk
     * @return true if all the boats have been sunk
     */
    public boolean checkGameOver() {
        if (numBoat == 0)
            return true;
        return false;
    }
} // end class Board
