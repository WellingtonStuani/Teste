import org.junit.*;

/**
 * JUnit Expected Exception Test
 * 
 * @author mkyong
 * 
 *         Adaptado de:
 *         http://www.mkyong.com/unittest/junit-4-tutorial-2-expected-exception-test/
 *
 */
public class JunitTest2 {

	@Test(expected = ArithmeticException.class)
	public void divisionWithException() {
		int i = 1 / 0;
	}

}