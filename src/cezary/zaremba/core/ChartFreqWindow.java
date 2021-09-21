package cezary.zaremba.core;

import org.jfree.chart.ChartPanel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ChartFreqWindow extends JFrame {

    private final RainAttenuationChart attenuationChart = new RainAttenuationChart();

    public ChartFreqWindow() {
        super("Projekt inżynierski");

        ChartPanel chartPanel = new ChartPanel(null);
        chartPanel.setMouseWheelEnabled(true);

        JPanel p1 = new JPanel();
        SpinnerModel modelRainRate = new SpinnerNumberModel(1, 0.1, 100, 0.1);
        SpinnerModel modelPathElevationAngle = new SpinnerNumberModel(10, 0, 90, 0.1);
        SpinnerModel modelPolarizationTiltAngle = new SpinnerNumberModel(45, 0, 90, 0.1);
        SpinnerModel modelFreqStart = new SpinnerNumberModel(1, 1, 1000, 0.1);
        SpinnerModel modelFreqStop = new SpinnerNumberModel(300, 1, 1000, 0.1);
        JSpinner spinnerRainRate = new JSpinner(modelRainRate);
        JLabel labelRainRate = new JLabel("Rain rate [mm/h]:");
        JSpinner spinnerpathElevationAngle = new JSpinner(modelPathElevationAngle);
        JLabel labelpathElevationAngle = new JLabel("Path elevation angle:");
        JSpinner spinnerpolarizationTiltAngle = new JSpinner(modelPolarizationTiltAngle);
        JLabel labelpolarizationTiltAngle = new JLabel("Polarization tilt angle:");
        JSpinner spinnerfreqStart = new JSpinner(modelFreqStart);
        JLabel labelfreqStart = new JLabel("Start frequency [GHz]:");
        JSpinner spinnerfreqStop = new JSpinner(modelFreqStop);
        JLabel labelfreqStop = new JLabel("Stop frequency [GHz]:");
        p1.add(labelRainRate);
        p1.add(spinnerRainRate);
        p1.add(labelpathElevationAngle);
        p1.add(spinnerpathElevationAngle);
        p1.add(labelpolarizationTiltAngle);
        p1.add(spinnerpolarizationTiltAngle);
        p1.add(labelfreqStart);
        p1.add(spinnerfreqStart);
        p1.add(labelfreqStop);
        p1.add(spinnerfreqStop);

        modelRainRate.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                double val = Double.parseDouble(spinnerRainRate.getValue().toString());
                attenuationChart.setRainRate(val);
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
        modelFreqStart.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                double val = Double.parseDouble(spinnerfreqStart.getValue().toString());
                attenuationChart.setFreqStart(val);
            }
        });
        modelFreqStop.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                double val = Double.parseDouble(spinnerfreqStop.getValue().toString());
                attenuationChart.setFreqStop(val);
            }
        });


        add(chartPanel, BorderLayout.NORTH);
        add(p1, BorderLayout.CENTER);
        add(new JButton(new AbstractAction("Draw Graph") {
            @Override
            public void actionPerformed(ActionEvent e) {
                chartPanel.setChart(
                        attenuationChart.runGraph("Rain attenuation", "Frequency [GHz]", "Attenuation [dB/km]", "Freq"));
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
