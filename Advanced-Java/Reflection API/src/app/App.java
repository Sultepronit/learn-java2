package app;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Date;

class User {
	public int code;
}

class Employee extends User {
	private int id;
	public String name;
	
	private void calculate() {
		System.out.println("Calculated all!");
	}
	
	public boolean setUpdated(Date updated, int index) {
		return true;
	}
	
	public void doSomething(int i) {
		System.out.println("Working! " + i);
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + "]";
	}
}

public class App {

	public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, SecurityException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Class<Employee> clazz = Employee.class;
		System.out.println(clazz);
		System.out.println(Arrays.asList(clazz.getFields()));
		System.out.println(Arrays.asList(clazz.getDeclaredFields()));
		
		System.out.println();
		Arrays.asList(clazz.getMethods()).forEach(System.out::println);
		//Arrays.asList(clazz.getMethods()).forEach(d -> System.out.println(d));
		System.out.println();
		Arrays.asList(clazz.getDeclaredMethods()).forEach(System.out::println);
		
		System.out.println();
		var nameField = clazz.getField("name");
		System.out.println(nameField);
		
		// a specific field & method
		System.out.println();
		var calculateMethod = clazz.getDeclaredMethod("calculate");
		System.out.println(calculateMethod);
		var setUpdatedMethod = clazz.getMethod("setUpdated", Date.class, int.class);
		System.out.println(setUpdatedMethod);
		
		// whether a method exists 
		var methodExists = Arrays.stream(clazz.getDeclaredMethods()).anyMatch(m -> m.getName().equals("setUpdated"));
		System.out.println(methodExists);
		
		// caling methods
		var doSomethingMethod = clazz.getDeclaredMethod("doSomething", int.class);
		doSomethingMethod.invoke(new Employee(), 3);
		
		calculateMethod.setAccessible(true);
		calculateMethod.invoke(new Employee());
		
		// setting fields
		var user = new Employee();
		// nameField = clazz.getField("name");
		nameField.set(user, "Poseidon");
		System.out.println(user);
		
		var idField = clazz.getDeclaredField("id");
		idField.setAccessible(true);
		idField.set(user, 7);
		System.out.println(user);
		
	}

}
