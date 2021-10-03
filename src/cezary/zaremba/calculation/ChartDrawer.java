package cezary.zaremba.calculation;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;

import java.awt.*;
import java.io.IOException;

public class ChartDrawer {

    public JFreeChart runGraph(String chartTitle, String xLabel, String yLabel, XYDataset dataset) throws IOException {
        JFreeChart xylineChart = ChartFactory.createXYLineChart(
                chartTitle,
                xLabel,
                yLabel,
                dataset,
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
}
