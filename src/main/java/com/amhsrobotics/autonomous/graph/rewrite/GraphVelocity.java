package com.amhsrobotics.autonomous.graph.rewrite;


import com.amhsrobotics.autonomous.constants.AutoPaths;
import com.amhsrobotics.purepursuit.Path;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

import java.awt.*;
import java.awt.geom.Point2D;


public class GraphVelocity extends Graph {

	private static GraphVelocity ourInstance = new GraphVelocity();

	public static GraphVelocity getInstance(){
		return ourInstance;
	}

	Path[] currentPaths;

	public GraphVelocity() {
		super("", "Position (in)", "Velocity (in/s)");
		setVelocityGraph(AutoPaths.BLUE_RIGHT_HATCH_CARGOSHIP_HATCH_ROCKET);
		getPlot().setRenderer(new CustomColorRenderer(true, false, currentPaths));
	}

	public void setVelocityGraph(Path[] paths) {
		currentPaths = paths;


		int length = 0;

		double maxVelocity = 0;

		for (int i = 0; i < paths.length; i++) {
			length += paths[i].length();
			for(int a = 0; a < paths[i].length(); a++){
				if(paths[i].get(a).getVelocity() > maxVelocity){
					maxVelocity = paths[i].get(a).getVelocity();
				}
			}
		}

		Point2D.Double[] points = new Point2D.Double[length];

		clearDataset(0);


		int currentLength = 0;
		double currentPosition = 0;
		for (int i = 0; i < paths.length; i++) {
			for (int a = 0; a < paths[i].length(); a++) {
				points[currentLength + a] = new Point2D.Double(paths[i].get(a).getPosition() + currentPosition, paths[i].get(a).getVelocity());
			}

			currentLength += paths[i].length();
			currentPosition += paths[i].get(paths[i].length() - 1).getPosition();
			graphPointsInSeries(new Point2D.Double[]{new Point2D.Double(currentPosition,0),new Point2D.Double(currentPosition,maxVelocity)});
		}

		graphPointsInSeries(points, 0);

		getChartPanel().repaint();
	}

	private class CustomColorRenderer extends XYLineAndShapeRenderer {

		private double[] colorByVelocity;
		private Path[] path;

		public CustomColorRenderer(boolean lines, boolean shapes, Path[] path) {
			super(lines, shapes);
			this.path = path;
		}

//		@Override
//		public Paint getItemPaint(int row, int col) {
////			System.out.println( row);
////			double velocity = path[row].get(col).getVelocity();
////			double upperBounds = 0;
////			for (int i = 0; i < path[row].length(); i++) {
////				if (path[row].get(i).getVelocity() > upperBounds) {
////					upperBounds = path[row].get(i).getVelocity();
////				}
////			}
////
////			return Color.getHSBColor((float) (velocity / upperBounds) / 4, 1f, 0.75f);
//			return Color.CYAN;
//
//		}

		@Override
		public Shape getItemShape(int row, int column) {
			return new Rectangle(2, 2);
		}
	}

}
