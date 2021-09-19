package cezary.zaremba.core;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;

public class RainAttenuationChart {


    private double rainRate = 5;
    private double rainRateStart = 0.1;
    private double rainRateStop = 10;
    private double pathElevationAngle = 10;
    private double pathElevationAngleStart = 1;
    private double pathElevationAngleStop = 10;
    private double polarizationTiltAngle = 45;
    private double polarizationTiltAngleStart = 0;
    private double polarizationTiltAngleStop = 90;
    private double freq = 1;
    private double freqStart = 1;
    private double freqStop = 300;


    public JFreeChart runGraph(String chartTitle, String xLabel, String yLabel, String type) {
        JFreeChart xylineChart = ChartFactory.createXYLineChart(
                chartTitle,
                xLabel,
                yLabel,
                createDataset(type),
                PlotOrientation.VERTICAL,
                true, false, false);
        final XYPlot plot = xylineChart.getXYPlot();
        plot.setBackgroundPaint(new Color(191, 191, 191));
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, false);
        renderer.setSeriesPaint(0, Color.BLACK);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        plot.setRenderer(renderer);
        return xylineChart;
}



    private XYDataset createDataset(String type) {
        final XYSeries attenuationSeries = new XYSeries("Attenuation");

        CoefficientsCalculation coefficientsCalculation = new CoefficientsCalculation();
        switch (type){
            case "Freq":
                for (double i = freqStart; i <= freqStop; i+=0.1) {
                    double[] coefficients = coefficientsCalculation.calculateCoefficients(i,pathElevationAngle,polarizationTiltAngle);
                    double attenuation = coefficients[0]*Math.pow(rainRate,coefficients[1]);
                    attenuationSeries.add(i , attenuation);
                }
                break;
            case "Rain rate":
                for (double i = rainRateStart; i <= rainRateStop; i+=0.1) {
                    double[] coefficients = coefficientsCalculation.calculateCoefficients(freq,pathElevationAngle,polarizationTiltAngle);
                    double attenuation = coefficients[0]*Math.pow(i,coefficients[1]);
                    attenuationSeries.add(i , attenuation);
                }
                break;
            case "Elevation":
                for (double i = pathElevationAngleStart; i <= pathElevationAngleStop; i+=0.1) {
                    double[] coefficients = coefficientsCalculation.calculateCoefficients(freq,i,polarizationTiltAngle);
                    double attenuation = coefficients[0]*Math.pow(rainRate,coefficients[1]);
                    attenuationSeries.add(i , attenuation);
                }
                break;
            case "Polarization":
                for (double i = polarizationTiltAngleStart; i <= polarizationTiltAngleStop; i+=0.1) {
                    double[] coefficients = coefficientsCalculation.calculateCoefficients(freq,pathElevationAngle,i);
                    double attenuation = coefficients[0]*Math.pow(rainRate,coefficients[1]);
                    attenuationSeries.add(i , attenuation);
                }
                break;
                default:
                break;
        }



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
    public void setPolarizationTiltAngle(double polarizationTiltAngle) { this.polarizationTiltAngle = polarizationTiltAngle; }
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
