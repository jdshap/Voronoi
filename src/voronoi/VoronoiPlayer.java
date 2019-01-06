package voronoi;

import java.util.Scanner;

/**
 * Plays the VoronoiGame in a text-based format. 
 * 
 * DO NOT MODIFY THIS FILE.
 * 
 * @author Drue Coles
 */
public class VoronoiPlayer {

    public static void main(String[] args) {

        // Prompt the user for the size of the grid and create the game engine.
        Scanner in = new Scanner(System.in);
        System.out.print("Grid size (rows columns): ");
        int r = in.nextInt();
        int c = in.nextInt();
        VoronoiGame game = new VoronoiGame(r, c);

        // Players will alternate until all seeds are placed.
        System.out.print("Number of seeds per player: ");
        int n = in.nextInt();

        System.out.println(game);

        for (int i = 0; i < n; i++) {
            move(game, "Seed location for RED: ", VoronoiGame.RED_SEED);
            move(game, "Seed location for BLACK: ", VoronoiGame.BLACK_SEED);
        }
        
        int diff = game.getRedCount() - game.getBlackCount();
        if (diff > 0) {
            System.out.println("RED wins!");
        } else if (diff < 0) {
            System.out.println("BLACK wins!");
        } else {
            System.out.println("TIE GAME!");
        }
    }

    /**
     * Prompts the player for input, checks validity, and repeats the prompt
     * in case of invalid input. For valid input, the updated state of the
     * game is written to the terminal window.
     * 
     * @param game the game engine
     * @param prompt text to display for requesting input from the player
     * @param color the color of the seed to be placed in the grid
     */
    private static void move(VoronoiGame game, String prompt, char color) {
        while (true) {
            
            // Request input and check for validity.
            System.out.print(prompt);
            Scanner in = new Scanner(System.in);
            String location = in.next();
            
            // The setSeed method returns true if the move was valid,
            // false otherwise.
            boolean success = game.setSeed(location, color);
            
            // If the input was valid, display the current state of the game.
            if (success) {
                System.out.println(game);
                System.out.println("Red: " + game.getRedCount());
                System.out.println("Black: " + game.getBlackCount() + "\n");
                return;
            }
            System.out.println("Invalid input. Try again.");
        }
    }
}