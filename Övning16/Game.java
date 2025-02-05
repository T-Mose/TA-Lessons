
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter first Pokemon's name:");
        String name1 = scanner.nextLine();

        System.out.println("Enter second Pokemon's name:");
        String name2 = scanner.nextLine();

        Pokemon p1 = PokemonAPI.fetchPokemon(name1);
        Pokemon p2 = PokemonAPI.fetchPokemon(name2);

        // If either fetch fails, fallback
        if (p1 == null) {
            System.out.println("Fallback to default Pikachu for " + name1);
            p1 = createDefaultPokemon("Pikachu");
        }
        if (p2 == null) {
            System.out.println("Fallback to default Bulbasaur for " + name2);
            p2 = createDefaultPokemon("Bulbasaur");
        }

        // Start the PvP battle
        Battle.startBattle(p1, p2);
    }

    private static Pokemon createDefaultPokemon(String name) {
        int hp, attack, defense, speed;
        List<Move> moves = new ArrayList<>();

        if ("Pikachu".equalsIgnoreCase(name)) {
            hp = 35; attack = 55; defense = 40; speed = 90;
            moves.add(new Move("Thunder Shock", 40, 100));
            moves.add(new Move("Quick Attack", 40, 100));
            moves.add(new Move("Tail Whip", 0, 100));
        } else if ("Bulbasaur".equalsIgnoreCase(name)) {
            hp = 45; attack = 49; defense = 49; speed = 45;
            moves.add(new Move("Vine Whip", 45, 100));
            moves.add(new Move("Tackle", 40, 95));
            moves.add(new Move("Growl", 0, 100));
        } else {
            // Generic fallback
            hp = 50; attack = 50; defense = 50; speed = 50;
            moves.add(new Move("Tackle", 40, 95));
            moves.add(new Move("Scratch", 40, 100));
        }
        return new Pokemon(name, hp, attack, defense, speed, moves);
    }
}
