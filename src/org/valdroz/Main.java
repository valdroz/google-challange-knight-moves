package org.valdroz;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Brute force method.
 */
public class Main {

    private final static int MAX_TRAVEL_STEP_POINTS = 8;

    private final static List<Cell> gridCells = new LinkedList<>();

    static {
        for (int id = 0; id < 64; ++id) {
            gridCells.add(new Cell(id));
        }
        for (Cell cell : gridCells) {
            int id = cell.getId();
            int nids[] = {id + 6, id - 6, id + 10, id - 10, id + 15, id - 15, id + 17, id - 17};
            for (int nid : nids) {
                if (nid >= 0 && nid < gridCells.size()) {
                    Cell nc = gridCells.get(nid);
                    if (cell.distance(nc) == 3) {
                        cell.addNeighbor(nc);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {

        System.out.println("answer(17, 45) = " + answer(17, 45));
        System.out.println("answer(19, 36) = " + answer(19, 36));
        System.out.println("answer(28, 36) = " + answer(28, 36));
        System.out.println("answer(0, 1) = " + answer(0, 1));
        System.out.println("answer(8, 6) = " + answer(8, 6));
        System.out.println("answer(56, 6) = " + answer(56, 6));
        System.out.println("answer(56, 7) = " + answer(56, 7));

        for (int x = 0; x < 8; ++x) {
            System.out.println("answer(56, " + x + ") = " + answer(56, x));
        }
    }

    public static int answer(int src, int dst) {
        Cell destCell = gridCells.get(dst);
        List<Cell> travel = new LinkedList<>();
        travel.add(gridCells.get(src));
        int moves = 0;
        while (true) {
            Set<Cell> newTravel = new HashSet<>();
            System.out.print("ts=" + travel.size() + " ");
            for (int i = 0; i < MAX_TRAVEL_STEP_POINTS && i < travel.size(); ++i) {
                Cell cell = travel.get(i);
                if (cell.getId() == destCell.getId()) {
                    return moves;
                }
                newTravel.addAll(cell.getNeighbors());
            }
            ++moves;
            newTravel.removeAll(travel);
            travel = new LinkedList<>();
            travel.addAll(newTravel);
            travel.sort((cl, cr) -> Integer.compare(cl.distance(destCell), cr.distance(destCell)));
        }
    }

}
