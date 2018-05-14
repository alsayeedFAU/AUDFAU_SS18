import static org.junit.Assert.*;
import java.util.*;
import java.util.concurrent.atomic.*;
import org.junit.*;

public class KochSnowflakePublicTest {
	// ========== SYSTEM ==========
	protected static final String EX_NAME_HELPER = "Helpers";
	protected static final String EX_NAME_MAIN = "Main";
	// --------------------

	private static final double DOUBLE_PRECISION = 1e-14d;

	// ========== PUBLIC TEST ==========
	@Test(timeout = 666)
	public void pubTest__getAE() {
		double[] vekAE = KochSnowflake.getAE(0, 0, 15, 0);
		assertArrayEquals("Aufruf mit (0, 0, 15, 0) geht kaputt.", new double[] { 15, 0 }, vekAE, DOUBLE_PRECISION);
	}

	@Test(timeout = 666)
	public void pubTest__getB() {
		double[] vekB = KochSnowflake.getB(0, 0, new double[] { 15, 0 });
		assertArrayEquals("Aufruf mit (0, 0, new double[] { 15, 0 }) geht kaputt.", new double[] { 5, 0 }, vekB, DOUBLE_PRECISION);
	}

	@Test(timeout = 666)
	public void pubTest__getD() {
		double[] vekD = KochSnowflake.getD(0, 0, new double[] { 15, 0 });
		assertArrayEquals("Aufruf mit (0, 0, new double[] { 15, 0 }) geht kaputt.", new double[] { 10, 0 }, vekD, DOUBLE_PRECISION);
	}

	@Test(timeout = 666)
	public void pubTest__getBC() {
		double[] vekBC = KochSnowflake.getBC(new double[] { 15, 0 });
		assertArrayEquals("Aufruf mit (new double[] { 15, 0 }) geht kaputt.", new double[] { 2.5, 4.330127018922193 }, vekBC, DOUBLE_PRECISION);
	}

	@Test(timeout = 666)
	public void pubTest__getC() {
		double[] vekC = KochSnowflake.getC(5, 0, new double[] { 2.5, 4.330127018922193 });
		assertArrayEquals("Aufruf mit (5, 0, new double[] { 2.5, 4.330127018922193 }) geht kaputt.", new double[] { 7.5, 4.330127018922193 }, vekC, DOUBLE_PRECISION);
	}

	@Test(timeout = 666)
	public void pubTest__bendSegment() {
		double[] geknickteABCDE_soll = { 0, 0, 5, 0, 7.5, 4.330127018922193, 10, 0, 15, 0 };
		double[] geknickteABCDE_ist = KochSnowflake.bendSegment(0, 0, 15, 0);
		assertArrayEquals("Aufruf mit (0, 0, 15, 0) geht kaputt.", geknickteABCDE_soll, geknickteABCDE_ist, DOUBLE_PRECISION);
	}

	private void compareLists(String message, List<Double> xsSoll, List<Double> ysSoll, List<Double> xs, List<Double> ys) {
		Double[] xsSollA = xsSoll.toArray(new Double[0]);
		Double[] ysSollA = ysSoll.toArray(new Double[0]);
		Double[] xsA = xs.toArray(new Double[0]);
		Double[] ysA = ys.toArray(new Double[0]);
		assertEquals("Die Anzahl der x-Koordinaten stimmt nicht.", xsSollA.length, xsA.length);
		assertEquals("Die Anzahl der x/y-Koordinaten stimmt nicht.", xsA.length, ysA.length);
		assertEquals("Die Anzahl der y-Koordinaten stimmt nicht.", ysSollA.length, ysA.length);
		for (int i = 0; i < xsSollA.length; i++) {
			assertEquals(message + " x-Koordinate " + i + " stimmt nicht.", xsSollA[i], xsA[i], DOUBLE_PRECISION);
			assertEquals(message + " y-Koordinate " + i + " stimmt nicht.", xsSollA[i], xsA[i], DOUBLE_PRECISION);
		}
	}

	@Test(timeout = 666)
	public void pubTest__draw__iters_0() throws InterruptedException {
		final LinkedList<Double> xs = new LinkedList<>(), ys = new LinkedList<>();
		KochSnowflakeCanvas sc = new KochSnowflakeCanvas() {
			public void drawLine(double xStart, double yStart, double xEnde, double yEnde) {
				xs.add(xStart);
				ys.add(yStart);
				xs.add(xEnde);
				ys.add(yEnde);
			}
		};
		KochSnowflake.draw(sc, 0, 0, 15, 0, 0);
		List<Double> xsSoll = Arrays.asList(0.0, 15.0);
		List<Double> ysSoll = Arrays.asList(0.0, 0.0);
		compareLists("Bei draw mit iters == 0:", xsSoll, ysSoll, xs, ys);
	}

