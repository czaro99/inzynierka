package cezary.zaremba.core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MenuWindow extends JFrame {

    public MenuWindow(){
        super("Projekt inżynierski");

        JButton freqButton = new JButton(new AbstractAction("Constant Rain Rate") {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChartFreqWindow chartFreqWindow = new ChartFreqWindow();
                dispose();
            }
        });

        JButton rainButton = new JButton(new AbstractAction("Constant Frequency") {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChartRainWindow chartRainWindow = new ChartRainWindow();
                dispose();
            }
        });

        add(freqButton, BorderLayout.NORTH);
        add(rainButton, BorderLayout.AFTER_LAST_LINE);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }




}
