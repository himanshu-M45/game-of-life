package org.example.Entities;

public class Location {
    private final int row;
    private final int col;
    private Cell[] neighbours;

    public Location(int row, int col) {
        this.row = row;
        this.col = col;
        this.neighbours = new Cell[8];
    }

    public Cell[] getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(Cell[] neighbours) {
        this.neighbours = neighbours;
    }

    public boolean isSameLocation(int row, int col) {
        return this.row == row && this.col == col;
    }
}
