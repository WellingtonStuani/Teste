import org.junit.*;

/**
 * JUnit Ignore Test
 * 
 * @author mkyong
 * 
 *         Adaptado de:
 *         http://www.mkyong.com/unittest/junit-4-tutorial-3-ignore-test/
 *
 */
public class JunitTest3 {

	@Ignore("Not Ready to Run")
	@Test
	public void divisionWithException() {
		System.out.println("Method is not ready yet");
	}

}