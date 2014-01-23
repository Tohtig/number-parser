package numberparser.factory;

import numberparser.number.exceptions.MantissaVolumeException;
import numberparser.number.exceptions.NumberPartException;
import numberparser.number.exceptions.SequencePartsException;
import numberparser.number.phrase.PNumber;

public class PhraseParser implements Parser {
	
	private PNumber phraseNum;
	
	public String invert(String phrase) {
		
		try {
			phraseNum = new PNumber(phrase);
		} catch (NumberPartException | SequencePartsException
				| MantissaVolumeException e) {
			e.printStackTrace();
		}
		return phraseNum.toString();
	}
}
