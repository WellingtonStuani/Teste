package com.mkyong.regex.utils;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * 
 * Fonte: http://www.mkyong.com/unittest/junit-4-tutorial-6-parameterized-test/
 *
 */
@RunWith(value = Parameterized.class)
public class DomainUtilsTest {

	private String domain;
	private boolean expected;

	public DomainUtilsTest(String domain, boolean expected) {
		this.domain = domain;
		this.expected = expected;
	}

	@Parameters(name = "{index}: isValid({0})={1}")
	public static Iterable<Object[]> data() {
		return Arrays.asList(new Object[][] { 
				{ "google.com", true },
				{ "mkyong.com", true }, 
				{ "-mkyong.com", false },
				{ "mkyong-.com", false }, 
				{ "3423kjk", false },
				{ "mk#$kdo.com", false } 
			});
	}

	@Test
	public void test_validDomains() {
		assertEquals(expected, DomainUtils.isValidDomainName(domain));
	}

}