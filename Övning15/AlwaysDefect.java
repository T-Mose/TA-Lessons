public class AlwaysDefect implements Prisoner {
    @Override
    public boolean decide(boolean[] opponentHistory) {
        return false; // Always defect
    }

    @Override
    public String getName() {
        return "Always Defect";
    }
}