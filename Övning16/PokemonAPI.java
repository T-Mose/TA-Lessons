

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PokemonAPI {

    /**
     * Calls the Python script to fetch real stats/moves from PokeAPI.
     * If there's an error, return null.
     */
    public static Pokemon fetchPokemon(String pokemonName) {
        try {
            // 1. Build the process to run fetch_pokemon.py
            ProcessBuilder pb = new ProcessBuilder("python3", "fetch_pokemon.py", pokemonName);
            pb.redirectErrorStream(true); // merge stderr into stdout
            Process process = pb.start();

            // 2. Read lines from the script
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            
            String name = null;
            int hp = 50, attack = 50, defense = 50, speed = 50;
            List<Move> moves = new ArrayList<>();

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("ERROR")) {
                    // The script signaled an error
                    System.out.println("Python script error: " + line);
                    process.destroy();
                    return null;
                }
                if (line.equals("DONE")) {
                    // No more data
                    break;
                }
                // parse each line
                // e.g. "NAME|Ditto", "HP|48", "MOVE|Pound|40|100"
                String[] parts = line.split("\\|");
                if (parts.length < 2) continue; // ignore bad lines

                switch (parts[0]) {
                    case "NAME":
                        name = parts[1];
                        break;
                    case "HP":
                        hp = parseIntSafe(parts[1], 50);
                        break;
                    case "ATTACK":
                        attack = parseIntSafe(parts[1], 50);
                        break;
                    case "DEFENSE":
                        defense = parseIntSafe(parts[1], 50);
                        break;
                    case "SPEED":
                        speed = parseIntSafe(parts[1], 50);
                        break;
                    case "MOVE":
                        if (parts.length == 4) {
                            String moveName = parts[1];
                            int power = parseIntSafe(parts[2], 50);
                            int accuracy = parseIntSafe(parts[3], 100);
                            moves.add(new Move(moveName, power, accuracy));
                        }
                        break;
                    default:
                        // ignore unknown lines
                        break;
                }
            }

            process.waitFor();

            if (name == null) {
                // Something went wrong
                return null;
            }

            // 3. Construct the Pokemon object
            return new Pokemon(name, hp, attack, defense, speed, moves);

        } catch (Exception e) {
            System.out.println("Failed to run Python script: " + e.getMessage());
            return null;
        }
    }

    /**
     * Safe integer parsing with a fallback.
     */
    private static int parseIntSafe(String s, int fallback) {
        try {
            return Integer.parseInt(s.trim());
        } catch (NumberFormatException e) {
            return fallback;
        }
    }
}
