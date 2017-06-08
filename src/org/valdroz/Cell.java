package org.valdroz;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Valerijus Drozdovas on 2/18/17.
 */
public class Cell {
    private int id;
    private int x;
    private int y;
    private List<Cell> neighbors = new LinkedList<>();

    public Cell(int id) {
        this.id = id;
        this.y = id / 8;
        this.x = id - this.y * 8;
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int distance(Cell cell) {
        return Math.abs(getX() - cell.getX()) + Math.abs(getY() - cell.getY());
    }

    public void addNeighbor(Cell cell) {
        neighbors.add(cell);
    }

    public List<Cell> getNeighbors() {
        return neighbors;
    }

    @Override
    public boolean equals(Object obj) {
        if ( obj instanceof Cell ) {
            return Cell.class.cast(obj).getId() == getId();
        }
        return false;
    }
}
