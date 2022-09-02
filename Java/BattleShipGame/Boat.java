/**
 * Written by Nicole Vu - vu000166 and Gina Yi - yi000058
 * Boat class creates Boat objects that store the cell coordination of a boat
 */

public class Boat {
    // Member variables declaration and initialization
    private Cell[] boatArray;
    private boolean isHorizontal;
    private int boatLength;
    private int boatLoc = 0;

    // Constructor
    public Boat(int boatLength) {
        boatArray = new Cell[boatLength];
        this.boatLength = boatLength;
    }

    // Getters and setters
    public Cell[] getBoat() {
        return boatArray;
    }
    public int getBoatLength() {
        return boatLength;
    }
    public void isHorizontal(boolean isHorizontal) {
        this.isHorizontal= isHorizontal;
    }

    /** addBoat() method creates cells into a boat object
      * @param newBoat a location on a boat
     */
    public void addBoat(Cell newBoat) {
       boatArray[boatLoc] = newBoat;
       boatLoc += 1;
    }

    /** isSunk() method checks if every cell in the boat is hit
     */
    public boolean isSunk() {
        for (int i = 0 ; i < boatLength; i++) {
            if (boatArray[i].getStatus() != 'H') {
                return false;
            }
        }
        return true;
    } // end isSunk()
} // end class Boat
