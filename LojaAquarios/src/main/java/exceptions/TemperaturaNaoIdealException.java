package exceptions;

public class TemperaturaNaoIdealException extends Exception {

	private static final long serialVersionUID = 1L;

	public TemperaturaNaoIdealException(String message) {
		super(message);
	}
}