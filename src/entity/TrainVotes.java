package entity;

public class TrainVotes {
	
	double democrat;
	int democrat_index;
	double population;
	int population_index;
	double population_change;
	int population_change_index;
	double age65plus;
	int age65plus_index;
	double Black;
	int black_index;
	double Hispanic;
	int hispanic_index;
	double Edu_bachelors;
	int edu_bachelors_index;
	double income;
	int income_index;
	double Poverty;
	int poverty_index;
	double density;
	int density_index;
	public double getDemocrat() {
		return democrat;
	}
	public void setDemocrat(double democrat) {
		this.democrat = democrat;
	}
	public int getDemocrat_index() {
		return democrat_index;
	}
	public void setDemocrat_index(int democrat_index) {
		this.democrat_index = democrat_index;
	}
	public double getPopulation() {
		return population;
	}
	public void setPopulation(double population) {
		this.population = population;
	}
	public int getPopulation_index() {
		return population_index;
	}
	public void setPopulation_index(int population_index) {
		this.population_index = population_index;
	}
	public double getPopulation_change() {
		return population_change;
	}
	public void setPopulation_change(double population_change) {
		this.population_change = population_change;
	}
	public int getPopulation_change_index() {
		return population_change_index;
	}
	public void setPopulation_change_index(int population_change_index) {
		this.population_change_index = population_change_index;
	}
	public double getAge65plus() {
		return age65plus;
	}
	public void setAge65plus(double age65plus) {
		this.age65plus = age65plus;
	}
	public int getAge65plus_index() {
		return age65plus_index;
	}
	public void setAge65plus_index(int age65plus_index) {
		this.age65plus_index = age65plus_index;
	}
	public double getBlack() {
		return Black;
	}
	public void setBlack(double black) {
		Black = black;
	}
	public int getBlack_index() {
		return black_index;
	}
	public void setBlack_index(int black_index) {
		this.black_index = black_index;
	}
	public double getHispanic() {
		return Hispanic;
	}
	public void setHispanic(double hispanic) {
		Hispanic = hispanic;
	}
	public int getHispanic_index() {
		return hispanic_index;
	}
	public void setHispanic_index(int hispanic_index) {
		this.hispanic_index = hispanic_index;
	}
	public double getEdu_bachelors() {
		return Edu_bachelors;
	}
	public void setEdu_bachelors(double edu_bachelors) {
		Edu_bachelors = edu_bachelors;
	}
	public int getEdu_bachelors_index() {
		return edu_bachelors_index;
	}
	public void setEdu_bachelors_index(int edu_bachelors_index) {
		this.edu_bachelors_index = edu_bachelors_index;
	}
	public double getIncome() {
		return income;
	}
	public void setIncome(double income) {
		this.income = income;
	}
	public int getIncome_index() {
		return income_index;
	}
	public void setIncome_index(int income_index) {
		this.income_index = income_index;
	}
	public double getPoverty() {
		return Poverty;
	}
	public void setPoverty(double poverty) {
		Poverty = poverty;
	}
	public int getPoverty_index() {
		return poverty_index;
	}
	public void setPoverty_index(int poverty_index) {
		this.poverty_index = poverty_index;
	}
	public double getDensity() {
		return density;
	}
	public void setDensity(double density) {
		this.density = density;
	}
	public int getDensity_index() {
		return density_index;
	}
	public void setDensity_index(int density_index) {
		this.density_index = density_index;
	}
	@Override
	public String toString() {
		return "TrainVotes [democrat=" + democrat + ", democrat_index=" + democrat_index + ", population=" + population
				+ ", population_index=" + population_index + ", population_change=" + population_change
				+ ", population_change_index=" + population_change_index + ", age65plus=" + age65plus
				+ ", age65plus_index=" + age65plus_index + ", Black=" + Black + ", black_index=" + black_index
				+ ", Hispanic=" + Hispanic + ", hispanic_index=" + hispanic_index + ", Edu_bachelors=" + Edu_bachelors
				+ ", edu_bachelors_index=" + edu_bachelors_index + ", income=" + income + ", income_index="
				+ income_index + ", Poverty=" + Poverty + ", poverty_index=" + poverty_index + ", density=" + density
				+ ", density_index=" + density_index + "]";
	}

	
}
