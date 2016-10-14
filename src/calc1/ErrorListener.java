package calc1;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;


public class ErrorListener implements ANTLRErrorListener {
	List<String> errors=new ArrayList<String>();
	

	/**
	 * This method is called by the parser when a full-context prediction
	 * results in an ambiguity.
	 */
	@Override
	public void reportAmbiguity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, boolean exact,
			BitSet ambigAlts, ATNConfigSet configs) {
		// TODO Auto-generated method stub

	}

	/**
	 * This method is called when an SLL conflict occurs and the parser is about
	 * to use the full context information to make an LL decision.
	 */
	@Override
	public void reportAttemptingFullContext(Parser recognizer, DFA dfa, int startIndex, int stopIndex,
			BitSet conflictingAlts, ATNConfigSet configs) {

	}

	/**
	 * This method is called by the parser when a full-context prediction has a
	 * unique result.
	 */
	@Override
	public void reportContextSensitivity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, int prediction,
			ATNConfigSet configs) {
		// TODO Auto-generated method stub

	}

	/**
	 * Upon syntax error, notify any interested parties. This is not how to
	 * recover from errors or compute error messages. ANTLRErrorStrategy
	 * specifies how to recover from syntax errors and how to compute error
	 * messages. This listener's job is simply to emit a computed message,
	 * though it has enough information to create its own message in many cases.
	 */
	@Override
	public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
			String msg, RecognitionException e) {
		errors.add(msg+" line:"+line+", char pos in line:"+charPositionInLine);

	}

	public List<String> getErrorsStr() {
		// TODO Auto-generated method stub
		return errors;
	}

}
