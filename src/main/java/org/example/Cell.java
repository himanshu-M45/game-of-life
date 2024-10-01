package org.example;

import org.example.Enum.State;

public class Cell {
    private Character value;
    private State state;

    public Cell() {
        this.state = State.DEAD;
        this.value = '.';
    }

    public void setInitialAlive() {
        this.state = State.ALIVE;
        this.value = '*';
    }

    public void setState(int neighbours) {
        if (this.state == State.DEAD && neighbours == 3) {
            this.state = State.ALIVE;
            this.value = '*';
        }
        if (this.state == State.ALIVE && (neighbours < 2 || neighbours > 3)) {
            this.state = State.DEAD;
            this.value = '.';
        }
    }

    public boolean isAlive() {
        return this.state == State.ALIVE;
    }

    public void printValue() {
        System.out.print(this.value + " ");
    }
}
