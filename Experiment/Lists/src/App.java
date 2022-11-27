import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class App {
	
	private static void listMapDeclaration() {
		// the list is immutable
		List<String> stringList = List.of("one", "two", "tree");
		stringList.forEach(e -> System.out.println(e));
		// error!!!
		//stringList.add("four");
		
		String[] array = {"four", "five", "six"};
		var stringList2 = List.of(array);
		stringList2.forEach(System.out::println);
		
		List<String> stringList3 = new ArrayList<>() {{
			add("seven");
			add("eight");
			add("nine");
		}};
		System.out.println(stringList3);
		
		var numberMap = Map.of("one", 1, "two", 2, "tree", 3);
		numberMap.forEach((k,v) -> System.out.println(k + ": " + v));
	}
	
	private static void floatEquals() {
		float f1 = 2.2f;
		float f2 = 2.0f;
		float f3 = f1 * f2;
		System.out.println(f3);
		if(f3 == 4.4) {
			System.out.println("float == double");
		} else {
			System.out.println(f3 + "f != 4.4");
		}
		
		if(f3 == (float) 4.4) {
			System.out.println("float == float");
		} else {
			System.out.println(f3 + " != " + 4.4);
		}
		
		if(Float.compare(f3, (float) 4.4) == 0) {
			System.out.println("Float.compare!");
		}
		
		System.out.println();
		float f4 = 1.9f;
		float f5 = 0.4f;
		float f6 = f4 % f5;
		System.out.println(f6);
		
		if(f6 == 0.3f) {
			System.out.println("eqals");
		} else {
			System.out.println(f6 + " != " + 0.3);
		}
		
		if(Float.compare(f6, (float) 0.3) == 0) {
			System.out.println("Float.compare!");
		} else {
			System.out.println("Float.compare can't help...");
		}
		
	}
	
	private static void bits() {
		byte b1 = 10;
		System.out.println(b1);
		String s1 = Integer.toBinaryString(b1);
		System.out.println(s1);
		String s2 = Integer.toBinaryString(b1 & 0xFF); //does nothing
		System.out.println(s2);
		String s3 = String.format("%8s", s2).replace(' ', '0');
		System.out.println(s3);
		
		byte b2 = (byte) ~b1;
		System.out.println(b2);
		String s21 = Integer.toBinaryString(b2);
		System.out.println(s21);
		String s22 = Integer.toBinaryString(b2 & 0xFF); //uses only 8 bits
		System.out.println(s22);
		String s23 = String.format("%8s", s22).replace(' ', '0');
		System.out.println(s23);
	}
	
	private static void bits2() {
		byte b1 = 117; 				 // 01110101
		byte b2 = 95; 				 // 01011111
		byte b3 = (byte) (b1 & b2);  // 01010101
		System.out.println(b3);
		String s1 = Integer.toBinaryString(b3);
		System.out.println(s1);
		String s2 = Integer.toBinaryString(b3 & 0xFF); //does nothing
		System.out.println(s2);
		String s3 = String.format("%8s", s2).replace(' ', '0');
		System.out.println(s3);
	}
	
	private static void bits3() {
		byte b1 = 117; 		 // 01110101
		byte b2 = 95; 		 // 01011111
		int b3 = (b1 | b2);  // 01010101
		System.out.println(b3);
		String s1 = Integer.toBinaryString(b3);
		System.out.println(s1);
		String s2 = Integer.toBinaryString(b3 & 0xFF); //does nothing
		System.out.println(s2);
		String s3 = String.format("%8s", s2).replace(' ', '0');
		System.out.println(s3);
	}
	
	static void args(String... args) {
		System.out.println(args[1]);
		//System.out.println(args0); //???
		
		System.out.println(args.getClass());
		int[] iArray = {1, 2, 3};
		System.out.println(iArray.getClass());
		var string = "string";
		System.out.println(string.getClass());
		var num = 55;
		//System.out.println(num.getClass()); // error!
		Integer[] IArray = {1, 2, 3};
		System.out.println(IArray.getClass());
		List<Integer> IList = List.of(IArray);
		System.out.println(IList.getClass());
	}
	
	static void switchCase() {
		var random = new Random();
		int i = random.nextInt(0, 10);
		System.out.println(i);
		switch(i) {
			case 0:
			case 1:
			case 2:
			case 3:
				System.out.println("less than 4");
				break;
			case 4:
			case 5:
			case 6:
				System.out.println("less than 7");
				break;
			default:
				System.out.println("7 or more");
		}
		
		switch(i) {
		case 0:
			System.out.println("0");
		case 1:
			System.out.println("1 or less");
		case 2:
			System.out.println("2 or less");
		case 3:
			System.out.println("3 or less");
		case 4:
			System.out.println("4 or less");
		case 5:
			System.out.println("5 or less");
			break;
		default:
			System.out.println("more than 5");
	}
	}
	
	static void littleStream() {
		int[] array = {1, 2, 3, 4, 5};
		Arrays.stream(array).forEach(System.out::println);
		
		// cannot convert from List<int[]> to List<Integer>!!!
		//List<Integer> list = List.of(array);
		
		var list = List.of(6, 7, 8, 9, 10);
		System.out.println(list);
		list.stream().forEach(i -> 
			System.out.println(Thread.currentThread().getName() + ": " + i)
		);
		
		var bigList = new ArrayList<Integer>();
		for(int i = 0; i < 10001; i += 1) {
			bigList.add(i);
		}
		System.out.println(bigList);
		bigList.parallelStream().forEach(i -> 
			System.out.println(Thread.currentThread().getName() + ": " + i)
		);
		
		var bigArray = bigList.toArray();
		// we can specify the start & the end indexes
		Arrays.stream(bigArray, 100, 1000).parallel().forEach(
			i -> System.out.println(Thread.currentThread().getName() + ": " + i)
		);
	}

	public static void main(String[] args) {
		//listMapDeclaration();
		//floatEquals();
		//bits();
		/*bits2();
		bits3();*/
		//args("one", "two", "three");
		switchCase();
		littleStream();
	}

}
