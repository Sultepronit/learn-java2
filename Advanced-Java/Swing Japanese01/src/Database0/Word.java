package Database0;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Random;

import com.mariten.kanatools.KanaConverter;

import application.PlayMP3;

public class Word {
	private int id;
	
	private String[] mainWritings;
	private boolean isUsed;
	private String[] additionalWritings;
	private String[] mainTranscriptions;
	private HashSet<Integer> isMuted = new HashSet<>();
	private String[] additionalTranscriptions;
	private String translation;
	private int example;
	
	//public boolean testThis = false; 
	private static Random random = new Random();
	
	public Word(String mw, boolean iu, String aw, String mt, String at, String tr, int ex) {
		mainWritings = mw.split(", ");
		isUsed = iu;
		additionalWritings = aw.split(", ");
		if(additionalWritings[0] == "") additionalWritings = new String[0];
		mainTranscriptions = mt.split(", ");
		for(int i = 0; i < mainTranscriptions.length; i++) {
			if(mainTranscriptions[i].charAt(0) == 'm') {
				mainTranscriptions[i] = mainTranscriptions[i].substring(1);
				isMuted.add(i);
				//testThis = true;
			}
			//else testThis = false;
		}
		additionalTranscriptions = at.split(", ");
		if(additionalTranscriptions[0] == "") additionalTranscriptions = new String[0];
		translation = tr;
		example = ex;
	}
	
	public String replaceBrackets(String word) {
		if(word.charAt(0) == '<') {
			//word = "#" + word.substring(1);
			word = word.substring(1);
			word = word.replaceAll("\\(", "<span style='color:yellow'>");
			word = word.replaceAll("\\[", "<span style='color:blue'>");
			word = word.replaceAll("\\{", "<span style='color:green'>");
			word = word.replaceAll("[\\)\\}\\]]", "</span>");
		}
		return word;
	}
	
	public String getWritings() {
		StringBuilder result = new StringBuilder();
		for(int i = 0; i < mainWritings.length; i++) {
			//result.append(mainWritings[i]);
			result.append(replaceBrackets(mainWritings[i]));
			if(i + 1 == mainWritings.length) break;
			result.append("　");
		}
		if(!isUsed) {
			result.insert(0, "<span style='color:blue'>");
			result.append("</span>");
		}
		if(additionalWritings.length > 0) {
			result.append("　<span style='color:gray'>");
			for(int i = 0; i < additionalWritings.length; i++) {
				result.append(additionalWritings[i]);
				if(i + 1 == additionalWritings.length) break;
				result.append("　");
			}
			result.append("</span>");
		}
		return result.toString();
	}
	
	public String deleteBrackets(String word) {
		if(word.charAt(0) == '<') {
			//word = word.replaceAll("[{}<\\[\\]]", "");
			word = word.replaceAll("[<\\[\\]]", "");
			word = word.replaceAll("\\(.*?\\)", "");
			if(random.nextInt(2) == 1) word = word.replaceAll("[{}]", "");
			else word = word.replaceAll("\\{.*?\\}", "");
		}
		return word;
	}
	
	public String getOneWriting() {
		Random random = new Random();
		int r = random.nextInt(mainWritings.length);
		return deleteBrackets(mainWritings[r]);
	}
	
	public LinkedHashSet<Character> getAllKanji() {
		var builder = new StringBuilder();
		for(int i = 0; i < mainWritings.length; i++) {
			builder.append(mainWritings[i]);
		}
		for(int i = 0; i < additionalWritings.length; i++) {
			builder.append(additionalWritings[i]);
		}
		var string =  builder.toString();
		var set = new LinkedHashSet<Character>();
		for(char c: string.toCharArray()) {
			if(c > 'ー') set.add(c);
			if(c > 'ー') {
				//System.out.println(c);
				/*var index = ReadKanjiDB.kanjiIndex.get(c);
				System.out.println(index);
				System.out.println(ReadKanjiDB.kanjis1.get(index).getName());*/
			}
		}
		return set;
	}
	
	public String getTranscriptions() {
		var result = new StringBuilder();
		for(int i = 0; i < mainTranscriptions.length; i++) {
			result.append(mainTranscriptions[i]);
			if(i + 1 == mainTranscriptions.length) break;
			result.append("　");
		}
		if(additionalTranscriptions.length > 0) {
			result.append("　(");
			for(int i = 0; i < additionalTranscriptions.length; i++) {
				result.append(additionalTranscriptions[i]);
				if(i + 1 == additionalTranscriptions.length) break;
				result.append("/");
			}
			result.append(")");
		}
		int conv_op_flags = KanaConverter.OP_ZEN_HIRA_TO_ZEN_KATA;
		if(random.nextInt(2) == 1) {
			return KanaConverter.convertKana(result.toString(), conv_op_flags);
		}
		return result.toString();
	}
	
	public String getTranslation() {
		return translation;
	}
	
	public String toTextLine() {
		var writings = getWritings();
		var transcriptions = getTranscriptions();
		return writings + " : " + transcriptions + " — " + translation;
	}
	
	public String getExample() {
		if(example > 0) return Examples.list[example];
		return null;
	}
	
	public void say() {
		int length = mainTranscriptions.length - isMuted.size();
		String[] list = new String[length];
		for(int i = 0, j = 0; i < mainTranscriptions.length; i++) {
			if(isMuted.contains(i)) continue;
			list[j++] = "https://assets.languagepod101.com/dictionary/japanese/audiomp3.php?kana="
					+ mainTranscriptions[i] + "&kanji=" + deleteBrackets(mainWritings[0]);
			//System.out.println(deleteBrackets(mainWritings[0]));
		}
		PlayMP3.play(list);
	}

	@Override
	public String toString() {
		return "{" + id + "\n" + Arrays.toString(mainWritings) + "\n" + isUsed
				+ "\n" + Arrays.toString(additionalWritings) + "\n"
				+ Arrays.toString(mainTranscriptions) + "\n"
				+ Arrays.toString(additionalTranscriptions) + "\n" + translation + "\n" + example
				+ "}";
	}
	
}
