/**
 * Written by Nicole Vu - vu000166 and Gina Yi - yi000058
 * Game class has main function to run the Battle Boat game
 */

import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Intro and mode menu
        System.out.println("\nWELCOME TO BATTLE BOAT!\n" +
                "-----------------------------\n" +
                "Game modes:\n" +
                "- beginner\n" +
                "- intermediate\n" +
                "- expert\n" +
                "-----------------------------");

        // Prompt user for game mode, keep asking till having "beginner", "intermediate" or "expert"
        System.out.println("Which mode do you want to choose? ");
        String mode = input.nextLine();
        while (!(mode.equalsIgnoreCase("beginner")
                || mode.equalsIgnoreCase("intermediate")
                || mode.equalsIgnoreCase("expert"))) {
            System.out.println("We don't have \"" + mode + "\" mode.\n" +
                    "Please choose beginner, intermediate or expert.");
            mode = input.nextLine();
        }
        int num = 0;
        if (mode.equalsIgnoreCase("beginner"))
            num = 1;
        else if (mode.equalsIgnoreCase("intermediate"))
            num = 2;
        else if (mode.equalsIgnoreCase("expert"))
            num = 3;

        // Create the board based on input mode
        Board brd = new Board(num);

        // Debug mode
        System.out.println("Do you want to run debug mode? (y/n) ");
        if (input.nextLine().equalsIgnoreCase("y"))
            brd.setDebugMode();

        // Inform the user the number of boats
        System.out.println("There are " + brd.getNumBoat() + " hidden boat(s) on this map.");
        brd.display();


        while (!brd.checkGameOver()) {
            if (brd.getPowerPoints() == 0) {
                System.out.println("You have 0 power point.");
                System.out.println("Enter your guess (x y)");
            }
            else {
                System.out.println("You have " + brd.getPowerPoints() + " power point(s).");
                System.out.println("Enter your guess (x y) or use a power (missile, drone, submarine)");
            }

            String in = input.next();

            // Using missile
            if (in.equalsIgnoreCase("missile") && brd.getPowerPoints() > 0) {
                System.out.println("Enter x & y value to launch missile: ");
                int mx = Integer.parseInt(input.next());
                int my = Integer.parseInt(input.next());
                while ((mx > (brd.getBoardLength() - 1))
                        || (mx < 0)
                        || (my > (brd.getBoardLength() - 1))
                        || (my < 0)) {
                    System.out.println("Invalid input. Please input number from 0 to " + (brd.getBoardLength()-1));
                    mx = Integer.parseInt(input.next());
                    my = Integer.parseInt(input.next());
                }
                System.out.println(brd.missile(mx, my));
            }

            // Using drone
            else if (in.equalsIgnoreCase("drone") && brd.getPowerPoints() > 0)
                System.out.println(brd.drone());

            // Using submarine
            else if (in.equalsIgnoreCase("submarine") && brd.getPowerPoints() > 0) {
                System.out.println("Enter x and y value to use submarine:");
                int sx = Integer.parseInt(input.next());
                int sy = Integer.parseInt(input.next());
                while ((sx > (brd.getBoardLength() - 1))
                        || (sx < 0)
                        || (sy > (brd.getBoardLength() - 1))
                        || (sy < 0)) {
                    System.out.println("Invalid input. Please input number from 0 to " + (brd.getBoardLength()-1));
                    sx = Integer.parseInt(input.next());
                    sy = Integer.parseInt(input.next());
                }
                if (brd.submarine(sx, sy))
                    System.out.println("Hit!");
                else
                    System.out.println("Miss");
            }

            // Out of power
            else if ((in.equalsIgnoreCase("missile") ||
                    in.equalsIgnoreCase("drone" )||
                    in.equalsIgnoreCase("submarine")) &&
                    brd.getPowerPoints() == 0){
                System.out.println("You cannot use more power.");
            }

            // get input for fire()
            else {
                try {
                    int x = Integer.parseInt(in);
                    int y = Integer.parseInt(input.next());
                    System.out.println(brd.fire(x, y));
                } catch(NumberFormatException e) {
                    System.out.println("Invalid input");
                }
            }
        }
        System.out.println("You win!");
        System.out.println("Number of turns: " + brd.getNumTurns());
    } // end main()
} // end class Game
