package numberparser.number.exceptions;

@SuppressWarnings("serial")
public class SequencePartsException extends Exception {
	private String msg;
	
	public SequencePartsException() {}
	
	public SequencePartsException(String s) { msg = s;}
	
	@Override
	public String toString() {
		return "Sequence Part Exception (" + msg + ")"; 
	}
}
