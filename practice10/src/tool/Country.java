package tool;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Country {
	private String name;
	private int population;
	private double gdp;
	private List<City> cities;

	public Country(String name, int population, double gdp, List<City> cities) {
		this.name = name;
		this.population = population;
		this.gdp = gdp;
		this.cities = cities;
	}
	
	public String getName() {
		return name;
	}

	public List<City> getCities() {
		return cities;
	}
}
