package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Stream;
import tool.City;
import tool.Country;
import tool.Product;

public class Test {
  int priceHigh = -1, priceLow = -1;
  double ratingHigh = -1, ratingLow = -1;
  Stream<Product> st3;

  public static void main(String[] args) {

    // ����(10-1) ������ �ִ��α� �������� �˻�

    System.out.println("---(����10-1)---");
    List<City> cities = Arrays.asList(new City("Busan", 800, "Korea"),
        new City("Incheon", 500, "Korea"), new City("Seoul", 1000, "Korea"));
    List<City> cities2 = Arrays.asList(new City("LA", 2500, "USA"),
        new City("New York", 3000, "USA"), new City("Chicago", 2000, "USA"));
    List<Country> nations = Arrays.asList(new Country("Korea", 5162, 18102, cities),
        new Country("USA", 33828, 229961, cities2));

    // ��Ʈ���� �޼ҵ� ȣ�� ���� ����
    City max = cities.stream().max(Comparator.comparing(City::getPopulation))
        .orElseThrow(NoSuchElementException::new);
    System.out.println("(���� ���) " + max);
    // ��Ʈ���� �޼ҵ� ȣ�� ���� ��

    Scanner sc = new Scanner(System.in);
    System.out.print("\n������Ī: ");
    String name = sc.nextLine();

    // ����(10-1) ���� ������ �ϼ��� ��
    if (name.equals("USA")) {
      City max2 = cities2.stream().max(Comparator.comparing(City::getPopulation))
          .orElseThrow(NoSuchElementException::new);
      System.out.println("\n(����) " + max2);
    } else if (name.equals("Korea")) {
      City max2 = cities.stream().max(Comparator.comparing(City::getPopulation))
          .orElseThrow(NoSuchElementException::new);
      System.out.println("\n(����) " + max2);
    }



    // (����10-2) ��ǰ �˻�

    System.out.println("\n---(����10-2)---");
    List<Product> list = new ArrayList<Product>();
    list.add(new Product(1, "NoteBook", 100, 3.5));
    list.add(new Product(2, "TV", 320, 3.3));
    list.add(new Product(3, "Washing Machine", 250, 2.3));
    list.add(new Product(4, "Air Conditioner", 500, 2.1));
    list.add(new Product(5, "NoteBook", 160, 4.2));
    list.add(new Product(6, "TV", 550, 4.7));
    list.add(new Product(7, "Washing Machine", 600, 4.8));
    list.add(new Product(8, "Air Conditioner", 800, 4.5));

    Test t = new Test();

    System.out.println("��ǰ�� �˻��Ͻÿ�.");
    System.out.print("��ǰ�� �̸�(*�� ��� ��ǰ�� �ǹ�):");
    String name2 = sc.nextLine();

    System.out.print("��ǰ�� ���� ����(-1�� ���� ������ �ǹ�):");
    t.priceHigh = sc.nextInt();
    System.out.print("��ǰ�� ���� ����(-1�� ���� ������ �ǹ�):");
    t.priceLow = sc.nextInt();

    System.out.print("��ǰ�� ���� ����(-1�� ���� ������ �ǹ�):");
    t.ratingHigh = sc.nextDouble();
    System.out.print("��ǰ�� ���� ����(-1�� ���� ������ �ǹ�):");
    t.ratingLow = sc.nextDouble();

    // ��Ʈ���� �޼ҵ� ȣ�� ���� ����
    List<Product> result =
        list.stream().filter(p -> t.priceHigh == -1 || p.getPrice() < t.priceHigh)
            .filter(p -> name2.equals("*") || p.getName().equals(name2)).toList();
    System.out.println("\n(���� ���1) " + result);
    // Ȥ��
    Predicate<Product> p1, p2;
    p1 = p -> t.priceHigh == -1 || p.getPrice() < t.priceHigh;
    p2 = p -> name2.equals("*") || p.getName().equals(name2);
    result = list.stream().filter(p1).filter(p2).toList();
    System.out.println("(���� ���2) " + result);
    // ��Ʈ���� �޼ҵ� ȣ�� ���� ��


    Predicate<Product> pr1, pr2, pr3;
    pr1 = (pr) -> name2.equals("*") || pr.getName().equals(name2);
    pr2 = (pr) -> (t.priceHigh == -1 ? true : pr.getPrice() <= t.priceHigh)
        && ((t.priceLow == -1) ? true : pr.getPrice() >= t.priceLow);
    pr3 = (pr) -> ((t.ratingHigh == -1) ? true : pr.getRating() <= t.ratingHigh)
        && ((t.ratingLow == -1) ? true : pr.getRating() >= t.ratingLow);


    // ����(10-2) ���� ������ �ϼ��� ��

    result = list.stream().filter(pr1).filter(pr2).filter(pr3).toList();
    System.out.println("(����) " + result);

  }
}
