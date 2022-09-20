package Database0;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFile {
	private static File db = new File("db.txt");
	//public static Word words1 = new
	public static ArrayList<Word> words1 = new ArrayList<>();
	
	public ReadFile() {
		read();
	}
	
	//public static void main (String[] args) {
	private static void read() {
		//System.out.println("Helllllo!");
		try {
			Scanner scanner = new Scanner(db);
			
			while(scanner.hasNext()) {
				/*String line = scanner.nextLine();
				System.out.println(line);*/
				boolean isUser = scanner.nextBoolean();
				//System.out.println(isUser);
				scanner.nextLine();
				String mainWritings = scanner.nextLine();
				//System.out.println(mainWritings);
				String additionalWritings = scanner.nextLine();
				String mainTranscriptions = scanner.nextLine();
				String additionalTranscriptions = scanner.nextLine();
				String translation = scanner.nextLine();
				//System.out.println(translation);
				int example = scanner.nextInt();
				scanner.nextLine();
				Word word = new Word(mainWritings, isUser, additionalWritings, mainTranscriptions, additionalTranscriptions, translation, example);
				//System.out.println(word);
				words1.add(word);
			}
			//System.out.println(words1.get(333));
			
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*public static void kanjiToWords(char kanji) {
		System.out.println(kanji);
		for(var word: words1) {
			if(word.getAllKanji().contains(kanji)) {
				System.out.println(word);
			}
		}
	}*/
}
