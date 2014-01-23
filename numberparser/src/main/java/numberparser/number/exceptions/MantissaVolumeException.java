package numberparser.number.exceptions;

@SuppressWarnings("serial")
public class MantissaVolumeException extends Exception {

	private String msg;
	
	public MantissaVolumeException(){ msg = null;} 

	public MantissaVolumeException(String s){ msg = s;} 

	public String toString(){
		return "Mantissa Volume Exception (" + msg + ")"; 
	}
}
