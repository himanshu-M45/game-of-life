package org.example;

import org.example.Enum.State;

public class Cell {
    private State state;

    public Cell() {
        this.state = State.DEAD;
    }

    public void setInitialAlive() {
        this.state = State.ALIVE;
    }

    public void setCellState(int neighbours) {
        if (this.state == State.DEAD && neighbours == 3) {
            this.state = State.ALIVE;
        }
        if (this.state == State.ALIVE && (neighbours < 2 || neighbours > 3)) {
            this.state = State.DEAD;
        }
    }

    public boolean isAlive() {
        return this.state == State.ALIVE;
    }

}
