package numberparser.number.phrase;

import java.util.Iterator;
import java.util.Properties;
import java.util.Stack;

import numberparser.number.exceptions.MantissaVolumeException;
import numberparser.number.exceptions.SequencePartsException;
import numberparser.number.invertmaps.InvertMapSelector;
import numberparser.number.invertmaps.InvertMapType;

public class PMantissa {
	private static final int MAX_VOLUME = 3;
	private static Properties mntIMap; // mantissa invert map
	private int mnt = 0;
	
	static{
		
		mntIMap = InvertMapSelector.getInstance()
				.getInvertMap(InvertMapType.PHRASE_MANTISSA);
	}
	
	public static boolean isIt(String numberPart) {
		
		return mntIMap.containsKey(numberPart);
	}

	public PMantissa(PBlock block) 
			throws MantissaVolumeException, SequencePartsException {
		
//		Проверка величины мантиссы "сто семьдесят шесть пять". 
//		Проверка случая "сто семь шесть" пока не предусмотрена! 
//		TODO Обобщить в одну.
		if(block.getMantissaPhrase().size() > PMantissa.MAX_VOLUME){
			throw new MantissaVolumeException("Unreal mantissa size - " + 
					block.getMantissaPhrase().size());
		}
		
		Stack<String> mntSrc = block.getMantissaPhrase();
		Iterator<String> iMntSrc = mntSrc.iterator();
		mnt = Integer.parseInt(mntIMap.getProperty(iMntSrc.next()));
		int part = 0;
		while(iMntSrc.hasNext()){
			part = Integer.parseInt(mntIMap.getProperty(iMntSrc.next()));
//			проверка случая "десять сто"
			if(part < mnt){
				mnt += part;
			} else {
				throw new SequencePartsException("Sequence mistake into source"
						+ " - " + mnt + " " + part);
			}
		}
	}
	
	public char[] toCharArray(){
		return String.valueOf(mnt).toCharArray();
	}
	
	@Override
	public String toString() {
		return String.valueOf(mnt);
	}
}
