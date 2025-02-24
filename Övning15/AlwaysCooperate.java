public class AlwaysCooperate implements Prisoner {
    @Override
    public boolean decide(boolean[] opponentHistory) {
        return true; // Always cooperate
    }

    @Override
    public String getName() {
        return "Always Cooperate";
    }
}

