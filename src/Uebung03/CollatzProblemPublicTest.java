import static org.junit.Assert.*;
import java.util.*;
import java.util.concurrent.atomic.*;
import org.junit.*;

public class CollatzProblemPublicTest {
	// ========== SYSTEM ==========
	protected static final String EX_NAME = "CollatzProblem";
	private static final String CLASS_NAME_CollatzProblem = "CollatzProblem";
	private static final String METHOD_NAME_f = "f";
	private static final String HELPER_CLASS_NAME_CollatzSeq = "CollatzSeq";
	private static final String HELPER_METHOD_NAME_add = "add";
	// --------------------

	// ========== PUBLIC TEST ==========
	@Test(timeout = 666)
	public void pubTest_aufgabenblatt_mitRekCheck__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		CollatzProblemPublicTest.commonTestCode(42L, 3, new long[] { 42, 21, 90, 45, 162, 81, 270, 135, 432, 216 }, 10, 55, "55");
	}

	// ========== HELPER ==========
	private static final long[] listToArr(List<Long> in) {
		Long[] inArr = in.toArray(new Long[0]);
		long[] out = new long[inArr.length];
		for (int i = 0; i < out.length; i++) {
			out[i] = inArr[i];
		}
		return out;
	}

	protected static final void commonTestCode(long n, int x, long[] res, long cfCalls, long rekCalls, String rekCallsStr) {
		final LinkedList<Long> cfs = new LinkedList<>();
		final AtomicInteger cfCallCounter = new AtomicInteger(), rekCallCounter = new AtomicInteger();
		final CollatzSeq cfMitRekCheck = new CollatzSeq() {
			public void add(long glied) {
				cfs.add(glied);
				cfCallCounter.addAndGet(1);
				StackTraceElement[] st = Thread.currentThread().getStackTrace();
				assertTrue("Die Anzahl der (rekursiven) Aufrufe von " + CLASS_NAME_CollatzProblem + "." + METHOD_NAME_f + "(n) stimmt nicht (der Stacktrace ist zu kurz).", st.length >= 2 + cfs.size());
				assertEquals("Der vorletzte Aufruf muss an " + HELPER_CLASS_NAME_CollatzSeq + ".*" + HELPER_METHOD_NAME_add + "* (also hierher ;)) gehen...", HELPER_METHOD_NAME_add, st[1].getMethodName());
				for (int i = 0; i < cfs.size(); i++) {
					assertEquals("... davor sollten genau " + cfs.size() + " (rekursive) Aufrufe an *" + CLASS_NAME_CollatzProblem + "*." + METHOD_NAME_f + "(n) stehen...", CLASS_NAME_CollatzProblem, st[2 + i].getClassName());
					assertEquals("... davor sollten genau " + cfs.size() + " (rekursive) Aufrufe an " + CLASS_NAME_CollatzProblem + ".*" + METHOD_NAME_f + "*(n) stehen...", METHOD_NAME_f, st[2 + i].getMethodName());
				}
				assertEquals("... und die wiederum sollten aus der uns umgebenden Methode angestossen worden sein.", "commonTestCode", st[2 + cfs.size()].getMethodName());
				for (StackTraceElement ste : st) {
					if (ste.getClassName().equals(CLASS_NAME_CollatzProblem) && ste.getMethodName().equals(METHOD_NAME_f)) {
						rekCallCounter.addAndGet(1);
					}
				}
			}
		};
		CollatzProblem.f(cfMitRekCheck, n, x);
		assertArrayEquals("Ermittelte Collatz-Folge ist falsch.", res, listToArr(cfs));
		assertEquals(CLASS_NAME_CollatzProblem + "." + METHOD_NAME_f + "(n) muss hier genau " + cfCalls + " Male aufgerufen werden.", cfCalls, cfCallCounter.longValue());
		assertEquals(CLASS_NAME_CollatzProblem + "." + METHOD_NAME_f + "(n) muss hier in Summe ueber alle beobachteten Stacktraces " + rekCallsStr + " = " + rekCalls + " Male stehen.", rekCalls, rekCallCounter.longValue());
	}
}