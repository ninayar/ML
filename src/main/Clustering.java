package main;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.opencsv.CSVReader;

import entity.Cluster;
import entity.Knn_entity;
import entity.TestVotes;
import entity.TrainVotes;

public class Clustering {

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

	public static double eucledianDistance(TrainVotes t, TrainVotes s) {
		distance = Math.sqrt(Math.pow(t.getDemocrat() - s.getDemocrat(), 2)
				+ Math.pow(t.getPopulation() - s.getPopulation(), 2)
				+ Math.pow(t.getPopulation_change() - s.getPopulation_change(), 2)
				+ Math.pow(t.getAge65plus() - s.getAge65plus(), 2) + Math.pow(t.getBlack() - s.getBlack(), 2)
				+ Math.pow(t.getHispanic() - s.getHispanic(), 2)
				+ Math.pow(t.getEdu_bachelors() - s.getEdu_bachelors(), 2) + Math.pow(t.getIncome() - s.getIncome(), 2)
				+ Math.pow(t.getPoverty() - s.getPoverty(), 2) + Math.pow(t.getDensity() - s.getDensity(), 2));
		return distance;
	}

	public static double[] centroid(TrainVotes a, TrainVotes b) {
		double[] centroid = { (a.getDemocrat() + b.getDemocrat()) / 2, (a.getPopulation() + b.getPopulation()) / 2,
				(a.getPopulation_change() + b.getPopulation_change()) / 2, (a.getAge65plus() + b.getAge65plus()) / 2,
				(a.getBlack() + b.getBlack()) / 2, (a.getHispanic() + b.getHispanic()) / 2,
				(a.getEdu_bachelors() + b.getEdu_bachelors()) / 2, (a.getIncome() + b.getIncome()) / 2,
				(a.getPoverty() + b.getPoverty()) / 2, (a.getDensity() + b.getDensity()) / 2 };
		return centroid;

	}

	public static Double[] initialCluster(TrainVotes a) {
		Double[] cluster = { a.getDemocrat(), a.getPopulation(), a.getPopulation_change(), a.getAge65plus(),
				a.getBlack(), a.getHispanic(), a.getEdu_bachelors(), a.getIncome(), a.getPoverty(), a.getDensity() };
		return cluster;

	}

	public static ArrayList<TrainVotes> clustering(TrainVotes[] t, int size) {
		Double[] initial = new Double[size];
		ArrayList<TrainVotes> cluster = new ArrayList<TrainVotes>();
		ArrayList<TrainVotes> new_cluster = new ArrayList<TrainVotes>();
		for (int i = 0; i < t.length; i++) {
			initial = initialCluster(t[i]);
			cluster.add(t[i]);
		}
	

		// get nearest neighbors
		TrainVotes[] cluster_train = cluster.toArray(new TrainVotes[size]);
		Cluster cl=new Cluster();
		TrainVotes combined=new TrainVotes();
//		int size=cluster_train.length;

		for (int i = 0; i < size; i++) {
			
			if(cluster.size()>1000){
			cl=getNearestNeighbour(cluster, i, cluster.size());
			combine(cluster,cl.getClosest_index(),cl.getSecond_closest_index());
			new_cluster.add(combined);
			cluster.add(combined);
			if(cl.getClosest_index()!=cl.getSecond_closest_index())
			{
			cluster.remove(cl.getClosest_index());
			cluster.remove(cl.getSecond_closest_index());
			}
			size=cluster.size();
			}

		}
		return cluster;

	}

	private static TrainVotes combine(ArrayList<TrainVotes> cluster,int closest_index, int second_closest_index) {
		TrainVotes[] t = cluster.toArray(new TrainVotes[cluster.size()]);
		TrainVotes train=new TrainVotes();
//			System.out.println(cluster.size());
//			System.out.println(closest_index);
//			closest_index=0;
//			second_closest_index=0;
		
		train.setDemocrat(t[closest_index].getDemocrat()+t[second_closest_index].getDemocrat());
		train.setPopulation(t[closest_index].getPopulation()+t[second_closest_index].getPopulation());
		train.setPopulation_change(t[closest_index].getPopulation_change()+t[second_closest_index].getPopulation_change());
		train.setAge65plus(t[closest_index].getAge65plus()+t[second_closest_index].getAge65plus());
		train.setBlack(t[closest_index].getBlack()+t[second_closest_index].getBlack());
		train.setHispanic(t[closest_index].getHispanic()+t[second_closest_index].getHispanic());
		train.setEdu_bachelors(t[closest_index].getEdu_bachelors()+t[second_closest_index].getEdu_bachelors());
		train.setIncome(t[closest_index].getIncome()+t[second_closest_index].getIncome());
		train.setPoverty(t[closest_index].getPoverty()+t[second_closest_index].getPoverty());
		train.setDensity(t[closest_index].getDensity()+t[second_closest_index].getDensity());
		
		t[closest_index]=train;

		return t[closest_index];
	}

