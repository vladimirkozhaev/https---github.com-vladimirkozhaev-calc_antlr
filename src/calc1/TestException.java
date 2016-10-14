package calc1;

import java.util.List;

public class TestException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<String> lexerException;
	List<String> parserException;
	public TestException(List<String> lexerException, List<String> parserException) {
		super();
		this.lexerException = lexerException;
		this.parserException = parserException;
	}
	public List<String> getLexerException() {
		return lexerException;
	}
	public List<String> getParserException() {
		return parserException;
	}
	
	
	
}
