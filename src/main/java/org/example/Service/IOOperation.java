package org.example.Service;

import org.example.Cell;

import java.util.List;

public class IOOperation {
    public static void printPopulation(List<List<Cell>> cellGrid) {
        for (List<Cell> row : cellGrid) {
            for (Cell cell : row) {
                cell.printValue();
            }
            System.out.println();
        }
        System.out.println();
    }
}
