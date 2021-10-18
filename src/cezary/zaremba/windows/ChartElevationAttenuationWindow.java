package cezary.zaremba.windows;

import cezary.zaremba.calculation.AttenuationChart;
import cezary.zaremba.model.ChartType;
import org.jfree.chart.ChartPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class ChartElevationAttenuationWindow extends JFrame {

    private final AttenuationChart attenuationDataset = new AttenuationChart();

    public ChartElevationAttenuationWindow() {
        super("Projekt inżynierski");

        ChartPanel chartPanel = new ChartPanel(null);
        chartPanel.setMouseWheelEnabled(true);

        JPanel p1 = new JPanel();
        SpinnerModel modelRainLayerLength = new SpinnerNumberModel(1, 0.1, 100000, 0.1);
        SpinnerModel modelFreq = new SpinnerNumberModel(1, 0.1, 1000, 0.1);
        SpinnerModel modelRainRate = new SpinnerNumberModel(10, 0.1, 100, 0.1);
        SpinnerModel modelPolarizationTiltAngle = new SpinnerNumberModel(45, 0, 90, 0.1);
        SpinnerModel modelPathElevationAngleStart = new SpinnerNumberModel(5, 5, 90, 0.1);
        SpinnerModel modelPathElevationAngleStop = new SpinnerNumberModel(90, 5, 90, 0.1);
        SpinnerModel modelStep = new SpinnerNumberModel(1, 0.1, 100, 0.1);
        JSpinner spinnerStep = new JSpinner(modelStep);
        JLabel labelStep = new JLabel("Krok:");
        JSpinner spinnerRainLayerLength = new JSpinner(modelRainLayerLength);
        JLabel labelRainLayerLength = new JLabel("Grubość warstwy deszczowej [km]: ");
        JSpinner spinnerFreq = new JSpinner(modelFreq);
        JLabel labelFreq = new JLabel("Częstotliwość [GHz]:");
        JSpinner spinnerRainRate = new JSpinner(modelRainRate);
        JLabel labelRainRate = new JLabel("Intensywność opadów [mm/h]:");
        JSpinner spinnerPolarizationTiltAngle = new JSpinner(modelPolarizationTiltAngle);
        JLabel labelPolarizationTiltAngle = new JLabel("Kąt polaryzacji:");
        JSpinner spinnerPathElevationAngleStart = new JSpinner(modelPathElevationAngleStart);
        JLabel labelPathElevationAngleStart = new JLabel("Min. kąt elewacji trajektorii fali radiowej:");
        JSpinner spinnerPathElevationAngleStop = new JSpinner(modelPathElevationAngleStop);
        JLabel labelPathElevationAngleStop = new JLabel("Max. kąt elewacji trajektorii fali radiowej:");
        p1.add(labelRainLayerLength);
        p1.add(spinnerRainLayerLength);
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
        p1.add(labelStep);
        p1.add(spinnerStep);

        modelRainLayerLength.addChangeListener(e -> {
            double val = Double.parseDouble(spinnerRainLayerLength.getValue().toString());
            attenuationDataset.setRainLayerLength(val);
        });

        modelFreq.addChangeListener(e -> {
            double val = Double.parseDouble(spinnerFreq.getValue().toString());
            attenuationDataset.setFreq(val);
        });

        modelRainRate.addChangeListener(e -> {
            double val = Double.parseDouble(spinnerRainRate.getValue().toString());
            attenuationDataset.setRainRate(val);
        });
        modelPolarizationTiltAngle.addChangeListener(e -> {
            double val = Double.parseDouble(spinnerPolarizationTiltAngle.getValue().toString());
            attenuationDataset.setPolarizationTiltAngle(val);
        });
        modelPathElevationAngleStart.addChangeListener(e -> {
            double val = Double.parseDouble(spinnerPathElevationAngleStart.getValue().toString());
            attenuationDataset.setPathElevationAngleStart(val);
        });
        modelPathElevationAngleStop.addChangeListener(e -> {
            double val = Double.parseDouble(spinnerPathElevationAngleStop.getValue().toString());
            attenuationDataset.setPathElevationAngleStop(val);
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
                            attenuationDataset.runGraph("", "Kąt elewacji trajektorii fali radiowej", "Tłumienie [dB]", attenuationDataset.createDataset(ChartType.ELEVATION), attenuationDataset.getPathElevationAngleStart(), attenuationDataset.getPathElevationAngleStop()));
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
