package app;

class User {
	
}

class Employee extends User {
	
}

public class App {

	public static void main(String[] args) throws ClassNotFoundException {
		
		//var clazz = Employee.class;
		Class<Employee> clazz = Employee.class;
		System.out.println(clazz);
		
		//var clazz2 = Class.forName("app.Employee");
		Class<?> clazz2 = Class.forName("app.Employee");
		System.out.println(clazz2);
		
		User u = new Employee();
		//var clazz3 = u.getClass();
		Class<? extends User> clazz3 = u.getClass();
		System.out.println(clazz3);
		
	}

}
