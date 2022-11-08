package app.model;

import java.util.ArrayList;

public class WordCard {
	private int id;
	private String word;
	private String transc;
	private String transl;
	private String example;
	private ArrayList<String> mp3urls;
	private int status;
	private int forward;
	private int backward;
	
	public WordCard(String word, String transc, String transl, String example, ArrayList<String> mp3urls) {
		this.id = 0;
		this.word = word;
		this.transc = transc;
		this.transl = transl;
		this.example = example;
		this.mp3urls = mp3urls;
		this.status = 0;
		this.forward = 0;
		this.backward = 0;
	}

	public WordCard(int id, String word, String transc, String transl, String example, String mp3urls,
			int status, int forward, int backward) {
		this.id = id;
		this.word = word;
		this.transc = transc;
		this.transl = transl;
		this.example = example;
		var array = mp3urls.split("\\+++");
		//urlList = (ArrayList<String>) Arrays.asList(array);
		this.mp3urls = new ArrayList<>();
		for(var url: array) {
			this.mp3urls.add(url);
		}
		this.status = status;
		this.forward = forward;
		this.backward = backward;
	}

	@Override
	public String toString() {
		return id + ": " + status + "/" + forward + "/" + backward + ": " + word + ", transc=" + transc + ", transl=" + transl + ", example="
				+ example + ", mp3urls=" + mp3urls;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getTransc() {
		return transc;
	}

	public void setTransc(String transc) {
		this.transc = transc;
	}

	public String getTransl() {
		return transl;
	}

	public void setTransl(String transl) {
		this.transl = transl;
	}

	public String getExample() {
		return example;
	}

	public void setExample(String example) {
		this.example = example;
	}

	public ArrayList<String> getMp3urls() {
		return mp3urls;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getForward() {
		return forward;
	}

	public void setForward(int forward) {
		this.forward = forward;
	}

	public int getBackward() {
		return backward;
	}

	public void setBackward(int backward) {
		this.backward = backward;
	}

	public String getMp3String() {
		var builder = new StringBuilder();
		for(int j = 0; j < mp3urls.size(); j++) {
			builder.append(mp3urls.get(j));
			if(j + 1 == mp3urls.size()) break;
			builder.append("+++");
		}
		return builder.toString();
	}

	public void setMp3urls(ArrayList<String> mp3urls) {
		this.mp3urls = mp3urls;
	}
	
	
	
	
}
