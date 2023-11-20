import java.util.*;

class Dog {
	private String name;
	public String breed;
	private int age;
	
	public Dog(String name, int age) {
		this.name = name;
		breed = "ǳ�갳";
		this.age = age;
	}
	
	public Dog(String name, String breed, int age) {
		this.name = name;
		this.breed = breed;
		this.age = age;
	}
	
	public String toString() {
		return ("(��Ī) " + name + ",  (����) " + breed + ",  (����) " + age + "��");
	}
	
	public void barking() {
		System.out.println("�۸�");
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
		
		
		System.out.println("(���࿹1) --------------------------------");
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				System.out.print(array[i][j]);
				
			}
			System.out.println();
		}
			
		// (����2-1) generate3X5() �޼ҵ带 �����ϴ� ��ġ
	}	
	
	public static void main(String[] args) {
		
			generate3X5();  // (����2-1) 3X5 ũ�� 2�����迭 ������ ���   <--- (�׽�Ʈ �ڵ�)
			
			Scanner sc = new Scanner(System.in);
			String name, breed;
			int age;
			
			System.out.print("������ �̸�, ����, ����: ");
			name = sc.next();
			breed = sc.next();
			age = sc.nextInt();
			
			Dog dg = new Dog(name, breed, age);
			System.out.println(dg.toString());
			dg.barking();
			
			
			
			
			
	}
}	