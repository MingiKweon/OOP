package game;

import java.util.Random;

public class WordGame {
	String[] wordArray;
	Random r = new Random();
	
	public WordGame(String[] words) {
		// (문제5-1) 구현 위치
		wordArray = words;
	}
	
	public String selectWord() {
		// (문제5-1) 구현 위치
		String word;
		
		word = wordArray[r.nextInt(wordArray.length - 1)];
		return word;
	}
	
	public static boolean check(String s, StringBuffer a, char ch) {
		// (문제5-1) 구현 위치
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ch) {
                a.setCharAt(i, ch);
            }
        }
        
        if (a.indexOf("_") == -1) {
        	return true;
        } else {
        	return false;
        }
	}
}
