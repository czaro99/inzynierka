package cezary.zaremba.core;

import org.jfree.chart.ChartPanel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ChartRainWindow extends JFrame {

    private final RainAttenuationChart attenuationChart = new RainAttenuationChart();

    public ChartRainWindow() {
        super("Projekt inżynierski");

        ChartPanel chartPanel = new ChartPanel(null);
        chartPanel.setMouseWheelEnabled(true);

        JPanel p1 = new JPanel();
        SpinnerModel modelFreq = new SpinnerNumberModel(1, 0.1, 1000, 0.1);
        SpinnerModel modelPathElevationAngle = new SpinnerNumberModel(10, 0, 90, 0.1);
        SpinnerModel modelPolarizationTiltAngle = new SpinnerNumberModel(45, 0, 90, 0.1);
        SpinnerModel modelRainStart = new SpinnerNumberModel(1, 0.1, 1000, 0.1);
        SpinnerModel modelRainStop = new SpinnerNumberModel(10, 0.1, 1000, 0.1);
        JSpinner spinnerFreq = new JSpinner(modelFreq);
        JLabel labelFreq = new JLabel("Frequency [GHz]:");
        JSpinner spinnerpathElevationAngle = new JSpinner(modelPathElevationAngle);
        JLabel labelpathElevationAngle = new JLabel("Path elevation angle:");
        JSpinner spinnerpolarizationTiltAngle = new JSpinner(modelPolarizationTiltAngle);
        JLabel labelpolarizationTiltAngle = new JLabel("Polarization tilt angle:");
        JSpinner spinnerRainStart = new JSpinner(modelRainStart);
        JLabel labelRainStart = new JLabel("min rain rate [mm/h]:");
        JSpinner spinnerRainStop = new JSpinner(modelRainStop);
        JLabel labelRainStop = new JLabel("max rain rate [mm/h]:");
        p1.add(labelFreq);
        p1.add(spinnerFreq);
        p1.add(labelpathElevationAngle);
        p1.add(spinnerpathElevationAngle);
        p1.add(labelpolarizationTiltAngle);
        p1.add(spinnerpolarizationTiltAngle);
        p1.add(labelRainStart);
        p1.add(spinnerRainStart);
        p1.add(labelRainStop);
        p1.add(spinnerRainStop);

        modelFreq.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                double val = Double.parseDouble(spinnerFreq.getValue().toString());
                attenuationChart.setFreq(val);
            }
        });

        modelPathElevationAngle.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                double val = Double.parseDouble(spinnerpathElevationAngle.getValue().toString());
                attenuationChart.setPathElevationAngle(val);
            }
        });
        modelPolarizationTiltAngle.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                double val = Double.parseDouble(spinnerpolarizationTiltAngle.getValue().toString());
                attenuationChart.setPolarizationTiltAngle(val);
            }
        });
        modelRainStart.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                double val = Double.parseDouble(spinnerRainStart.getValue().toString());
                attenuationChart.setRainRateStart(val);
            }
        });
        modelRainStop.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                double val = Double.parseDouble(spinnerRainStop.getValue().toString());
                attenuationChart.setRainRateStop(val);
            }
        });


        add(chartPanel, BorderLayout.NORTH);
        add(p1, BorderLayout.CENTER);
        add(new JButton(new AbstractAction("Draw Graph") {
            @Override
            public void actionPerformed(ActionEvent e) {
                chartPanel.setChart(
                        attenuationChart.runGraph("Rain attenuation", "Rain Rate [mm/h]", "Attenuation [dB/km]", "Rain rate"));
            }
        }), BorderLayout.SOUTH);

        JButton menuButton = new JButton(new AbstractAction("Back to menu") {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuWindow menuWindow = new MenuWindow();
                dispose();
            }
        });

        add(menuButton, BorderLayout.AFTER_LINE_ENDS);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
