package game;

import java.util.Random;

public class WordGame {
	String[] wordArray;
	Random r = new Random();
	
	public WordGame(String[] words) {
		// (����5-1) ���� ��ġ
		wordArray = words;
	}
	
	public String selectWord() {
		// (����5-1) ���� ��ġ
		String word;
		
		word = wordArray[r.nextInt(wordArray.length - 1)];
		return word;
	}
	
	public static boolean check(String s, StringBuffer a, char ch) {
		// (����5-1) ���� ��ġ
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
