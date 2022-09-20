import java.util.HashMap;
import java.util.Map;

public class Train1 {

	public static void main(String[] args) {
		String s = "aabb";
		Map<Character, Integer> map = decomposition(s);
		System.out.println(map);
		int size = map.size();
		char[] cc = new char[size];
		int index = 0;
		for(char c: map.keySet()) cc[index++] = c;
		//System.out.println(cc[0]);
		/*for(int i = 0; i < size; i++) {
			cc[i] = map.next().
		}*/
		for(int i = 0; i < size; i++) {
			int[] calc = new int[size];
			calc[i]++;
			System.out.println(calc[i]);
			System.out.println(cc[i]);
			for(int j = 1; j < s.length(); j++) {
				for(int k = 0; k < size; k++) {
					if(calc[k] < map.get(cc[k])) {
						System.out.println(cc[k]);
						calc[k]++;
					}
				}
			}
		}
	}
	
	private static Map<Character, Integer> decomposition(String s) {
		var map = new HashMap<Character, Integer>();
		for(char c: s.toCharArray()) {
			Integer i = map.get(c);
			if(i == null) map.put(c, 1);
			else map.put(c, ++i);
		}
		return map;
	}

}
