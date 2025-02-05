
import java.util.List;

public class Pokemon {
    private String name;
    private int hp;
    private int attack;
    private int defense;
    private int speed;
    private List<Move> moves;

    public Pokemon(String name, int hp, int attack, int defense, int speed, List<Move> moves) {
        this.name = name;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.moves = moves;
    }

    public String getName() { return name; }
    public int getHp() { return hp; }
    public int getAttack() { return attack; }
    public int getDefense() { return defense; }
    public int getSpeed() { return speed; }
    public List<Move> getMoves() { return moves; }

    // reduce HP but not below zero
    public void reduceHp(int amount) {
        this.hp = Math.max(this.hp - amount, 0);
    }
}
