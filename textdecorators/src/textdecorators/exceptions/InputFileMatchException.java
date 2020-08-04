package textdecorators.exceptions;

public class InputFileMatchException extends Throwable {
	/**
	 * exception thrown if two input files have same name and path
	 * @author Krupa Sawant
	 */
	public InputFileMatchException(String s){
		super(s);


	}

}
