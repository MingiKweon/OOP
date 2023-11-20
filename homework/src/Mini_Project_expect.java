import java.util.*;
public class Mini_Project_expect {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		Random rand = new Random(); //강의자료랑은 다른 방법
		
		int num;
		int count = 0;
		int answer = (int) Math.floor(Math.random() * 20 + 1); //int answer = rand.nextInt(20) + 1;
		
		do {
			System.out.print("정답을 추측하여 보시오: ");
			num = sc.nextInt();
			
			if (num > answer) {
				System.out.println("제시한 정수가 높습니다.");
				count++;
			} else if (num < answer) {
				System.out.println("제시한 정수가 낮습니다.");
				count++;
			} else {
				count++;
				System.out.println("축하합니다. 시도횟수 = " + count);
				break;
			}
		} while (true);
	}

}
