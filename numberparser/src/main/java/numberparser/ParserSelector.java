package numberparser;

import numberparser.factory.DigitalParser;
import numberparser.factory.Parser;
import numberparser.factory.PhraseParser;

public class ParserSelector {
	
	public static final byte DIGITS_TO_PHRASE = 1;
	public static final byte PHRASE_TO_DIGITS = 2;
	
    public static Parser getWanted(int mode) {
    	
    	Parser parser;
        switch (mode) {
            case DIGITS_TO_PHRASE:
            	parser = new DigitalParser();
                break;
            case PHRASE_TO_DIGITS:
            	parser = new PhraseParser();
                break;
            default:
            	throw new RuntimeException("Parser mode not exists!");
        }
        return parser;
    }
}
