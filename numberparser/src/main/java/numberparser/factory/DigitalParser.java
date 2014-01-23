package numberparser.factory;

import numberparser.number.digits.DNumber;
import numberparser.number.exceptions.NumberPartException;

public class DigitalParser implements Parser {
	
	private DNumber digitalNum;

	public String invert(String number) {
		
		try {
			digitalNum = new DNumber(number);
		} catch (NumberPartException e) {
			e.printStackTrace();
		}
		return digitalNum.toString();
	}

}
