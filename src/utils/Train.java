package utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.opencsv.CSVReader;

import entity.TrainVotes;

public class Train {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String csvFile = "D:/ninayar/workspace/ML/src/utils/votes-train.csv";
		String[] temp;
		TrainVotes v = new TrainVotes();
		ArrayList<TrainVotes> train=new ArrayList<TrainVotes>();

		try (CSVReader reader = new CSVReader(new FileReader(csvFile), ',')) {
			while ((temp = reader.readNext()) != null) {

				// use comma as separator
				for (int i = 0; i < temp.length; i++) {
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
					train.add(v);
				}
				System.out.print(train);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
