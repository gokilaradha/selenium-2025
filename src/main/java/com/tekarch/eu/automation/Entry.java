package com.tekarch.eu.automation;

import com.salesforce.oauth.SalesforceAuth;

public class Entry {
	
	public static void  main(String[] args) {
		String ckey ="3MVG9rZjd7MXFdLj_gsb_rL6t4sXCgfU2yradiexDm1YdN6vQbHiU1KsodpmJXVXdUjDBhhvRpHMO7YhTh.7u";
		String csec	="5B95C3BF3E436B35EC874949EF56302FCD83B80601C8F08339EBDA295C3A2423"	;
		SalesforceAuth auth= new SalesforceAuth(ckey,csec,false);
		System.out.println(auth.start());
		

}
}
