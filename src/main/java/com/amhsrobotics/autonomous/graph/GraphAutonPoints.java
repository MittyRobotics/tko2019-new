package com.amhsrobotics.autonomous.graph;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import com.amhsrobotics.autonomous.constants.AutoConstants;
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
public class GraphAutonPoints extends JFrame {
    private static final long serialVersionUID = 6294689542092367723L;

    private ArrayList<Path> currentPath = new ArrayList();

    public GraphAutonPoints(String title) {
        super(title);

        // Create dataset
        XYDataset dataset = createDataset();
        XYDataset pathDataset = pathDataset();

        // Create chart
        JFreeChart chart = ChartFactory.createScatterPlot(
                "Field Points",
                "X-Axis", "Y-Axis", dataset);



        //Changes background color
        XYPlot plot = (XYPlot)chart.getPlot();
        plot.setDataset(1,pathDataset);
        plot.setBackgroundPaint(Color.DARK_GRAY);
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        CustomColorRenderer colorRenderer = new CustomColorRenderer(true,true, currentPath.toArray(new Path[currentPath.size()]));

        Rectangle rect = new Rectangle(2, 2);
        for(int i = 0; i < currentPath.size(); i++){
            colorRenderer.setSeriesShape(i,rect);
        }





        renderer.setSeriesPaint(0, new Color(0, 0xff, 85));
        renderer.setSeriesPaint(1, new Color(0x00, 0xff, 68));
        renderer.setSeriesPaint(3, new Color(255, 234, 0));
        renderer.setDefaultLinesVisible(false);


        plot.setRenderer(renderer);
        plot.setRenderer(1,colorRenderer);

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
        setContentPane(panel);
    }

    private XYDataset createDataset() {
        XYSeriesCollection dataset = new XYSeriesCollection();

        //Boys (Age,weight) series
        XYSeries series0 = new XYSeries("Field Points");
        series0.add(AutoConstants.BLUE_LEFT_FRONT_HATCH.getWaypoint().getX(), AutoConstants.BLUE_LEFT_FRONT_HATCH.getWaypoint().getY());
        series0.add(AutoConstants.BLUE_RIGHT_FRONT_HATCH.getWaypoint().getX(), AutoConstants.BLUE_RIGHT_FRONT_HATCH.getWaypoint().getY());
        series0.add(AutoConstants.BLUE_LEFT_LOADER_STATION.getWaypoint().getX(), AutoConstants.BLUE_LEFT_LOADER_STATION.getWaypoint().getY());
        series0.add(AutoConstants.BLUE_RIGHT_LOADER_STATION.getWaypoint().getX(), AutoConstants.BLUE_RIGHT_LOADER_STATION.getWaypoint().getY());
        series0.add(AutoConstants.BLUE_LEFT_ROCKET_HATCH.getWaypoint().getX(), AutoConstants.BLUE_LEFT_ROCKET_HATCH.getWaypoint().getY());
        series0.add(AutoConstants.BLUE_RIGHT_ROCKET_HATCH.getWaypoint().getX(), AutoConstants.BLUE_RIGHT_ROCKET_HATCH.getWaypoint().getY());


        dataset.addSeries(series0);

        XYSeries series1 = new XYSeries("Start Pos");
        series1.add(AutoConstants.BLUE_LEFT_START_POS.getWaypoint().getX(), AutoConstants.BLUE_LEFT_START_POS.getWaypoint().getY());
        series1.add(AutoConstants.BLUE_CENTER_START_POS.getWaypoint().getX(), AutoConstants.BLUE_CENTER_START_POS.getWaypoint().getY());
        series1.add(AutoConstants.BLUE_RIGHT_START_POS.getWaypoint().getX(), AutoConstants.BLUE_RIGHT_START_POS.getWaypoint().getY());



        dataset.addSeries(series1);


        XYSeries series2 = new XYSeries("Standard Pos");
        series2.add(AutoConstants.BLUE_LEFT_FRONT_HATCH_STANDARD.getWaypoint().getX(), AutoConstants.BLUE_LEFT_FRONT_HATCH_STANDARD.getWaypoint().getY());
        series2.add(AutoConstants.BLUE_RIGHT_FRONT_HATCH_STANDARD.getWaypoint().getX(), AutoConstants.BLUE_RIGHT_FRONT_HATCH_STANDARD.getWaypoint().getY());
        series2.add(AutoConstants.BLUE_LEFT_ROCKET_HATCH_STANDARD.getWaypoint().getX(), AutoConstants.BLUE_LEFT_ROCKET_HATCH_STANDARD.getWaypoint().getY());
        series2.add(AutoConstants.BLUE_RIGHT_ROCKET_HATCH_STANDARD.getWaypoint().getX(), AutoConstants.BLUE_RIGHT_ROCKET_HATCH_STANDARD.getWaypoint().getY());
        series2.add(AutoConstants.BLUE_CENTER_START_POS_STANDARD.getWaypoint().getX(), AutoConstants.BLUE_CENTER_START_POS_STANDARD.getWaypoint().getY());
        series2.add(AutoConstants.BLUE_RIGHT_START_POS_STANDARD.getWaypoint().getX(), AutoConstants.BLUE_RIGHT_START_POS_STANDARD.getWaypoint().getY());
        series2.add(AutoConstants.BLUE_LEFT_START_POS_STANDARD.getWaypoint().getX(), AutoConstants.BLUE_LEFT_START_POS_STANDARD.getWaypoint().getY());
        series2.add(AutoConstants.BLUE_LEFT_LOADER_STATION_STANDARD.getWaypoint().getX(), AutoConstants.BLUE_LEFT_LOADER_STATION_STANDARD.getWaypoint().getY());
        series2.add(AutoConstants.BLUE_RIGHT_LOADER_STATION_STANDARD.getWaypoint().getX(), AutoConstants.BLUE_RIGHT_LOADER_STATION_STANDARD.getWaypoint().getY());






        dataset.addSeries(series2);



        XYSeries series3 = new XYSeries("Helper Pos");
        series3.add(AutoConstants.BLUE_LEFT_HELPER_POINT.getWaypoint().getX(), AutoConstants.BLUE_LEFT_HELPER_POINT.getWaypoint().getY());
        series3.add(AutoConstants.BLUE_RIGHT_HELPER_POINT.getWaypoint().getX(), AutoConstants.BLUE_RIGHT_HELPER_POINT.getWaypoint().getY());





        dataset.addSeries(series3);

        XYSeries series4 = new XYSeries("Field Corners");
//        series4.add(-10,-1);
//        series4.add(AutoConstants.FIELD_LENGTH/2 - 18,0);
//
//        series4.add(AutoConstants.FIELD_LENGTH/2 - 18, AutoConstants.FIELD_HEIGHT);
//
//        series4.add(-10, AutoConstants.FIELD_HEIGHT);
        series4.add(0,0);
        series4.add(AutoConstants.FIELD_LENGTH/2 ,0);

        series4.add(AutoConstants.FIELD_LENGTH/2, AutoConstants.FIELD_HEIGHT);

        series4.add(0, AutoConstants.FIELD_HEIGHT);


        dataset.addSeries(series4);

        return dataset;
    }