	private static Cluster getNearestNeighbour(ArrayList<TrainVotes> cluster, int index, int size) {
		TrainVotes[] train = cluster.toArray(new TrainVotes[cluster.size()]);
		ArrayList<TrainVotes> new_cluster = new ArrayList<TrainVotes>();
		double closest=0.0;
		double next_closest=0.0;
		double distance;
		double[] dist=new double[size];
		Cluster cl=new Cluster();
		for(int i=index;i<train.length;i++)
		{
			for(int j=0; j<train.length;j++)
			{
				distance=getDistance(train[i],train[j]);
				dist[j]=distance;
			}
			closest=dist[0];
			next_closest=dist[0];
		}
		for(int i=0; i<dist.length;i++)
		{
			if(dist[i]<closest)
			{
				next_closest=closest;
				closest=dist[i];
				cl.setClosest(closest);
				cl.setClosest_index(i);
			}
			else if(dist[i]<next_closest && dist[i] != closest)
			{
				next_closest=dist[i];
				cl.setClosest(next_closest);
				cl.setSecond_closest_index(i);
			}
		}
		
		return cl;
	}

	private static double getDistance(TrainVotes trainVotes1, TrainVotes trainVotes2) {
		double dist = Math.sqrt(Math.pow(trainVotes1.getDemocrat() - trainVotes2.getDemocrat(), 2)
				+ Math.pow(trainVotes1.getPopulation() - trainVotes2.getPopulation(), 2)
				+ Math.pow(trainVotes1.getPopulation_change() - trainVotes2.getPopulation_change(), 2)
				+ Math.pow(trainVotes1.getAge65plus() - trainVotes2.getAge65plus(), 2)
				+ Math.pow(trainVotes1.getBlack() - trainVotes2.getBlack(), 2)
				+ Math.pow(trainVotes1.getHispanic() - trainVotes2.getHispanic(), 2)
				+ Math.pow(trainVotes1.getEdu_bachelors() - trainVotes2.getEdu_bachelors(), 2)
				+ Math.pow(trainVotes1.getIncome() - trainVotes2.getIncome(), 2)
				+ Math.pow(trainVotes1.getPoverty() - trainVotes2.getPoverty(), 2)
				+ Math.pow(trainVotes1.getDensity() - trainVotes2.getDensity(), 2));
		return dist;
	}

	public static void main(String[] args) {
		train = train();
		test = test();
		int len_t = train.size();
		int len_s = test.size();
		TrainVotes[] t = train.toArray(new TrainVotes[len_t]);
		TrainVotes dist[] = new TrainVotes[t.length];

		TestVotes[] s = test.toArray(new TestVotes[len_s]);
		double[] updated_t1 = null;
		ArrayList<TrainVotes> cluster_combined = new ArrayList<TrainVotes>();
		int size=t.length;
		TrainVotes[] comb = null;
		while(size>0)
		{
		cluster_combined=clustering(t,size);
		comb = cluster_combined.toArray(new TrainVotes[cluster_combined.size()]);

		}
		System.out.println(size);

		//// initial cluster is t
		// for (int i = 0; i < t.length; i++) {
		// dist = eucledianDistance(t[i], t);
		// distance2[i] = dist;
		// }
		//// combine
		// int size=len_t/2;
		//
		// for(int j=len_t; j>1;j--)
		// {
		// for(int i=0; i<size;i++)
		// {
		// updated_t1= new double[len_t/2];
		//
		// double updated_t[]={t[i].getDemocrat()+t[i+1].getDemocrat(),
		//// t[i].getPopulation()+t[i+1].getPopulation(),
		// t[i].getPopulation_change()+t[i+1].getPopulation_change(),
		//// t[i].getAge65plus()+t[i+1].getAge65plus(),
		// t[i].getBlack()+t[i+1].getBlack(),
		//// t[i].getHispanic()+t[i+1].getHispanic(),
		// t[i].getEdu_bachelors()+t[i+1].getEdu_bachelors(),
		//// t[i].getIncome()+t[i+1].getIncome(),
		// t[i].getPoverty()+t[i+1].getPoverty(),
		//// t[i].getDensity()+t[i+1].getDensity()};
		// updated_t1=updated_t;
		// size=size/2;
		// }
		//
		// j=size/2;
		// }
		// System.out.println(updated_t1[0]);

	}

}
