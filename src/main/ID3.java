package main;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.opencsv.CSVReader;

import entity.TestVotes;
import entity.TrainVotes;
import entity.Tree;

public class ID3 {
	static double distance;
	static ArrayList<TrainVotes> train = new ArrayList<TrainVotes>();
	static ArrayList<TrainVotes> test = new ArrayList<TrainVotes>();
	static ArrayList<Double> d = new ArrayList<Double>();

	public static ArrayList<TrainVotes> train() {
		String csvFile = "D:/ninayar/workspace/ML/src/utils/vector_norm_Train.csv";
		String[] temp;
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

	public static ArrayList<TrainVotes> test() {

		String csvFile = "D:/ninayar/workspace/ML/src/utils/vector_norm_test.csv";
		String[] temp;
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
					test.add(v);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return test;
	}

	public static Tree probability(TrainVotes[] t, int f) {
		int feature = f;
		double[] p = new double[9];
		ArrayList<TrainVotes> left = new ArrayList<TrainVotes>();
		ArrayList<TrainVotes> right = new ArrayList<TrainVotes>();

		Tree tree = new Tree();
		int count_pop_h = 0, count_pop_l = 0;
		int count_pop_change_h = 0, count_pop_change_l = 0;
		int count_age_h = 0, count_age_l = 0;
		int count_black_h = 0, count_black_l = 0;
		int count_hispanic_h = 0, count_hispanic_l = 0;
		int count_edu_h = 0, count_edu_l = 0;
		int count_inc_h = 0, count_inc_l = 0;
		int count_pov_h = 0, count_pov_l = 0;
		int count_den_h = 0, count_den_l = 0;
		int count_pop = 0, count_black = 0, count_hispanic = 0, count_edu = 0, count_age_65 = 0, count_pop_change = 0,
				count_pov = 0, count_inc = 0, count_den = 0;
		int count_d = 0;

		for (int i = 0; i < t.length; i++) {

			if (t[i].getDemocrat() == 1) {
				count_d++;
			}

			if (t[i].getPopulation() > 0.009) {
				count_pop++;
				if (t[i].getDemocrat() == 1) {
					count_pop_h++;
				}

			} else {
				if (t[i].getDemocrat() == 1) {
					count_pop_l++;
				}
			}
			if (t[i].getPopulation_change() > 0.39) {
				count_pop_change++;
				if (t[i].getDemocrat() == 1) {
					count_pop_change_h++;
				}
			} else {
				if (t[i].getDemocrat() == 1) {
					count_pop_change_l++;
				}
			}
			if (t[i].getAge65plus() > 0.248) {
				count_age_65++;
				if (t[i].getDemocrat() == 1) {
					count_age_h++;
				}

			} else {
				if (t[i].getDemocrat() == 1) {
					count_age_l++;
				}
			}
			if (t[i].getBlack() > 0.11) {
				count_black++;
				if (t[i].getDemocrat() == 1) {
					count_black_h++;
				}
			} else {
				if (t[i].getDemocrat() == 1) {
					count_black_l++;
				}
			}
			if (t[i].getHispanic() > 0.09) {
				count_hispanic++;
				if (t[i].getDemocrat() == 1) {
					count_hispanic_h++;
				}

			} else {
				if (t[i].getDemocrat() == 1) {
					count_hispanic_l++;
				}
			}
			if (t[i].getEdu_bachelors() > 0.20) {
				count_edu++;
				if (t[i].getDemocrat() == 1) {
					count_edu_h++;
				}

			} else {
				if (t[i].getDemocrat() == 1) {
					count_edu_l++;
				}
			}
			if (t[i].getIncome() > 0.25) {
				count_inc++;
				if (t[i].getDemocrat() == 1) {
					count_inc_h++;
				}

			} else {
				if (t[i].getDemocrat() == 1) {
					count_inc_l++;
				}
			}
			if (t[i].getPoverty() > 0.3027) {
				count_pov++;
				if (t[i].getDemocrat() == 1) {
					count_pov_h++;
				}
			} else {
				if (t[i].getDemocrat() == 1) {
					count_pov_l++;
				}
			}
			if (t[i].getDensity() > 0.0103) {
				count_den++;
				if (t[i].getDemocrat() == 1) {
					count_den_h++;
				}

			} else {
				if (t[i].getDemocrat() == 1) {
					count_den_l++;
				}
			}

		}
		double prob_d = ((double) count_d / 2334);
		double int_gain = -(prob_d * (Math.log(prob_d) / Math.log(2))
				+ (1 - prob_d) * (Math.log(1 - prob_d) / Math.log(2)));

		double prob_pop_h = ((double) count_pop_h) / ((double) count_pop);
		double prob_pop_change_h = ((double) count_pop_change_h) / ((double) count_pop_change);
		double prob_age_65_h = ((double) count_age_h) / ((double) count_age_65);
		double prob_black_h = ((double) count_black_h) / ((double) count_black);
		double prob_hispanic_h = ((double) count_hispanic_h) / ((double) count_hispanic);
		double prob_edu_h = ((double) count_edu_h) / ((double) count_edu);
		double prob_inc_h = ((double) count_inc_h) / ((double) count_inc);
		double prob_pov_h = ((double) (count_pov_h)) / ((double) (count_pov));
		double prob_den_h = ((double) count_den_h) / ((double) count_den);

		double prob_pop_l = ((double) count_pop_l) / ((double) count_pop);
		double prob_pop_change_l = ((double) count_pop_change_l) / ((double) count_pop_change);
		double prob_age_65_l = ((double) count_age_l) / ((double) count_age_65);
		double prob_black_l = ((double) count_black_l) / ((double) count_black);
		double prob_hispanic_l = ((double) count_hispanic_l) / ((double) count_hispanic);
		double prob_edu_l = ((double) count_edu_l) / ((double) count_edu);
		double prob_inc_l = ((double) count_inc_l) / ((double) count_inc);
		double prob_pov_l = ((double) count_pov_l) / ((double) count_pov);
		double prob_den_l = ((double) count_den_l) / ((double) count_den);

		double entropy_pop_h = prob_pop_h * (Math.log(prob_pop_h) / Math.log(2))
				+ (1 - prob_pop_h) * (Math.log(1 - prob_pop_h) / Math.log(2));
		double entropy_pop_l = prob_pop_l * (Math.log(prob_pop_l) / Math.log(2))
				+ (1 - prob_pop_l) * (Math.log(1 - prob_pop_l) / Math.log(2));
		double entro_pop = (count_pop / 2334) * entropy_pop_h + (1 - (count_pop / 2334)) * entropy_pop_l;
		double gain_pop = 0;
		if((prob_pop_h>0 && prob_pop_h<1)&& (prob_pop_l>0 && prob_pop_l<1) )
		{
			if(prob_pop_h==1 || prob_pop_l==1)
				tree.setCategory(1);
			else if(prob_pop_h==0 || prob_pop_l==0)
				tree.setCategory(0);
			gain_pop = int_gain - entro_pop;
		}
//		System.out.println(gain_pop);

		double entropy_pop_change_h = prob_pop_change_h * (Math.log(prob_pop_change_h) / Math.log(2))
				+ (1 - prob_pop_change_h) * (Math.log(1 - prob_pop_change_h) / Math.log(2));
		double entropy_pop_change_l = prob_pop_change_l * (Math.log(prob_pop_change_l) / Math.log(2))
				+ (1 - prob_pop_change_l) * (Math.log(1 - prob_pop_change_l) / Math.log(2));
		double entro_pop_change = (count_pop_change / 2334) * entropy_pop_change_h
				+ (1 - (count_pop_change / 2334)) * entropy_pop_change_l;
		double gain_pop_change = 0;
		if((prob_pop_change_h>0 && prob_pop_change_h<1)&& (prob_pop_change_l>0 && prob_pop_change_l<1) )
		{
			if(prob_pop_change_h==1 || prob_pop_change_l==1)
				tree.setCategory(1);
			else if ((prob_pop_change_h==0 || prob_pop_change_l==0))
				tree.setCategory(0);
			
			gain_pop_change = int_gain - entro_pop_change;
		}
//		System.out.println(gain_pop_change);

		double entropy_age_65_h = -(prob_age_65_h * (Math.log(prob_age_65_h) / Math.log(2))
				+ (1 - prob_age_65_h) * (Math.log(1 - prob_age_65_h) / Math.log(2)));
		double entropy_age_65_l = -(prob_age_65_l * (Math.log(prob_age_65_l) / Math.log(2))
				+ (1 - prob_age_65_l) * (Math.log(1 - prob_age_65_l) / Math.log(2)));
		double entro_age_65 = (count_age_65 / 2334) * entropy_age_65_h + (1 - (count_age_65 / 2334)) * entropy_age_65_l;
		double gain_age_65 =0.0;
		if((prob_age_65_h>0 && prob_age_65_h<1)&& (prob_age_65_l>0 && prob_age_65_l<1) )
		{
			if(prob_age_65_h==1 || prob_age_65_l==1)
			{
				tree.setCategory(1);
			}
			else if (prob_age_65_h==0 || prob_age_65_l==0)
			{
				tree.setCategory(0);
			}
			gain_age_65 = int_gain - entro_age_65;
		}
//		System.out.println(gain_age_65);

		double entropy_black_h = -(prob_black_h * (Math.log(prob_black_h) / Math.log(2))
				+ (1 - prob_black_h) * (Math.log(1 - prob_black_h) / Math.log(2)));
		double entropy_black_l = -(prob_black_l * (Math.log(prob_black_l) / Math.log(2))
				+ (1 - prob_black_l) * (Math.log(1 - prob_black_l) / Math.log(2)));
		double entro_black = (count_black / 2334) * entropy_black_h + (1 - (count_black / 2334)) * entropy_black_l;
		double gain_black = 0;		
		if((prob_black_h>0 && prob_black_h<1)&& (prob_black_l>0 && prob_black_l<1) )
		{
			if(prob_black_h==1 || prob_black_l==1)
			{
				tree.setCategory(1);
			}
			else if (prob_black_h==0 || prob_black_l==0)
			{
				tree.setCategory(0);
			}
			gain_black = int_gain - entro_black;
		}
//		System.out.println(gain_black);

		double entropy_hispanic_h = -(prob_hispanic_h * (Math.log(prob_hispanic_h) / Math.log(2))
				+ (1 - prob_hispanic_h) * (Math.log(1 - prob_hispanic_h) / Math.log(2)));
		double entropy_hispanic_l = -(prob_hispanic_l * (Math.log(prob_hispanic_l) / Math.log(2))
				+ (1 - prob_hispanic_l) * (Math.log(1 - prob_hispanic_l) / Math.log(2)));
		double entro_hispanic = (count_hispanic / 2334) * entropy_hispanic_h
				+ (1 - (count_hispanic / 2334)) * entropy_hispanic_l;
		double gain_hispanic = 0;
		if((prob_hispanic_h>0 && prob_hispanic_h<1)&& (prob_hispanic_l>0 && prob_hispanic_l<1) )
		{
			if(prob_hispanic_h==1 || prob_hispanic_l==1)
			{
				tree.setCategory(1);
			}
			else if (prob_hispanic_h==0 || prob_hispanic_l==0)
			{
				tree.setCategory(0);
			}
			gain_hispanic = int_gain - entro_hispanic;
		}
//		System.out.println(gain_hispanic);

		double entropy_edu_h = -(prob_edu_h * (Math.log(prob_edu_h) / Math.log(2))
				+ (1 - prob_edu_h) * (Math.log(1 - prob_edu_h) / Math.log(2)));
		double entropy_edu_l = -(prob_edu_l * (Math.log(prob_edu_l) / Math.log(2))
				+ (1 - prob_edu_l) * (Math.log(1 - prob_edu_l) / Math.log(2)));
		double entro_edu = (count_edu / 2334) * entropy_edu_h + (1 - (count_edu / 2334)) * entropy_edu_l;
		double gain_edu = int_gain - entro_edu;
		if((prob_edu_h>0 && prob_edu_h<1)&& (prob_edu_l>0 && prob_edu_l<1) )
		{
			if(prob_edu_h==1 || prob_edu_l==1)
			{
				tree.setCategory(1);
			}
			else if (prob_edu_h==0 || prob_edu_l==0)
			{
				tree.setCategory(0);
			}
			gain_edu = int_gain - entro_edu;
		}
//		System.out.println(gain_edu);

		double entropy_inc_h = -(prob_inc_h * (Math.log(prob_inc_h) / Math.log(2))
				+ (1 - prob_inc_h) * (Math.log(1 - prob_inc_h) / Math.log(2)));
		double entropy_inc_l = -(prob_inc_l * (Math.log(prob_inc_l) / Math.log(2))
				+ (1 - prob_inc_l) * (Math.log(1 - prob_inc_l) / Math.log(2)));
		double entro_inc = (count_inc / 2334) * entropy_inc_h + (1 - (count_inc / 2334)) * entropy_inc_l;
		double gain_inc = 0;
		if((prob_inc_h>0 && prob_inc_h<1)&& (prob_inc_l>0 && prob_inc_l<1) )
		{
			if(prob_inc_h==1 || prob_inc_l==1)
			{
				tree.setCategory(1);
			}
			else if (prob_inc_h==0 || prob_inc_l==0)
			{
				tree.setCategory(0);
			}
			gain_inc = int_gain - entro_inc;
		}
//		System.out.println(gain_inc);

		double entropy_pov_h = -(prob_pov_h * (Math.log(prob_pov_h) / Math.log(2))
				+ (1 - prob_pov_h) * (Math.log(1 - prob_pov_h) / Math.log(2)));
		double entropy_pov_l = -(prob_pov_l * (Math.log(prob_pov_l) / Math.log(2))
				+ (1 - prob_pov_l) * (Math.log(1 - prob_pov_l) / Math.log(2)));
		double entro_pov = (count_pov / 2334) * entropy_pov_h + (1 - (count_pov / 2334)) * entropy_pov_l;
		double gain_pov = 0;
		if((prob_pov_h>0 && prob_pov_h<1)&& (prob_pov_l>0 && prob_pov_l<1) )
		{
			if(prob_pov_h==1 || prob_pov_l==1)
			{
				tree.setCategory(1);
			}
			else if (prob_pov_h==0 || prob_pov_l==0)
			{
				tree.setCategory(0);
			}
			tree.setCategory(1);
			gain_pov = int_gain - entro_pov;
		}
//		System.out.println(gain_pov);

		double entropy_den_h = -(prob_den_h * (Math.log(prob_den_h) / Math.log(2))
				+ (1 - prob_den_h) * (Math.log(1 - prob_den_h) / Math.log(2)));
		double entropy_den_l = -(prob_den_l * (Math.log(prob_den_l) / Math.log(2))
				+ (1 - prob_den_l) * (Math.log(1 - prob_den_l) / Math.log(2)));
		double entro_den = ((count_den / 2334) * entropy_den_h + (1 - (count_den / 2334)) * entropy_den_l);
		double gain_den = 0;
		if((prob_den_h>0 && prob_den_h<1)&& (prob_den_l>0 && prob_den_l<1) )
		{
			if(prob_den_h==1 || prob_den_l==1)
			{
				tree.setCategory(1);
			}
			else if (prob_den_h==0 || prob_den_l==0)
			{
				tree.setCategory(0);
			}
			gain_den = int_gain - entro_den;
		}
//		System.out.println(gain_den);
		double max=0;

		if(feature!=0)
		{
			switch(feature)
			{
			case 1:
				max = Math.max(Math.max(Math
						.max(Math.max(Math.max(Math.max(Math.max(gain_pop_change, gain_age_65), gain_black),
								gain_hispanic), gain_edu), gain_inc),
						gain_pov), gain_den);
				break;
			case 2:
				max = Math.max(Math.max(Math
						.max(Math.max(Math.max(Math.max(Math.max(gain_pop, gain_age_65), gain_black),
								gain_hispanic), gain_edu), gain_inc),
						gain_pov), gain_den);
				break;
			case 3:
				max = Math.max(Math.max(Math
						.max(Math.max(Math.max(Math.max(Math.max(gain_pop, gain_pop_change), gain_black),
								gain_hispanic), gain_edu), gain_inc),
						gain_pov), gain_den);
				break;
			case 4:
				max = Math.max(Math.max(Math
						.max(Math.max(Math.max(Math.max(Math.max(gain_pop, gain_pop_change), gain_age_65),
								gain_hispanic), gain_edu), gain_inc),
						gain_pov), gain_den);
				break;
			case 5:
				max = Math.max(Math.max(Math
						.max(Math.max(Math.max(Math.max(Math.max(gain_pop, gain_pop_change), gain_age_65),
								gain_black), gain_edu), gain_inc),
						gain_pov), gain_den);
				break;
			case 6:
				max = Math.max(Math.max(Math
						.max(Math.max(Math.max(Math.max(Math.max(gain_pop, gain_pop_change), gain_age_65),
								gain_black), gain_hispanic), gain_inc),
						gain_pov), gain_den);
				break;
			case 7:
				max = Math.max(Math.max(Math
						.max(Math.max(Math.max(Math.max(Math.max(gain_pop, gain_pop_change), gain_age_65),
								gain_black), gain_hispanic), gain_edu),
						gain_pov), gain_den);
				break;
			case 8:
				max = Math.max(Math.max(Math
						.max(Math.max(Math.max(Math.max(Math.max(gain_pop, gain_pop_change), gain_age_65),
								gain_black), gain_hispanic), gain_edu),
						gain_inc), gain_den);
				break;
			case 9:
				max = Math.max(Math.max(Math
						.max(Math.max(Math.max(Math.max(Math.max(gain_pop, gain_pop_change), gain_age_65),
								gain_black), gain_hispanic), gain_edu),
						gain_inc), gain_pov);
				break;
				
			}
			
		}
		else
		{
			max = Math.max(Math.max(Math
					.max(Math.max(Math.max(Math.max(Math.max(Math.max(gain_pop, gain_pop_change), gain_age_65), gain_black),
							gain_hispanic), gain_edu), gain_inc),
					gain_pov), gain_den);
		}
//		for (int i = 0; i < t.length; i++) {
			if (max == gain_pop) {
				feature = 1;
				for (int i = 0; i < t.length; i++) {

				if (t[i].getPopulation() > 0.009 && !left.contains(t[i])) {
					left.add(t[i]);
				} else if(!right.contains(t[i])){
					right.add(t[i]);
				}
				}
			} else if (max == gain_pop_change) {
				feature = 2;
				for (int i = 0; i < t.length; i++) {

				if (t[i].getPopulation_change() > 0.39 && !left.contains(t[i])) {
					left.add(t[i]);
				} else if(!right.contains(t[i])) {
					right.add(t[i]);
				}
				}
			} else if (max == gain_age_65) {
				feature = 3;
				for (int i = 0; i < t.length; i++) {

				if (t[i].getAge65plus() > 0.248 && !left.contains(t[i])) {
					left.add(t[i]);
				} else if(!right.contains(t[i])) {
					right.add(t[i]);
				}
				}
			} else if (max == gain_black) {
				feature = 4;
				for (int i = 0; i < t.length; i++) {

				if (t[i].getBlack() > 0.11 && !left.contains(t[i])) {
					left.add(t[i]);
				} else if(!right.contains(t[i])) {
					right.add(t[i]);
				}
				}
			} else if (max == gain_hispanic) {
				feature = 5;
				for (int i = 0; i < t.length; i++) {

				if (t[i].getHispanic() > 0.09 && !left.contains(t[i])) {
					left.add(t[i]);
				} else if(!right.contains(t[i])) {
					right.add(t[i]);
				}
				}
			} else if (max == gain_edu) {
				feature = 6;
				for (int i = 0; i < t.length; i++) {

				if (t[i].getEdu_bachelors() > 0.20 && !left.contains(t[i])) {
					left.add(t[i]);
				} else if( !right.contains(t[i])){
					right.add(t[i]);
				}
				}
			} else if (max == gain_inc) {
				feature = 7;
				for (int i = 0; i < t.length; i++) {

				if (t[i].getIncome() > 0.25 && !left.contains(t[i])) {
					left.add(t[i]);
				} else if(!right.contains(t[i])) {
					right.add(t[i]);
				}
				}
			} else if (max == gain_pov) {
				feature = 8;
				for (int i = 0; i < t.length; i++) {

				if (t[i].getPoverty() > 0.3027 && !left.contains(t[i])) {
					left.add(t[i]);
				} else if(!right.contains(t[i])) {
					right.add(t[i]);
				}
				}
			} else if (max == gain_den) {
				feature = 9;
				for (int i = 0; i < t.length; i++) {

				if (t[i].getDensity() > 0.0103 && !left.contains(t[i])) {
					left.add(t[i]);
				} else if (!right.contains(t[i])){
					right.add(t[i]);
				}
				}
			}
		tree.setFeature(feature);
		tree.setMax_gain(max);
		tree.setRight(right);
		tree.setLeft(left);

		return tree;
	}

	public static void main(String[] args) {
		train = train();
		Tree tree = new Tree();
		tree.setRoot(train);
		tree.setFeature(0);
		TrainVotes[] t = train.toArray(new TrainVotes[train.size()]);
		Tree left_tree = new Tree();
		Tree right_tree = new Tree();
		tree = probability(t, tree.getFeature());
		tree.setRootNode(tree);
		for (int i = 0; i < 2; i++) {
			TrainVotes[] new_root = tree.getLeft().toArray(new TrainVotes[tree.getLeft().size()]);
			TrainVotes[] new_root_right = tree.getRight().toArray(new TrainVotes[tree.getRight().size()]);
			left_tree = probability(new_root,tree.getFeature());
			tree.setLeft(left_tree.getLeft());
			tree.setLeftNode(left_tree);
			right_tree = probability(new_root_right,tree.getFeature());
			tree.setRight(right_tree.getRight());
			tree.setLeftNode(right_tree);
			}
		test=test();
		TrainVotes[] s = test.toArray(new TrainVotes[777]);
		int rep=0;
		for(int i=0; i<10;i++)
		{
		TrainVotes test=s[i];
		
		 System.out.println("Category of 1 represents Democrat and 0 represents Republic");

		switch(tree.getFeature())
		{ 
		case 1:
			 if(test.getPopulation()>0.009)
				 System.out.println("category " + right_tree.getCategory());
			 else
				 System.out.println("category " + rep);
			 break;
		case 2:
			 if(test.getPopulation_change()>0.39)
				 System.out.println("category " + right_tree.getCategory());
			 else
				 System.out.println("category " + rep);
			 break;
			 
		case 3:
			 if(test.getAge65plus()>0.248)
				 System.out.println("category " + right_tree.getCategory());
			 else
				 System.out.println("category " + rep);
			 break;
		case 4:
			 if(test.getBlack()>0.11)
				 System.out.println("category " + right_tree.getCategory());
			 else
				 System.out.println("category " + rep);
			 break;
		case 5:
			 if(test.getHispanic()>0.09)
				 System.out.println("category " + right_tree.getCategory());
			 else
				 System.out.println("category " + rep);
			 break;
		case 6:
			 if(test.getEdu_bachelors()>0.20)
				 System.out.println("category " + right_tree.getCategory());
			 else
				 System.out.println("category " + rep);
			 break;
		case 7:
			 if(test.getIncome()>0.25)
				 System.out.println("category " + right_tree.getCategory());
			 else
				 System.out.println("category " + rep);
			 break;
		case 8:
			 if(test.getPoverty()>0.3027)
				 System.out.println("category " + right_tree.getCategory());
			 else
				 System.out.println("category " + rep);
			 break;
		case 9:
			 if(test.getPoverty()>0.0103)
				 System.out.println("category " + right_tree.getCategory());
			 else
				 System.out.println("category " + rep);
			 break;
		
		}	
		}
	}

}
