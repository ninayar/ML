package entity;

public class TestVotes {

	double democrat;
	double population;
	double population_change;
	double age65plus;
	double Black;
	double Hispanic;
	double Edu_bachelors;
	double income;
	double Poverty;
	double density;

	public double getDemocrat() {
		return democrat;
	}


	public void setDemocrat(double democrat) {
		this.democrat = democrat;
	}

	public double getPopulation() {
		return population;
	}


	public void setPopulation(double population) {
		this.population = population;
	}

	public double getPopulation_change() {
		return population_change;
	}


	public void setPopulation_change(double population_change) {
		this.population_change = population_change;
	}

	public double getAge65plus() {
		return age65plus;
	}


	public void setAge65plus(double age65plus) {
		this.age65plus = age65plus;
	}

	public double getBlack() {
		return Black;
	}


	public void setBlack(double black) {
		Black = black;
	}

	public double getHispanic() {
		return Hispanic;
	}


	public void setHispanic(double hispanic) {
		Hispanic = hispanic;
	}

	public double getEdu_bachelors() {
		return Edu_bachelors;
	}


	public void setEdu_bachelors(double edu_bachelors) {
		Edu_bachelors = edu_bachelors;
	}

	public double getIncome() {
		return income;
	}


	public void setIncome(double income) {
		this.income = income;
	}

	public double getPoverty() {
		return Poverty;
	}


	public void setPoverty(double poverty) {
		Poverty = poverty;
	}

	public double getDensity() {
		return density;
	}


	public void setDensity(double density) {
		this.density = density;
	}

	@Override
	public String toString() {
		return "TestVotes [democrat=" + democrat + ", population=" + population + ", population_change="
				+ population_change + ", age65plus=" + age65plus + ", Black=" + Black + ", Hispanic=" + Hispanic
				+ ", Edu_bachelors=" + Edu_bachelors + ", income=" + income + ", Poverty=" + Poverty + ", density="
				+ density + "]";
	}	
}
