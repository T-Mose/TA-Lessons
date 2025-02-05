import java.util.*;
// Solution
public class Solution {
    public static void main(String[] args) {
        // Create the Royal Guild
        RoyalGuild guild = new RoyalGuild();

        // Create collectors
        Collector collector1 = new Collector("Alice");
        Collector collector2 = new Collector("Bob");
        Collector collector3 = new Collector("Charlie");

        // Add collectors to the guild
        guild.addCollector(collector1);
        guild.addCollector(collector2);
        guild.addCollector(collector3);

        // Add artifacts to collectors
        guild.addArtifactForCollector(collector1, new Artifact("Golden Chalice", 500));
        guild.addArtifactForCollector(collector1, new Artifact("Ancient Coin", 300));
        guild.addArtifactForCollector(collector1, new Artifact("Ancient Coin", 800));
        guild.addArtifactForCollector(collector2, new Artifact("Emerald Necklace", 800));
        guild.addArtifactForCollector(collector2, new Artifact("Ruby Ring", 400));
        guild.addArtifactForCollector(collector3, new Artifact("Silver Sword", 600));
        guild.addArtifactForCollector(collector3, new Artifact("Diamond Crown", 1200));
        guild.addArtifactForCollector(collector3, new Artifact("Diamond Crown", 2200));

        // Test Level 3: Basic Summation (O(n^2))
        guild.resetCounter();
        System.out.println("Total Value (Basic Summation): " + guild.getTotalValueBasic());
        System.out.println("Operations Count (Basic Summation): " + guild.getCounter());

        // Test Level 2: Collector-Level Optimization (O(n))
        guild.resetCounter();
        System.out.println("Total Value (Collector Optimized): " + guild.getTotalValueCollectorOptimized());
        System.out.println("Operations Count (Collector Optimized): " + guild.getCounter());

        // Test Level 1: Guild-Level Precomputation (O(1))
        guild.resetCounter();
        System.out.println("Total Value (Guild Optimized): " + guild.getTotalValueGuildOptimized());
        System.out.println("Operations Count (Guild Optimized): " + guild.getCounter());

    }
    
}

class RoyalGuild {
    private List<Collector> collectors; // All collectors.
    private int totalArtifactValue; // Precomputed total value.
    private int counter; // Counter for operations.

    public RoyalGuild() {
        this.collectors = new ArrayList<>();
        this.totalArtifactValue = 0;
        this.counter = 0;
    }

    public void addCollector(Collector collector) {
        collectors.add(collector);
        // Precompute the total value for existing artifacts in the guild.
        totalArtifactValue += collector.getArtifactTotalValue();
    }

    public void addArtifactForCollector(Collector collector, Artifact artifact) {
        collector.addArtifact(artifact);
        totalArtifactValue += artifact.getValue(); // Update guild-level total.
    }

    public void resetCounter() {
        this.counter = 0;
    }

    public int getCounter() {
        return this.counter;
    }

    // Level 3: Basic Summation (O(n^2))
    public int getTotalValueBasic() {
        int total = 0;
        for (Collector collector : collectors) {
            counter++; // Count operation for outer loop
            for (Artifact artifact : collector.getArtifacts()) {
                counter++; // Count operation for inner loop
                total += artifact.getValue(); // Count operation for addition
                counter++; // Count operation for addition
            }
        }
        return total;
    }

    // Level 2: Collector-Level Optimization (O(n))
    public int getTotalValueCollectorOptimized() {
        int total = 0;
        for (Collector collector : collectors) {
            counter++; // Count operation for loop
            total += collector.getArtifactTotalValue(); // Count operation for addition
            counter++; // Count operation for addition
        }
        return total;
    }

    // Level 1: Guild-Level Precomputation (O(1))
    public int getTotalValueGuildOptimized() {
        counter++; // Count operation for returning precomputed value
        return totalArtifactValue; // Return precomputed guild total.
    }
}

class Collector {
    private String name;
    private List<Artifact> artifacts;
    private int artifactTotalValue; // Precomputed total for artifacts.

    public Collector(String name) {
        this.name = name;
        this.artifacts = new ArrayList<>();
        this.artifactTotalValue = 0;
    }

    public String getName() {
        return name;
    }

    public List<Artifact> getArtifacts() {
        return artifacts;
    }

    public int getArtifactTotalValue() {
        return artifactTotalValue;
    }

    public void addArtifact(Artifact artifact) {
        artifacts.add(artifact);
        artifactTotalValue += artifact.getValue(); // Increment total value.
    }
}

class Artifact {
    private String name;
    private int value;

    public Artifact(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}