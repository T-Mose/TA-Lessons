import java.util.Random;
import java.util.Scanner;

public class RockPaperScissorsGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        System.out.println("Welcome to Rock, Paper, Scissors!");

        while (true) {
            // Get the user's move
            Move userMove = getUserMove(scanner);
            
            // Generate a random move for the computer
            Move computerMove = getRandomMove(random);
            System.out.println("Computer chose: " + computerMove.getMoveName());

            // Determine the result
            if (userMove.beats(computerMove)) {
                System.out.println("You win! " + userMove.getMoveName() + " beats " + computerMove.getMoveName());
                break; // Exit the loop if the user wins
            } else if (computerMove.beats(userMove)) {
                System.out.println("You lose! " + computerMove.getMoveName() + " beats " + userMove.getMoveName());
                break; // Exit the loop if the computer wins
            } else {
                System.out.println("It's a draw! Both chose " + userMove.getMoveName());
                System.out.println("Let's play again!");
            }
        }

        System.out.println("Thanks for playing!");
        scanner.close();
    }

    public static Move getUserMove(Scanner scanner) {
        while (true) {
            System.out.println("Enter your move (Rock, Paper, Scissors): ");
            String userInput = scanner.nextLine().trim().toLowerCase();

            switch (userInput) {
                case "rock":
                    return new Rock();
                case "paper":
                    return new Paper();
                case "scissors":
                    return new Scissors();
                default:
                    System.out.println("Invalid input. Please enter 'Rock', 'Paper', or 'Scissors'.");
            }
        }
    }

    private static Move getRandomMove(Random random) {
        int randomNumber = random.nextInt(3); // 0, 1, or 2
        switch (randomNumber) {
            case 0: return new Rock();
            case 1: return new Paper();
            default: return new Scissors();
        }
    }
}

abstract class Move {
    public abstract boolean beats(Move other);
    public abstract String getMoveName();
}

class Rock extends Move {
    public boolean beats(Move other) {
        return other instanceof Scissors;
    }
    public String getMoveName() {
        return "Rock";
    }
}

class Paper extends Move {
    public boolean beats(Move other) {
        return other instanceof Rock;
    }
    public String getMoveName() {
        return "Paper";
    }
}

class Scissors extends Move {
    public boolean beats(Move other) {
        return other instanceof Paper;
    }
    public String getMoveName() {
        return "Scissors";
    }
}
