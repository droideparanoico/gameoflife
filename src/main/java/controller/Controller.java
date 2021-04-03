package main.java.controller;

import main.java.GenerationAlgorithm;
import main.java.model.Universe;
import main.java.view.Grid;

import javax.swing.*;

public class Controller extends Thread {

    private GenerationAlgorithm generationAlgorithm;
    private final JLabel genLabel;
    private final JLabel aliveLabel;
    private final Grid grid;
    private boolean playing;
    private int generation = 1;
    private int speed = 500;

    public Controller(GenerationAlgorithm generationAlgorithm, JLabel genLabel, JLabel aliveLabel, Grid grid) {
        this.generationAlgorithm = generationAlgorithm;
        this.genLabel = genLabel;
        this.aliveLabel = aliveLabel;
        this.grid = grid;
        updateUI();
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                Thread.sleep(speed);
                if (playing) {
                    generationAlgorithm.evolveUniverse();
                    updateUI();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void playPause() {
        playing = !playing;
    }

    public void reset() {
        playing = false;
        generationAlgorithm.resetUniverse();
        generation = 1;
        updateUI();
    }

    public void changeUniSize(Universe universe) {
        this.grid.setSize(universe.getUniverseSize());
        this.generationAlgorithm = new GenerationAlgorithm(universe);
        reset();
    }

    public void changeSpeed(int speed) {
        this.speed = speed;
    }

    private void updateUI() {
        genLabel.setText("Generation: " + generation++);
        aliveLabel.setText("Living cells: " + generationAlgorithm.getAliveCells());
        grid.setMatrix(generationAlgorithm.getMatrix());
        grid.repaint();
    }
}

