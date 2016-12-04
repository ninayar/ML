package entity;

import java.util.Arrays;

public class Knn_entity {
	
	double eucledianDist;
	int index;
	double category;
	double[] distance_array;
	
	public double[] getDistance_array() {
		return distance_array;
	}
	public void setDistance_array(double[] distance_array) {
		this.distance_array = distance_array;
	}
	public double getCategory() {
		return category;
	}
	public void setCategory(double category) {
		this.category = category;
	}
	public double getEucledianDist() {
		return eucledianDist;
	}
	public void setEucledianDist(double eucledianDist) {
		this.eucledianDist = eucledianDist;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	@Override
	public String toString() {
		return "Knn_entity [eucledianDist=" + eucledianDist + ", index=" + index + ", category=" + category
				+ ", distance_array=" + Arrays.toString(distance_array) + "]";
	}
	
	

}
