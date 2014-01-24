package be.nuytsm.SwtLdapQuery.view.swt;


import java.awt.image.BufferedImage;

import javax.naming.NamingException;
import javax.naming.directory.SearchResult;

import org.eclipse.jface.bindings.BindingManager;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import be.nuytsm.SwtLdapQuery.api.LdapQuery;
import be.nuytsm.SwtLdapQuery.view.controller.InputQuery;

public class HomeScreen {
	
	private InputQuery inputQuery = new InputQuery();
	LdapQuery lq = new LdapQuery();
	private StyledText result;
	private Text queryText;
	
	public HomeScreen() {
		
		Display display = new Display();
		Shell shell = new Shell(display);
		
		addProgramIcon(display, shell);
		
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

	private void addProgramIcon(Display display, Shell shell) {
		Image image = new Image(display, ClassLoader.getSystemResourceAsStream("64px-LDAP-Finder-Icon.png"));
		shell.setImage(image);
	}

	private void initApplicationView(Shell shell) {
		GridLayout gridlayout = new GridLayout(1, false);
		shell.setLayout(gridlayout);
		
		Group queryGroup = createQueryGroup(shell);
		queryGroup.setLayout(new GridLayout(4, false));
		
		
		Group resultGroup = createResultGroup(shell);
		resultGroup.setLayout(new GridLayout(1, false));
		
		createResultPart(resultGroup);	
		createQueryPart(queryGroup);
	}

	private void createResultPart(Group resultGroup) {
		result = new StyledText(resultGroup, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI | SWT.WRAP);
	    result.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).span(2, 1).create());
		
//		result = new Text(resultGroup, SWT.BORDER | SWT.MULTI);
//		result.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		result.setText(getQueryResult());
	}

	private String getQueryResult() {
		try {
			SearchResult searchResult = lq.queryByAccountName(inputQuery.getAccountName());
			return lq.processSearchResultToString(searchResult);
		} catch (NamingException e) {
			return "nothing found";
		}
	}

	private void createQueryPart(final Group queryGroup) {
		Label label = new Label(queryGroup, SWT.NONE);
		label.setText("Name to query:");
		
		queryText = new Text(queryGroup, SWT.NONE);
		queryText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		queryText.addTraverseListener(new TraverseListener() {
			
			public void keyTraversed(TraverseEvent e) {
				if (e.detail == SWT.TRAVERSE_RETURN){
					query();
				}
			}			
		});
		
		
		Button button = new Button(queryGroup, SWT.PUSH);
		button.setText("Query");
		button.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				query();
			}
		});
	}
	
	private void query() {
		inputQuery.setAccountName(queryText.getText());
		result.setText(getQueryResult());
		result.redraw();
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
