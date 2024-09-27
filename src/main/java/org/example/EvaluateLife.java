package org.example;

import org.example.Exceptions.CellNotFoundException;
import org.example.Exceptions.Neighbours;
import org.example.enums.State;

import java.util.List;

public class EvaluateLife {
    public void evaluateNextGeneration(List<List<Cell>> cellGrid) {
        Neighbours neighbours = new Neighbours();
        for (List<Cell> row : cellGrid) {
            for (Cell cell : row) {
                int neighbourCount = neighbours.getNeighbours(cellGrid, cell);
                if (!cell.isAlive() && neighbourCount == 3) {
                    cell.setState(State.ALIVE);
                }
                if (cell.isAlive() && neighbourCount < 2 || neighbourCount > 3) {
                    cell.setState(State.DEAD);
                }
            }
        }
    }
}