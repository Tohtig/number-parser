package run;

import numberparser.ParserSelector;
import numberparser.factory.Parser;

public class RunnerTests {
	
// покрыть код приличными тестами
	public static void main(String[] args) {
		String number1 = "8";
		String number2 = "-20000000300000";
		String phrase = "минус восемь";
		Parser ipr = ParserSelector.getWanted(ParserSelector.DIGITS_TO_PHRASE);
		
		System.out.println(ipr.invert(number1));
		System.out.println(ipr.invert(number2));
		
		ipr = ParserSelector.getWanted(ParserSelector.PHRASE_TO_DIGITS);
		System.out.println(ipr.invert(phrase));
	}

}
