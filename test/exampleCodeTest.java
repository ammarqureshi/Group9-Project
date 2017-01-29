import static org.junit.Assert.*;

import org.junit.Test;

public class exampleCodeTest {

	@Test
	public void testEchoInt() {
		assertEquals(1, exampleCode.echoInt(1));
		assertEquals(99, exampleCode.echoInt(99));
	}

	@Test
	public void testSumTwo() {
		assertEquals(4, exampleCode.sumTwo(2, 2));
	}
	
	@Test
	public void testSumTwoFailTest() {
		assertEquals(5, exampleCode.sumTwo(2, 2));
	}

}
