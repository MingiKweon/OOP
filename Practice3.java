import java.util.ArrayList;
import java.util.Scanner;

//(����3-1) 1�� Ŭ����
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



//(����3-2) 3�� Ŭ����
class Phone {
	 public String owner;

    public Phone(String owner) {
        this.owner = owner;
    }

    public void talk() {
        System.out.println(owner + "�� ��ȭ ���̴�.");
    }
}

class Answeringphone extends Phone {
	public String message;

    public Answeringphone(String owner, String message) {
        super(owner);
        this.message = message;
    }

    public void autoAnswering() {
        System.out.println(owner + "�� ���� ���̴�. " + message + " ��ȭ �ٷ�.");
    }
}

class Smartphone extends Answeringphone {
	private String game;

    public Smartphone(String owner, String message, String game) {
        super(owner, message);
        this.game = game;
    }

    public void playGame() {
        System.out.println(owner + "�� " + game + " ������ �ϴ� ���̴�. " + message + " ��ȭ �ٷ�.");
    }
}



//(����3-3) 2�� Ŭ����
class Vehicle {
	public String color;
    public int speed;

    public Vehicle(String color, int speed) {
        this.color = color;
        this.speed = speed;
    }

    public void show() {
        System.out.println("����: " + color);
        System.out.println("�ӵ�: " + speed);
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
        System.out.println("�ڵ��� ����: " + color);
        System.out.println("�ڵ��� �ӵ�: " + speed);
        System.out.println("�ڵ��� ��ⷮ: " + displacement);
        System.out.println("�ڵ��� ��� �ܼ�: " + gears);
    }
}


// (�ǽ�3) �׽�Ʈ Ŭ����
public class Practice3 {
    public static void main(String[] args) {
    	
    	Scanner sc = new Scanner(System.in);
    	ArrayList<Book> books = new ArrayList<>();
    	
        // (����3-1) �׽�Ʈ�ڵ� ���� ��ġ
		System.out.println("---(����3-1)---");
        
		while (true) {
			System.out.println("============================");
            System.out.println("1. å ���");
            System.out.println("2. å �˻�");
            System.out.println("3. ��� å ���");
            System.out.println("4. ����");
            System.out.println("============================");
            System.out.print("�޴��� �����Ͻÿ�: ");
            
            int x = sc.nextInt();
            
            if (x == 1) {
                System.out.print("å ����: ");
                sc.nextLine();
                String title = sc.nextLine();
                
                System.out.print("å ����: ");
                int score = sc.nextInt();
                
                
                Book book = new Book(title, score);
                books.add(book);
                
            } else if (x == 2) {
                System.out.print("å ����: ");
                sc.nextLine();
                String searchTitle = sc.nextLine();

                int foundCount = 0;
                for (Book book : books) {
                    if (book.getTitle().contains(searchTitle)) {
                        System.out.println(book);
                        foundCount++;
                    }
                }
                System.out.println("�˻��� å ����: " + foundCount + "��");
            } else if (x == 3) {
                System.out.println("��ϵ� å ����: " + books.size() + "��");
                for (Book book : books) {
                    System.out.println(book);
                }
            } else if (x == 4) {
                break;
            }
                
            
            
		}
        //    (�۾�1)  Book ��ü ����, �޴� ���� �� ó��
        //    (�۾�2)  å �迭 Ÿ���� ArrayList�� �����
        //    (�۾�3)  å �̸� �񱳴� String �޼ҵ�(indexOf())�� �����

    	
        //  ((����3-2) �׽�Ʈ�ڵ� ���� ��ġ
		System.out.println("\n\n---(����3-2)---");
		Phone[] phones = { new Phone("Ȳ����"), new Answeringphone("�浿��", "����"), new Smartphone("�α���", "���ᶧ", "������") };
        // --> �� ���忡�� �ʱ�ȭ�� ��ȭ�� �迭��ü�� ����Ͽ� �Ʒ� ��ġ�� �׽�Ʈ �ڵ带 �߰�-�ϼ��� ��.

        //  (����3-2) �׽�Ʈ�ڵ� �߰�
		for (Phone phone : phones) {
            if (phone instanceof Smartphone) {
                ((Smartphone) phone).playGame();
            } else if (phone instanceof Answeringphone) {
                ((Answeringphone) phone).autoAnswering();
            } else if (phone instanceof Phone) {
                phone.talk();
            }
        }
		
        // (����3-3) �׽�Ʈ�ڵ� ���� ��ġ
		System.out.println("\n\n---(����3-3)---");
		Vehicle v = new Vehicle("�ǹ�", 150);
		v.show();
		
		System.out.println();
		Car c = new Car("�Ķ�", 200, 1000, 5);
		c.show();

		System.out.println();
		Vehicle v2 = c;
		v2.show();
		
    }
}