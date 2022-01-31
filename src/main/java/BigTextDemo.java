import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * SWT application to demonstrate a text field with more than 100k characters.
 */
public class BigTextDemo {

    public static void main(String[] args) {
        Shell shell = createShell();

        Text textField = new Text(shell, SWT.MULTI);
        StringBuilder textBuilder = new StringBuilder();
        for (int i = 1; i <= 50000; i++) {
            if (i > 1) {
                textBuilder.append(' ');
            }
            textBuilder.append(i);
        }
        textField.setText(textBuilder.toString());

        openShell(shell);
    }

    private static Shell createShell() {
        Display display = Display.getDefault();
        Shell shell = new Shell(display);
        shell.setText("Demo Application");
        shell.setLayout(new FillLayout());
        return shell;
    }

    private static void openShell(Shell shell) {
        shell.open();

        Display display = shell.getDisplay();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }

        display.dispose();
    }
}
