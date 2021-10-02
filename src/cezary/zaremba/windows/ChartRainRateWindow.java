package cezary.zaremba.windows;

import cezary.zaremba.calculation.RainAttenuationRateChart;
import org.jfree.chart.ChartPanel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class ChartRainRateWindow extends JFrame {

    private final RainAttenuationRateChart attenuationChart = new RainAttenuationRateChart();

    public ChartRainRateWindow() {
        super("Projekt inżynierski");

        ChartPanel chartPanel = new ChartPanel(null);
        chartPanel.setMouseWheelEnabled(true);

        JPanel p1 = new JPanel();
        SpinnerModel modelFreq = new SpinnerNumberModel(10, 0.1, 1000, 0.1);
        SpinnerModel modelPathElevationAngle = new SpinnerNumberModel(30, 0, 90, 0.1);
        SpinnerModel modelPolarizationTiltAngle = new SpinnerNumberModel(45, 0, 90, 0.1);
        SpinnerModel modelRainStart = new SpinnerNumberModel(1, 0.1, 1000, 0.1);
        SpinnerModel modelRainStop = new SpinnerNumberModel(100, 0.1, 1000, 0.1);
        JSpinner spinnerFreq = new JSpinner(modelFreq);
        JLabel labelFreq = new JLabel("Częstotliwość [GHz]:");
        JSpinner spinnerpathElevationAngle = new JSpinner(modelPathElevationAngle);
        JLabel labelpathElevationAngle = new JLabel("Kąt elewacji:");
        JSpinner spinnerpolarizationTiltAngle = new JSpinner(modelPolarizationTiltAngle);
        JLabel labelpolarizationTiltAngle = new JLabel("Kąt polaryzacji:");
        JSpinner spinnerRainStart = new JSpinner(modelRainStart);
        JLabel labelRainStart = new JLabel("Min. współczynnik opadów [mm/h]:");
        JSpinner spinnerRainStop = new JSpinner(modelRainStop);
        JLabel labelRainStop = new JLabel("Max. współczynnik opadów [mm/h]:");
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
        add(new JButton(new AbstractAction("Rysuj wykres") {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    chartPanel.setChart(
                            attenuationChart.runGraph("Tłumienie jednostkowe deszczu", "Współczynnik opadów [mm/h]", "Tłumienie jednostkowe [dB/km]", "Rain rate"));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }), BorderLayout.SOUTH);

        JButton menuButton = new JButton(new AbstractAction("Powrót do menu") {
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
