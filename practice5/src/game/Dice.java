package game;

import java.util.Random;

public class Dice {
	private int value;
	Random r = new Random();
	
	public Dice(int val){
		this.value = val;
		// (문제5-2) 구현 위치

	}
	
	public int roll(){
		value = r.nextInt(6);
		
		return value;
		// (문제5-2) 구현 위치

	}
	
	public int getValue(){
		return value;
		// (문제5-2) 구현 위치

	}
	
	public String toString(){
		return "현재 주사위 상태 : " + value;
		// (문제5-2) 구현 위치

	}
}