package cezary.zaremba.core;

import org.jfree.chart.ChartPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;

public class Window extends JFrame {

    private NumberFormat numberFormat;

    public Window() {
        super("Projekt inżynierski");

        ChartPanel chartPanel = new ChartPanel(null);
        add(chartPanel);
//        add(new JFormattedTextField(numberFormat), BorderLayout.NORTH);
        add(new JButton(new AbstractAction("Draw Graph") {
            @Override
            public void actionPerformed(ActionEvent e) {
                chartPanel.setChart(
                        new XYChart().runGraph("Rain attenuation", "Frequency [GHz]", "Attenuation [dB/km]"));
            }
        }), BorderLayout.SOUTH);


        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
