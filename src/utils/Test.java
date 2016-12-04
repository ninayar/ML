package utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.opencsv.CSVReader;

import entity.TestVotes;
import entity.TrainVotes;

public class Test {

	public static void main(String[] args) {

		String csvFile = "D:/ninayar/workspace/ML/src/utils/votes-test.csv";
		String[] temp;
		
		ArrayList<TestVotes> test = new ArrayList<TestVotes>();
		TestVotes[] s = new TestVotes[777];
		int row = 777;

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
			s = test.toArray(new TestVotes[test.size()]);
			System.out.println(s[0]);
			System.out.println(s[3]);
			System.out.println(s[9]);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
