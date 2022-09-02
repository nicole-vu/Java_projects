// Names: Nicole Vu, Gina Yi
// x500s: vu000166, yi000058

import java.util.Random;
import java.util.Scanner;

public class MyMaze{
    // Declare variables
    Cell[][] maze;
    int startRow;
    int endRow;
    int rows, cols;

    // Constructor
    public MyMaze(int rows, int cols, int startRow, int endRow) {
        maze = new Cell[rows][cols];

        // create a new cell object for each index
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                maze[i][j] = new Cell();

        // Initialize variables
        this.startRow = startRow;
        this.endRow = endRow;
        this.rows = rows;
        this.cols = cols;
    }

    /* TODO: Create a new maze using the algorithm found in the writeup. */
    public static MyMaze makeMaze(int level) {
        Random rand = new Random();
        MyMaze mazeObj;
        int startRow, endRow;
        // Level 1: 5x5
        if (level == 1){
            startRow = rand.nextInt(5);
            endRow = rand.nextInt(5);
            mazeObj = new MyMaze(5, 5, startRow, endRow);
        }
        // Level 2: 5x20
        else if(level == 2) {
            startRow = rand.nextInt(5);
            endRow = rand.nextInt(5);
            mazeObj = new MyMaze(5,20, startRow, endRow);
        }
        // Level 3: 20x20
        else {
            startRow = rand.nextInt(20);
            endRow = rand.nextInt(20);
            mazeObj = new MyMaze(20,20, startRow, endRow);
        }


        // Generate maze
        // Create stack of cells, mark entrance as visited
        Stack1Gen<int[]> stack = new Stack1Gen<>();
        stack.push(new int[] {startRow,0});
        while(!stack.isEmpty()) {
            int[] current = stack.top();
            int[] neighbor = null;

            //checks to see if all direction the current index can go is visited or out of bounds.
            if(isDead(mazeObj,current[0]-1, current[1]) &&
                isDead(mazeObj, current[0]+1, current[1]) &&
                isDead(mazeObj, current[0], current[1]+1) &&
                isDead(mazeObj, current[0], current[1]-1)) {
                stack.pop();
                continue;
            }

            //randomly assigns the direction to get the next neighbor and assigns next neighbor value
            int direction;
            do {
                direction = rand.nextInt(4);
                if (direction == 0 &&
                    !isDead(mazeObj, current[0] - 1, current[1])) { //next direction is up
                    neighbor = new int[]{current[0] - 1, current[1]};
                }
                else if (direction == 1 &&
                        !isDead(mazeObj, current[0], current[1] + 1)) { //next direction is right
                    neighbor = new int[]{current[0], current[1] + 1};
                }
                else if (direction == 2 &&
                        !isDead(mazeObj, current[0] + 1, current[1])) { //next direction is down
                    neighbor = new int[]{current[0] + 1, current[1]};
                }
                else if (direction == 3 &&
                        !isDead(mazeObj, current[0], current[1] - 1)) { //next direction is left
                    neighbor = new int[]{current[0], current[1] - 1};
                }
            }
            while (neighbor == null);

            stack.push(neighbor);
            mazeObj.maze[neighbor[0]][neighbor[1]].setVisited(true);

            //Remove wall between current and neighbor cell
            if (direction == 0){
                mazeObj.maze[neighbor[0]][neighbor[1]].setBottom(false);
            }
            else if (direction == 1){
                mazeObj.maze[current[0]][current[1]].setRight(false);
            }
            else if (direction == 2){
                mazeObj.maze[current[0]][current[1]].setBottom(false);
            }
            else if (direction == 3){
                mazeObj.maze[neighbor[0]][neighbor[1]].setRight(false);
            }
        }

        //sets all the cells to be unvisited
        for (int i = 0; i < mazeObj.maze.length; i++) {
            for (int j = 0; j < mazeObj.maze[0].length; j++) {
                mazeObj.maze[i][j].setVisited(false);
            }
        }
        return mazeObj;
    }

    //checks to see if a cell is out of bounds or already visited
    private static boolean isDead(MyMaze mazeObj, int row, int col){
        if (row < 0 ||
            row >= mazeObj.maze.length ||
            col < 0 ||
            col>= mazeObj.maze[0].length||
            mazeObj.maze[row][col].getVisited())
            return true;
        return false;
    }

    /* TODO: Print a representation of the maze to the terminal */
    public void printMaze() {
        for (int i = 0; i < rows; i++) {
            // Print topmost walls
            if (i == 0) {
                System.out.print("|");
                for (int j = 0; j < cols; j++)
                    System.out.print("---|");
                System.out.println();
            }

            // Print leftmost wall, except entrance
            if (i != startRow)
                System.out.print("|");
            else
                System.out.print(" ");

            // Print paths and right walls, except exit
            for (int j = 0; j < cols; j++) {
                // Print visited or not visited paths
                if (maze[i][j].getVisited() == true)
                    System.out.print(" * ");
                else
                    System.out.print("   ");
                // Print right walls or right paths, except exit
                if ((i == endRow) && (j == cols - 1))
                    System.out.print(" ");
                else if (maze[i][j].getRight() == true)
                    System.out.print("|");
                else
                    System.out.print(" ");
            }
            System.out.println();

            // Print bottom walls
            System.out.print("|");
            for (int j = 0; j < cols; j++){
                if (maze[i][j].getBottom() == true)
                    System.out.print("---|");
                else
                    System.out.print("   |");
            }
            System.out.println();
        }
    }

    /* TODO: Solve the maze using the algorithm found in the writeup. */
    public void solveMaze() {
        Q1Gen<int[]> queue = new Q1Gen<int[]>();
        queue.add(new int[] {startRow,0});
        int[] current;

        while (queue.length() != 0) {
            current = queue.remove();
            maze[current[0]][current[1]].setVisited(true);
            // Break out of loop if it is the exit
            if (current[0] == endRow && current[1] == cols-1)
                break;
            else {
                // Up direction
                if (current[0]-1 >= 0 && maze[current[0]-1][current[1]].getVisited() == false && !maze[current[0]-1][current[1]].getBottom())
                    queue.add(new int[]{current[0]-1, current[1]});
                // Right direction
                if (current[1]+1 < cols && maze[current[0]][current[1]+1].getVisited() == false && !maze[current[0]][current[1]].getRight())
                    queue.add(new int[]{current[0], current[1]+1});
                // Down direction
                if (current[0]+1 < rows && maze[current[0]+1][current[1]].getVisited() == false && !maze[current[0]][current[1]].getBottom())
                    queue.add(new int[]{current[0]+1, current[1]});
                // Left direction
                if (current[1]-1 >= 0 && maze[current[0]][current[1]-1].getVisited() == false && !maze[current[0]][current[1]-1].getRight())
                    queue.add(new int[]{current[0], current[1]-1});
            }
        }
        printMaze();
    }

    public static void main(String[] args){
        /* Use scanner to get user input for maze level, then make and solve maze */
        // Ask user the level of maze they want to play
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the level you want to play (1/2/3): ");
        int lvl = s.nextInt();
        // Prompt user for valid input
        while (lvl != 1 && lvl != 2 && lvl != 3) {
            System.out.println("Invalid input. Please enter 1,2 or 3.");
            lvl = s.nextInt();
        }
        // Create maze accordingly to level input
        MyMaze mz = makeMaze(lvl);
        System.out.println("Unsolved maze");
        mz.printMaze();
        System.out.println("Solved maze");
        mz.solveMaze();
    }

}
