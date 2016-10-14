package calc1;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.Interval;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.widgets.Display;

import calc1.LibExprParser.AssignContext;
import calc1.LibExprParser.IdContext;

public class HilightListener extends LibExprBaseListener {
	public HilightListener(Display display) {
		super();
		this.display = display;
	}

	List<StyleRange> styleRanges = new ArrayList<StyleRange>();

	public StyleRange[] getStyleRanges() {

		StyleRange[] ranges = new StyleRange[styleRanges.size()];
		for (int i = 0; i < ranges.length; i++) {
			ranges[i] = styleRanges.get(i);
		}
		return ranges;
	}

	Display display;

	@Override
	public void exitId(IdContext ctx) {
		StyleRange styleRange = new StyleRange();
		styleRange.start = ctx.getStart().getStartIndex();
		styleRange.length = getFullText(ctx).length();
		styleRange.foreground = display.getSystemColor(SWT.COLOR_RED);

		styleRanges.add(styleRange);
	}

	@Override
	public void exitAssign(AssignContext ctx) {
		StyleRange styleRange = new StyleRange();
		styleRange.start = ctx.getStart().getStartIndex();
		styleRange.length = getFullText(ctx).length();
		styleRange.foreground = display.getSystemColor(SWT.COLOR_DARK_RED);

		styleRanges.add(styleRange);
	}

	private String getFullText(ParserRuleContext pCtx) {
		
		return pCtx.getStart().getInputStream()
				.getText(Interval.of(pCtx.getStart().getStartIndex(), pCtx.getStop().getStopIndex()));
	}

}
