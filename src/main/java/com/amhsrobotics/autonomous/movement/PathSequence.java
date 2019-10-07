package com.amhsrobotics.autonomous.movement;

import com.amhsrobotics.purepursuit.Path;

import java.util.Arrays;

public class PathSequence {

	private PathProperties[] pathProperties;


	private Path[] paths;


	private String name;

	public PathSequence(String name, PathProperties... pathProperties) {
		this.name = name;
		this.pathProperties = pathProperties;
		paths = new Path[pathProperties.length];
		for(int i = 0; i < pathProperties.length; i++){
			paths[i] = pathProperties[i].getPath();
		}
	}

	public PathProperties[] getPathProperties() {
		return pathProperties;
	}

	public void setPathProperties(PathProperties[] pathProperties) {
		this.pathProperties = pathProperties;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public Path[] getPaths() {
		return paths;
	}

	public void setPaths(Path[] paths) {
		this.paths = paths;
	}

}
