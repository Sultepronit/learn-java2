import java.util.List;
import java.util.Map;

public class App {

	public static void main(String[] args) {
		// the list is immutable
		List<String> stringList = List.of("one", "two", "tree");
		stringList.forEach(e -> System.out.println(e));
		// error!!!
		//stringList.add("four");
		
		
		String[] array = {"four", "five", "six"};
		var stringList2 = List.of(array);
		stringList2.forEach(System.out::println);
		
		var numberMap = Map.of("one", 1, "two", 2, "tree", 3);
		numberMap.forEach((k,v) -> System.out.println(k + ": " + v));
	}

}
