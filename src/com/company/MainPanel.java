package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel{
    private static final String TAG = "MainPanel";
    private static final int HGAP = 10;
    private static final int VGAP = 10;
    private Controller controller;
    private JLabel titleLabel;
    private JComboBox<Integer> difficultyBox;
    private JButton playbutton;

    public MainPanel(Controller controller) {
        this.controller = controller;
        setLayout(new BorderLayout(HGAP,VGAP));

        //initialize title
        titleLabel = new JLabel(Controller.TITLE, SwingConstants.CENTER);
        titleLabel.setFont(new Font(titleLabel.getName(),Font.PLAIN, 36));

        //initialize play button
        playbutton = new JButton("Play");
        playbutton.addActionListener(new ButtonListener());

        //initialize difficulty picker
        Integer [] difficulties = {3, 4, 5, 6, 7, 8, 9, 10};
        difficultyBox = new JComboBox<Integer>(difficulties);
        difficultyBox.setPreferredSize(new Dimension(this.getWidth() - playbutton.getWidth(), playbutton.getHeight()));
        difficultyBox.setSelectedIndex(1);

        //add components
        add(titleLabel, BorderLayout.NORTH);
        JPanel gridPanel = new JPanel(new GridLayout(1,2, HGAP, VGAP));
        JPanel panel = new JPanel();
        panel.add(difficultyBox);
        gridPanel.add(panel);
        panel = new JPanel();
        panel.add(playbutton);
        gridPanel.add(panel);
        add(gridPanel, BorderLayout.CENTER);
//        add(difficultyBox, BorderLayout.SOUTH);
//        add(playbutton, BorderLayout.SOUTH);
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == playbutton){
                int difficulty = (int) difficultyBox.getSelectedItem();
                controller.displayBoardPanel(difficulty);
            }
        }
    }
}
