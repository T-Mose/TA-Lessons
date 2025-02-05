import java.util.Random;
import java.util.Scanner;

public class RockPaperScissorsGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Write your game logic here

        scanner.close();
    }
    public static Move getUserMove(Scanner scanner) {
        while (true) {
            System.out.println("Enter your move (Rock, Paper, Scissors): ");
            String userInput = scanner.nextLine().trim().toLowerCase();
            // Error manegment for scanner, below write logic
        }
    }

    private static Move getRandomMove(Random random) {
        int randomNumber = random.nextInt(3); // 0, 1, or 2
        // Write logic
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
