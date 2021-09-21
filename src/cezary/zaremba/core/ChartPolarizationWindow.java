package cezary.zaremba.core;

import org.jfree.chart.ChartPanel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ChartPolarizationWindow extends JFrame {

    private final RainAttenuationChart attenuationChart = new RainAttenuationChart();

    public ChartPolarizationWindow() {
        super("Projekt inżynierski");

        ChartPanel chartPanel = new ChartPanel(null);
        chartPanel.setMouseWheelEnabled(true);

        JPanel p1 = new JPanel();
        SpinnerModel modelFreq = new SpinnerNumberModel(1, 0.1, 1000, 0.1);
        SpinnerModel modelRainRate = new SpinnerNumberModel(10, 0.1, 100, 0.1);
        SpinnerModel modelPathElevationAngle = new SpinnerNumberModel(10, 0, 90, 0.1);
        SpinnerModel modelPolarizationTiltAngleStart = new SpinnerNumberModel(1, 0, 90, 0.1);
        SpinnerModel modelPolarizationTiltAngleStop = new SpinnerNumberModel(90, 0, 90, 0.1);
        JSpinner spinnerFreq = new JSpinner(modelFreq);
        JLabel labelFreq = new JLabel("Frequency [GHz]:");
        JSpinner spinnerRainRate = new JSpinner(modelRainRate);
        JLabel labelRainRate = new JLabel("Rain rate [mm/h]:");
        JSpinner spinnerPathElevationAngle = new JSpinner(modelPathElevationAngle);
        JLabel labelPathElevationAngle = new JLabel("Path elevation angle:");
        JSpinner spinnerPolarizationTiltAngleStart = new JSpinner(modelPolarizationTiltAngleStart);
        JLabel labelPolarizationTiltAngleStart = new JLabel("min Polarization angle:");
        JSpinner spinnerPolarizationTiltAngleStop = new JSpinner(modelPolarizationTiltAngleStop);
        JLabel labelPolarizationTiltAngleStop = new JLabel("max Polarization angle:");
        p1.add(labelFreq);
        p1.add(spinnerFreq);
        p1.add(labelRainRate);
        p1.add(spinnerRainRate);
        p1.add(labelPathElevationAngle);
        p1.add(spinnerPathElevationAngle);
        p1.add(labelPolarizationTiltAngleStart);
        p1.add(spinnerPolarizationTiltAngleStart);
        p1.add(labelPolarizationTiltAngleStop);
        p1.add(spinnerPolarizationTiltAngleStop);

        modelFreq.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                double val = Double.parseDouble(spinnerFreq.getValue().toString());
                attenuationChart.setFreq(val);
            }
        });

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
                double val = Double.parseDouble(spinnerPathElevationAngle.getValue().toString());
                attenuationChart.setPathElevationAngle(val);
            }
        });
        modelPolarizationTiltAngleStart.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                double val = Double.parseDouble(spinnerPolarizationTiltAngleStart.getValue().toString());
                attenuationChart.setPolarizationTiltAngleStart(val);
            }
        });
        modelPolarizationTiltAngleStop.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                double val = Double.parseDouble(spinnerPolarizationTiltAngleStop.getValue().toString());
                attenuationChart.setPolarizationTiltAngleStop(val);
            }
        });


        add(chartPanel, BorderLayout.NORTH);
        add(p1, BorderLayout.CENTER);
        add(new JButton(new AbstractAction("Draw Graph") {
            @Override
            public void actionPerformed(ActionEvent e) {
                chartPanel.setChart(
                        attenuationChart.runGraph("Rain attenuation", "Polarization tilt angle", "Attenuation [dB/km]", "Polarization"));
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
