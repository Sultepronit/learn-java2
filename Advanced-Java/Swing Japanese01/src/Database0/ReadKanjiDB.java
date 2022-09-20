package Database0;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ReadKanjiDB {
	private static File db = new File("kanjidb");
	//public static Word words1 = new
	public static ArrayList<Kanji> kanjis1 = new ArrayList<>();
	public static HashMap<Character, Integer> kanjiIndex = new HashMap<>();
	
	public ReadKanjiDB() {
		read();
	}
	
	//public static void main (String[] args) {
	private static void read() {
		//System.out.println("Helllllo!");
		int i = 0;
		try {
			Scanner scanner = new Scanner(db);
			
			while(scanner.hasNext()) {
				/*String line = scanner.nextLine();
				System.out.println(line);*/
				//char symbol = scanner.nextLine().charAt(0);
				String ssymbol = scanner.nextLine();
				char symbol = ssymbol.charAt(0);
				//System.out.print(ssymbol);
				String name = scanner.nextLine();
				String elements = scanner.nextLine();
				String readings = scanner.nextLine();
				Kanji kanji = new Kanji(symbol, name, elements, readings);
				//System.out.println(kanji);
				kanjis1.add(kanji);
				kanjiIndex.put(symbol, i++);
			}
			//System.out.println(kanjis1.get(0));
			//System.out.println(kanjiIndex);
			
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
