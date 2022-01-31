import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * SWT application to demonstrate the usage of radio menu items.
 * <p>
 * The application contains a menu bar with this structure:
 * <pre>
 * Example
 * +-- Options
 *     +-- Option 1
 *     +-- Option 2
 *     +-- Option 3
 * </pre>
 * <p>
 * The content panel contains a text field which acts as log of all actions.
 * <p>
 * Each menu item has a selection listener which prints into the log field
 * as soon as it has been selected or de-selected.
 */
public class MenuDemo {

    private static Text logField;

    public static void main(String[] args) {
        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setText("Demo Application");

        shell.setLayout(new FillLayout());

        logField = new Text(shell, SWT.MULTI);
        logField.setText("");

        Menu menubar = new Menu(shell, SWT.BAR);
        shell.setMenuBar(menubar);

        MenuItem exampleMenuItem = createMenuItem("Example", menubar);
        Menu exampleMenu = createSubMenu(exampleMenuItem);
        MenuItem optionsMenuItem = createMenuItem("Options", exampleMenu);
        Menu optionsMenu = createSubMenu(optionsMenuItem);
        MenuItem option1 = addRadioMenuItem("Option 1", optionsMenu);
        option1.setSelection(true);
        MenuItem option2 = addRadioMenuItem("Option 2", optionsMenu);
        option2.setSelection(false);
        MenuItem option3 = addRadioMenuItem("Option 3", optionsMenu);
        option3.setSelection(false);

        shell.open();

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }

        display.dispose();
    }

    private static MenuItem createMenuItem(String text, Menu menu) {
        return createMenuItem(text, menu, SWT.CASCADE);
    }

    private static MenuItem createMenuItem(String text, Menu menu, int style) {
        MenuItem menuItem = new MenuItem(menu, style);
        menuItem.setText(text);
        return menuItem;
    }

    private static MenuItem addRadioMenuItem(String text, Menu menu) {
        MenuItem optionItem = createMenuItem(text, menu, SWT.RADIO);
        optionItem.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                String logMessage = text + " " + (optionItem.getSelection() ? "selected" : "deselected");
                logField.append(logMessage + System.lineSeparator());
            }
        });
        return optionItem;
    }

    private static Menu createSubMenu(MenuItem menuItem) {
        Shell shell = menuItem.getParent().getShell();
        Menu subMenu = new Menu(shell, SWT.DROP_DOWN);
        menuItem.setMenu(subMenu);
        return subMenu;
    }
}
