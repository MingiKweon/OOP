import java.util.*;

public class Mini_Project_temp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int num;
		double temp;
		
		System.out.println("======================");
		System.out.println("1. 화씨 -> 섭씨");
		System.out.println("2. 섭씨 -> 화씨");
		System.out.println("======================");
		System.out.print("번호를 선택하시오: ");
		num = sc.nextInt();
		
		System.out.print(((num == 1) ? "화씨" : "섭씨") + "온도를 입력하시오: ");
		temp = sc.nextDouble();
		System.out.println(((num == 2) ? "화씨" : "섭씨") + "온도는 " + ((num == 1) ? (temp - 32) * 5/9 : (temp * 9/5 + 32)));
		
		
		
		
	}

}
