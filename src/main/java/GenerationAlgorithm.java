package main.java;

import main.java.model.Universe;

public class GenerationAlgorithm {

    private final Universe universe;
    private final int uniSize;
    private static final int[][] NEIGHBOURS = {
            {-1, +1}, { 0,+1}, {+1, +1},
            {-1,  0},          {+1,  0},
            {-1, -1}, { 0,-1}, {+1, -1}};

    public GenerationAlgorithm(Universe universe) {
        this.universe = universe;
        this.uniSize = universe.getUniverseSize();
    }

    public void evolveUniverse () {
        boolean[][] nextMatrix = new boolean[uniSize][uniSize];

        int nextLivingCells = 0;

        for (int col = 0; col < uniSize; col++) {
            for (int row = 0; row < uniSize; row++) {
                int neighbors = countAliveNeighbors(col, row);

                // Game of life rules implementation
                // Survival: Each live cell with either 2 or 3 alive neighbors will remain alive.
                // Births: Each dead cell adjacent to exactly three living neighbors will become alive.
                // Death: Each live cell with less than 2 or more than 3 alive neighbors will die.
                if ((isAlive(col, row) && (neighbors > 1 && neighbors < 4))
                        || (!isAlive(col, row) && neighbors == 3)) {
                    nextMatrix[col][row] = true;
                    nextLivingCells++;
                } else {
                    nextMatrix[col][row] = false;
                }
            }
        }

        universe.setLivingCells(nextLivingCells);
        universe.setMatrix(nextMatrix);
    }

    public int countAliveNeighbors(int x, int y) {
        int alive = 0;
        for (int[] offset : NEIGHBOURS) {
            if (isAlive(x + offset[0], y + offset[1])) {
                alive++;
            }
        }
        return alive;
    }

    public int getAliveCells(){
        return this.universe.getLivingCells();
    }

    public boolean isAlive(int x, int y) {
        return universe.getMatrix()[checkBoundaries(x)][checkBoundaries(y)];
    }

    public int checkBoundaries(int coordinate) {
        // Universe is periodic
        if (coordinate < 0) {
            coordinate = uniSize - 1;
        }
        if (coordinate > uniSize - 1) {
            coordinate = 0;
        }
        return coordinate;
    }

    public boolean[][] getMatrix() {
        return this.universe.getMatrix();
    }

    public void resetUniverse() {
        this.universe.initializeUniverse();
    }
}
