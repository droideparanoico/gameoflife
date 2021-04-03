package main.java.view;

import javax.swing.*;
import java.awt.*;

public class Grid extends JPanel {
    private int size;
    private boolean[][] matrix;

    public Grid(int size) {
        this.size = size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setMatrix(boolean[][] matrix) {
        this.matrix = matrix;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int cellWidth = getWidth()/size;
        int cellHeight = getHeight()/size;

        for (int col = 0; col < size; col++) {
            // Paint grid
            g.drawLine(0, cellHeight * col, cellWidth * size, cellHeight * col);
            g.drawLine(cellWidth * col, 0, cellWidth * col, cellHeight * size);
            g.drawLine(0, cellHeight * size, cellWidth * size, cellHeight * size);
            g.drawLine(cellWidth * size, 0, cellWidth * size, cellHeight * size);
            for (int row = 0; row < size; row++) {
                // Paint living cells
                if (matrix[col][row]) {
                    g.fillRect(cellWidth * row, cellHeight * col, cellWidth, cellHeight);
                }
            }
        }
    }
}
