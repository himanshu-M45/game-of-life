package org.example;

import org.example.enums.State;

public class Cell {
    private int value;
    private State state;

    public Cell () {
        this.state = State.DEAD;
        this.value= 0;
    }

    public void setState(State state) {
        this.state = state;
        this.value = (state == State.ALIVE) ? 1 : 0;
    }

    public boolean isAlive() {
        return this.state == State.ALIVE;
    }

    public void printValue() {
        System.out.print(this.value + " ");
    }
}
