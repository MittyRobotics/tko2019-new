package com.amhsrobotics.autonomous.graph;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.amhsrobotics.autonomous.constants.AutoConstants;
import com.amhsrobotics.autonomous.constants.AutoPaths;
import com.amhsrobotics.purepursuit.Path;
import com.amhsrobotics.purepursuit.PathGenerator;
import com.amhsrobotics.purepursuit.Waypoint;
import com.amhsrobotics.purepursuit.enums.PathType;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * @author imssbora
 */
public class Graph extends JPanel {


    public Graph(String title) {


        // Create dataset
        XYDataset dataset = new XYSeriesCollection();
        // Create chart
        JFreeChart chart = ChartFactory.createScatterPlot(
                "Field Points",
                "X-Axis (inches)", "Y-Axis (inches)", dataset);

        chart.removeLegend();

        chart.setBackgroundPaint(new Color(71, 71, 71));

        chart.getTitle().setPaint(new Color(158, 159, 157));



        //Changes background color
        XYPlot plot = (XYPlot)chart.getPlot();


        plot.setBackgroundPaint(Color.DARK_GRAY);
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        Rectangle rect = new Rectangle(2, 2);


        plot.getDomainAxis().setLabelPaint(new Color(158, 159, 157));
        plot.getRangeAxis().setLabelPaint(new Color(158, 159, 157));
        plot.getDomainAxis().setTickLabelPaint(new Color(158, 159, 157));
        plot.getRangeAxis().setTickLabelPaint(new Color(158, 159, 157));



        renderer.setSeriesPaint(0, new Color(0, 0xff, 85));
        renderer.setSeriesPaint(1, new Color(0x00, 0xff, 68));
        renderer.setSeriesPaint(3, new Color(255, 234, 0));
        renderer.setDefaultLinesVisible(false);


        plot.setRenderer(renderer);

        plot.setDomainGridlinePaint(new Color(0,0,0, 180));
        plot.setRangeGridlinePaint(new Color(0,0,0, 180));







        try {
            plot.setBackgroundImage(ImageIO.read(new File("images/halfField.jpeg")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        plot.getRangeAxis().setLowerBound(-15);
        plot.getRangeAxis().setUpperBound(340);
        plot.getRangeAxis().setUpperMargin(0);
        plot.getRangeAxis().setLowerMargin(0);

        plot.getDomainAxis().setLowerBound(-25);
        plot.getDomainAxis().setUpperBound(324);

        // Create Panel
        ChartPanel panel = new ChartPanel(chart);



        add(panel);
        setBackground(new Color(71, 71, 71));

    }





}