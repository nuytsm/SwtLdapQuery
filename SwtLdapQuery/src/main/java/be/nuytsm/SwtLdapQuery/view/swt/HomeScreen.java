package be.nuytsm.SwtLdapQuery.view.swt;


import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;

public class HomeScreen {
	
	public HomeScreen() {
		
		Display display = new Display();
		Shell shell = new Shell(display);
		initApplicationView(shell);	
		shell.open();
		// run the event loop as long as the window is open
		while (!shell.isDisposed()) {
		    // read the next OS event queue and transfer it to a SWT event 
		  if (!display.readAndDispatch())
		   {
		  // if there are currently no other OS event to process
		  // sleep until the next OS event is available 
		    display.sleep();
		   }
		}
	
		// disposes all associated windows and their components
		display.dispose(); 
	}

	private void initApplicationView(Shell shell) {
		GridLayout gridlayout = new GridLayout(1, false);
//		RowLayout rowlayout = new RowLayout(SWT.VERTICAL);
		shell.setLayout(gridlayout);
		
		Group queryGroup = createQueryGroup(shell);
		queryGroup.setLayout(new GridLayout(4, false));
		
		
		Group resultGroup = createResultGroup(shell);
		resultGroup.setLayout(new GridLayout(1, false));
		
		Button button = new Button(queryGroup, SWT.PUSH);
		button.setText("TestButton");
		button.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				System.out.println("pushed");
			}
		});
		
	}

	private Group createQueryGroup(Shell shell) {
		Group group = new Group(shell, SWT.NONE);
	    group.setText("Query");
	    GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, false);
	    group.setLayoutData(gridData);
	    return group;
	}
	
	private Group createResultGroup(Shell shell) {
		Group group = new Group(shell, SWT.NONE);
	    group.setText("Result");
	    GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
	    group.setLayoutData(gridData);
	    group.setLayout(new RowLayout(SWT.VERTICAL));
	    return group;
	}


}
