package app;

import java.util.List;

public class App {
	
	
	record Example(int a, int b) {
		// "canonical constructor"
		// every record contain canonical constructor by default!
		Example(int a, int b) {
			this.a = a;
			this.b = b;
		}
	}
	
	record Example2(int a, int b, List<Integer> values) {
		Example2(int a, int b, List<Integer> values) {
			this.a = a;
			this.b = b;
			this.values = List.copyOf(values);
		}
	}
	
	record Range(int begin, int end) {
		/*Range(int begin, int end) {
			if(end < begin) {
				throw new IllegalStateException("End should be greater than begin");
			}
			this.begin = begin;
			this.end = end;
		}*/
		
		//compact version
		Range {
			if(end < begin) {
				throw new IllegalStateException("End should be greater than begin");
			}
		}
		
		Range(int end) {
			this(0, end);
		}
	}
	
	public static void main(String[] args) {
		System.out.println("Hello!");
		
		Example example = new Example(1,2); 
		System.out.println(example);
		
		Range range = new Range(7,5);
		
	}
}
 