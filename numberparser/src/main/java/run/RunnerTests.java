package run;

import numberparser.ParserSelector;
import numberparser.factory.Parser;

public class RunnerTests {
	
	public static void main(String[] args) {
		String number1 = "8";
		String number2 = "102000";
		String phrase = "минус восемь октиллионов две тысячи одиннадцать";
		Parser parser = ParserSelector.getWanted(ParserSelector.DIGITS_TO_PHRASE);

		System.out.println(parser.invert(number1));
		System.out.println(parser.invert(number2));

		parser = ParserSelector.getWanted(ParserSelector.PHRASE_TO_DIGITS);
		System.out.println(parser.invert(phrase));
	}

}
