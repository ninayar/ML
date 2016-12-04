package main;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import com.opencsv.CSVReader;

import entity.TestVotes;
import entity.TrainVotes;

public class Perceptron {
	static double distance;
	static ArrayList<TrainVotes> train = new ArrayList<TrainVotes>();
	static ArrayList<TestVotes> test = new ArrayList<TestVotes>();
	static ArrayList<Double> d = new ArrayList<Double>();
	double[] weights;

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
	
	public static double[] initialize()
	{
        double[] weights = new double[10];
        Random r = new Random();
        
        //initialize weights
        for(int i=0;i<10;i++)
        {
            weights[i] = r.nextDouble();
        }
        return weights;
	}
	
	public static double[] calculate(double[] w, TrainVotes s)
	{
		double[] w1 = new double[10];
		double mul=w[0]*s.getDemocrat()+ w[1]*s.getPopulation()+ w[2]*s.getPopulation_change()+w[3]*s.getAge65plus()+ w[4]*s.getBlack()+
				w[5]*s.getHispanic()+ w[6]*s.getEdu_bachelors()+ w[7]*s.getIncome()+ w[8]*s.getPoverty()+w[9]*s.getDensity();
		double[] s_array={s.getDemocrat(),s.getPopulation(),s.getPopulation_change(),s.getAge65plus(),s.getBlack(),s.getHispanic(),
				s.getEdu_bachelors(), s.getIncome(), s.getPoverty(),s.getDensity()};
		
		for(int i=0; i<10;i++)
		{
			if(mul>0)
			{
				w1[i]=w[i];
			}
			else
			{
				w1[i]=w[i]+s_array[i];

			}
		}
		return w1;

		
	}

	public static void main(String[] args) {
		double[] w=initialize();
		double[] final_w=new double[10];
		train = train();
		test = test();
		TrainVotes[] t = train.toArray(new TrainVotes[train.size()]);
		TestVotes[] s = test.toArray(new TestVotes[test.size()]);
		for(int i=0;i<t.length;i++)
		{
		final_w=calculate(w,t[i]);
		}
		double cat=s[9].getDemocrat()*final_w[0];
		if(cat>0)
		System.out.println("democrat");
		else
		System.out.println("republic");

	}

}
