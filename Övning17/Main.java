public class Main {

    public static void main(String[] args) {
        Maze maze = new Maze(30);
        maze.createMazeRecursiveBackTracker();
        maze.braiding(1);

        // Stage 1: Determine endpoints for the longest route.
        Cell[] endpoints = MazeSolver.findLongestEndpoints(maze);
        Cell start = endpoints[0];
        Cell end = endpoints[1];

        // Now, independently solve using different methods.
        // For each, reset the maze, set start/end, and time the solver.

        // Student algorithm
        MazeSolver.resetCells(maze);
        long t1 = System.nanoTime();
        MazeSolver.solveStudentAlgorithm(maze, start, end); // TODO
        long t2 = System.nanoTime();
        System.out.println("Student algorithm took " + (t2 - t1) / 1e6 + " ms and " 
                + MazeSolver.countSolutionSteps(start, end) + " steps");
        // Optimal algorithm
        MazeSolver.resetCells(maze);
        t1 = System.nanoTime();
        MazeSolver.solveDijkstra(maze, start, end);
        t2 = System.nanoTime();
        System.out.println("Dijkstra took " + (t2 - t1) / 1e6 + " ms and " 
                + MazeSolver.countSolutionSteps(start, end) + " steps");

        // Display the maze (with optimal path marked)
        System.out.println(maze.toString());
        System.out.println("Number of dead ends: " + maze.deadEnds().size());

    }
}