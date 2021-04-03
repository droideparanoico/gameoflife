package main.java.model;

import java.util.Random;

public class Universe {

    private final int universeSize;
    private int livingCells;
    private boolean[][] matrix;

    public Universe(int universeSize) {
        this.universeSize = universeSize;
        matrix = new boolean[universeSize][universeSize];
        initializeUniverse();
    }

    public void initializeUniverse() {
        livingCells = 0;
        for(int i = 0; i < universeSize; i++) {
            for(int j = 0; j < universeSize; j++) {
                matrix[i][j] = new Random().nextBoolean();
                if ((matrix[i][j])) {
                    livingCells++;
                }
            }
        }
    }

    public int getUniverseSize() {
        return universeSize;
    }

    public int getLivingCells() {
        return livingCells;
    }

    public void setLivingCells(int livingCells) {
        this.livingCells = livingCells;
    }

    public boolean[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(boolean[][] matrix) {
        this.matrix = matrix;
    }
}
