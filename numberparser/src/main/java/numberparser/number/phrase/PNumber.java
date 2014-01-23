package numberparser.number.phrase;

import java.util.Iterator;
import java.util.Stack;
import java.util.StringTokenizer;

import numberparser.number.exceptions.MantissaVolumeException;
import numberparser.number.exceptions.NumberPartException;
import numberparser.number.exceptions.SequencePartsException;

public class PNumber{
	private String view;
	private boolean positive = true;
	
	public PNumber(String number)
			throws NumberPartException, 
				SequencePartsException, 
				MantissaVolumeException{
		
		Stack<String> src = createNumberStack(number);
		if (this.positive){
			view = new String(invert(src));
		} else {
			view = SIGN + new String(invert(src));
		}
	}

	private char[] invert(Stack<String> src) 
			throws SequencePartsException, MantissaVolumeException {

		Iterator<String> iSrc = src.iterator();
//		инициализация
		PBlock tgrBlock = new PBlock(src); // target block
		PMantissa mantissa = new PMantissa(tgrBlock);
		PExponent exponent = new PExponent(tgrBlock); 
//		формирование первичного числа конкатенацией мантиссы и экспоненты
		char[] mntArray = mantissa.toCharArray(); // mantissa array
		char[] expArray = exponent.toCharArray(); // exponent array
		char[] number = new char[mntArray.length + expArray.length];
		numberAppender(number, mntArray, expArray);

//		Дальнейшее формирование и редактирование числа 		
		while(iSrc.hasNext()){
//			почему не передает по ссылке?! Придется вручную.
//			createPart(src, tgrBlock, mantissa, exponent); 
			tgrBlock = new PBlock(src); // target block
			mantissa = new PMantissa(tgrBlock);
			exponent = new PExponent(tgrBlock); 
			mntArray = mantissa.toCharArray();
			expArray = exponent.toCharArray();
			numberAppender(number, mntArray, expArray);
		}
		return number;
	}

/** Доводит число. Например 31000000 + (mnt~12 и exp^3) -> 31012000*/
	private void numberAppender(char[] number, char[] mntArray, 
			char[] expArray) {
		
		int index = number.length - (mntArray.length + expArray.length);
		for(int i = 0; i < mntArray.length; i++, index++)
			number[index] = mntArray[i];
		for(int i = 0; i < expArray.length; i++, index++)
			number[index] = expArray[i];
	}

	private Stack<String> createNumberStack(String number) throws NumberPartException{
		
		Stack<String> numberPhrase = new Stack<>();
		StringTokenizer st = new StringTokenizer(number);
		String firstElement = st.nextToken();
		if (firstElement.equals(MINUS)){
			positive = false;
		} else {
			numberPhrase.push(firstElement);
		}
		
		while(st.hasMoreTokens()){
			String numberPart = st.nextToken();
			if ((PMantissa.isIt(numberPart)) || PExponent.isIt(numberPart)){
				numberPhrase.push(numberPart);
			} else {
				throw new NumberPartException("Anomal input data. "+ numberPart
						+ "-isn't chiper.");
			}
		}
		
		return numberPhrase;
	}
	
	@Override
	public String toString() {
		return view;
	}

	private static String MINUS = "минус";
	private static char SIGN = '-';
}
