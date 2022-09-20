import java.util.ArrayList;
import java.util.Random;

public class Worker {
	
	private Random random = new Random();
	
	private Object lock1 = new Object();
	private Object lock2 = new Object();
	
	private ArrayList<Integer> list1 = new ArrayList<>();
	private ArrayList<Integer> list2 = new ArrayList<>();
	
	public void stageOne() {
		// these blocks are executed in only one thread at time
		// without them app will try to access to to both, and time will be duplicated
		synchronized (lock1) { 
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			list1.add(random.nextInt(100));
		}
	}
	
	public void stageTwo() {
		// these blocks are executed in only one thread at time
		// without them app will try to access to to both, and time will be duplicated
		synchronized (lock2) { 
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			list2.add(random.nextInt(100));
		}
	}
	
	public void process() {
		for(int i = 0; i < 1000; i++) {
			stageOne();
			stageTwo();
		}
	}
	
	public void main() {
		System.out.println("Starting....");
		long start = System.currentTimeMillis();
		
		//process();
		Thread t1 = new Thread(() -> process());
		Thread t2 = new Thread(() -> process());
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		
		System.out.println("Time take: " + (end - start));
		System.out.println("List1: " + list1.size() + "; List2: " + list2.size());
	}
}
