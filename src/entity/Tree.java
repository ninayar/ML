package entity;

import java.util.ArrayList;

public class Tree {
	
	int feature;
	double max_gain;
	ArrayList<TrainVotes> root = new ArrayList<TrainVotes>();
	ArrayList<TrainVotes> left = new ArrayList<TrainVotes>();
	ArrayList<TrainVotes> right = new ArrayList<TrainVotes>();
	
	public int getFeature() {
		return feature;
	}
	public void setFeature(int feature) {
		this.feature = feature;
	}
	public double getMax_gain() {
		return max_gain;
	}
	public void setMax_gain(double max_gain) {
		this.max_gain = max_gain;
	}
	public ArrayList<TrainVotes> getLeft() {
		return left;
	}
	public void setLeft(ArrayList<TrainVotes> left) {
		this.left = left;
	}
	public ArrayList<TrainVotes> getRight() {
		return right;
	}
	public void setRight(ArrayList<TrainVotes> right) {
		this.right = right;
	}
	
	public ArrayList<TrainVotes> getRoot() {
		return root;
	}
	public void setRoot(ArrayList<TrainVotes> root) {
		this.root = root;
	}
	@Override
	public String toString() {
		return "Tree [feature=" + feature + ", max_gain=" + max_gain + ", root=" + root + ", left=" + left + ", right="
				+ right + "]";
	}
	
	

}