    private XYDataset pathDataset() {
        XYSeriesCollection dataset = new XYSeriesCollection();


        dataset.addSeries(graphPath(AutoConstants.BLUE_LEFT_START_POS, AutoConstants.BLUE_LEFT_FRONT_HATCH,0));

        dataset.addSeries(graphPath(AutoConstants.BLUE_LEFT_FRONT_HATCH_REVERSED, AutoConstants.BLUE_LEFT_HELPER_POINT,1));

        dataset.addSeries(graphPath(AutoConstants.BLUE_LEFT_HELPER_POINT_REVERSED, AutoConstants.BLUE_LEFT_LOADER_STATION,2));

        dataset.addSeries(graphPath(AutoConstants.BLUE_LEFT_LOADER_STATION_REVERSED, AutoConstants.BLUE_LEFT_HELPER_POINT,3));


        dataset.addSeries(graphPath(AutoConstants.BLUE_LEFT_HELPER_POINT_REVERSED, AutoConstants.BLUE_LEFT_ROCKET_HATCH,4));







        return dataset;
    }

    public XYSeries graphPath(Waypoint startPoint, Waypoint endPoint, int index){
        XYSeries series = new XYSeries("Path" + index);
        Path path = PathGenerator.getInstance().generate(new Waypoint[]{startPoint,endPoint}, PathType.CUBIC_HERMITE_PATH,5,5,40,20);
        for (int i = 0; i < path.length(); i ++){
            series.add(path.get(i).getX(), path.get(i).getY());
        }
        this.currentPath.add( path);
        return series;

    }

    private class CustomColorRenderer extends XYLineAndShapeRenderer {

        private double[] colorByVelocity;
        private Path[] path;

        public CustomColorRenderer(boolean lines, boolean shapes, Path[] path) {
            super(lines, shapes);
            this.path = path;
        }

        @Override
        public Paint getItemPaint(int row, int col) {
            double velocity = path[row].get(col).getVelocity();
            double upperBounds = 0;
            for(int i = 0; i < path[row].length(); i++){
                if(path[row].get(i).getVelocity() > upperBounds){
                    upperBounds = path[row].get(i).getVelocity();
                }
            }

                return Color.getHSBColor( (float) (velocity / upperBounds) / 4, 1f, 0.75f);

        }
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