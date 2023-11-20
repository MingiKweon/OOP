import java.util.*;

class Dog {
	private String name;
	public String breed;
	private int age;
	
	public Dog(String name, int age) {
		this.name = name;
		breed = "풍산개";
		this.age = age;
	}
	
	public Dog(String name, String breed, int age) {
		this.name = name;
		this.breed = breed;
		this.age = age;
	}
	
	public String toString() {
		return ("(애칭) " + name + ",  (견종) " + breed + ",  (나이) " + age + "살");
	}
	
	public void barking() {
		System.out.println("멍멍");
	}
	
}

public class Practice2 {
	
	static void generate3X5() {
		
		Random rd = new Random();
		
		int[][] array = new int[3][5];
		
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				array[i][j] = 0;
			}
		}
		
		for (int k = 1; k <= 5; k++) {
			int row, col;
			do {
				row = rd.nextInt(3);
				col = rd.nextInt(5);
				
				
			} while (array[row][col] != 0);
			array[row][col] = k;
		}
		
		
		System.out.println("(실행예1) --------------------------------");
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				System.out.print(array[i][j]);
				
			}
			System.out.println();
		}
			
		// (문제2-1) generate3X5() 메소드를 구현하는 위치
	}	
	
	public static void main(String[] args) {
		
			generate3X5();  // (문제2-1) 3X5 크기 2차원배열 생성과 출력   <--- (테스트 코드)
			
			Scanner sc = new Scanner(System.in);
			String name, breed;
			int age;
			
			System.out.print("강아지 이름, 종류, 나이: ");
			name = sc.next();
			breed = sc.next();
			age = sc.nextInt();
			
			Dog dg = new Dog(name, breed, age);
			System.out.println(dg.toString());
			dg.barking();
			
			
			
			
			
	}
}	