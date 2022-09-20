import java.util.HashMap;

public class TrainRoman {

	public static void main(String[] args) {
		
		String rom = "";
		rom = toRoman(2);
		System.out.println(rom);
		System.out.println(fromRoman(rom));
		rom = toRoman(8);
		System.out.println(rom);
		System.out.println(fromRoman(rom));
		rom = toRoman(18);
		System.out.println(rom);
		System.out.println(fromRoman(rom));
		rom = toRoman(22);
		System.out.println(rom);
		System.out.println(fromRoman(rom));
		rom = toRoman(14);
		System.out.println(rom);
		System.out.println(fromRoman(rom));
		rom = toRoman(19);
		System.out.println(rom);
		System.out.println(fromRoman(rom));
		rom = toRoman(52);
		System.out.println(rom);
		System.out.println(fromRoman(rom));
		rom = toRoman(1000);
		System.out.println(rom);
		System.out.println(fromRoman(rom));
		rom = toRoman(1990);
		System.out.println(rom);
		System.out.println(fromRoman(rom));
		/*
		System.out.println(toRoman(52));
		System.out.println(toRoman(1000));
		System.out.println(toRoman(1666));
		System.out.println(toRoman(2008));
		System.out.println(toRoman(19));
		System.out.println(toRoman(1990));*/

	}
	
	private static String[] rome = {"I", "V", "X", "L", "C", "D", "M"};
	//private static char[] rome = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};
	private static int[] arab = {1, 5, 10, 50, 100, 500, 1000}; 
	
	public static String toRoman(int n) {
		System.out.println(n);
	    var re = new StringBuilder();
	    
	    int remainder = 0;
	    for(int i = 6; i >= 0; i--) {
		    remainder = n / arab[i];
		    if((i > 0) && (n / arab[i - 1] == 9)) {
		    	if((i - 1) % 2 == 0) {
		    		re.append(String.format(rome[i - 1] + rome[i + 1]));
		    		continue;
		    	}
		    }
		    n -= (remainder * arab[i]);
		    switch (remainder) {
		    	case 1:
		    		re.append(rome[i]);
		    		break;	
		    	case 2:
		    		re.append(String.format(rome[i] + rome[i]));
		    		break;
		    	case 3:
		    		re.append(String.format(rome[i] + rome[i] + rome[i]));
		    		break;
		    	case 4:
		    		re.append(String.format(rome[i] + rome[i + 1]));
		    		break;
		    }
	    }
	    
	    return re.toString();
	}
	
	public static int fromRoman(String r) {
	    var map = new HashMap<Character, Integer>();
	    for(int i = 0; i < rome.length; i++) {
	    	map.put(rome[i].charAt(0), arab[i]);
	    }
	    System.out.println(map);
	    int re = 0;
	    for(int i = 0; i < r.length(); i++) {
	    	int n = map.get(r.charAt(i));
	    	if(i + 1 < r.length() && n < map.get(r.charAt(i + 1))) {
	    		re -= n;
	    		continue;
	    	}
	    	re += n;
	    }
		return re;
	}

}

