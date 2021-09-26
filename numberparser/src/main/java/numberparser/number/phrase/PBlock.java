package numberparser.number.phrase;

import java.util.Iterator;
import java.util.Stack;

import numberparser.number.exceptions.SequencePartsException;

public class PBlock {
	private Stack<String> mantissa = new Stack<>();
	private String exponenta = "";
	
	public PBlock(Stack<String> src) throws SequencePartsException {
		
		Iterator<String> iSrc = src.iterator();
//		проверка аномального блока начинающегося с экспоненты "миллион сто"
		String part = iSrc.next();
		iSrc.remove();
		if(PExponent.isIt(part))
			throw new SequencePartsException(part + " - incorrect "
					+ "position");
			
//		не теряем проверенную мантиссу.
		mantissa.push(part);
		while(iSrc.hasNext() & !PExponent.isIt(part)){
			part = iSrc.next();
			iSrc.remove();
			if(PMantissa.isIt(part)) 
				mantissa.push(part);
		};

		if(PExponent.isIt(part)) exponenta = part;
	}
	
	public Stack<String> getMantissaPhrase(){
		return mantissa;
	}
	
	public String getExpPowerName(){
		return exponenta;
	}
}
