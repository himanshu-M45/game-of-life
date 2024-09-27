package org.example;

import org.example.enums.State;

public class Cell {
    private Character value;
    private State state;

    public Cell() {
        this.state = State.DEAD;
        this.value = '.';
    }

    public void setState(State state) {
        this.state = state;
        this.value = (state == State.ALIVE) ? '*' : '.';
    }

    public boolean isAlive() {
        return this.state == State.ALIVE;
    }

    public void printValue() {
        System.out.print(this.value + " ");
    }
}
