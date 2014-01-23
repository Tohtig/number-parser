package numberparser.number.digits;

import java.util.Iterator;
import java.util.Stack;

public class DBlock {
	private static final byte NUMBER_BLOCK_VOLUME = 3;
	private int viewMnt;
	private int expPower;

	public DBlock(Stack<Character> src){
		
		if (src.size() <= NUMBER_BLOCK_VOLUME){
			create((byte) src.size(), src.iterator()); 
		} else if (src.size() % NUMBER_BLOCK_VOLUME == 0){
			create(NUMBER_BLOCK_VOLUME, src.iterator());
		} else{
			int size_mantissa = src.size() % NUMBER_BLOCK_VOLUME;
			create((byte) size_mantissa, src.iterator());
		}
		expPower = src.size();
	}
	
	public int getMantissaView() {
		
		return viewMnt;
	}

	public int getExponentaPower() {
		
		return expPower;
	}

	public void create(byte size, Iterator<Character> iSrc){
		
		char[] block = new char[size];
		int i = 0;
		while(i < block.length){
			block[i++] = iSrc.next();
			iSrc.remove();
		}
		viewMnt = Integer.parseInt(new String(block));
	}
}
