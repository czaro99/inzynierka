package cezary.zaremba.projekt.inzynierski.charts;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;

import java.awt.*;
import java.io.IOException;

public abstract class ChartDrawer {

    public JFreeChart runGraph(String chartTitle, String xLabel, String yLabel, XYDataset dataset, double start, double stop) throws IOException {
        JFreeChart xylineChart = ChartFactory.createXYLineChart(
                chartTitle,
                xLabel,
                yLabel,
                dataset,
                PlotOrientation.VERTICAL,
                true, false, false);
        final XYPlot plot = xylineChart.getXYPlot();
        plot.setBackgroundPaint(new Color(255,255,255));
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, false);
        plot.setRangeGridlinePaint(new Color(0,0,0));
        plot.setDomainGridlinePaint(Color.BLACK);
        renderer.setSeriesPaint(0, Color.BLACK);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        renderer.setDefaultSeriesVisibleInLegend(false);
        plot.getDomainAxis().setRange(start,stop);
        plot.setRenderer(renderer);
        Font font = new Font("Dialog",Font.PLAIN, 25);
        plot.getDomainAxis().setLabelFont(font);
        plot.getRangeAxis().setLabelFont(font);
        return xylineChart;
    }
}
