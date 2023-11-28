package tool;

public class City {
  private String name;
  private int population;
  private String countryName;

  @Override
  public String toString() {
    return "City [name = " + name + ", population = " + population + ", countryName = "
        + countryName + "]";
  }

  public City(String name, int population, String countryName) {
    super();
    this.name = name;
    this.population = population;
    this.countryName = countryName;
  }

  public int getPopulation() {
    return population;
  }

  public void setPopulation(int population) {
    this.population = population;
  }
}
