package com.amhsrobotics.autonomous.graph.rewrite;

import com.amhsrobotics.autonomous.constants.AutoConstants;
import com.amhsrobotics.purepursuit.Path;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;
import java.awt.geom.Point2D;

public class GraphAutoPath extends Graph {


	private static GraphAutoPath ourInstance = new GraphAutoPath();

	public static GraphAutoPath getInstance(){
		return ourInstance;
	}

	Path[] currentPaths;

	public GraphAutoPath() {
		super("", "", "");
		getChartPanel().setDomainZoomable(false);
		getChartPanel().setRangeZoomable(false);
		setFieldImage();
		graphFieldPoints();
		setPathGraph(new Path[]{});

	}

	public void graphFieldPoints() {
		Point2D.Double[] fieldPoints = new Point2D.Double[6];
		fieldPoints[0] = new Point2D.Double(AutoConstants.BLUE_LEFT_FRONT_HATCH.getWaypoint().getX(), AutoConstants.BLUE_LEFT_FRONT_HATCH.getWaypoint().getY());
		fieldPoints[1] = new Point2D.Double(AutoConstants.BLUE_RIGHT_FRONT_HATCH.getWaypoint().getX(), AutoConstants.BLUE_RIGHT_FRONT_HATCH.getWaypoint().getY());
		fieldPoints[2] = new Point2D.Double(AutoConstants.BLUE_LEFT_LOADER_STATION.getWaypoint().getX(), AutoConstants.BLUE_LEFT_LOADER_STATION.getWaypoint().getY());
		fieldPoints[3] = new Point2D.Double(AutoConstants.BLUE_RIGHT_LOADER_STATION.getWaypoint().getX(), AutoConstants.BLUE_RIGHT_LOADER_STATION.getWaypoint().getY());
		fieldPoints[4] = new Point2D.Double(AutoConstants.BLUE_LEFT_ROCKET_HATCH.getWaypoint().getX(), AutoConstants.BLUE_LEFT_ROCKET_HATCH.getWaypoint().getY());
		fieldPoints[5] = new Point2D.Double(AutoConstants.BLUE_RIGHT_ROCKET_HATCH.getWaypoint().getX(), AutoConstants.BLUE_RIGHT_ROCKET_HATCH.getWaypoint().getY());

		Point2D.Double[] startPoints = new Point2D.Double[3];
		startPoints[0] = new Point2D.Double(AutoConstants.BLUE_LEFT_START_POS.getWaypoint().getX(), AutoConstants.BLUE_LEFT_START_POS.getWaypoint().getY());
		startPoints[1] = new Point2D.Double(AutoConstants.BLUE_CENTER_START_POS.getWaypoint().getX(), AutoConstants.BLUE_CENTER_START_POS.getWaypoint().getY());
		startPoints[2] = new Point2D.Double(AutoConstants.BLUE_RIGHT_START_POS.getWaypoint().getX(), AutoConstants.BLUE_RIGHT_START_POS.getWaypoint().getY());

		Point2D.Double[] standardPoints = new Point2D.Double[9];
		standardPoints[0] = new Point2D.Double(AutoConstants.BLUE_LEFT_FRONT_HATCH_STANDARD.getWaypoint().getX(), AutoConstants.BLUE_LEFT_FRONT_HATCH_STANDARD.getWaypoint().getY());
		standardPoints[1] = new Point2D.Double(AutoConstants.BLUE_RIGHT_FRONT_HATCH_STANDARD.getWaypoint().getX(), AutoConstants.BLUE_RIGHT_FRONT_HATCH_STANDARD.getWaypoint().getY());
		standardPoints[2] = new Point2D.Double(AutoConstants.BLUE_LEFT_ROCKET_HATCH_STANDARD.getWaypoint().getX(), AutoConstants.BLUE_LEFT_ROCKET_HATCH_STANDARD.getWaypoint().getY());
		standardPoints[3] = new Point2D.Double(AutoConstants.BLUE_RIGHT_ROCKET_HATCH_STANDARD.getWaypoint().getX(), AutoConstants.BLUE_RIGHT_ROCKET_HATCH_STANDARD.getWaypoint().getY());
		standardPoints[4] = new Point2D.Double(AutoConstants.BLUE_CENTER_START_POS_STANDARD.getWaypoint().getX(), AutoConstants.BLUE_CENTER_START_POS_STANDARD.getWaypoint().getY());
		standardPoints[5] = new Point2D.Double(AutoConstants.BLUE_RIGHT_START_POS_STANDARD.getWaypoint().getX(), AutoConstants.BLUE_RIGHT_START_POS_STANDARD.getWaypoint().getY());
		standardPoints[6] = new Point2D.Double(AutoConstants.BLUE_LEFT_START_POS_STANDARD.getWaypoint().getX(), AutoConstants.BLUE_LEFT_START_POS_STANDARD.getWaypoint().getY());
		standardPoints[7] = new Point2D.Double(AutoConstants.BLUE_LEFT_LOADER_STATION_STANDARD.getWaypoint().getX(), AutoConstants.BLUE_LEFT_LOADER_STATION_STANDARD.getWaypoint().getY());
		standardPoints[8] = new Point2D.Double(AutoConstants.BLUE_RIGHT_LOADER_STATION_STANDARD.getWaypoint().getX(), AutoConstants.BLUE_RIGHT_LOADER_STATION_STANDARD.getWaypoint().getY());

		Point2D.Double[] helperPoints = new Point2D.Double[2];
		helperPoints[0] = new Point2D.Double(AutoConstants.BLUE_LEFT_HELPER_POINT.getWaypoint().getX(), AutoConstants.BLUE_LEFT_HELPER_POINT.getWaypoint().getY());
		helperPoints[1] = new Point2D.Double(AutoConstants.BLUE_RIGHT_HELPER_POINT.getWaypoint().getX(), AutoConstants.BLUE_RIGHT_HELPER_POINT.getWaypoint().getY());

		Point2D.Double[] fieldCornerPoints = new Point2D.Double[4];
		fieldCornerPoints[0] = new Point2D.Double(0, 0);
		fieldCornerPoints[1] = new Point2D.Double(AutoConstants.FIELD_LENGTH / 2, 0);
		fieldCornerPoints[2] = new Point2D.Double(AutoConstants.FIELD_LENGTH / 2, AutoConstants.FIELD_HEIGHT);
		fieldCornerPoints[3] = new Point2D.Double(0, AutoConstants.FIELD_HEIGHT);

		graphPointsInSeries(fieldPoints);
		graphPointsInSeries(startPoints);
		graphPointsInSeries(standardPoints);
		graphPointsInSeries(helperPoints);
		graphPointsInSeries(fieldCornerPoints);
	}

