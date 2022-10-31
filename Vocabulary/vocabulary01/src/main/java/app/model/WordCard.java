package app.model;

import java.util.ArrayList;

public class WordCard {
	private int id;
	private String word;
	private String transc;
	private String transl;
	private String example;
	private ArrayList<String> mp3urls;
	
	public WordCard(String word, String transc, String transl, String example, ArrayList<String> mp3urls) {
		this.id = 0;
		this.word = word;
		this.transc = transc;
		this.transl = transl;
		this.example = example;
		this.mp3urls = mp3urls;
	}

	@Override
	public String toString() {
		return "WordCard [id=" + id + ", word=" + word + ", transc=" + transc + ", transl=" + transl + ", example="
				+ example + ", mp3urls=" + mp3urls + "]";
	}
	
	
	
	
}
