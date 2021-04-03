package main.java.view;

import main.java.GenerationAlgorithm;
import main.java.controller.Controller;
import main.java.model.Universe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.Enumeration;

public class GameOfLife extends JFrame {
    private static final int WINDOW_WIDTH= 600;
    private static final int WINDOW_HEIGHT= 730;
    private static final int UNIVERSE_SIZE = 20;
    private transient Controller controller;

    public GameOfLife() {
        super("Game of life");

        Universe universe = new Universe(UNIVERSE_SIZE);
        GenerationAlgorithm generationAlgorithm = new GenerationAlgorithm(universe);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setUIFont(new javax.swing.plaf.FontUIResource(new Font("SansSerif", Font.BOLD, 16)));
        FlowLayout flowlayout = new FlowLayout(FlowLayout.LEFT, 10, 10);
        Dimension buttonDimension = new Dimension(100,30);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(flowlayout);

        JToggleButton playButton = new JToggleButton("Play");
        playButton.setPreferredSize(buttonDimension);
        playButton.addActionListener(e -> controller.playPause());
        playButton.addItemListener(event -> {
            if (event.getStateChange() == ItemEvent.SELECTED) {
                playButton.setText("Pause");
            } else {
                playButton.setText("Play");
            }
        });
        inputPanel.add(playButton);

        JButton resetButton = new JButton("Reset");
        resetButton.setPreferredSize(buttonDimension);
        resetButton.addActionListener(e -> controller.reset());
        resetButton.addActionListener(e -> playButton.setSelected(false));
        inputPanel.add(resetButton);

        JPanel sizeSpinner = new JPanel();
        sizeSpinner.setLayout(new BoxLayout(sizeSpinner, BoxLayout.X_AXIS));
        sizeSpinner.add(new JLabel("Size "));
        SpinnerNumberModel sizeInput = new SpinnerNumberModel(20, 10, 30, 1);
        sizeInput.addChangeListener(e -> controller.changeUniSize(new Universe((Integer)sizeInput.getValue())));
        sizeInput.addChangeListener(e -> playButton.setSelected(false));
        sizeSpinner.add(new JSpinner(sizeInput));
        inputPanel.add(sizeSpinner);

        inputPanel.add(new JLabel("Speed"));

        JSlider speedSlider = new JSlider(50, 1500, 1000);
        speedSlider.setInverted(true);
        speedSlider.setPreferredSize(new Dimension(190,30));
        speedSlider.addChangeListener(e -> controller.changeSpeed(speedSlider.getValue()));
        inputPanel.add(speedSlider);

        add(inputPanel, BorderLayout.NORTH);

        Grid grid = new Grid(UNIVERSE_SIZE);
        add(grid, BorderLayout.CENTER);

        JPanel labelPanel = new JPanel();
        JLabel genLabel = new JLabel();
        JLabel aliveLabel = new JLabel();
        labelPanel.add(genLabel);
        labelPanel.add(aliveLabel);
        labelPanel.setLayout(flowlayout);
        add(labelPanel, BorderLayout.SOUTH);

        controller = new Controller(generationAlgorithm, genLabel, aliveLabel, grid);
        controller.start();

        setResizable(false);
        setVisible(true);
    }

    public static void setUIFont (javax.swing.plaf.FontUIResource f){
        Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get (key);
            if (value instanceof javax.swing.plaf.FontUIResource)
                UIManager.put (key, f);
        }
    }
}