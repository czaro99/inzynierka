package cezary.zaremba.windows;

import cezary.zaremba.calculation.RainAttenuationChart;
import cezary.zaremba.calculation.RainAttenuationDataset;
import cezary.zaremba.model.ChartType;
import org.jfree.chart.ChartPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class ChartFreqAttenuationWindow extends JFrame{

    private final RainAttenuationDataset attenuationDataset = new RainAttenuationDataset();

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
        JSpinner spinnerRainLayerLength = new JSpinner(modelRainLayerLength);
        JLabel labelRainLayerLength = new JLabel("Grubość warstwy deszczowej [km]: ");
        JSpinner spinnerRainRate = new JSpinner(modelRainRate);
        JLabel labelRainRate = new JLabel("Współczynnik opadów [mm/h]:");
        JSpinner spinnerpathElevationAngle = new JSpinner(modelPathElevationAngle);
        JLabel labelpathElevationAngle = new JLabel("Kąt elewacji:");
        JSpinner spinnerpolarizationTiltAngle = new JSpinner(modelPolarizationTiltAngle);
        JLabel labelpolarizationTiltAngle = new JLabel("Kąt polaryzacji:");
        JSpinner spinnerfreqStart = new JSpinner(modelFreqStart);
        JLabel labelfreqStart = new JLabel("Min. częstotliwość [GHz]:");
        JSpinner spinnerfreqStop = new JSpinner(modelFreqStop);
        JLabel labelfreqStop = new JLabel("Max. częstotliwość [GHz]:");
        p1.add(labelRainLayerLength);
        p1.add(spinnerRainLayerLength);
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


        modelRainLayerLength.addChangeListener(e -> {
            double val = Double.parseDouble(spinnerRainLayerLength.getValue().toString());
            attenuationDataset.setRainLayerLength(val);
        });

        modelRainRate.addChangeListener(e -> {
            double val = Double.parseDouble(spinnerRainRate.getValue().toString());
            attenuationDataset.setRainRate(val);
        });

        modelPathElevationAngle.addChangeListener(e -> {
            double val = Double.parseDouble(spinnerpathElevationAngle.getValue().toString());
            attenuationDataset.setPathElevationAngle(val);
        });
        modelPolarizationTiltAngle.addChangeListener(e -> {
            double val = Double.parseDouble(spinnerpolarizationTiltAngle.getValue().toString());
            attenuationDataset.setPolarizationTiltAngle(val);
        });
        modelFreqStart.addChangeListener(e -> {
            double val = Double.parseDouble(spinnerfreqStart.getValue().toString());
            attenuationDataset.setFreqStart(val);
        });
        modelFreqStop.addChangeListener(e -> {
            double val = Double.parseDouble(spinnerfreqStop.getValue().toString());
            attenuationDataset.setFreqStop(val);
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
