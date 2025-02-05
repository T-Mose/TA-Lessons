import java.util.*;

public class Exercise {
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
        System.out.println("Total Value (Basic Summation): " + guild.getTotalValueN2());
        System.out.println("Operations Count (Basic Summation): " + guild.getCounter());

        // Test Level 2: Collector-Level Optimization (O(n))
        guild.resetCounter();
        System.out.println("Total Value (Collector Optimized): " + guild.getTotalValueN());
        System.out.println("Operations Count (Collector Optimized): " + guild.getCounter());

        // Test Level 1: Guild-Level Precomputation (O(1))
        guild.resetCounter();
        System.out.println("Total Value (Guild Optimized): " + guild.getTotalValue1());
        System.out.println("Operations Count (Guild Optimized): " + guild.getCounter());
    }
}

class RoyalGuild {
    private List<Collector> collectors; // All collectors.
    private int counter; // Counter for operations.

    public RoyalGuild() {
        this.collectors = new ArrayList<>();
        this.counter = 0;
    }

    public void addCollector(Collector collector) {
        collectors.add(collector);
    }

    public void addArtifactForCollector(Collector collector, Artifact artifact) {
        collector.addArtifact(artifact);
    }

    public void resetCounter() {
        this.counter = 0;
    }

    public int getCounter() {
        return this.counter;
    }

    /**
     * Level 3: Basic Summation (O(n^2))
     * @return Total value of all artifacts in the guild.
     */
    public int getTotalValueN2() {
        return 0;
    }

    /**
     * Level 2: Collector-Level Optimization (O(n))
     * @return Total value of all artifacts in the guild.
     */
    public int getTotalValueN() {
        return 0;
    }

    /**
     * Level 1: Guild-Level Precomputation (O(1))
     * @return Total value of all artifacts in the guild.
     */
    public int getTotalValue1() {
        return 0;
    }
}

class Collector {
    private String name;
    private List<Artifact> artifacts;

    public Collector(String name) {
        this.name = name;
        this.artifacts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Artifact> getArtifacts() {
        return artifacts;
    }

    public void addArtifact(Artifact artifact) {
        artifacts.add(artifact);
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