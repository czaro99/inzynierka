package cezary.zaremba.windows;

import cezary.zaremba.windows.ChartElevationWindow;
import cezary.zaremba.windows.ChartFreqWindow;
import cezary.zaremba.windows.ChartPolarizationWindow;
import cezary.zaremba.windows.ChartRainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MenuWindow extends JFrame {

    public MenuWindow(){
        super("Projekt inżynierski");

        JButton freqButton = new JButton(new AbstractAction("Frequency") {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChartFreqWindow chartFreqWindow = new ChartFreqWindow();
                dispose();
            }
        });

        JButton rainButton = new JButton(new AbstractAction("Rain rate") {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChartRainWindow chartRainWindow = new ChartRainWindow();
                dispose();
            }
        });

        JButton elevationButton = new JButton(new AbstractAction("Path elevation") {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChartElevationWindow chartElevationWindow = new ChartElevationWindow();
                dispose();
            }
        });

        JButton polarizationButton = new JButton(new AbstractAction("Polarization tilt") {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChartPolarizationWindow chartPolarizationWindow = new ChartPolarizationWindow();
                dispose();
            }
        });

//        JPanel p1 = new JPanel();
//        JPanel p2 = new JPanel();
//        JPanel p3 = new JPanel();
//        p1.add(freqButton);
//        p2.add(rainButton, BorderLayout.NORTH);
//        p2.add(elevationButton, BorderLayout.SOUTH);
//        p3.add(polarizationButton);
//
//        add(p1, BorderLayout.PAGE_START);
//        add(p2, BorderLayout.CENTER);
//        add(p3, BorderLayout.PAGE_END);
        add(freqButton, BorderLayout.NORTH);
        add(rainButton, BorderLayout.CENTER);
        add(elevationButton, BorderLayout.AFTER_LINE_ENDS);
        add(polarizationButton, BorderLayout.SOUTH);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }




}
