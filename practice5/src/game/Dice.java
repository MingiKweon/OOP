package game;

import java.util.Random;

public class Dice {
	private int value;
	Random r = new Random();
	
	public Dice(int val){
		this.value = val;
		// (����5-2) ���� ��ġ

	}
	
	public int roll(){
		value = r.nextInt(6);
		
		return value;
		// (����5-2) ���� ��ġ

	}
	
	public int getValue(){
		return value;
		// (����5-2) ���� ��ġ

	}
	
	public String toString(){
		return "���� �ֻ��� ���� : " + value;
		// (����5-2) ���� ��ġ

	}
}