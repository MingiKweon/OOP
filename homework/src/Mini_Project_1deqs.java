
public class Mini_Project_1deqs {

	public static void main(String[] args) {
		int x, y;
		
		for (x = 0; x <= 100; x++) {
			for (y = 0; y <= 100; y++) {
				if (7 * x + 5 * y == 1000) {
					System.out.println("("+ x + ", " + y + ")");
				}
			}
		}

	}

}
