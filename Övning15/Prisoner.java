public interface Prisoner {
    /**
     * Decide whether to cooperate or defect based on the opponent's history.
     * 
     * @param opponentHistory A boolean array where true represents cooperation,
     *                        and false represents defection.
     * @return true to cooperate, false to defect.
     */
    boolean decide(boolean[] opponentHistory);
}
