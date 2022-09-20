package Database0;

public class Kanji {
	private char symbol;
	private String name;
	private String elements;
	private String readings;
	
	public Kanji(char symbol, String name, String elements, String readings) {
		this.symbol = symbol;
		this.name = name;
		this.elements = elements;
		this.readings = readings;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Kanji [symbol=" + symbol + ", name=" + name + ", elements=" + elements + ", readings=" + readings + "]";
	}
	
}
