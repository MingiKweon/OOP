import java.util.ArrayList;
import java.util.Scanner;

//(문제3-1) 1개 클래스
class Book {
	private String title;
    private int score;

    public Book(String title, int score) {
        this.title = title;
        this.score = score;
    }
    
    
    public String getTitle() {
        return title;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Book [title=" + title + ", score=" + score + "]";
    }
    
}



//(문제3-2) 3개 클래스
class Phone {
	 public String owner;

    public Phone(String owner) {
        this.owner = owner;
    }

    public void talk() {
        System.out.println(owner + "가 통화 중이다.");
    }
}

class Answeringphone extends Phone {
	public String message;

    public Answeringphone(String owner, String message) {
        super(owner);
        this.message = message;
    }

    public void autoAnswering() {
        System.out.println(owner + "가 부재 중이다. " + message + " 전화 줄래.");
    }
}

class Smartphone extends Answeringphone {
	private String game;

    public Smartphone(String owner, String message, String game) {
        super(owner, message);
        this.game = game;
    }

    public void playGame() {
        System.out.println(owner + "가 " + game + " 게임을 하는 중이다. " + message + " 전화 줄래.");
    }
}



//(문제3-3) 2개 클래스
class Vehicle {
	public String color;
    public int speed;

    public Vehicle(String color, int speed) {
        this.color = color;
        this.speed = speed;
    }

    public void show() {
        System.out.println("색상: " + color);
        System.out.println("속도: " + speed);
    }
}

class Car extends Vehicle {
	private int displacement;
    private int gears;

    public Car(String color, int speed, int displacement, int gears) {
        super(color, speed);
        this.displacement = displacement;
        this.gears = gears;
    }

    @Override
    public void show() {
        System.out.println("자동차 색상: " + color);
        System.out.println("자동차 속도: " + speed);
        System.out.println("자동차 배기량: " + displacement);
        System.out.println("자동차 기어 단수: " + gears);
    }
}


// (실습3) 테스트 클래스
public class Practice3 {
    public static void main(String[] args) {
    	
    	Scanner sc = new Scanner(System.in);
    	ArrayList<Book> books = new ArrayList<>();
    	
        // (문제3-1) 테스트코드 시작 위치
		System.out.println("---(문제3-1)---");
        
		while (true) {
			System.out.println("============================");
            System.out.println("1. 책 등록");
            System.out.println("2. 책 검색");
            System.out.println("3. 모든 책 출력");
            System.out.println("4. 종료");
            System.out.println("============================");
            System.out.print("메뉴를 선택하시오: ");
            
            int x = sc.nextInt();
            
            if (x == 1) {
                System.out.print("책 제목: ");
                sc.nextLine();
                String title = sc.nextLine();
                
                System.out.print("책 평점: ");
                int score = sc.nextInt();
                
                
                Book book = new Book(title, score);
                books.add(book);
                
            } else if (x == 2) {
                System.out.print("책 제목: ");
                sc.nextLine();
                String searchTitle = sc.nextLine();

                int foundCount = 0;
                for (Book book : books) {
                    if (book.getTitle().contains(searchTitle)) {
                        System.out.println(book);
                        foundCount++;
                    }
                }
                System.out.println("검색된 책 갯수: " + foundCount + "권");
            } else if (x == 3) {
                System.out.println("등록된 책 갯수: " + books.size() + "권");
                for (Book book : books) {
                    System.out.println(book);
                }
            } else if (x == 4) {
                break;
            }
                
            
            
		}
        //    (작업1)  Book 객체 생성, 메뉴 생성 및 처리
        //    (작업2)  책 배열 타입은 ArrayList를 사용함
        //    (작업3)  책 이름 비교는 String 메소드(indexOf())를 사용함

    	
        //  ((문제3-2) 테스트코드 시작 위치
		System.out.println("\n\n---(문제3-2)---");
		Phone[] phones = { new Phone("황진이"), new Answeringphone("길동이", "내일"), new Smartphone("민국이", "저녁때", "갤러그") };
        // --> 위 문장에서 초기화된 전화기 배열객체를 사용하여 아래 위치에 테스트 코드를 추가-완성할 것.

        //  (문제3-2) 테스트코드 추가
		for (Phone phone : phones) {
            if (phone instanceof Smartphone) {
                ((Smartphone) phone).playGame();
            } else if (phone instanceof Answeringphone) {
                ((Answeringphone) phone).autoAnswering();
            } else if (phone instanceof Phone) {
                phone.talk();
            }
        }
		
        // (문제3-3) 테스트코드 시작 위치
		System.out.println("\n\n---(문제3-3)---");
		Vehicle v = new Vehicle("실버", 150);
		v.show();
		
		System.out.println();
		Car c = new Car("파랑", 200, 1000, 5);
		c.show();

		System.out.println();
		Vehicle v2 = c;
		v2.show();
		
    }
}