package main;

import game.Dice;
import game.WordGame;
import statistics.Document;

import java.util.*;
import java.io.*;

public class Practice5 {

	public static void main(String[] args) throws IOException {

		// (문제5-1)
		System.out.println("----(문제5-1)----");
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
				System.out.println("현재의 상태: " + answer);
				System.out.print("글자를 추측하시오: ");
				s = sc.nextLine();
				if (WordGame.check(solution, answer, s.charAt(0)) == true)
					break;
			}
			System.out.println("현재의 상태: " + answer + "\n");
		} while(true);
		
		// (문제5-2)
		System.out.println("\n----(문제5-2)----");
		Dice d = new Dice(5);	
		for(int i=0; i<10; i++) {
			System.out.println(d.toString());
			d.roll();
		}
		
		// (문제5-3)
		System.out.println("\n----(문제5-3)----");
		Document doc = new Document();
		int[] counter;
		
		System.out.print("----------------\n영어 문장을 입력하세요: ");
		s = sc.nextLine();
		
		String[] wordList = doc.splitWords(s);
		System.out.println("----------------\n단어개수: "+wordList.length+"개");
		
		System.out.println("----------------");
		counter = doc.countLetters(wordList);
		for(int i=0;i<26; i++) {
			ch = (char)(i+'a');  
			System.out.println("문자('" + ch + "')개수: "+counter[i]+"개");
	    }
		
		int total = doc.totalLetters(counter);
		System.out.println("----------------\n글자개수: "+total+"개\n----------------");
	}
}