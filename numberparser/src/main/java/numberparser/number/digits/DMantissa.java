package numberparser.number.digits;

import java.util.Properties;

import numberparser.number.invertmaps.InvertMapSelector;
import numberparser.number.invertmaps.InvertMapType;

public class DMantissa {
	private final char SPACE = ' ';
	private Properties mntIMap; // mantissa invert map
	private Properties mntExIMap; // mantissa exception invert map
	private DBlock src;
	private StringBuilder result = new StringBuilder();
	
	protected DMantissa(DBlock number){
		mntIMap = InvertMapSelector.getInstance()
				.getInvertMap(InvertMapType.DIGITAL_MANTISSA);
		mntExIMap = InvertMapSelector.getInstance()
				.getInvertMap(InvertMapType.DIGITAL_MANTISSA_EXCEPTIONS);
		src = number; 
		parseMantissa(number.getMantissaView());
	}
	
	private String parseMantissa(int mantissa) {
//		метод разбора "в лоб". Для числового блока "000" достаточно.
		while(mantissa > 0){
			if(mantissa >= HUNDRED){
				int quantityHdr = (int) Math.floor(mantissa / HUNDRED);
				result.append(SPACE + mntIMap.getProperty(String.valueOf(
						quantityHdr * HUNDRED)));
				mantissa -= quantityHdr * HUNDRED;
			} else if(mantissa >= TWENTY){
				int quantityTen = (int) Math.floor(mantissa / TEN);
				result.append(SPACE + mntIMap.getProperty(String.valueOf(
						quantityTen * TEN)));
				mantissa -= quantityTen * TEN;
			} else{
				if (isException(mantissa, src.getExponentaPower())){
					result.append(SPACE + mntExIMap.getProperty(String.valueOf(
							mantissa)));
				} else{
					result.append(SPACE + mntIMap.getProperty(String.valueOf(
							mantissa)));
				}
				mantissa -= mantissa;
			}
		}
		return result.toString();
	}
	
	private boolean isException(int mntEnd, int expPower){
		return ((mntEnd <= THOUSAND_EXEPTION_BOUND) 
				& (expPower == EXP_DEGREE_THOUSAND));
	}
	
	@Override
	public String toString() {
		return result.toString();
	}
	
	private static final byte HUNDRED = 100;
	private static final byte TWENTY = 20;
	private static final byte TEN = 10;
	private static final byte EXP_DEGREE_THOUSAND = 3;
	private static final byte THOUSAND_EXEPTION_BOUND = 2;
}
