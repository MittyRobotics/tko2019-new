package com.amhsrobotics.autonomous.graph.rewrite;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * @author imssbora
 */
public class Graph extends JPanel {

    private XYPlot plot;

    private ChartPanel chartPanel;

    private int currentSeriesID;


    public Graph(String title, String domainTitle, String rangeTitle) {

        //Panel graphics
        setBackground(new Color(71, 71, 71));

        // Create dataset
        XYDataset dataset = new XYSeriesCollection();

        // Create chart
        JFreeChart chart = ChartFactory.createScatterPlot(
                "",
                domainTitle, rangeTitle, dataset);



        //Chart graphics
        chart.removeLegend();
        chart.setBackgroundPaint(new Color(71, 71, 71));
        chart.getTitle().setPaint(new Color(158, 159, 157));


        //Create the XYPlot
        XYPlot plot = (XYPlot)chart.getPlot();


        //Plot Graphics
        plot.setBackgroundPaint(Color.DARK_GRAY);

        plot.getDomainAxis().setLabelPaint(new Color(158, 159, 157));
        plot.getRangeAxis().setLabelPaint(new Color(158, 159, 157));
        plot.getDomainAxis().setTickLabelPaint(new Color(158, 159, 157));
        plot.getRangeAxis().setTickLabelPaint(new Color(158, 159, 157));

        plot.setDomainGridlinePaint(new Color(0,0,0, 180));
        plot.setRangeGridlinePaint(new Color(0,0,0, 180));

        //Plot line and shape renderer
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, new Color(0, 0xff, 85));
        renderer.setSeriesPaint(1, new Color(0x00, 0xff, 68));
        renderer.setSeriesPaint(3, new Color(255, 234, 0));
        renderer.setDefaultLinesVisible(false);
        plot.setRenderer(renderer);

        // Create Panel
        ChartPanel panel = new ChartPanel(chart);

        //Add ChartPanel
        add(panel);

        //Set size of graph (standardized)
        panel.setPreferredSize(new Dimension((int)(582*1.5),(int)(595*1.5)));

        plot.setDataset(1,new XYSeriesCollection());

        this.plot = plot;
        this.chartPanel = panel;

    }

    public void setFieldImage(){
        //Set plot background
        try {
            plot.setBackgroundImage(ImageIO.read(new File("images/halfField.jpeg")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //domain
        plot.getDomainAxis().setLowerBound(-25);
        plot.getDomainAxis().setUpperBound(324);
        //range
        plot.getRangeAxis().setLowerBound(-15);
        plot.getRangeAxis().setUpperBound(340);
        plot.getRangeAxis().setUpperMargin(0);
        plot.getRangeAxis().setLowerMargin(0);
    }

    public void graphPointsInSeries(Point2D.Double[] points){
        graphPointsInSeries(points, 0);
    }

    public void graphPointsInSeries(Point2D.Double[] points, int datasetID){
        graphPointsInSeries(points,false,datasetID);
    }


    public void graphPointsInSeries(Point2D.Double[] points, boolean clear, int datasetID){
        XYSeriesCollection dataset = (XYSeriesCollection) plot.getDataset(datasetID);

        if(clear == true){
            dataset.removeAllSeries();
        }

        currentSeriesID++;

        XYSeries series = new XYSeries("series" + currentSeriesID);

        for(int i = 0; i < points.length; i++){
            series.add(points[i].getX(), points[i].getY());
        }

        dataset.addSeries(series);

        plot.setDataset(datasetID, dataset);
    }

    public void clearDataset(int datasetID){
        XYSeriesCollection dataset = (XYSeriesCollection) plot.getDataset(datasetID);

        dataset.removeAllSeries();

        plot.setDataset(datasetID, dataset);
    }

    public XYPlot getPlot(){
        return plot;
    }

    public ChartPanel getChartPanel(){
        return chartPanel;
    }






}