
public class App {

	public static void main(String[] args) {
		
		Test test = new Test();
		
		int value = test.getInitialValue();
		
		test.doStuff(value);
		
		System.out.println("Starting...");
		
		System.out.println("Process");
		
		value++;
		
		value = value - 8;
		
		System.out.println(value);
		
		System.out.println("Finnish.");

	}

}
