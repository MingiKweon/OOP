package statistics;

public class Document {
	// (����5-3) ���� ��ġ
	String[] list;
	int[] counter = new int[26];
	
	public String[] splitWords(String s) {
		list = s.split(" ");
		return list;
		

	}
	
	public int[] countLetters(String[] wordList) {
		// (����5-3) ���� ��ġ
		String alpa = "abcdefghijklmnopqrstuvwxyz";
		int index = 0;
		
		for (int i = 0; i < wordList.length; i++) {
			counter[i] = 0;
		}
		
		for (char s : alpa.toCharArray()) {
			int numCount = 0;
			for (String word : wordList) {
				for (int i = 0; i < word.length(); i++) {
					if (word.toLowerCase().charAt(i) == s) {
						numCount++;
					}
				}
			}
			counter[index++] = numCount;
		}
		
		return counter;
	}
	
	public int totalLetters(int[] countArray) {
		// (����5-3) ���� ��ġ
		int total = 0;
		for (int count : countArray) {
			total += count;
		}
		return total;
	}
}
