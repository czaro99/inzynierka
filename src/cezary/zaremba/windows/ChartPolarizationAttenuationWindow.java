package cezary.zaremba.windows;

import cezary.zaremba.calculation.RainAttenuationChart;
import cezary.zaremba.calculation.RainAttenuationRateChart;
import org.jfree.chart.ChartPanel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class ChartPolarizationAttenuationWindow extends JFrame {

    private final RainAttenuationChart attenuationChart = new RainAttenuationChart();

    public ChartPolarizationAttenuationWindow() {
        super("Projekt inżynierski");

        ChartPanel chartPanel = new ChartPanel(null);
        chartPanel.setMouseWheelEnabled(true);

        JPanel p1 = new JPanel();
        SpinnerModel modelRainLayerLength = new SpinnerNumberModel(1, 0.1, 100000, 0.1);
        SpinnerModel modelFreq = new SpinnerNumberModel(1, 0.1, 1000, 0.1);
        SpinnerModel modelRainRate = new SpinnerNumberModel(10, 0.1, 100, 0.1);
        SpinnerModel modelPathElevationAngle = new SpinnerNumberModel(10, 0, 90, 0.1);
        SpinnerModel modelPolarizationTiltAngleStart = new SpinnerNumberModel(1, 0, 90, 0.1);
        SpinnerModel modelPolarizationTiltAngleStop = new SpinnerNumberModel(90, 0, 90, 0.1);
        JSpinner spinnerRainLayerLength = new JSpinner(modelRainLayerLength);
        JLabel labelRainLayerLength = new JLabel("Grubość warstwy deszczowej [km]: ");
        JSpinner spinnerFreq = new JSpinner(modelFreq);
        JLabel labelFreq = new JLabel("Częstotliwość [GHz]:");
        JSpinner spinnerRainRate = new JSpinner(modelRainRate);
        JLabel labelRainRate = new JLabel("Współczynnik opadów [mm/h]:");
        JSpinner spinnerPathElevationAngle = new JSpinner(modelPathElevationAngle);
        JLabel labelPathElevationAngle = new JLabel("Kąt elewacji:");
        JSpinner spinnerPolarizationTiltAngleStart = new JSpinner(modelPolarizationTiltAngleStart);
        JLabel labelPolarizationTiltAngleStart = new JLabel("Min. kąt polaryzacji:");
        JSpinner spinnerPolarizationTiltAngleStop = new JSpinner(modelPolarizationTiltAngleStop);
        JLabel labelPolarizationTiltAngleStop = new JLabel("Max. kąt polaryzacji:");

        p1.add(labelRainLayerLength);
        p1.add(spinnerRainLayerLength);
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

        modelRainLayerLength.addChangeListener(e -> {
            double val = Double.parseDouble(spinnerRainLayerLength.getValue().toString());
            attenuationChart.setRainLayerLength(val);
        });

        modelFreq.addChangeListener(e -> {
            double val = Double.parseDouble(spinnerFreq.getValue().toString());
            attenuationChart.setFreq(val);
        });

        modelRainRate.addChangeListener(e -> {
            double val = Double.parseDouble(spinnerRainRate.getValue().toString());
            attenuationChart.setRainRate(val);
        });
        modelPathElevationAngle.addChangeListener(e -> {
            double val = Double.parseDouble(spinnerPathElevationAngle.getValue().toString());
            attenuationChart.setPathElevationAngle(val);
        });
        modelPolarizationTiltAngleStart.addChangeListener(e -> {
            double val = Double.parseDouble(spinnerPolarizationTiltAngleStart.getValue().toString());
            attenuationChart.setPolarizationTiltAngleStart(val);
        });
        modelPolarizationTiltAngleStop.addChangeListener(e -> {
            double val = Double.parseDouble(spinnerPolarizationTiltAngleStop.getValue().toString());
            attenuationChart.setPolarizationTiltAngleStop(val);
        });


        add(chartPanel, BorderLayout.NORTH);
        add(p1, BorderLayout.CENTER);
        add(new JButton(new AbstractAction("Rysuj wykres") {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    chartPanel.setChart(
                            attenuationChart.runGraph("Tłumienie deszczu", "Kąt polaryzacji", "Tłumienie [dB]", "Polarization"));
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
