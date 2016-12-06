package entity;

public class Cluster {

	double closest;
	double second_closest;
	int closest_index;
	int second_closest_index;
	
	public int getClosest_index() {
		return closest_index;
	}
	public void setClosest_index(int closest_index) {
		this.closest_index = closest_index;
	}
	public int getSecond_closest_index() {
		return second_closest_index;
	}
	public void setSecond_closest_index(int second_closest_index) {
		this.second_closest_index = second_closest_index;
	}
	public double getClosest() {
		return closest;
	}
	public void setClosest(double closest) {
		this.closest = closest;
	}
	public double getSecond_closest() {
		return second_closest;
	}
	public void setSecond_closest(double second_closest) {
		this.second_closest = second_closest;
	}
	public int getIndex() {
		return closest_index;
	}
	public void setIndex(int index) {
		this.closest_index = index;
	}
	@Override
	public String toString() {
		return "Cluster [closest=" + closest + ", second_closest=" + second_closest + ", closest_index=" + closest_index
				+ ", second_closest_index=" + second_closest_index + "]";
	}
	
	
}