	public void setPathGraph(Path[] paths) {
		currentPaths = paths;
		getPlot().setRenderer(1,new CustomColorRenderer(true,true,currentPaths));
		getPlot().setDataset(1, pathDataset(paths));
		getChartPanel().repaint();
	}

	private XYDataset pathDataset(Path[] paths) {
		XYSeriesCollection dataset = new XYSeriesCollection();

		for (int i = 0; i < paths.length; i++) {

			dataset.addSeries(graphPath(paths[i], i));
		}


		return dataset;
	}

	private XYSeries graphPath(Path path, int index) {
		XYSeries series = new XYSeries("Path" + index);
		for (int i = 0; i < path.length(); i++) {
			series.add(path.get(i).getX(), path.get(i).getY());
		}
		return series;
	}

	public void removeGuessLines(){

	}

	public void graphGuessLines(double x, double y){
		graphPointsInSeries(new Point2D.Double[]{new Point2D.Double(x,-1000),new Point2D.Double(x,1000)},true,1);
		graphPointsInSeries(new Point2D.Double[]{new Point2D.Double(-1000,y),new Point2D.Double(1000,y)},1);

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
			if(row == 0){
				double velocity = path[row].get(col).getVelocity();
				double upperBounds = 0;
				for(int i = 0; i < path[row].length(); i++){
					if(path[row].get(i).getVelocity() > upperBounds){
						upperBounds = path[row].get(i).getVelocity();
					}
				}

				return Color.getHSBColor( (float) (velocity / upperBounds) / 4, 1f, 0.75f);

			}
			else{
				return Color.YELLOW;
			}

		}

		@Override
		public Shape getItemShape(int row, int column) {
			if(column %10 == 0){
				return new Rectangle(2,2);
			}
			else{
				return  new Rectangle(0,0);
			}


		}
	}


}
