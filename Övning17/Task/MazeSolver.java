import java.util.Comparator;
import java.util.PriorityQueue;

public class MazeSolver {

    // === ORIGINAL SOLVER LOGIC (Dijkstra's Algorithm) ===
    public static void solveDijkstra(Maze maze, Cell start, Cell end) {
        start.setDistance(0);
        PriorityQueue<Cell> frontier = new PriorityQueue<>(Comparator.comparingInt(Cell::getIntDistance));
        frontier.add(start);

        while (!frontier.isEmpty()) {
            Cell current = frontier.poll();
            for (Cell linkedCell : current.getLinked()) {
                int newDist = current.getIntDistance() + 1;
                if (newDist < linkedCell.getIntDistance()) {
                    linkedCell.setDistance(newDist);
                    linkedCell.setPredecessor(current);
                    frontier.add(linkedCell);
                }
            }
        }
        // Mark the path from 'end' back to 'start'
        Cell current = end;
        while (current != null) {
            current.setOptimalPath(true);
            current = current.getPredecessor();
        }
    }
    public static void solveStudentAlgorithm(Maze maze, Cell start, Cell end) {
        // TODO
    }

    // === ENDPOINT DETERMINATION ===
    // This method determines the endpoints for the longest route in the maze.
    // It performs two Dijkstra passes:
    // 1. From (0,0) to bottom-right, then finds the farthest cell from (0,0) → newStart.
    // 2. From newStart to bottom-right, then finds the farthest cell from newStart → newEnd.
    // It returns a Cell array: [newStart, newEnd].
    public static Cell[] findLongestEndpoints(Maze maze) {
        // First pass: from (0,0) to bottom-right.
        Cell initialStart = maze.getCell(0, 0);
        solveDijkstra(maze, initialStart, maze.getCell(maze.getSize() - 1, maze.getSize() - 1));
        Cell newStart = findFarthestCellFrom(maze, initialStart);
        
        resetCells(maze);
        
        // Second pass: from newStart to bottom-right.
        solveDijkstra(maze, newStart, maze.getCell(maze.getSize() - 1, maze.getSize() - 1));
        Cell newEnd = findFarthestCellFrom(maze, newStart);
        
        resetCells(maze);  // Prepare maze for the subsequent solving runs.
        return new Cell[]{ newStart, newEnd };
    }

    // Helper: finds the cell with the greatest distance from 'start' (using current distances)
    private static Cell findFarthestCellFrom(Maze maze, Cell start) {
        Cell farthest = start;
        for (int i = 0; i < maze.getSize(); i++) {
            for (int j = 0; j < maze.getSize(); j++) {
                Cell cell = maze.getCell(i, j);
                if (cell.getIntDistance() > farthest.getIntDistance()) {
                    farthest = cell;
                }
            }
        }
        return farthest;
    }

    // Helper: resets every cell's distance, visited flag, predecessor, and optimalPath.
    public static void resetCells(Maze maze) {
        for (int i = 0; i < maze.getSize(); i++) {
            for (int j = 0; j < maze.getSize(); j++) {
                maze.getCell(i, j).reset();
            }
        }
    }
    
    // --- NEW HELPER METHOD ---
    // This method counts the steps along the marked solution path by following the predecessor chain.
    // (It assumes that a valid solution has been marked and that predecessor pointers have been set.)
    public static int countSolutionSteps(Cell start, Cell end) {
        int count = 0;
        Cell current = end;
        while (current != null) {
            count++;
            current = current.getPredecessor();
        }
        return count;
    }
}
