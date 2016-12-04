package main;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.opencsv.CSVReader;

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
	
	public static double eucledianDistance(TrainVotes t, TestVotes s) {
		distance = Math.sqrt(Math.pow(t.getDemocrat() - s.getDemocrat(), 2)
				+ Math.pow(t.getPopulation() - s.getPopulation(), 2)
				+ Math.pow(t.getPopulation_change() - s.getPopulation_change(), 2)
				+ Math.pow(t.getAge65plus() - s.getAge65plus(), 2) + Math.pow(t.getBlack() - s.getBlack(), 2)
				+ Math.pow(t.getHispanic() - s.getHispanic(), 2)
				+ Math.pow(t.getEdu_bachelors() - s.getEdu_bachelors(), 2) + Math.pow(t.getIncome() - s.getIncome(), 2)
				+ Math.pow(t.getPoverty() - s.getPoverty(), 2) + Math.pow(t.getDensity() - s.getDensity(), 2));
		return distance;
	}
	
	public static double distance(double a, double b)
	{
		return b;
		
	}
	
	public static void clustering()
	{
		
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		train = train();
		test = test();
		int len_t=train.size();
		int len_s=test.size();
		TrainVotes[] t = train.toArray(new TrainVotes[len_t]);
		TestVotes[] s = test.toArray(new TestVotes[len_s]);
		double[] updated_t1= null;
//		initial cluster is t
//		combine
		int size=len_t/2;

		for(int j=len_t; j>1;j--)
		{
		for(int i=0; i<size;i++)
		{
			updated_t1= new double[len_t/2];
			
			double updated_t[]={t[i].getDemocrat()+t[i+1].getDemocrat(), t[i].getPopulation()+t[i+1].getPopulation(), 
					t[i].getPopulation_change()+t[i+1].getPopulation_change(), t[i].getAge65plus()+t[i+1].getAge65plus(),
					t[i].getBlack()+t[i+1].getBlack(), t[i].getHispanic()+t[i+1].getHispanic(), 
					t[i].getEdu_bachelors()+t[i+1].getEdu_bachelors(), t[i].getIncome()+t[i+1].getIncome(),
					t[i].getPoverty()+t[i+1].getPoverty(), t[i].getDensity()+t[i+1].getDensity()};
			updated_t1=updated_t;
			size=size/2;
		}
		
		j=size/2;
		}
		System.out.println(updated_t1[0]);
		

	}

}
