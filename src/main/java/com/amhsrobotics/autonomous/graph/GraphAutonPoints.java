package com.amhsrobotics.autonomous.graph;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import com.amhsrobotics.autonomous.constants.AutoConstants;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * @author imssbora
 */
public class GraphAutonPoints extends JFrame {
    private static final long serialVersionUID = 6294689542092367723L;

    public GraphAutonPoints(String title) {
        super(title);

        // Create dataset
        XYDataset dataset = createDataset();

        // Create chart
        JFreeChart chart = ChartFactory.createScatterPlot(
                "Field Points",
                "X-Axis", "Y-Axis", dataset);


        //Changes background color
        XYPlot plot = (XYPlot)chart.getPlot();
        plot.setBackgroundPaint(Color.DARK_GRAY);


        // Create Panel
        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }

    private XYDataset createDataset() {
        XYSeriesCollection dataset = new XYSeriesCollection();

        //Boys (Age,weight) series
        XYSeries series1 = new XYSeries("Field Points");
        series1.add(AutoConstants.BLUE_LEFT_FRONT_HATCH.getWaypoint().getX(), AutoConstants.BLUE_LEFT_FRONT_HATCH.getWaypoint().getY());
        series1.add(AutoConstants.BLUE_RIGHT_FRONT_HATCH.getWaypoint().getX(), AutoConstants.BLUE_RIGHT_FRONT_HATCH.getWaypoint().getY());
        series1.add(AutoConstants.BLUE_LEFT_LOADER_STATION.getWaypoint().getX(), AutoConstants.BLUE_LEFT_LOADER_STATION.getWaypoint().getY());
        series1.add(AutoConstants.BLUE_RIGHT_LOADER_STATION.getWaypoint().getX(), AutoConstants.BLUE_RIGHT_LOADER_STATION.getWaypoint().getY());
        series1.add(AutoConstants.BLUE_LEFT_ROCKET_HATCH.getWaypoint().getX(), AutoConstants.BLUE_LEFT_ROCKET_HATCH.getWaypoint().getY());
        series1.add(AutoConstants.BLUE_RIGHT_ROCKET_HATCH.getWaypoint().getX(), AutoConstants.BLUE_RIGHT_ROCKET_HATCH.getWaypoint().getY());


        dataset.addSeries(series1);

        XYSeries series2 = new XYSeries("Start Pos");
        series2.add(AutoConstants.BLUE_LEFT_START_POS.getWaypoint().getX(), AutoConstants.BLUE_LEFT_START_POS.getWaypoint().getY());
        series2.add(AutoConstants.BLUE_CENTER_START_POS.getWaypoint().getX(), AutoConstants.BLUE_CENTER_START_POS.getWaypoint().getY());
        series2.add(AutoConstants.BLUE_RIGHT_START_POS.getWaypoint().getX(), AutoConstants.BLUE_RIGHT_START_POS.getWaypoint().getY());



        dataset.addSeries(series2);


        XYSeries series3 = new XYSeries("Standard Pos");
        series3.add(AutoConstants.BLUE_LEFT_FRONT_HATCH_STANDARD.getWaypoint().getX(), AutoConstants.BLUE_LEFT_FRONT_HATCH_STANDARD.getWaypoint().getY());
        series3.add(AutoConstants.BLUE_RIGHT_FRONT_HATCH_STANDARD.getWaypoint().getX(), AutoConstants.BLUE_RIGHT_FRONT_HATCH_STANDARD.getWaypoint().getY());
        series3.add(AutoConstants.BLUE_LEFT_ROCKET_HATCH_STANDARD.getWaypoint().getX(), AutoConstants.BLUE_LEFT_ROCKET_HATCH_STANDARD.getWaypoint().getY());
        series3.add(AutoConstants.BLUE_RIGHT_ROCKET_HATCH_STANDARD.getWaypoint().getX(), AutoConstants.BLUE_RIGHT_ROCKET_HATCH_STANDARD.getWaypoint().getY());
        series3.add(AutoConstants.BLUE_CENTER_START_POS_STANDARD.getWaypoint().getX(), AutoConstants.BLUE_CENTER_START_POS_STANDARD.getWaypoint().getY());
        series3.add(AutoConstants.BLUE_RIGHT_START_POS_STANDARD.getWaypoint().getX(), AutoConstants.BLUE_RIGHT_START_POS_STANDARD.getWaypoint().getY());
        series3.add(AutoConstants.BLUE_LEFT_START_POS_STANDARD.getWaypoint().getX(), AutoConstants.BLUE_LEFT_START_POS_STANDARD.getWaypoint().getY());
        series3.add(AutoConstants.BLUE_LEFT_LOADER_STATION_STANDARD.getWaypoint().getX(), AutoConstants.BLUE_LEFT_LOADER_STATION_STANDARD.getWaypoint().getY());
        series3.add(AutoConstants.BLUE_RIGHT_LOADER_STATION_STANDARD.getWaypoint().getX(), AutoConstants.BLUE_RIGHT_LOADER_STATION_STANDARD.getWaypoint().getY());






        dataset.addSeries(series3);



        XYSeries series4 = new XYSeries("Helper Pos");
        series4.add(AutoConstants.LEFT_HELPER_POINT.getWaypoint().getX(), AutoConstants.LEFT_HELPER_POINT.getWaypoint().getY());
        series4.add(AutoConstants.RIGHT_HELPER_POINT.getWaypoint().getX(), AutoConstants.RIGHT_HELPER_POINT.getWaypoint().getY());





        dataset.addSeries(series4);


        return dataset;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GraphAutonPoints example = new GraphAutonPoints("test");
            example.setSize(800, 400);
            example.setLocationRelativeTo(null);
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            example.setVisible(true);
        });

    }
}