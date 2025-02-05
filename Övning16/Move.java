
public class Move {
    private String name;
    private int power;
    private int accuracy;

    public Move(String name, int power, int accuracy) {
        this.name = name;
        this.power = power;
        this.accuracy = accuracy;
    }

    public String getName() { return name; }
    public int getPower() { return power; }
    public int getAccuracy() { return accuracy; }
}
