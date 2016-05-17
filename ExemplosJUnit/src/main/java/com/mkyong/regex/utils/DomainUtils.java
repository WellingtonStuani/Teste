package com.mkyong.regex.utils;

import java.util.regex.Pattern;

/**
 * 
 * Fonte: http://www.mkyong.com/unittest/junit-4-tutorial-6-parameterized-test/
 *
 */
public class DomainUtils {

	private static Pattern pDomainName;

	private static final String DOMAIN_NAME_PATTERN = "^((?!-)[A-Za-z0-9-]{1,63}(?<!-)\\.)+[A-Za-z]{2,6}$";

	static {
		pDomainName = Pattern.compile(DOMAIN_NAME_PATTERN);
	}

	//is this a valid domain name?
	public static boolean isValidDomainName(String domainName) {
		return pDomainName.matcher(domainName).find();
	}

}