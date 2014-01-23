package numberparser.number.digits;

import java.util.Iterator;
import java.util.Stack;

import numberparser.number.exceptions.NumberPartException;

public class DNumber{
	
	private boolean positive = true;
	private String view;
	
	public DNumber(String number) throws NumberPartException{
		
		Stack<Character> src = createNumberStack(number.toCharArray());
		view = invert(src);
	}
	
	public DNumber(long number){
		
		Stack<Character> src = createNumberStack(number);
		view = invert(src);
	}
	
	public DNumber(char[] number) throws NumberPartException{
		
		Stack<Character> src = createNumberStack(number);
		view = invert(src);
	}
	
	private Stack<Character> createNumberStack(long number){
		
		Stack<Character> src = new Stack<>();
		String numString;
//		if is Below ZERO cut sign from follow analyze
		if (Long.signum(number) == -1){
			numString = String.valueOf((-1) * number);
			for(int i = 0; i < numString.length(); i++)
				src.add(numString.charAt(i));
			positive = false;
		}else{
			numString = Long.toString(number);
			for(int i = 0; i < numString.length(); i++)
				src.add(numString.charAt(i));
		}
		return src;
	}
	
	private Stack<Character> createNumberStack(char[] number) throws NumberPartException{
		
		Stack<Character> src = new Stack<>();
//		if is Below ZERO cut sign from follow analyze
		if(number[0] == SIGN){
			for(int i = 1; i < number.length; i++)
				if (Character.isDigit(number[i])){
					src.add(number[i]);
				} else {
					throw new NumberPartException("Anomal input data. " + number[i]
							+ " isn't chiper.");
				}
			positive = false;
		} else {
			for(int i = 0; i < number.length; i++)
				if (Character.isDigit(number[i])){
					src.add(number[i]);
				} else {
					throw new NumberPartException("Anomal input data. " + number[i]
							+ " isn't chiper.");
				}
		}
		return src;
	}
	
	private String invert(Stack<Character> src){
		
		Iterator<Character> iSrc = src.iterator();
		StringBuilder result = new StringBuilder();
		DMantissa mantissa;
		DExponent exponenta;
		DBlock tgrBlock; // target block
		do {
			tgrBlock = new DBlock(src);
			mantissa = new DMantissa(tgrBlock);
			exponenta = new DExponent(tgrBlock);
			result.append(mantissa.toString() + exponenta.toString());
		} while(iSrc.hasNext());
//		удаляет ведущий пробел
		result.deleteCharAt(0);
		if (!positive)
			result.insert(0, MINUS);
		return result.toString();
	}
	
 	@Override
 	public String toString() {
 		return view;
 	}
 	
 	private final String MINUS = "минус ";
 	private final char SIGN = '-';
}
