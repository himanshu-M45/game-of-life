package org.example;

import org.example.Exceptions.CellNotFoundException;
import org.example.enums.State;

import java.util.List;

public class GenerateNextGeneration {
    public void evaluateNextGeneration(List<List<Cell>> cellGrid) {
        for (List<Cell> row : cellGrid) {
            for (Cell cell : row) {
                int neighbourCount = getNeighbours(cellGrid, cell);
                if (!cell.isAlive() && neighbourCount == 3) {
                    cell.setState(State.ALIVE);
                }
                if (cell.isAlive() && neighbourCount < 2 || neighbourCount > 3) {
                    cell.setState(State.DEAD);
                }
            }
        }
    }

    private int getNeighbours(List<List<Cell>> cellGrid, Cell cell) {
        int[] coordinates = findCellCoordinates(cellGrid, cell);
        int row = coordinates[0];
        int column = coordinates[1];
        int[] stepsToReachNeighboursRow = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] stepsToReachNeighboursColumn = {-1, 0, 1, -1, 1, -1, 0, 1};
        int count = 0;
        // Check all 8 possible neighbors and count alive ones
        for (int i = 0; i < 8; i++) {
            int newRow = row + stepsToReachNeighboursRow[i];
            int newColumn = column + stepsToReachNeighboursColumn[i];
            if (newRow >= 0 && newRow < cellGrid.size() && newColumn >= 0 && newColumn < cellGrid.get(row).size()) {
                if (cellGrid.get(newRow).get(newColumn).isAlive()) {
                    count++;
                }
            }
        }
        return count;
    }

    private int[] findCellCoordinates(List<List<Cell>> cellGrid, Cell cell) {
        for (int i = 0; i < cellGrid.size(); i++) {
            List<Cell> rowList = cellGrid.get(i);
            for (int j = 0; j < rowList.size(); j++) {
                if (rowList.get(j) == cell) {
                    return new int[]{i, j};
                }
            }
        }
        throw new CellNotFoundException("Cell not found in the grid");
    }
}