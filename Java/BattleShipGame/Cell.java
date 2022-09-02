/**
 * Written by Nicole Vu - vu000166 and Gina Yi - yi000058
 * Cell class creates cells that store the x and y coordination and status of a cell
 */

public class Cell {
    // Member variables declaration and initialization
    private int col; //x
    private int row; //y
    private char status;

    // Constructor
    public Cell(int row, int col, char status) {
        this.col = col;
        this.row = row;
        this.status = status;
    }

    // Setters and getters
    public char getStatus() {
        return status;
    }
    public void setStatus(char newStatus) {
        status = newStatus;
    }
    public int getCol(){
        return col;
    }
    public int getRow(){
        return row;
    }
}
