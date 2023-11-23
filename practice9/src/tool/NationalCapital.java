package tool;

import java.util.HashMap;

public class NationalCapital {
  HashMap<String, String> hmCountry = new HashMap<String, String>();

  public NationalCapital() {
    hmCountry.put("Canada", "Ottawa");
    hmCountry.put("USA", "Washington");
    hmCountry.put("Japan", "Tokyo");
    hmCountry.put("China", "Beijing");
    hmCountry.put("UK", "London");
    hmCountry.put("France", "Paris");
    hmCountry.put("Germany", "Berlin");
    hmCountry.put("Korea", "Seoul");
    // (����9-1) ���� ��ġ(1)

  }

  public void printDataSet() {
    hmCountry.forEach((key, value) -> {
      System.out.printf("Key: %s, Value: %s", key, hmCountry.get(key));
      System.out.println("");
    });
    // (����9-1) ���� ��ġ(2)

  }

  public void capitalName(String country) {
    System.out.printf("%s �� ����: %s", country, hmCountry.get(country));
    System.out.println("");
    // (����9-1) ���� ��ġ(3)
  }
}

