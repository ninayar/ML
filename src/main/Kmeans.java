package main;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.opencsv.CSVReader;

import entity.Centroids;
import entity.TestVotes;
import entity.TrainVotes;

public class Kmeans {

	static double distance;
	static ArrayList<TrainVotes> train = new ArrayList<TrainVotes>();
	static ArrayList<TestVotes> test = new ArrayList<TestVotes>();
	static ArrayList<Double> d = new ArrayList<Double>();

	public static ArrayList<TrainVotes> train() {
		String csvFile = "D:/ninayar/workspace/ML/src/utils/votes-train.csv";
		String[] temp;
		ArrayList<TestVotes> test = new ArrayList<TestVotes>();
		int row = 0;
		try (CSVReader reader = new CSVReader(new FileReader(csvFile), ',')) {
			// while ((temp = reader.readNext()) != null) {
			for (row = 0; row < 2334; row++) {
				temp = reader.readNext();
				if (temp != null) {
					// use comma as separator
					TrainVotes v = new TrainVotes();
					for (int i = 0; i < 10; i++) {
						switch (i) {
						case 0:
							v.setDemocrat(Double.parseDouble(temp[i]));
							break;
						case 1:
							v.setPopulation(Double.parseDouble(temp[i]));
							break;
						case 2:
							v.setPopulation_change(Double.parseDouble(temp[i]));
							break;
						case 3:
							v.setAge65plus(Double.parseDouble(temp[i]));
							break;
						case 4:
							v.setBlack(Double.parseDouble(temp[i]));
							break;
						case 5:
							v.setHispanic(Double.parseDouble(temp[i]));
							break;
						case 6:
							v.setEdu_bachelors(Double.parseDouble(temp[i]));
							break;
						case 7:
							v.setIncome(Double.parseDouble(temp[i]));
							break;
						case 8:
							v.setPoverty(Double.parseDouble(temp[i]));
							break;
						case 9:
							v.setDensity(Double.parseDouble(temp[i]));
							break;
						}
					}
					train.add(v);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return train;
	}

	public static ArrayList<TestVotes> test() {

		String csvFile = "D:/ninayar/workspace/ML/src/utils/votes-test.csv";
		String[] temp;

		ArrayList<TestVotes> test = new ArrayList<TestVotes>();
		int row = 0;

		try (CSVReader reader = new CSVReader(new FileReader(csvFile), ',')) {
			// while ((temp = reader.readNext()) != null) {
			for (row = 0; row < 777; row++) {
				temp = reader.readNext();
				if (temp != null) {
					// use comma as separator
					TestVotes v = new TestVotes();

					for (int i = 0; i < 10; i++) {
						switch (i) {
						case 0:
							v.setDemocrat(Double.parseDouble(temp[i]));
							break;
						case 1:
							v.setPopulation(Double.parseDouble(temp[i]));
							break;
						case 2:
							v.setPopulation_change(Double.parseDouble(temp[i]));
							break;
						case 3:
							v.setAge65plus(Double.parseDouble(temp[i]));
							break;
						case 4:
							v.setBlack(Double.parseDouble(temp[i]));
							break;
						case 5:
							v.setHispanic(Double.parseDouble(temp[i]));
							break;
						case 6:
							v.setEdu_bachelors(Double.parseDouble(temp[i]));
							break;
						case 7:
							v.setIncome(Double.parseDouble(temp[i]));
							break;
						case 8:
							v.setPoverty(Double.parseDouble(temp[i]));
							break;
						case 9:
							v.setDensity(Double.parseDouble(temp[i]));
							break;
						}
					}
					test.add(v);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return test;
	}

	public static Centroids segment(TrainVotes[] t, Centroids c1) {
		// k = 2
		double[] centroid1 = { 0, 3000, 2, 33, 0.01, 0.21, 10, 40000, 20.5, 28 };// Republican
		double[] centroid2 = { 1, 5000, -2.7, 15, 0.01, 0.21, 10, 55000, 34.5, 100 };// Democrat
		c1.setCentroid1(centroid1);
		c1.setCentroid2(centroid2);
		double[] d1 = eucledianDistance(t, centroid1);
		double[] d2 = eucledianDistance(t, centroid2);
		double[] newCentroid = null;
		double[] temp;

		for (int i = 0; i < t.length; i++) {
			for (int j = 0; j < t.length; j++) {
				if (Double.compare(d1[j], d2[j]) < 0) {
					newCentroid = getNewCentroid(centroid1, t[j]);
					centroid1 = newCentroid;
					c1.setCentroid1(centroid1);
					if (centroid1[0] == 0) {
						// System.out.print("Republican");
						c1.setCategory(0);
					} else {
						// System.out.print("Democrat");
						c1.setCategory(1);
					}
				} else {
					newCentroid = getNewCentroid(centroid2, t[j]);
					centroid2 = newCentroid;
					c1.setCentroid2(centroid2);
					if (centroid1[0] == 0) {
						// System.out.print("Republican");
						c1.setCategory(0);
					} else {
						// System.out.print("Democrat");
						c1.setCategory(1);
					}
				}
			}

		}
		return c1;
	}

	public static Centroids testSet(TestVotes s, Centroids c2) {
		double[] centroid1 = c2.getCentroid1();
		double[] centroid2 = c2.getCentroid2();
		double d1 = eucledianDistanceTest(s, centroid1);
		double d2 = eucledianDistanceTest(s, centroid2);
		double[] newCentroid = null;
		double[] temp;

		// for (int i = 0; i < s.length; i++) {
		// for(int j=0; j<s.length;j++)
		// {
		if (Double.compare(d1, d2) < 0) {
			newCentroid = getNewCentroidTest(centroid1, s);
			centroid1 = newCentroid;
			c2.setCentroid1(centroid1);
			System.out.println("point is in centroid1");
			if (centroid1[0] == 0) {
				 System.out.print("Republican");
				c2.setCategory(0);
			} else {
				 System.out.print("Democrat");
				c2.setCategory(1);
			}
		} else {
			newCentroid = getNewCentroidTest(centroid2, s);
			centroid2 = newCentroid;
			c2.setCentroid2(centroid2);
			System.out.println("point is in centroid2");
			if (centroid1[0] == 0) {
				System.out.print("Republican");
				c2.setCategory(0);
			} else {
				System.out.print("Democrat");
				c2.setCategory(1);
			}
		}

		// }
		//
		// }
		return c2;

	}

	public static double[] eucledianDistance(TrainVotes[] t, double[] s) {
		double[] dist = new double[t.length];

		for (int i = 0; i < t.length; i++) {
			distance = Math.sqrt(Math.pow(t[i].getDemocrat() - s[0], 2) + Math.pow(t[i].getPopulation() - s[1], 2)
					+ Math.pow(t[i].getPopulation_change() - s[2], 2) + Math.pow(t[i].getAge65plus() - s[3], 2)
					+ Math.pow(t[i].getBlack() - s[4], 2) + Math.pow(t[i].getHispanic() - s[5], 2)
					+ Math.pow(t[i].getEdu_bachelors() - s[6], 2) + Math.pow(t[i].getIncome() - s[7], 2)
					+ Math.pow(t[i].getPoverty() - s[8], 2) + Math.pow(t[i].getDensity() - s[9], 2));
			dist[i] = distance;
		}

		return dist;

	}

	public static double eucledianDistanceTest(TestVotes s2, double[] s) {
		distance = Math.sqrt(Math.pow(s2.getDemocrat() - s[0], 2) + Math.pow(s2.getPopulation() - s[1], 2)
				+ Math.pow(s2.getPopulation_change() - s[2], 2) + Math.pow(s2.getAge65plus() - s[3], 2)
				+ Math.pow(s2.getBlack() - s[4], 2) + Math.pow(s2.getHispanic() - s[5], 2)
				+ Math.pow(s2.getEdu_bachelors() - s[6], 2) + Math.pow(s2.getIncome() - s[7], 2)
				+ Math.pow(s2.getPoverty() - s[8], 2) + Math.pow(s2.getDensity() - s[9], 2));

		return distance;

	}

	// public static double[] getCentroid(TrainVotes[] t) {
	// double a=0,b=0,c=0,d=0,e=0,f=0,g=0,h=0,l=0,m=0;
	//
	// for (int i = 0; i < t.length; i++) {
	// a = a + t[i].getDemocrat();
	// b = b + t[i].getPopulation();
	// c = c + t[i].getPopulation_change();
	// d = d + t[i].getAge65plus();
	// e = e + t[i].getBlack();
	// f = f + t[i].getHispanic();
	// g = g + t[i].getEdu_bachelors();
	// h = h + t[i].getIncome();
	// l = l + t[i].getPoverty();
	// m = m + t[i].getDensity();
	// }
	// double[] centroid={a/9,b/9,c/9,d/9,e/9,f/9,g/9,h/9,l/9,m/9};
	//
	// return centroid;
	// }

	public static double[] getNewCentroid(double[] centroidOld, TrainVotes t) {
		double centroid[] = { (centroidOld[0] + t.getDemocrat()) / 2, (centroidOld[1] + t.getPopulation()) / 2,
				(centroidOld[2] + t.getPopulation_change()) / 2, (centroidOld[3] + t.getAge65plus()) / 2,
				(centroidOld[4] + t.getBlack()) / 2, (centroidOld[5] + t.getHispanic()) / 2,
				(centroidOld[6] + t.getEdu_bachelors()) / 2, (centroidOld[7] + t.getIncome()) / 2,
				(centroidOld[8] + t.getPoverty()) / 2, (centroidOld[9] + t.getDensity()) / 2 };
		return centroid;

	}

	public static double[] getNewCentroidTest(double[] centroidOld, TestVotes s) {
		double centroid[] = { (centroidOld[0] + s.getDemocrat()) / 2, (centroidOld[1] + s.getPopulation()) / 2,
				(centroidOld[2] + s.getPopulation_change()) / 2, (centroidOld[3] + s.getAge65plus()) / 2,
				(centroidOld[4] + s.getBlack()) / 2, (centroidOld[5] + s.getHispanic()) / 2,
				(centroidOld[6] + s.getEdu_bachelors()) / 2, (centroidOld[7] + s.getIncome()) / 2,
				(centroidOld[8] + s.getPoverty()) / 2, (centroidOld[9] + s.getDensity()) / 2 };
		return centroid;

	}

	public static void main(String[] args) {
		Centroids c1 = new Centroids();
		Centroids newC1 = new Centroids();

		train = train();
		test = test();
		TrainVotes[] t = train.toArray(new TrainVotes[train.size()]);
		TestVotes[] s = test.toArray(new TestVotes[test.size()]);
		// double[] centroid =getCentroid(t);
		c1 = segment(t, c1);
		newC1 = testSet(s[3], c1);
		System.out.println(newC1.getCentroid1());
		System.out.println(newC1.getCategory());

	}

}
