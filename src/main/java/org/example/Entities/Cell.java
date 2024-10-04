package org.example.Entities;

import org.example.Enum.State;

public class Cell {
    private State state;
    private final Location location;

    public Cell(int row, int col) {
        this.state = State.DEAD;
        this.location = new Location(row, col);
    }

    public void setInitialAlive() {
        this.state = State.ALIVE;
    }

    public void setCellState() {
        int neighbours = countAliveNeighbours();
        if (this.state == State.DEAD && neighbours == 3) {
            this.state = State.ALIVE;
        }
        if (this.state == State.ALIVE && (neighbours < 2 || neighbours > 3)) {
            this.state = State.DEAD;
        }
    }

    public boolean isAlive() {
        return this.state == State.ALIVE;
    }

    private int countAliveNeighbours() {
        int count = 0;
        Cell[] neighbours = location.getNeighbours();
        for (Cell cell : neighbours) {
            if (cell != null && cell.state == State.ALIVE) {
                count++;
            }
        }
        return count;
    }

    public Location getLocation() {
        return location;
    }
}
