package exceptions;

public class PeixeNaoEncontradoException extends Exception {

	private static final long serialVersionUID = 1L;

	public PeixeNaoEncontradoException(String message) {
		super(message);
	}
}
