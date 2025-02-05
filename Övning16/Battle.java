

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Battle {
    private static final int LEVEL = 50;  
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Start a PvP battle between two Pokémon. Each player chooses moves manually.
     */
    public static void startBattle(Pokemon p1, Pokemon p2) {
        System.out.println("=== BATTLE START ===");
        System.out.println(p1.getName() + " VS " + p2.getName());

        // Decide who goes first
        boolean isPlayer1Turn = (p1.getSpeed() >= p2.getSpeed());

        while (p1.getHp() > 0 && p2.getHp() > 0) {
            Pokemon attacker = isPlayer1Turn ? p1 : p2;
            Pokemon defender = isPlayer1Turn ? p2 : p1;

            // Let the current player pick a move
            Move chosenMove = selectMove(attacker);
            System.out.println(attacker.getName() + " uses " + chosenMove.getName() + "!");

            // Check accuracy
            int hitRoll = new Random().nextInt(100) + 1;
            if (hitRoll <= chosenMove.getAccuracy()) {
                // Calculate damage
                int damage = calculateDamage(attacker, defender, chosenMove);
                defender.reduceHp(damage);
                System.out.println(defender.getName() + " took " + damage + " damage! (HP left: " + defender.getHp() + ")");
            } else {
                System.out.println("It missed!");
            }

            // Check if defender fainted
            if (defender.getHp() <= 0) {
                System.out.println(defender.getName() + " fainted!");
                break;
            }

            // Swap turns
            isPlayer1Turn = !isPlayer1Turn;
        }

        // Announce winner
        if (p1.getHp() <= 0 && p2.getHp() <= 0) {
            System.out.println("It's a tie!");
        } else if (p1.getHp() <= 0) {
            System.out.println(p2.getName() + " wins!");
        } else {
            System.out.println(p1.getName() + " wins!");
        }
        System.out.println("=== BATTLE END ===");
    }

    /**
     * Let the player choose a move from the list.
     */
    private static Move selectMove(Pokemon pokemon) {
        List<Move> moves = pokemon.getMoves();
        if (moves.isEmpty()) {
            System.out.println(pokemon.getName() + " has no moves! Using Struggle...");
            return new Move("Struggle", 50, 100);
        }

        while (true) {
            System.out.println("\n" + pokemon.getName() + "'s turn! Choose a move:");
            for (int i = 0; i < moves.size(); i++) {
                System.out.println((i + 1) + ". " + moves.get(i).getName() 
                                   + " (Power: " + moves.get(i).getPower()
                                   + ", Accuracy: " + moves.get(i).getAccuracy() + ")");
            }
            System.out.print("Enter the number of the move: ");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= moves.size()) {
                    return moves.get(choice - 1);
                }
            } catch (NumberFormatException ignored) {}
            System.out.println("Invalid choice. Try again.");
        }
    }

    /**
     * A simplified damage formula (no type matching).
     */
    private static int calculateDamage(Pokemon attacker, Pokemon defender, Move move) {
        int power = move.getPower();
        if (power <= 0) return 0;

        // Basic formula: damage = ((2 * level / 5 + 2) * (Atk * Power / Def)) / 50 + 2
        double numerator = ((2.0 * LEVEL) / 5.0 + 2) * attacker.getAttack() * power / defender.getDefense();
        int damage = (int) (numerator / 50.0) + 2;

        return Math.max(damage, 1);
    }
}
