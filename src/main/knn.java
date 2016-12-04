package main;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import com.opencsv.CSVReader;

import entity.Knn_entity;
import entity.TestVotes;
import entity.TrainVotes;

public class knn {

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

	public static double eucledianDistance(TrainVotes t, TestVotes s) {
		// double[] dist = new double[t.length];
		// Knn_entity knn=new Knn_entity();

		// for (int i = 0; i < t.length; i++) {
		// distance = Math.sqrt(Math.pow(t[i].getDemocrat() - s.getDemocrat(),
		// 2)
		// + Math.pow(t[i].getPopulation() - s.getPopulation(), 2)
		// + Math.pow(t[i].getPopulation_change() - s.getPopulation_change(), 2)
		// + Math.pow(t[i].getAge65plus() - s.getAge65plus(), 2)
		// + Math.pow(t[i].getBlack() - s.getBlack(), 2)
		// + Math.pow(t[i].getHispanic() - s.getHispanic(), 2)
		// + Math.pow(t[i].getEdu_bachelors() - s.getEdu_bachelors(), 2)
		// + Math.pow(t[i].getIncome() - s.getIncome(), 2)
		// + Math.pow(t[i].getPoverty() - s.getPoverty(), 2)
		// + Math.pow(t[i].getDensity() - s.getDensity(), 2));
		// knn.setEucledianDist(distance);
		// knn.setIndex(i);
		// knn.setCategory(t[i].getDemocrat());
		// dist[i] = distance;
		// }
		// knn.setDistance(dist);
		// return knn;

		distance = Math.sqrt(Math.pow(t.getDemocrat() - s.getDemocrat(), 2)
				+ Math.pow(t.getPopulation() - s.getPopulation(), 2)
				+ Math.pow(t.getPopulation_change() - s.getPopulation_change(), 2)
				+ Math.pow(t.getAge65plus() - s.getAge65plus(), 2) + Math.pow(t.getBlack() - s.getBlack(), 2)
				+ Math.pow(t.getHispanic() - s.getHispanic(), 2)
				+ Math.pow(t.getEdu_bachelors() - s.getEdu_bachelors(), 2) + Math.pow(t.getIncome() - s.getIncome(), 2)
				+ Math.pow(t.getPoverty() - s.getPoverty(), 2) + Math.pow(t.getDensity() - s.getDensity(), 2));
		return distance;

	}

	public static double knnalgo(TrainVotes[] t, TestVotes s) {
		double[] distance2 = new double[2334];
		double dist = 0.0, cat = 0.0;
		double temp = 0.0, cat1 = 0.0, cat2 = 0.0, temp_cat = 0.0;
		ArrayList<Knn_entity> knn_list = new ArrayList<Knn_entity>();
		Knn_entity knn1 = new Knn_entity();
		for (int i = 0; i < t.length; i++) {
			dist = eucledianDistance(t[i], s);
			distance2[i] = dist;
			knn1.setEucledianDist(dist);
			knn1.setCategory(t[i].getDemocrat());
			knn1.setIndex(i);
			knn_list.add(knn1);
		}
		Knn_entity[] knn_array = knn_list.toArray(new Knn_entity[knn_list.size()]);

		// distance2=knn1.getDistance();
		for (int j = 0; j < distance2.length - 1; j++) {
			if (Double.compare(distance2[j], distance2[j + 1]) < 0) {
				cat1 = knn_array[j].getCategory();
				cat2 = knn_array[j + 1].getCategory();
				temp = distance2[j];
				distance2[j] = distance2[j + 1];
				distance2[j + 1] = temp;
				temp_cat = cat1;
				cat1 = cat2;
				cat2 = temp_cat;
			}
		}
		// k=3;
		int len = distance2.length;
		int count_rep = 0, count_dem = 0;
		for (int i = 1; i < 5; i++) {
			if (knn_array[len - i].getCategory() == 0) {
				count_rep++;
			} else {
				count_dem++;
			}
		}
		if (count_rep > count_dem) {
			System.out.println("Republican");
			cat = 0;
		} else {
			System.out.println("Democrat");
			cat = 1;
		}
		return cat;
	}

	public static void main(String[] args) {
		double distance2 = 0.0;
		train = train();
		test = test();
		TrainVotes[] t = train.toArray(new TrainVotes[train.size()]);
		TestVotes[] s = test.toArray(new TestVotes[test.size()]);
		double total = 0.0;
		int cat = 0, count_rep = 0, count_dem = 0;
		for (int i = 0; i < 10; i++) {
			distance2 = knnalgo(t, s[i]);
			if (distance2 == 0) {
				count_rep++;
			} else {
				count_dem++;
			}

			if (count_rep > count_dem) {
				System.out.println("Republican");
				cat = 0;
			} else {
				System.out.println("Democrat");
				cat = 1;
			}
		}
		System.out.println(total / 10);
	}

}
