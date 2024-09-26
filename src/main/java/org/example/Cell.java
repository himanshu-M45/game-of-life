package org.example;

import org.example.enums.State;

public class Cell {
    public final int value;
    private State state;

    public Cell (int value) {
        this.value = value;
        this.state = State.DEAD;
    }
}
