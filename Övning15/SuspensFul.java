import java.util.*;

public class SuspensFul {
    private static final int AVERAGE_ROUNDS = 200;

    public static void main(String[] args) throws Exception {
        List<Class<? extends Prisoner>> prisonerClasses = loadPrisoners();
        Map<Class<? extends Prisoner>, Integer> scores = new HashMap<>();

        // Initialize scores
        for (Class<? extends Prisoner> cls : prisonerClasses) {
            scores.put(cls, 0);
        }

        // Run simulations
        for (int i = 0; i < prisonerClasses.size(); i++) {
            for (int j = i + 1; j < prisonerClasses.size(); j++) {
                Class<? extends Prisoner> clsA = prisonerClasses.get(i);
                Class<? extends Prisoner> clsB = prisonerClasses.get(j);

                // Announce match
                System.out.printf("Preparing to simulate match: %s vs. %s...%n",
                        clsA.getSimpleName(), clsB.getSimpleName());
                Thread.sleep(500);

                int[] result = simulateMatch(clsA, clsB);

                // Display result for this fight
                System.out.printf("Match finished: %s vs. %s -> %d : %d%n%n",
                        clsA.getSimpleName(), clsB.getSimpleName(), result[0], result[1]);
                Thread.sleep(2000);

                // Update total scores
                scores.put(clsA, scores.get(clsA) + result[0]);
                scores.put(clsB, scores.get(clsB) + result[1]);
            }
        }

        // Sort and display the final leaderboard
        scores.entrySet().stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .forEach(entry -> System.out.printf("%s: %d points%n",
                        entry.getKey().getSimpleName(), entry.getValue()));
    }

    private static int[] simulateMatch(Class<? extends Prisoner> clsA, Class<? extends Prisoner> clsB)
            throws Exception {
        Prisoner prisonerA = clsA.getDeclaredConstructor().newInstance();
        Prisoner prisonerB = clsB.getDeclaredConstructor().newInstance();

        List<Boolean> historyA = new ArrayList<>();
        List<Boolean> historyB = new ArrayList<>();

        int rounds = AVERAGE_ROUNDS + (int) (Math.random() * 10); // 200 + random(0-9)
        int scoreA = 0;
        int scoreB = 0;

        for (int round = 0; round < rounds; round++) {
            boolean moveA = prisonerA.decide(toBooleanArray(historyB));
            boolean moveB = prisonerB.decide(toBooleanArray(historyA));

            historyA.add(moveA);
            historyB.add(moveB);

            // Scoring rules
            if (moveA && moveB) { // Both cooperate
                scoreA += 3;
                scoreB += 3;
            } else if (moveA) { // A cooperates, B defects
                scoreB += 5;
            } else if (moveB) { // B cooperates, A defects
                scoreA += 5;
            } else { // Both defect
                scoreA += 1;
                scoreB += 1;
            }
        }
        return new int[] { scoreA, scoreB };
    }

    private static boolean[] toBooleanArray(List<Boolean> list) {
        boolean[] array = new boolean[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    private static List<Class<? extends Prisoner>> loadPrisoners() throws Exception {
        // Manually list student classes
        return List.of(
            // Add student Prisoner class names here, e.g., MyPrisoner.class
            RandomPrisoner.class,
            TAStrategy.class,
            pym.class,
            OGTrustOnce.class,
            JsJ.class,
            GPS.class,
            KarenHannahLinnOliver.class
        );
    }
}

