package org.example;

import org.example.enums.State;

import java.util.List;

public class GenerateNextGeneration {
    public void evaluateNextGeneration(List<List<Cell>> cellGrid) {
        for (int row = 0; row < cellGrid.size(); row++) {
            for (int column = 0; column < cellGrid.get(row).size(); column++) {
                Cell cell = cellGrid.get(row).get(column); // extract cell from the grid
                int neighbourCount = getNeighboursCount(cellGrid, row, column); // get total alive neighbours count
                if (!cell.isAlive() && neighbourCount == 3) {
                    cell.setState(State.ALIVE);
                }
                if (cell.isAlive() && neighbourCount < 2 || neighbourCount > 3) {
                    cell.setState(State.DEAD);
                }
            }
        }
    }

    private int getNeighboursCount(List<List<Cell>> cellGrid, int row, int column) {
        int[] stepsToReachNeighbourRow = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] stepsToReachNeighbourColumn = {-1, 0, 1, -1, 1, -1, 0, 1};
        int count = 0;
        // Check all 8 possible neighbors and count alive ones
        for (int i = 0; i < 8; i++) {
            int newRow = row + stepsToReachNeighbourRow[i];
            int newColumn = column + stepsToReachNeighbourColumn[i];
            if (newRow >= 0 && newRow < cellGrid.size() && newColumn >= 0 && newColumn < cellGrid.get(row).size()) {
                if (cellGrid.get(newRow).get(newColumn).isAlive()) {
                    count++;
                }
            }
        }
        return count;
    }
}