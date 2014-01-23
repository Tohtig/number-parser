package numberparser.number.digits;

import java.util.Properties;

import numberparser.number.invertmaps.InvertMapSelector;
import numberparser.number.invertmaps.InvertMapType;

public class DExponent {
	private Properties expIMap;
	private DBlock src;
	private String result;
	
	public DExponent(DBlock number) {
		if ((number.getMantissaView() == 0)
				|| (number.getExponentaPower() == ZERO)){
			result = "";
		}else {
			expIMap = InvertMapSelector.getInstance()
					.getInvertMap(InvertMapType.DIGITAL_EXPONENTA);
			src = number;
			result = parseExponenta();
		}
	}
	
	private String parseExponenta(){
		StringBuilder keyE = new StringBuilder();
		keyE.append(EXPONENTA).append(src.getExponentaPower());
		byte mantissaFactor;
		
		if(src.getMantissaView() > TWENTY){
			mantissaFactor = (byte) (src.getMantissaView() % TWENTY);
		} else { 
			mantissaFactor = (byte) src.getMantissaView();
		}
		
		// build end part keyE вынести в метод грамматических правил
		if(mantissaFactor == END_V1_CONTRACT){
			keyE.append(END_V1);
		} else if((END_V2_CONTRACT_BEGIN <= mantissaFactor) & 
				(mantissaFactor <= END_V2_CONTRACT_END)){
			keyE.append(END_V2);
		} else if((mantissaFactor >= END_V3_CONTRACT_BEGIN) | (mantissaFactor == ZERO)){
			keyE.append(END_V3);
		}
		return loadExponent(keyE.toString());
//		return ' ' + expIMap.getProperty(keyE.toString());
	}

	private String loadExponent(String key) {
		String exp = expIMap.getProperty(key);
		if (exp == null){
			throw new RuntimeException("Number with exponenta power " + 
					src.getExponentaPower() + " this app not support. "
					+ "Sorry." );
		} else{
			return ' ' + exp;
		}
	}

	@Override
	public String toString() {
		return result;
	}
	
	private static final String EXPONENTA = "e";
	private static final byte TWENTY = 20;
	private static final byte ZERO = 0;

	// Грамматические правила подбора вариантов окончаний из словаря.
	// Может нужно обобщить в отдельном классе? Для возм-сти интернационализации.
	/** если последняя цифра мантиссы = 1, экспонента не имеет окончания*/
	private static final String END_V1 = "v1";
	private static final byte END_V1_CONTRACT = 1;
	/**если окончание мантиссы в диапазоне [2,4], то экспонента имеет 
	 * окончание 'a'*/
	private static final String END_V2 = "v2";
	private static final byte END_V2_CONTRACT_BEGIN = 2;
	private static final byte END_V2_CONTRACT_END = 4;
	/**если окончание мантиссы в диапазоне [5,0], то экспонента имеет
	 * окончание 'ов'*/
	private static final String END_V3 = "v3";
	private static final byte END_V3_CONTRACT_BEGIN = 5;
}
