import org.junit.*;

/**
 * JUnit TimeOut Test
 * 
 * @author mkyong
 * 
 *         Adaptado de:
 *         http://www.mkyong.com/unittest/junit-4-tutorial-4-time-test/
 *
 */
public class JunitTest4 {

	@Test(timeout = 1000)
	public void infinity() {
		while (true)
			;
	}

}