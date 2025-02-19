import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;

public class Cell {
    public enum Direction { 
        NORTH, SOUTH, WEST, EAST
    }

    private Map<Direction, Cell> adjacent = new EnumMap<>(Direction.class);
    private ArrayList<Cell> linked = new ArrayList<>();
    private int x;
    private int y;
    private int distance = Integer.MAX_VALUE; // For maze solving
    private boolean optimalPath = false;      // For visuals
    private Cell predecessor = null;          // For visuals
    private boolean visited = false;          // For generation algorithms

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void addAdjacentCell(Cell cell, Direction direction) {
        adjacent.put(direction, cell);
    }

    public Cell getAdjacentCell(Direction direction) {
        return adjacent.get(direction);
    }

    public boolean hasDirection(Direction direction) {
        return adjacent.containsKey(direction);
    }

    public Map<Direction, Cell> getAllAdjacentCells() {
        return adjacent;
    }

    public ArrayList<Cell> getAdjacentAsList() {
        ArrayList<Cell> adjacentList = new ArrayList<>();
        for (Cell cell : adjacent.values()) {
            if (cell != null) {
                adjacentList.add(cell);
            }
        }
        return adjacentList;
    }

    public void setPredecessor(Cell cell) {
        predecessor = cell;
    }

    public int getIntDistance() {
        return distance;
    }

    public void setOptimalPath(boolean value) {
        optimalPath = value;
    }

    public boolean isOptimalPath() {
        return optimalPath;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public ArrayList<Cell> getUnvisitedNeighbors() {
        ArrayList<Cell> unvisitedNeighbors = new ArrayList<>();
        for (Cell cell : getAdjacentAsList()) {
            if (!cell.isVisited())
                unvisitedNeighbors.add(cell);
        }
        return unvisitedNeighbors;
    }

    public ArrayList<Cell> getVisitedNeighbors() {
        ArrayList<Cell> visitedNeighbors = new ArrayList<>();
        for (Cell neighbor : getAdjacentAsList()) {
            if (neighbor.isVisited()) {
                visitedNeighbors.add(neighbor);
            }
        }
        return visitedNeighbors;
    }

    public void addLink(Cell cell) { // Link the two cells (bidirectionally)
        if (!linked.contains(cell)) {
            linked.add(cell);
            cell.addLink(this);
        }
    }

    public boolean isLinked(Cell cell) {
        return linked.contains(cell);
    }

    public String getName() {
        return x + ":" + y;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public ArrayList<Cell> getLinked() {
        return linked;
    }

    public Cell getPredecessor() {
        return predecessor;
    }

    public Cell getRandom() {
        return getAdjacentAsList().get(Maze.random.nextInt(getAdjacentAsList().size()));
    }

    public Cell getRandomUnlinked() {
        ArrayList<Cell> adjacentList = getAdjacentAsList();
        Cell cell;
        do {
            cell = adjacentList.get(Maze.random.nextInt(adjacentList.size()));
        } while (cell.isLinked(this));
        return cell;
    }

    public void reset() {
        setDistance(Integer.MAX_VALUE);
        setOptimalPath(false);
        setPredecessor(null);
        setVisited(false);
    }
}
