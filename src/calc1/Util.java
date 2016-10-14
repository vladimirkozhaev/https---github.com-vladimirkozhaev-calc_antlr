package calc1;

import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.widgets.Display;

public class Util {
	public static List<Integer> calcWithVisitor(String str) {
		LibExprLexer lexer = new LibExprLexer(new ANTLRInputStream(str));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		LibExprParser parser = new LibExprParser(tokens);
		parser.getInterpreter().setPredictionMode(PredictionMode.LL_EXACT_AMBIG_DETECTION);
		ParseTree tree = parser.prog();
		EvalVisitor eval = new EvalVisitor();
		eval.visit(tree);

		return eval.getValues();
	}

	public static StyleRange[] getStyleRanges(String text, Display display) throws TestException {
		LibExprLexer lexer = new LibExprLexer(new ANTLRInputStream(text));
		ErrorListener parserErrorListener = new ErrorListener();
		ErrorListener lexerErrorListener = new ErrorListener();
		lexer.addErrorListener(lexerErrorListener);

		// Get a list of matched tokens
		CommonTokenStream tokens = new CommonTokenStream(lexer);

		LibExprParser parser = new LibExprParser(tokens);
		parser.addErrorListener(parserErrorListener);

		ParseTreeWalker walker = new ParseTreeWalker();
		HilightListener listener = new HilightListener(display);
		walker.walk(listener, parser.prog());

		if ((lexerErrorListener.getErrorsStr().size() > 0) || parserErrorListener.getErrorsStr().size() > 0) {
			throw new TestException(lexerErrorListener.getErrorsStr(), parserErrorListener.getErrorsStr());
		}
		return listener.getStyleRanges();
	}

}
