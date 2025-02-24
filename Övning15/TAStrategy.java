public class TAStrategy implements Prisoner {

    @Override
    public boolean decide(boolean[] opponentHistory) {
        // If this is the first move, cooperate
        if (opponentHistory.length == 0) {
            return true;
        }
        // Otherwise mirror the opponent's last move
        return opponentHistory[opponentHistory.length - 1];
    }

    @Override
    public String getName() {
        return "Tit for Tat";
    }
}

