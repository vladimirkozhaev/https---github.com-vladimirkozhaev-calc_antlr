package calc1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import calc1.LibExprParser.AddSubContext;
import calc1.LibExprParser.AssignContext;
import calc1.LibExprParser.IdContext;
import calc1.LibExprParser.IntContext;
import calc1.LibExprParser.MulDivContext;
import calc1.LibExprParser.ParensContext;
import calc1.LibExprParser.PowContext;
import calc1.LibExprParser.PrintExprContext;

public class EvalVisitor extends LibExprBaseVisitor<Integer> {
	/** "memory" for our calculator; variable/value pairs go here */
	Map<String, Integer> memory = new HashMap<String, Integer>();

	List<Integer> values = new ArrayList<Integer>();

	public List<Integer> getValues() {
		return values;
	}

	@Override
	public Integer visitParens(ParensContext ctx) {
		return visit(ctx.expr()); // return child expr's value
	}

	@Override
	public Integer visitMulDiv(MulDivContext ctx) {
		int left = visit(ctx.expr(0)); // get value of left subexpression
		int right = visit(ctx.expr(1)); // get value of right subexpression
		if (ctx.op.getType() == LibExprParser.MUL)
			return left * right;
		return left / right; // must be DIV
	}

	
	
	@Override
	public Integer visitPow(PowContext ctx) {
		int left = visit(ctx.expr(0)); // get value of left subexpression
		int right = visit(ctx.expr(1)); // get value of right subexpression
		return (int) Math.pow(left, right);
	}

	@Override
	public Integer visitAddSub(AddSubContext ctx) {
		int left = visit(ctx.expr(0)); // get value of left subexpression
		int right = visit(ctx.expr(1)); // get value of right subexpression
		if (ctx.op.getType() == LibExprParser.ADD)
			return left + right;
		return left - right; // must be SUB
	}

	@Override
	public Integer visitId(IdContext ctx) {
		String id = ctx.ID().getText();
		if (memory.containsKey(id))
			return memory.get(id);
		return 0;
	}

	@Override
	public Integer visitInt(IntContext ctx) {

		return Integer.valueOf(ctx.INT().getText());
	}

	@Override
	public Integer visitPrintExpr(PrintExprContext ctx) {
		Integer value = visit(ctx.expr()); // evaluate the expr child
		values.add(value);
		return value;
	}

	@Override
	public Integer visitAssign(AssignContext ctx) {
		String id = ctx.ID().getText(); // id is left-hand side of '='
		int value = visit(ctx.expr()); // compute value of expression on right
		memory.put(id, value); // store it in our memory
		return value;
	}

}