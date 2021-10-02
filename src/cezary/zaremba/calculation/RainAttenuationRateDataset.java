package cezary.zaremba.calculation;

import cezary.zaremba.file.FileManager;
import cezary.zaremba.model.ChartType;
import cezary.zaremba.model.Coefficients;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RainAttenuationRateDataset extends RainAttenuationChart{


    private double rainRate = 5;
    private double rainRateStart = 1;
    private double rainRateStop = 200;
    private double pathElevationAngle = 10;
    private double pathElevationAngleStart = 1;
    private double pathElevationAngleStop = 90;
    private double polarizationTiltAngle = 45;
    private double polarizationTiltAngleStart = 1;
    private double polarizationTiltAngleStop = 90;
    private double freq = 1;
    private double freqStart = 1;
    private double freqStop = 300;
    private final FileManager fileManager = new FileManager();


    public XYDataset createDataset(String type) throws IOException {
        final XYSeries attenuationSeries = new XYSeries("Attenuation");

        CoefficientsCalculation coefficientsCalculation = new CoefficientsCalculation();
        List<String[]> results = new ArrayList<>();
        switch (type) {
            case ChartType.FREQUENCY:
                for (double i = freqStart; i <= freqStop; i += 1) {
                    Coefficients coefficients = coefficientsCalculation.calculateCoefficients(i, pathElevationAngle, polarizationTiltAngle);
                    double attenuation = coefficients.getK() * Math.pow(rainRate, coefficients.getAlfa());
                    attenuationSeries.add(i, attenuation);
                    results.add(new String[]{String.valueOf(i), String.valueOf(attenuation)});
                }

                break;
            case ChartType.RAIN_RATE:
                for (double i = rainRateStart; i <= rainRateStop; i += 1) {
                    Coefficients coefficients = coefficientsCalculation.calculateCoefficients(freq, pathElevationAngle, polarizationTiltAngle);
                    double attenuation = coefficients.getK() * Math.pow(i, coefficients.getAlfa());
                    attenuationSeries.add(i, attenuation);
                    results.add(new String[]{String.valueOf(i), String.valueOf(attenuation)});
                }
                break;
            case ChartType.ELEVATION:
                for (double i = pathElevationAngleStart; i <= pathElevationAngleStop; i += 1) {
                    Coefficients coefficients = coefficientsCalculation.calculateCoefficients(freq, i, polarizationTiltAngle);
                    double attenuation = coefficients.getK() * Math.pow(rainRate, coefficients.getAlfa());
                    attenuationSeries.add(i, attenuation);
                    results.add(new String[]{String.valueOf(i), String.valueOf(attenuation)});
                }
                break;
            case ChartType.POLARIZATION:
                for (double i = polarizationTiltAngleStart; i <= polarizationTiltAngleStop; i += 1) {
                    Coefficients coefficients = coefficientsCalculation.calculateCoefficients(freq, pathElevationAngle, i);
                    double attenuation = coefficients.getK() * Math.pow(rainRate, coefficients.getAlfa());
                    attenuationSeries.add(i, attenuation);
                    results.add(new String[]{String.valueOf(i), String.valueOf(attenuation)});
                }
                break;
            default:
                break;

        }
        fileManager.writeToCSV(results);
        results.clear();


        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(attenuationSeries);
        return dataset;
    }

    public void setRainRate(double rainRate) {
        this.rainRate = rainRate;
    }

    public void setPathElevationAngle(double pathElevationAngle) {
        this.pathElevationAngle = pathElevationAngle;
    }

    public void setPolarizationTiltAngle(double polarizationTiltAngle) {
        this.polarizationTiltAngle = polarizationTiltAngle;
    }

    public void setFreqStart(double freqStart) {
        this.freqStart = freqStart;
    }

    public void setFreqStop(double freqStop) {
        this.freqStop = freqStop;
    }

    public void setRainRateStart(double rainRateStart) {
        this.rainRateStart = rainRateStart;
    }

    public void setRainRateStop(double rainRateStop) {
        this.rainRateStop = rainRateStop;
    }

    public void setFreq(double freq) {
        this.freq = freq;
    }


    public void setPathElevationAngleStart(double pathElevationAngleStart) {
        this.pathElevationAngleStart = pathElevationAngleStart;
    }

    public void setPathElevationAngleStop(double pathElevationAngleStop) {
        this.pathElevationAngleStop = pathElevationAngleStop;
    }

    public void setPolarizationTiltAngleStart(double polarizationTiltAngleStart) {
        this.polarizationTiltAngleStart = polarizationTiltAngleStart;
    }

    public void setPolarizationTiltAngleStop(double polarizationTiltAngleStop) {
        this.polarizationTiltAngleStop = polarizationTiltAngleStop;
    }
}
