public class StepTracker {

    private int dailyGoal;
    private int totalSteps;

    public StepTracker(int dailyGoal) {
        this.dailyGoal = dailyGoal;
        this.totalSteps = 0;
    }

    public void addSteps(int steps) {
        totalSteps += steps;
    }

    public void addStepsForMultipleDays(int days, int stepsPerDay) {
        for (int i = 0; i < days; i++) {
            addSteps(stepsPerDay);
        }
    }

    public boolean isGoalMet(int steps) {
        return steps >= dailyGoal;
    }

    public void printStepSummary(int steps) {
        System.out.println("Steps today: " + steps);
        if (isGoalMet(steps)) {
            System.out.println("Great job! You met your daily step goal of " + dailyGoal + " steps.");
        } else {
            System.out.println("Keep going! You need " + (dailyGoal - steps) + " more steps to reach your goal.");
        }
        System.out.println("Total steps: " + totalSteps);
    }

    public static void main(String[] args) {
        StepTracker tracker = new StepTracker(10000);
        
        tracker.addSteps(7500);
        tracker.printStepSummary(7500);
        
        System.out.println("\nAdding steps for multiple days...");
        tracker.addStepsForMultipleDays(5, 8000);
        tracker.printStepSummary(8000);
    }
}
