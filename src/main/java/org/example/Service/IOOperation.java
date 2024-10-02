package org.example.Service;

import org.example.Cell;

import java.util.List;

public class IOOperation {
    public static void printPopulation(Cell[][] cellGrid) {
        for (Cell[] cells : cellGrid) {
            for (int column = 0; column < cellGrid[0].length; column++) {
                System.out.print(cells[column].isAlive() ? "* " : ". ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
