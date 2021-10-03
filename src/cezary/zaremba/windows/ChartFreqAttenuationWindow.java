package cezary.zaremba.windows;

import cezary.zaremba.calculation.AttenuationChart;
import cezary.zaremba.model.ChartType;
import org.jfree.chart.ChartPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class ChartFreqAttenuationWindow extends JFrame{

    private final AttenuationChart attenuationDataset = new AttenuationChart();

    public ChartFreqAttenuationWindow() {
        super("Projekt inżynierski");

        ChartPanel chartPanel = new ChartPanel(null);
        chartPanel.setMouseWheelEnabled(true);

        JPanel p1 = new JPanel();
        SpinnerModel modelRainLayerLength = new SpinnerNumberModel(1, 0.1, 100000, 0.1);
        SpinnerModel modelRainRate = new SpinnerNumberModel(1, 0.1, 100, 0.1);
        SpinnerModel modelPathElevationAngle = new SpinnerNumberModel(10, 0, 90, 0.1);
        SpinnerModel modelPolarizationTiltAngle = new SpinnerNumberModel(45, 0, 90, 0.1);
        SpinnerModel modelFreqStart = new SpinnerNumberModel(1, 1, 1000, 0.1);
        SpinnerModel modelFreqStop = new SpinnerNumberModel(300, 1, 1000, 0.1);
        SpinnerModel modelStep = new SpinnerNumberModel(1, 0.1, 100, 0.1);
        JSpinner spinnerRainLayerLength = new JSpinner(modelRainLayerLength);
        JLabel labelRainLayerLength = new JLabel("Grubość warstwy deszczowej [km]: ");
        JSpinner spinnerRainRate = new JSpinner(modelRainRate);
        JLabel labelRainRate = new JLabel("Współczynnik opadów [mm/h]:");
        JSpinner spinnerPathElevationAngle = new JSpinner(modelPathElevationAngle);
        JLabel labelPathElevationAngle = new JLabel("Kąt elewacji:");
        JSpinner spinnerPolarizationTiltAngle = new JSpinner(modelPolarizationTiltAngle);
        JLabel labelPolarizationTiltAngle = new JLabel("Kąt polaryzacji:");
        JSpinner spinnerFreqStart = new JSpinner(modelFreqStart);
        JLabel labelFreqStart = new JLabel("Min. częstotliwość [GHz]:");
        JSpinner spinnerFreqStop = new JSpinner(modelFreqStop);
        JLabel labelFreqStop = new JLabel("Max. częstotliwość [GHz]:");
        JSpinner spinnerStep = new JSpinner(modelStep);
        JLabel labelStep = new JLabel("Krok:");
        p1.add(labelRainLayerLength);
        p1.add(spinnerRainLayerLength);
        p1.add(labelRainRate);
        p1.add(spinnerRainRate);
        p1.add(labelPathElevationAngle);
        p1.add(spinnerPathElevationAngle);
        p1.add(labelPolarizationTiltAngle);
        p1.add(spinnerPolarizationTiltAngle);
        p1.add(labelFreqStart);
        p1.add(spinnerFreqStart);
        p1.add(labelFreqStop);
        p1.add(spinnerFreqStop);
        p1.add(labelStep);
        p1.add(spinnerStep);


        modelRainLayerLength.addChangeListener(e -> {
            double val = Double.parseDouble(spinnerRainLayerLength.getValue().toString());
            attenuationDataset.setRainLayerLength(val);
        });

        modelRainRate.addChangeListener(e -> {
            double val = Double.parseDouble(spinnerRainRate.getValue().toString());
            attenuationDataset.setRainRate(val);
        });

        modelPathElevationAngle.addChangeListener(e -> {
            double val = Double.parseDouble(spinnerPathElevationAngle.getValue().toString());
            attenuationDataset.setPathElevationAngle(val);
        });
        modelPolarizationTiltAngle.addChangeListener(e -> {
            double val = Double.parseDouble(spinnerPolarizationTiltAngle.getValue().toString());
            attenuationDataset.setPolarizationTiltAngle(val);
        });
        modelFreqStart.addChangeListener(e -> {
            double val = Double.parseDouble(spinnerFreqStart.getValue().toString());
            attenuationDataset.setFreqStart(val);
        });
        modelFreqStop.addChangeListener(e -> {
            double val = Double.parseDouble(spinnerFreqStop.getValue().toString());
            attenuationDataset.setFreqStop(val);
        });

        modelStep.addChangeListener(e -> {
            double val = Double.parseDouble(spinnerStep.getValue().toString());
            attenuationDataset.setStep(val);
        });


        add(chartPanel, BorderLayout.NORTH);
        add(p1, BorderLayout.CENTER);
        add(new JButton(new AbstractAction("Rysuj wykres") {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    chartPanel.setChart(
                            attenuationDataset.runGraph("Tłumienie deszczu", "Częstotliwość [GHz]", "Tłumienie [dB]", attenuationDataset.createDataset(ChartType.FREQUENCY)));
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
