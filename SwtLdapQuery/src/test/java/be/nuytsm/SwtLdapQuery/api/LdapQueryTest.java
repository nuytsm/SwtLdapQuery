package be.nuytsm.SwtLdapQuery.api;

import javax.naming.NamingException;
import javax.naming.directory.SearchResult;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class LdapQueryTest {
	
	private LdapQuery ldapQuery;
	private String resultQueryByAccountNuytsM;
	
	@Before
	public void before() throws NamingException{
		ldapQuery = new LdapQuery();
		SearchResult sr = ldapQuery.queryByAccountName("nuyts.m");
		resultQueryByAccountNuytsM = ldapQuery.processSearchResultToString(sr);
	}
	
	@Test
	public void ldapQuery_stringNotEmpty(){
		Assert.assertNotNull(resultQueryByAccountNuytsM);	
		Assert.assertFalse(resultQueryByAccountNuytsM.isEmpty());
	}
	
	@Test
	public void ldapQuery_stringContainsAccount(){
		Assert.assertTrue(resultQueryByAccountNuytsM.toLowerCase().contains("nuyts.m"));
	}
	
	
	

}
