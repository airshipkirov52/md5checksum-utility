package checksum.app;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class CheckSumApplication extends TestCase {

	public CheckSumApplication(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(CheckSumApplication.class);
	}

	public void testApp() {
		assertTrue(true);
	}
}
