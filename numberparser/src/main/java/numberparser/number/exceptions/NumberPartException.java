package numberparser.number.exceptions;

@SuppressWarnings("serial")
public class NumberPartException extends Exception {
	private String msg; 

	public NumberPartException(){ msg = null;} 

	public NumberPartException(String s){ msg = s;} 

	public String toString(){
		return "Chiper Exception (" + msg + ")"; 
	}
}
