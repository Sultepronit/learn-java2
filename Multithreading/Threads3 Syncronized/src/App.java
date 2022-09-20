
public class App {
	
	private int count = 0;
	
	public synchronized void increment() {
		count++;
	}

	public static void main(String[] args) {
		
		App app = new App();
		app.doWork();

	}
	
	public void doWork() {
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				for(int i = 0; i < 10000; i++) increment(); //count++;
			}
			
		});
		Thread t2 = new Thread(() -> {
			for(int i = 0; i < 10000; i++) increment(); //count++;
		});
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// without .join() always 0 -- don't wait for threads to end 
		// if count++ almost always 20000
		// if increment() always 20000
		System.out.println(count);
		
	}

}
