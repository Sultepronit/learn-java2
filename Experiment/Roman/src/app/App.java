package app;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class App {

	public static void main(String[] args) {
		//romanToInt("III");
		romanToInt("LVIII");
		romanToInt("MCMXCIV");
		romanToInt("XIX");
	}
	
	private static Map romanMap = new HashMap<Character, Integer>() {{
		 put('I', 1);
		 put('V', 5);
		 put('X', 10);
	 }};
	
	public static int romanToInt(String s) {
		 System.out.println(s);
     
		 //var map = new LinkedHashMap<Character, Integer>() {{
		 //Map map = new LinkedHashMap<Character, Integer>() {{
		 var map = new HashMap<Character, Integer>() {{
			 put('I', 1);
			 put('V', 5);
			 put('X', 10);
			 put('L', 50);
			 put('C', 100);
			 put('D', 500);
			 put('M', 1000);
		 }};
		 //System.out.println(map);
		 
		 /*for(var entry: map.entrySet()) {
			 var key = entry.getKey();
			 System.out.println(key);
			 if(s.charAt(0) == key) {
				 System.out.println(entry.getValue());
			 }
		 }*/
		 
		 int re = 0;
		 int lastBiggest = 0;
		 
		 for(int i = s.length() - 1; i >= 0; i--) {
			 //System.out.println(s.charAt(i) + "/" + map.get(s.charAt(i)));
			 int n = map.get(s.charAt(i));
			 //System.out.println(n + " : " + lastBiggest);
			 if(lastBiggest < n) lastBiggest = n;

			 re += (lastBiggest == n) ? n : -n;
			 //System.out.println(re);
			 //re += n;
		 }
		 System.out.println(re);
        
		 return re;
	 }

}
