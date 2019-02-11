package ui;

import game.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WinPanel extends JPanel {
    private static final String TAG = "MainPanel";
    private static final int HGAP = 10;
    private static final int VGAP = 10;
    private Controller controller;
    private JButton playButton;
    private JButton backToMenuButton;

    public WinPanel(Controller controller) {
        this.controller = controller;
        setLayout(new BorderLayout(HGAP,VGAP));

        //initialize title
        JLabel titleLabel = new JLabel("YOU WON!", SwingConstants.CENTER);
        titleLabel.setFont(new Font("",Font.PLAIN, 36));

        //TODO: Add score and total moves label here.

        //initialize play button
        ButtonListener listener =new ButtonListener();
        playButton = new JButton("Play again");
        playButton.addActionListener(listener);
        backToMenuButton = new JButton("Back to menu");
        backToMenuButton.addActionListener(listener);

        //add components
        add(titleLabel, BorderLayout.NORTH);
        JPanel gridPanel = new JPanel(new GridLayout(2,1, HGAP, VGAP));
        JPanel panel = new JPanel();
        panel.add(playButton);
        gridPanel.add(panel);
        panel = new JPanel();
        panel.add(backToMenuButton);
        gridPanel.add(panel);
        add(gridPanel, BorderLayout.CENTER);
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == playButton){
                int difficulty = controller.getDifficulty();
                controller.displayBoardPanel(difficulty);
            } else if (e.getSource() == backToMenuButton){
                controller.displayMainPanel();
            }
        }
    }
}