	@Test(timeout = 666)
	public void pubTest__draw__iters_1() throws InterruptedException {
		final LinkedList<Double> xs = new LinkedList<>(), ys = new LinkedList<>();
		KochSnowflakeCanvas sc = new KochSnowflakeCanvas() {
			public void drawLine(double xStart, double yStart, double xEnde, double yEnde) {
				xs.add(xStart);
				ys.add(yStart);
				xs.add(xEnde);
				ys.add(yEnde);
			}
		};
		KochSnowflake.draw(sc, 0, 0, 15, 0, 1);
		List<Double> xsSoll = Arrays.asList(0.0, 5.0, 5.0, 7.5, 7.5, 10.0, 10.0, 15.0);
		List<Double> ysSoll = Arrays.asList(0.0, 0.0, 0.0, 4.330127018922193, 4.330127018922193, 0.0, 0.0, 0.0);
		compareLists("Bei draw mit iters == 1:", xsSoll, ysSoll, xs, ys);
	}

	@Test(timeout = 666)
	public void pubTest__draw__iters_2() throws InterruptedException {
		final LinkedList<Double> xs = new LinkedList<>(), ys = new LinkedList<>();
		KochSnowflakeCanvas sc = new KochSnowflakeCanvas() {
			public void drawLine(double xStart, double yStart, double xEnde, double yEnde) {
				xs.add(xStart);
				ys.add(yStart);
				xs.add(xEnde);
				ys.add(yEnde);
			}
		};
		KochSnowflake.draw(sc, 0, 0, 15, 0, 2);
		List<Double> xsSoll = Arrays.asList(0.0, 1.6666666666666667, 1.6666666666666667, 2.5, 2.5, 3.3333333333333335, 3.3333333333333335, 5.0, 5.0, 5.833333333333333, 5.833333333333333, 5.0, 5.0, 6.666666666666667, 6.666666666666667, 7.5, 7.5, 8.333333333333334, 8.333333333333334, 10.0, 10.0, 9.166666666666666, 9.166666666666666, 10.0, 10.0, 11.666666666666666, 11.666666666666666, 12.5, 12.5, 13.333333333333334, 13.333333333333334, 15.0);
		List<Double> ysSoll = Arrays.asList(0.0, 0.0, 0.0, 1.4433756729740643, 1.4433756729740643, 0.0, 0.0, 0.0, 0.0, 1.4433756729740643, 1.4433756729740643, 2.8867513459481287, 2.8867513459481287, 2.8867513459481287, 2.8867513459481287, 4.330127018922193, 4.330127018922193, 2.886751345948128, 2.886751345948128, 2.886751345948128, 2.886751345948128, 1.443375672974064, 1.443375672974064, 0.0, 0.0, 0.0, 0.0, 1.4433756729740643, 1.4433756729740643, 0.0, 0.0, 0.0);
		compareLists("Bei draw mit iters == 2:", xsSoll, ysSoll, xs, ys);
	}

	@Test(timeout = 666)
	public void pubTest__draw__check_recursion() throws InterruptedException {
		final AtomicInteger zfCallCounter = new AtomicInteger(), rekCallCounter = new AtomicInteger();
		final KochSnowflakeCanvas zfMitRekCheck = new KochSnowflakeCanvas() {
			public void drawLine(double xStart, double yStart, double xEnde, double yEnde) {
				zfCallCounter.addAndGet(1);
				StackTraceElement[] st = Thread.currentThread().getStackTrace();
				assertTrue("Die Anzahl der (rekursiven) Aufrufe stimmt nicht (der Stacktrace ist zu kurz).", st.length >= 10);
				assertEquals("Der vorletzte Aufruf sollten an uns gehen...", "drawLine", st[1].getMethodName());
				for (int i = 2; i < 10; i++) {
					assertEquals("... davor sollten genau 7 rekursive Aufrufe stehen...", "KochSnowflake", st[i].getClassName());
					assertEquals("... davor sollten genau 7 rekursive Aufrufe stehen...", "draw", st[i].getMethodName());
				}
				assertEquals("... und die wiederum sollten aus unserem Test angestossen worden sein.", "pubTest__draw__check_recursion", st[10].getMethodName());
				for (StackTraceElement ste : st) {
					if (ste.getClassName().equals("KochSnowflake") && ste.getMethodName().equals("draw")) {
						rekCallCounter.addAndGet(1);
					}
				}
			}
		};
		KochSnowflake.draw(zfMitRekCheck, 0, 0, 15, 0, 7);
		assertEquals("KochSnowflakeCanvas.drawLine muss hier genau 4^7 = 16384 Male aufgerufen werden.", 16384, zfCallCounter.longValue());
		assertEquals("KochSnowflake.draw muss hier in Summe ueber alle Pfade genau 8x4^7 = 131072 Male im Aufrufbaum stehen.", 131072, rekCallCounter.longValue());
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