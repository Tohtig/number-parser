package numberparser.number.phrase;

import java.util.Properties;

import numberparser.number.invertmaps.InvertMapSelector;
import numberparser.number.invertmaps.InvertMapType;

public class PExponent {
	private static Properties expIMap; // mantissa invert map
	private int power;
	
	static{
		expIMap = InvertMapSelector.getInstance()
				.getInvertMap(InvertMapType.PHRASE_EXPONENTA);
	}
	
	public static boolean isIt(String numberPart) {
		return expIMap.containsKey(numberPart);
	}

	public PExponent(PBlock block) {
		if(!block.getExpPowerName().isEmpty()){
			power = Integer.parseInt(expIMap.getProperty(block.getExpPowerName()));
		} else{
			power = 0;
		}
	}
	
	public int getPower(){
		return power;
	}
	
	public char[] toCharArray(){
		char[] view = new char[power];
		for(int i = 0; i < view.length; i++)
			view[i] = '0';
		return view;
	}
	
}
