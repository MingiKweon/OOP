package test;

import java.util.Scanner;
import tool.MoleGame;
import tool.NationalCapital;

public class Test {
  public static void main(String[] args) {


    // ����(9-1) ���� ���� ���߱�

    NationalCapital nc = new NationalCapital();
    nc.printDataSet();

    Scanner sc = new Scanner(System.in);
    while (true) {
      System.out.print("���� �̸��� �Է��Ͻÿ�: ");
      String s = sc.nextLine();
      if (s.equals("quit"))
        break;
      nc.capitalName(s);
    }


    // ����(9-2) �δ��� ����

    MoleGame m = new MoleGame();

  }
}
