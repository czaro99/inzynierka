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

public class XYChart {


    private double rainRate = 5;
    private double pathElevationAngle = 10;
    private double polarizationTiltAngle = 45;
    private double freqStart = 1;
    private double freqStop = 300;


    public JFreeChart runGraph(String chartTitle, String xLabel, String yLabel) {
        JFreeChart xylineChart = ChartFactory.createXYLineChart(
                chartTitle,
                xLabel,
                yLabel,
                createDataset(),
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

    private XYDataset createDataset() {
        final XYSeries attenuationSeries = new XYSeries("Attenuation");

        Calculation calculation = new Calculation();



        for (double i = freqStart; i <= freqStop; i++) {

            double[] coefficients = calculation.calculateCoefficients(i,pathElevationAngle,polarizationTiltAngle);

            double attenuation = coefficients[0]*Math.pow(rainRate,coefficients[1]);

            attenuationSeries.add(i , attenuation);
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

    public void setPolarizationTiltAngle(double polarizationTiltAngle) {
        this.polarizationTiltAngle = polarizationTiltAngle;
    }
    public void setFreqStart(double freqStart) {
        this.freqStart = freqStart;
    }

    public void setFreqStop(double freqStop) {
        this.freqStop = freqStop;
    }

}
