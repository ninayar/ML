package entity;

import java.util.Arrays;

public class Centroids {
	
	double[] centroid1;
	double[] centroid2;
	double category;
	
	public double getCategory() {
		return category;
	}
	public void setCategory(double category) {
		this.category = category;
	}
	public double[] getCentroid1() {
		return centroid1;
	}
	public void setCentroid1(double[] centroid1) {
		this.centroid1 = centroid1;
	}
	public double[] getCentroid2() {
		return centroid2;
	}
	public void setCentroid2(double[] centroid2) {
		this.centroid2 = centroid2;
	}
	@Override
	public String toString() {
		return "Centroids [centroid1=" + Arrays.toString(centroid1) + ", centroid2=" + Arrays.toString(centroid2)
				+ ", category=" + category + "]";
	}
	
}
