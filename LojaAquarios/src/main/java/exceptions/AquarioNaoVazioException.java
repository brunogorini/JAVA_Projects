package exceptions;

public class AquarioNaoVazioException extends Exception {

	private static final long serialVersionUID = 1L;

	public AquarioNaoVazioException(String message) {
		super(message);
	}

}