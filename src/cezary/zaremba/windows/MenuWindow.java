package cezary.zaremba.windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MenuWindow extends JFrame {

    public MenuWindow() {
        super("Projekt inżynierski");

        JButton freqButtonRate = new JButton(new AbstractAction("częstotliwości") {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChartFreqRateWindow chartFreqRateWindow = new ChartFreqRateWindow();
                dispose();
            }
        });

        JButton freqButton = new JButton(new AbstractAction("częstotliwości") {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChartFreqAttenuationWindow chartFreqAttenuationWindow = new ChartFreqAttenuationWindow();
                dispose();
            }
        });

        JButton rainButtonRate = new JButton(new AbstractAction("intensywności opadów") {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChartRainRateWindow chartRainRateWindow = new ChartRainRateWindow();
                dispose();
            }
        });

        JButton rainButton = new JButton(new AbstractAction("intensywności opadów") {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChartRainAttenuationWindow chartRainAttenuationWindow = new ChartRainAttenuationWindow();
                dispose();
            }
        });

        JButton elevationButtonRate = new JButton(new AbstractAction("kąta elewacji trajektorii fali radiowej") {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChartElevationRateWindow chartElevationRateWindow = new ChartElevationRateWindow();
                dispose();
            }
        });

        JButton elevationButton = new JButton(new AbstractAction("kąta elewacji trajektorii fali radiowej") {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChartElevationAttenuationWindow chartElevationAttenuationWindow = new ChartElevationAttenuationWindow();
                dispose();
            }
        });

        JButton polarizationButtonRate = new JButton(new AbstractAction("kąta polaryzacji") {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChartPolarizationRateWindow chartPolarizationRateWindow = new ChartPolarizationRateWindow();
                dispose();
            }
        });

        JButton polarizationButton = new JButton(new AbstractAction("kąta polaryzacji") {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChartPolarizationAttenuationWindow chartPolarizationAttenuationWindow = new ChartPolarizationAttenuationWindow();
                dispose();
            }
        });

        JButton freqButtonCoefficientAlfa = new JButton(new AbstractAction("alfa w funkcji częstotliwości") {
            @Override
            public void actionPerformed(ActionEvent e) {
                CoefficientAlfaFreqWindow coefficientAlfaFreqWindow = new CoefficientAlfaFreqWindow();
                dispose();
            }
        });

        JButton freqButtonCoefficientK = new JButton(new AbstractAction("k w funkcji częstotliwości") {
            @Override
            public void actionPerformed(ActionEvent e) {
                CoefficientKFreqWindow coefficientKFreqWindow = new CoefficientKFreqWindow();
                dispose();
            }
        });

        JLabel labelPlotRate = new JLabel("Tłumienie jednostkowe w funkcji:");
        JLabel labelPlot = new JLabel("Tłumienie w funkcji:");
        JLabel labelPlotCoeff = new JLabel("Współczynnik:");

        setLayout(new GridLayout(3, 5));


        add(labelPlotRate);
        add(freqButtonRate);
        add(rainButtonRate);
        add(elevationButtonRate);
        add(polarizationButtonRate);
        add(labelPlot);
        add(freqButton);
        add(rainButton);
        add(elevationButton);
        add(polarizationButton);
        add(labelPlotCoeff);
        add(freqButtonCoefficientAlfa);
        add(freqButtonCoefficientK);
        add(new JLabel());
        add(new JLabel());


        pack();
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }


}
