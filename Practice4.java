// (����4-1)
interface Queue {
	void enqueue(int item);
	int dequeue();
	boolean isEmpty();
	void display();
}

class MyQueue implements Queue {
	private static int front, back, capacity;
	private static int queue[];
	private final int INC_SIZE = 3;

	public MyQueue(int size) {
		front = back = 0;
		capacity = size;
		queue = new int[capacity];
	}

	public void enqueue(int item) {
		// (����4-1) ���� ��ġ
		if (back == capacity) {
			int new_capa = capacity + INC_SIZE;
			int new_queue[] = new int[new_capa];
			
			for (int i = 0; i < capacity; i++) {
				new_queue[i] = queue[i];
			}
			
			capacity = new_capa;
			queue = new_queue;
			
		}
		
		queue[back] = item;
		back++;
		
		

	}

	public int dequeue() {
		// (����4-1) ���� ��ġ
		int item = queue[front];
		for (int j = 0; j < back; j++) {
			queue[j] = queue[j + 1];
		}
		back--;
		return item;
		
		

	}

	public void display() {
		// (����4-1) ���� ��ġ
		if (isEmpty()) {
			System.out.println("Queue is Empty");
		} else {
			System.out.print("[ ");
			for (int x = front; x < back; x++) {
				System.out.print(queue[x] + " ");
				
			}
			System.out.println("]");
			
			if (back == capacity) {
				System.out.println(String.format("Queue is full... increasing capacity from %d to %d", capacity, capacity + INC_SIZE));
				
			}
			
		}
		

	}

	@Override
	public boolean isEmpty() {
		// (����4-1) ���� ��ġ
		return front == back;
		

	}
}


// (����4-2)
class CardDeck implements java.util.Iterator {
	// (����4-2) ���� ��ġ
	private static String[] cardNames = {
             "2", "3", "4", "5", "6", "7", "8", "9",
	     "10", "Jack", "Queen", "King", "Ace" };
    private int current = 0;
    private boolean state = false;
    
    public boolean hasNext() {
    	return current < cardNames.length;
		

    }
    
    public Object next() {
    	// (����4-2) ���� ��ġ

		state = false;
		return cardNames[current++];
		

    }
    
    public void remove() {    	
    	// (����4-2) ���� ��ġ
    	if (!state && current > 0) {
			for (int y = current - 1; y < cardNames.length - 1; y++) {
				cardNames[y] = cardNames[y + 1];
			}
			cardNames[cardNames.length - 1] = null;
			state = true;
			current--;
		 }
		

    }
}


// (����4-3)
interface GetInfo {
	double getValue();
	static double average(GetInfo[] objects) { return 0; }
}

class Student implements GetInfo {
	double score;

	public Student(double score) {
		// (����4-3) ���� ��ġ
		this.score = score;
		

	}

	@Override
	public double getValue() {
		// (����4-3) ���� ��ġ
		return score;
		

	}

	public static double average(GetInfo[] objects) {
		// (����4-3) ���� ��ġ
		double sum = 0;
		for (GetInfo obj : objects) {
			sum += obj.getValue();
		}
		return sum / objects.length;
		

	}
}


public class Practice4 {
	public static void main(String[] args) {
    	// (����4-1)
		System.out.println("---(����4-1)---");
		MyQueue q = new MyQueue(3);
		q.display();
		q.enqueue(10);
		q.display();
		q.enqueue(20);
		q.display();
		q.enqueue(30);
		q.display();
		q.enqueue(40);
		q.display();
		q.enqueue(50);
		q.display();
		q.enqueue(60);
		q.display();
		q.enqueue(70);
		q.display();

		q.dequeue();
		q.display();
		q.dequeue();
		q.display();
		q.dequeue();
		q.display();
		q.dequeue();
		q.display();
		q.dequeue();
		q.display();
		q.dequeue();
		q.display();
		q.dequeue();
		q.display();

    	// (����4-2)
		System.out.println("\n---(����4-2)---");
        System.out.println("(ù��° ����)");
		CardDeck c = new CardDeck();
        while (c.hasNext()) {
        	String s = (String) c.next();
        	if(s == "Jack")
        		c.remove();
            if(s != null) System.out.println("next()�� ��ȯ�ϴ� ��:"+ s);
        }
        System.out.println("\n(�ι�° ����)");
		c = new CardDeck();
        while (c.hasNext()) {
        	String s = (String) c.next();
        	if(s != null) System.out.println("next()�� ��ȯ�ϴ� ��:"+ s);
        }

    	// (����4-3)
		System.out.println("\n---(����4-3)---");
		Student[] list = new Student[3];
		list[0] = new Student(10.0);
		list[1] = new Student(20.0);
		list[2] = new Student(30.0);
		System.out.println(Student.average(list));
	}
}