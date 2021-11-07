package cezary.zaremba.projekt.inzynierski.windows;

import cezary.zaremba.projekt.inzynierski.charts.AttenuationRateChart;
import cezary.zaremba.projekt.inzynierski.model.ChartType;
import org.jfree.chart.ChartPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class ChartRainRateWindow extends JFrame {

    private final AttenuationRateChart attenuationDataset = new AttenuationRateChart();

    public ChartRainRateWindow() {
        super("Projekt inżynierski");

        ChartPanel chartPanel = new ChartPanel(null);
        chartPanel.setMouseWheelEnabled(true);

        JPanel p1 = new JPanel();
        SpinnerModel modelFreq = new SpinnerNumberModel(10, 0.1, 1000, 0.1);
        SpinnerModel modelPathElevationAngle = new SpinnerNumberModel(30, 0, 90, 0.1);
        SpinnerModel modelPolarizationTiltAngle = new SpinnerNumberModel(45, 0, 90, 0.1);
        SpinnerModel modelRainStart = new SpinnerNumberModel(1, 0.1, 1000, 0.1);
        SpinnerModel modelRainStop = new SpinnerNumberModel(200, 0.1, 1000, 0.1);
        SpinnerModel modelStep = new SpinnerNumberModel(1, 0.1, 100, 0.1);
        JSpinner spinnerStep = new JSpinner(modelStep);
        JLabel labelStep = new JLabel("Krok:");
        JSpinner spinnerFreq = new JSpinner(modelFreq);
        JLabel labelFreq = new JLabel("Częstotliwość [GHz]:");
        JSpinner spinnerPathElevationAngle = new JSpinner(modelPathElevationAngle);
        JLabel labelPathElevationAngle = new JLabel("Kąt elewacji trajektorii fali radiowej:");
        JSpinner spinnerPolarizationTiltAngle = new JSpinner(modelPolarizationTiltAngle);
        JLabel labelPolarizationTiltAngle = new JLabel("Kąt polaryzacji:");
        JSpinner spinnerRainStart = new JSpinner(modelRainStart);
        JLabel labelRainStart = new JLabel("Min. Intensywność opadów [mm/h]:");
        JSpinner spinnerRainStop = new JSpinner(modelRainStop);
        JLabel labelRainStop = new JLabel("Max. Intensywność opadów [mm/h]:");
        p1.add(labelFreq);
        p1.add(spinnerFreq);
        p1.add(labelPathElevationAngle);
        p1.add(spinnerPathElevationAngle);
        p1.add(labelPolarizationTiltAngle);
        p1.add(spinnerPolarizationTiltAngle);
        p1.add(labelRainStart);
        p1.add(spinnerRainStart);
        p1.add(labelRainStop);
        p1.add(spinnerRainStop);
        p1.add(labelStep);
        p1.add(spinnerStep);

        modelFreq.addChangeListener(e -> {
            double val = Double.parseDouble(spinnerFreq.getValue().toString());
            attenuationDataset.setFreq(val);
        });

        modelPathElevationAngle.addChangeListener(e -> {
            double val = Double.parseDouble(spinnerPathElevationAngle.getValue().toString());
            attenuationDataset.setPathElevationAngle(val);
        });
        modelPolarizationTiltAngle.addChangeListener(e -> {
            double val = Double.parseDouble(spinnerPolarizationTiltAngle.getValue().toString());
            attenuationDataset.setPolarizationTiltAngle(val);
        });
        modelRainStart.addChangeListener(e -> {
            double val = Double.parseDouble(spinnerRainStart.getValue().toString());
            attenuationDataset.setRainRateStart(val);
        });
        modelRainStop.addChangeListener(e -> {
            double val = Double.parseDouble(spinnerRainStop.getValue().toString());
            attenuationDataset.setRainRateStop(val);
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
                            attenuationDataset.runGraph("", "Intensywność opadów [mm/h]", "Tłumienie jednostkowe [dB/km]", attenuationDataset.createDataset(ChartType.RAIN_RATE), attenuationDataset.getRainRateStart(), attenuationDataset.getRainRateStop()));
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
