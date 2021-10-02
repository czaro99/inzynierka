package cezary.zaremba.windows;

import cezary.zaremba.calculation.RainAttenuationRateChart;
import org.jfree.chart.ChartPanel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class ChartElevationRateWindow extends JFrame {

    private final RainAttenuationRateChart attenuationChart = new RainAttenuationRateChart();

    public ChartElevationRateWindow() {
        super("Projekt inżynierski");

        ChartPanel chartPanel = new ChartPanel(null);
        chartPanel.setMouseWheelEnabled(true);

        JPanel p1 = new JPanel();
        SpinnerModel modelFreq = new SpinnerNumberModel(1, 0.1, 1000, 0.1);
        SpinnerModel modelRainRate = new SpinnerNumberModel(10, 0.1, 100, 0.1);
        SpinnerModel modelPolarizationTiltAngle = new SpinnerNumberModel(45, 0, 90, 0.1);
        SpinnerModel modelPathElevationAngleStart = new SpinnerNumberModel(1, 0, 90, 0.1);
        SpinnerModel modelPathElevationAngleStop = new SpinnerNumberModel(90, 0, 90, 0.1);
        JSpinner spinnerFreq = new JSpinner(modelFreq);
        JLabel labelFreq = new JLabel("Częstotliwość [GHz]:");
        JSpinner spinnerRainRate = new JSpinner(modelRainRate);
        JLabel labelRainRate = new JLabel("Współczynnik opadów [mm/h]:");
        JSpinner spinnerPolarizationTiltAngle = new JSpinner(modelPolarizationTiltAngle);
        JLabel labelPolarizationTiltAngle = new JLabel("Kąt polaryzacji:");
        JSpinner spinnerPathElevationAngleStart = new JSpinner(modelPathElevationAngleStart);
        JLabel labelPathElevationAngleStart = new JLabel("Min. kąt elewacji:");
        JSpinner spinnerPathElevationAngleStop = new JSpinner(modelPathElevationAngleStop);
        JLabel labelPathElevationAngleStop = new JLabel("Max. kąt elewacji:");
        p1.add(labelFreq);
        p1.add(spinnerFreq);
        p1.add(labelRainRate);
        p1.add(spinnerRainRate);
        p1.add(labelPolarizationTiltAngle);
        p1.add(spinnerPolarizationTiltAngle);
        p1.add(labelPathElevationAngleStart);
        p1.add(spinnerPathElevationAngleStart);
        p1.add(labelPathElevationAngleStop);
        p1.add(spinnerPathElevationAngleStop);

        modelFreq.addChangeListener(e -> {
            double val = Double.parseDouble(spinnerFreq.getValue().toString());
            attenuationChart.setFreq(val);
        });

        modelRainRate.addChangeListener(e -> {
            double val = Double.parseDouble(spinnerRainRate.getValue().toString());
            attenuationChart.setRainRate(val);
        });
        modelPolarizationTiltAngle.addChangeListener(e -> {
            double val = Double.parseDouble(spinnerPolarizationTiltAngle.getValue().toString());
            attenuationChart.setPolarizationTiltAngle(val);
        });
        modelPathElevationAngleStart.addChangeListener(e -> {
            double val = Double.parseDouble(spinnerPathElevationAngleStart.getValue().toString());
            attenuationChart.setPathElevationAngleStart(val);
        });
        modelPathElevationAngleStop.addChangeListener(e -> {
            double val = Double.parseDouble(spinnerPathElevationAngleStop.getValue().toString());
            attenuationChart.setPathElevationAngleStop(val);
        });


        add(chartPanel, BorderLayout.NORTH);
        add(p1, BorderLayout.CENTER);
        add(new JButton(new AbstractAction("Rysuj wykres") {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    chartPanel.setChart(
                            attenuationChart.runGraph("Tłumienie jednostkowe deszczu", "Kąt elewacji", "Tłumienie jednostkowe [dB/km]", "Elevation"));
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
