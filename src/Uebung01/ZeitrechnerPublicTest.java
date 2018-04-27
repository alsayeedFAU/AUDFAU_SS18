import static org.junit.Assert.*;
import org.junit.*;

public class ZeitrechnerPublicTest {
	// ========== SYSTEM ==========
	protected static final String EX_NAME_sekunden = "sekunden";
	// --------------------

	// ========== PUBLIC TEST ==========
	@Test(timeout = 666)
	public void pubTest__sekunden() {
		ZeitrechnerPublicTest.check(new int[] { 10, 15, 00, 11, -45, 00 }, -22, " (m22 is negative => your method must return -22 as the result of your check)");
		ZeitrechnerPublicTest.check(new int[] { 10, 15, 00, 11, 45, 00 }, 90 * 60, " (usual lecture slot is 90min)");
	}

	// ========== HELPER ==========
	protected static final void check(int[] t, long expected, String msg) {
		long actual = Zeitrechner.sekunden(t[0], t[1], t[2], t[3], t[4], t[5]);
		assertEquals(ZeitrechnerPublicTest.EX_NAME_sekunden + " failed on " + java.util.Arrays.toString(t) + msg, expected, actual);
	}

	// ========== main ==========
	// nothing to do ;) - please do nothing here:
	public static void main(String args[]) {
		// to compile on command line: javac -cp .:/usr/share/java/junit4.jar *.java
		// to run on command line: java -cp .:/usr/share/java/junit4.jar <nameOfThisClass>

		// starts junit runner - don't try to understand!
		org.junit.runner.JUnitCore.main(new Object() {
		}.getClass().getEnclosingClass().getSimpleName());
	}
}