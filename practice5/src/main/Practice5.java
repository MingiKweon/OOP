package main;

import game.Dice;
import game.WordGame;
import statistics.Document;

import java.util.*;
import java.io.*;

public class Practice5 {

	public static void main(String[] args) throws IOException {

		// (����5-1)
		System.out.println("----(����5-1)----");
		String solution;
		String s;
		char ch;
		Scanner sc = new Scanner(System.in);
		
		String[] words = { "java", "count", "school", "student", "computer", "programmer" };
		WordGame wg = new WordGame(words);
		
		do {
			System.out.print("(1)Word Game, (2)exit: ");
			s = sc.nextLine();
			if(s.charAt(0)=='2') break;
			solution = wg.selectWord();
			StringBuffer answer = new StringBuffer(solution.length());
			for (int i = 0; i < solution.length(); i++)
				answer.append('_');

			while (true) {
				System.out.println("������ ����: " + answer);
				System.out.print("���ڸ� �����Ͻÿ�: ");
				s = sc.nextLine();
				if (WordGame.check(solution, answer, s.charAt(0)) == true)
					break;
			}
			System.out.println("������ ����: " + answer + "\n");
		} while(true);
		
		// (����5-2)
		System.out.println("\n----(����5-2)----");
		Dice d = new Dice(5);	
		for(int i=0; i<10; i++) {
			System.out.println(d.toString());
			d.roll();
		}
		
		// (����5-3)
		System.out.println("\n----(����5-3)----");
		Document doc = new Document();
		int[] counter;
		
		System.out.print("----------------\n���� ������ �Է��ϼ���: ");
		s = sc.nextLine();
		
		String[] wordList = doc.splitWords(s);
		System.out.println("----------------\n�ܾ��: "+wordList.length+"��");
		
		System.out.println("----------------");
		counter = doc.countLetters(wordList);
		for(int i=0;i<26; i++) {
			ch = (char)(i+'a');  
			System.out.println("����('" + ch + "')����: "+counter[i]+"��");
	    }
		
		int total = doc.totalLetters(counter);
		System.out.println("----------------\n���ڰ���: "+total+"��\n----------------");
	}
}