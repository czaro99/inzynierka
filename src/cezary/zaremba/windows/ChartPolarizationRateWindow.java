package cezary.zaremba.windows;

import cezary.zaremba.calculation.RainAttenuationRateDataset;
import cezary.zaremba.model.ChartType;
import org.jfree.chart.ChartPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class ChartPolarizationRateWindow extends JFrame {

    private final RainAttenuationRateDataset attenuationDataset = new RainAttenuationRateDataset();


    public ChartPolarizationRateWindow() {
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
        JLabel labelRainRate = new JLabel("Współczynnik opadów [mm/h]:");
        JSpinner spinnerPathElevationAngle = new JSpinner(modelPathElevationAngle);
        JLabel labelPathElevationAngle = new JLabel("Kąt elewacji:");
        JSpinner spinnerPolarizationTiltAngleStart = new JSpinner(modelPolarizationTiltAngleStart);
        JLabel labelPolarizationTiltAngleStart = new JLabel("Min. kąt polaryzacji:");
        JSpinner spinnerPolarizationTiltAngleStop = new JSpinner(modelPolarizationTiltAngleStop);
        JLabel labelPolarizationTiltAngleStop = new JLabel("Max. kąt polaryzacji:");
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

        modelFreq.addChangeListener(e -> {
            double val = Double.parseDouble(spinnerFreq.getValue().toString());
            attenuationDataset.setFreq(val);
        });

        modelRainRate.addChangeListener(e -> {
            double val = Double.parseDouble(spinnerRainRate.getValue().toString());
            attenuationDataset.setRainRate(val);
        });
        modelPathElevationAngle.addChangeListener(e -> {
            double val = Double.parseDouble(spinnerPathElevationAngle.getValue().toString());
            attenuationDataset.setPathElevationAngle(val);
        });
        modelPolarizationTiltAngleStart.addChangeListener(e -> {
            double val = Double.parseDouble(spinnerPolarizationTiltAngleStart.getValue().toString());
            attenuationDataset.setPolarizationTiltAngleStart(val);
        });
        modelPolarizationTiltAngleStop.addChangeListener(e -> {
            double val = Double.parseDouble(spinnerPolarizationTiltAngleStop.getValue().toString());
            attenuationDataset.setPolarizationTiltAngleStop(val);
        });


        add(chartPanel, BorderLayout.NORTH);
        add(p1, BorderLayout.CENTER);
        add(new JButton(new AbstractAction("Rysuj wykres") {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    chartPanel.setChart(
                            attenuationDataset.runGraph("Tłumienie jednostkowe deszczu", "Kąt polaryzacji", "Tłumienie jednostkowe [dB/km]", attenuationDataset.createDataset(ChartType.POLARIZATION)));
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
