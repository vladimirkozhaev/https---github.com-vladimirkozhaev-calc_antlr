package calc1;

import java.util.Arrays;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Main {
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);

		// pos x, pos y, width, height
		shell.setBounds(200, 200, 400, 500);
		shell.setText("SWT Text Demonstration");
		shell.setLayout(new GridLayout());

		shell.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		StyledText program = new StyledText(shell, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.WRAP);
		StyledText result = new StyledText(shell, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.WRAP);
		program.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL));
		result.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL));
		
		final Button button = new Button(shell, SWT.PUSH);
		button.setText("Hilight the text");

		button.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent event) {
				try {
					program.setStyleRanges(Util.getStyleRanges(program.getText(),display));
					result.setText(Arrays.toString(Util.calcWithVisitor(program.getText()).toArray()));
				} catch (TestException e) {
					result.setText("lexer exception ---\n"+e.getLexerException()+"\n parser exception --------\n"+e.getParserException());
				}
			}

			public void widgetDefaultSelected(SelectionEvent event) {

			}
		});
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}

		display.dispose();
	}
}
