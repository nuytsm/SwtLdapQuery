package be.nuytsm.SwtLdapQuery.view.controller;

import java.beans.PropertyChangeSupport;

public class InputQuery extends ModelPropertyChange{
	private PropertyChangeSupport changeSupport = 
			new PropertyChangeSupport(this);
	
	private String accountName;
	
	public void setAccountName(String accountName) {
	    firePropertyChange("accountName", this.accountName, this.accountName = accountName);
	}

	public String getAccountName() {
		return accountName;
	}
	

	
}
