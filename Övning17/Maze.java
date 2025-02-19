import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;
import java.util.stream.Collectors;

public class Maze {
    public static Random random = new Random();
    public Cell[][] maze;
    public int size;
    private boolean numberDisplay = true;
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";

    public Maze(int size) {
        this.size = size;
        maze = new Cell[size][size];
        // Create cells
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                maze[i][j] = new Cell(i, j);
            }
        }
        // Set adjacent cells with boundary checks
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i > 0)
                    maze[i][j].addAdjacentCell(maze[i - 1][j], Cell.Direction.NORTH);
                if (i < size - 1)
                    maze[i][j].addAdjacentCell(maze[i + 1][j], Cell.Direction.SOUTH);
                if (j > 0)
                    maze[i][j].addAdjacentCell(maze[i][j - 1], Cell.Direction.WEST);
                if (j < size - 1)
                    maze[i][j].addAdjacentCell(maze[i][j + 1], Cell.Direction.EAST);
            }
        }
    }

    public Cell getCell(int x, int y) {
        return maze[x][y];
    }

    public int getSize() {
        return size;
    }

    public Cell getRandomCell() {
        return getCell(random.nextInt(size), random.nextInt(size));
    }

    public ArrayList<Cell> deadEnds() {
        ArrayList<Cell> deadEnds = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (getCell(i, j).getLinked().size() == 1)
                    deadEnds.add(getCell(i, j));
            }
        }
        return deadEnds;
    }
    
    public void braiding(int fraction) {
        for (Cell cell : deadEnds()) {
            if (random.nextInt(fraction) == 0)
                cell.addLink(cell.getRandomUnlinked());
        }
    }

    ////////// Maze Creation Methods //////////

    public void createMazeBinaryTree() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Cell current = getCell(i, j);
                ArrayList<Cell> adjacent = new ArrayList<>();
                if (current.hasDirection(Cell.Direction.NORTH))
                    adjacent.add(current.getAdjacentCell(Cell.Direction.NORTH));
                if (current.hasDirection(Cell.Direction.EAST))
                    adjacent.add(current.getAdjacentCell(Cell.Direction.EAST));
                if (!adjacent.isEmpty()) {
                    Cell adj = adjacent.get(random.nextInt(adjacent.size()));
                    current.addLink(adj);
                }
            }
        }
    }
    
    public void createMazeSideWinder() {
        for (Cell[] row : maze) {
            ArrayList<Cell> run = new ArrayList<>();
            for (Cell cell : row) {
                run.add(cell);
                boolean shouldClose = !cell.hasDirection(Cell.Direction.EAST)
                        || (cell.hasDirection(Cell.Direction.NORTH) && random.nextInt(2) == 0);
                if (shouldClose) {
                    Cell member = run.get(random.nextInt(run.size()));
                    if (member.hasDirection(Cell.Direction.NORTH)) {
                        member.addLink(member.getAdjacentCell(Cell.Direction.NORTH));
                        run.clear();
                    }
                } else {
                    cell.addLink(cell.getAdjacentCell(Cell.Direction.EAST));
                }
            }
        }
    }
    
    public void createMazeAldousBroder() {
        Cell current = getRandomCell();
        int unvisited = (size * size) - 1;
        while (unvisited > 0) {
            Cell adjacent = current.getRandom();
            if (adjacent.getLinked().isEmpty()) {
                current.addLink(adjacent);
                unvisited--;
            }
            current = adjacent;
        }
    }
    
    public void createMazeWilsons() {
        ArrayList<Cell> unvisited = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                unvisited.add(getCell(i, j));
            }
        }
        // Remove a random cell from unvisited
        unvisited.remove(random.nextInt(unvisited.size()));
        
        while (!unvisited.isEmpty()) {
            Cell cell = unvisited.get(random.nextInt(unvisited.size()));
            ArrayList<Cell> path = new ArrayList<>();
            path.add(cell);
            while (unvisited.contains(cell)) {
                cell = cell.getRandom();
                int pos = path.indexOf(cell);
                if (pos != -1) {
                    path = new ArrayList<>(path.subList(0, pos + 1));
                } else {
                    path.add(cell);
                }
            }
            for (int i = 0; i < path.size() - 1; i++) {
                Cell current = path.get(i);
                Cell next = path.get(i + 1);
                current.addLink(next);
                unvisited.remove(current);
            }
        }
    }
    
    public void createMazeHuntAndKill() {
        Cell current = getCell(random.nextInt(size), random.nextInt(size));
        current.setVisited(true);
        while (true) {
            List<Cell> unvisitedNeighbors = current.getUnvisitedNeighbors();
            if (!unvisitedNeighbors.isEmpty()) {
                Cell neighbor = unvisitedNeighbors.get(random.nextInt(unvisitedNeighbors.size()));
                current.addLink(neighbor);
                neighbor.setVisited(true);
                current = neighbor;
            } else {
                boolean found = false;
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        Cell cell = maze[i][j];
                        if (!cell.isVisited() && !cell.getVisitedNeighbors().isEmpty()) {
                            current = cell;
                            current.setVisited(true);
                            List<Cell> visitedNeighbors = current.getVisitedNeighbors();
                            if (!visitedNeighbors.isEmpty()) {
                                Cell visitedNeighbor = visitedNeighbors.get(random.nextInt(visitedNeighbors.size()));
                                current.addLink(visitedNeighbor);
                                found = true;
                                break;
                            }
                        }
                    }
                    if (found)
                        break;
                }
                if (!found)
                    break;
            }
        }
    }
    
    public void createMazeRecursiveBackTracker() {
        Stack<Cell> stack = new Stack<>();
        stack.push(getRandomCell());
        while (!stack.isEmpty()) {
            Cell current = stack.lastElement();
            List<Cell> neighbors = current.getAdjacentAsList().stream()
                    .filter(n -> n.getLinked().isEmpty())
                    .collect(Collectors.toList());
            if (neighbors.isEmpty())
                stack.pop();
            else {
                Cell neighbor = neighbors.get(random.nextInt(neighbors.size()));
                current.addLink(neighbor);
                stack.push(neighbor);
            }
        }
    }

    @Override
    public String toString() { // Display the maze in ASCII
        StringBuilder output = new StringBuilder();
        String top = "+---";
        for (int i = 0; i < size; i++) {
            output.append(top);
        }
        output.append("+\n");
        
        for (Cell[] row : maze) {
            StringBuilder topBuilder = new StringBuilder("|");
            StringBuilder bottomBuilder = new StringBuilder("+");
            for (Cell cell : row) {
                String eastBoundary = cell.isLinked(cell.getAdjacentCell(Cell.Direction.EAST)) ? " " : "|";
                String southBoundary = cell.isLinked(cell.getAdjacentCell(Cell.Direction.SOUTH)) ? "   " : "---";
                if (numberDisplay) {
                    String cellText = cell.isOptimalPath()
                            ? ANSI_GREEN + String.format("%3d", cell.getIntDistance()) + ANSI_RESET
                            : String.format("%3d", cell.getIntDistance());
                    topBuilder.append(cellText).append(eastBoundary);
                } else {
                    topBuilder.append("   ").append(eastBoundary);
                }
                bottomBuilder.append(southBoundary).append("+");
            }
            output.append(topBuilder).append("\n").append(bottomBuilder).append("\n");
        }
        return output.toString();
    }
}
