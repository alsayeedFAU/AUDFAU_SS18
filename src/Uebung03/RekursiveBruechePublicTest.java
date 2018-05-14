import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

public class RekursiveBruechePublicTest {
	// ========== SYSTEM ==========
	protected static final String EX_NAME_FareyFolge = "FareyFolge";
	protected static final String CLASS_NAME_FareyFolge = "FareyFolge";
	protected static final String METHOD_NAME_berechneFareyFolge = "berechneFareyFolge";
	// -----
	protected static final String EX_NAME_RegulaereKettenBrueche_konvRat = "RegulaereKettenBrueche.konvRat";
	protected static final String EX_NAME_RegulaereKettenBrueche_konvPseudoIrrat = "RegulaereKettenBrueche.konvPseudoIrrat";
	protected static final String CLASS_NAME_RegulaereKettenBrueche = "RegulaereKettenBrueche";
	protected static final String METHOD_NAME_konvRat = "konvRat";
	protected static final String METHOD_NAME_konvPseudoIrrat = "konvPseudoIrrat";
	// --------------------
	// ========== TEST-DATA ==========
	private static final Random RND = new Random();

	// ========== PUBLIC TEST ==========
	@Test(timeout = 3666)
	public void pubTest__FareyFolge__RECURSION_CHECK__THIS_TEST_IS_VERY_IMPORTANT__IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		int[][] expecteds6 = { { 0, 1 }, { 1, 6 }, { 1, 5 }, { 1, 4 }, { 1, 3 }, { 2, 5 }, { 1, 2 }, { 3, 5 }, { 2, 3 }, { 3, 4 }, { 4, 5 }, { 5, 6 }, { 1, 1 } };
		checkFarey(6, expecteds6);
		int[][] expecteds7 = { { 0, 1 }, { 1, 7 }, { 1, 6 }, { 1, 5 }, { 1, 4 }, { 2, 7 }, { 1, 3 }, { 2, 5 }, { 3, 7 }, { 1, 2 }, { 4, 7 }, { 3, 5 }, { 2, 3 }, { 5, 7 }, { 3, 4 }, { 4, 5 }, { 5, 6 }, { 6, 7 }, { 1, 1 } };
		checkFarey(7, expecteds7);
		for (int pass = 0; pass < 42; pass++) {
			checkFarey(1 + RND.nextInt(42), null);
		}
	}

	// ----------------------------------------
	@Test(timeout = 3666)
	public void pubTest__Kettenbruch__konvRat__RECURSION_CHECK__THIS_TEST_IS_VERY_IMPORTANT__IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		checkChained_konvRat(77708431, 2640858, new int[] { 29, 2, 2, 1, 5, 1, 4, 1, 1, 2, 1, 6, 1, 10, 2, 2, 3 });
		checkChained_konvRat(17, 10, new int[] { 1, 1, 2, 3 });
		for (int pass = 0; pass < 42; pass++) {
			int z = RND.nextInt(4711_0815), n = RND.nextInt(4711_0815), ggt = ggT(z, n);
			checkChained_konvRat(z / ggt, n / ggt, null);
		}
	}

	// ----------------------------------------
	@Test(timeout = 3666)
	public void pubTest__Kettenbruch__konvPseudoIrrat__RECURSION_CHECK__THIS_TEST_IS_VERY_IMPORTANT__IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		// https://de.wikipedia.org/wiki/Kreiszahl#Kettenbruchentwicklung
		checkChained_konvPseudoIrrat(Math.PI, 13, new long[] { 3, 7, 15, 1, 292, 1, 1, 1, 2, 1, 3, 1, 14 });
		// https://de.wikipedia.org/wiki/Eulersche_Zahl#Kettenbruchentwicklungen
		checkChained_konvPseudoIrrat(Math.E, 20, new long[] { 2, 1, 2, 1, 1, 4, 1, 1, 6, 1, 1, 8, 1, 1, 10, 1, 1, 12, 1, 1 });
		for (int pass = 0; pass < 42; pass++) {
			double x = RND.nextDouble();
			int l = 42 + RND.nextInt(42);
			checkChained_konvPseudoIrrat(x, l, null);
		}
	}

	// ========== HELPER ==========
	protected static final void checkFarey(int n, int[][] expecteds) {
		String msg = CLASS_NAME_FareyFolge + "." + METHOD_NAME_berechneFareyFolge + "(..., " + n + ") hat ";
		BigBrother bb = new BigBrother();
		int[][] actuals = FareyFolge.berechneFareyFolge(bb, n);
		assertNotNull(msg + "faelschlich null zurueckgegeben.", actuals);
		assertEquals(msg + "Feld falscher Laenge zurueckgegeben.", cardinality(n), actuals.length);
		for (int i = 0; i < actuals.length; i++) {
			assertNotNull(msg + "faelschlich null-Eintrag bei [" + i + "] zurueckgeben.", actuals[i]);
			assertEquals(msg + "Feld falscher Laenge bei [" + i + "] zurueckgegeben.", 2, actuals[i].length);
			assertEquals(msg + "fehlerhaften Eintrag (Bruch nicht gekuerzt) bei [" + i + "] zurueckgeben (" + actuals[i][0] + "/" + actuals[i][1] + ")", 1, ggT(actuals[i][0], actuals[i][1]));
			assertTrue(msg + "fehlerhaften Eintrag (Bruch nicht im Einheitsintervall) bei [" + i + "] zurueckgeben (" + actuals[i][0] + "/" + actuals[i][1] + ")", actuals[i][0] <= actuals[i][1]);
			assertTrue(msg + "hat fehlerhaften Eintrag (Zaehler zu gross) bei [" + i + "] zurueckgeben (" + actuals[i][0] + "/" + actuals[i][1] + ")", actuals[i][1] <= n);
			if (i < actuals.length - 1) {
				assertTrue(msg + "hat falsche Reihenfolge bei [" + i + "] vs. [" + (i + 1) + "] zurueckgeben.", actuals[i][0] * actuals[i + 1][1] < actuals[i + 1][0] * actuals[i][1]);
			}
			if (expecteds != null) {
				assertArrayEquals(msg + "falschen Eintrag bei [" + i + "] zurueckgeben.", expecteds[i], actuals[i]);
			}
		}
		assertEquals(msg + "falschen Basisfall.", 1, bb.stackTraceLog.size());
		assertEquals(msg + "falsche Rekursionstiefe.", n, bb.stackTraceLog.get(0).intValue());
	}

	protected static final void checkChained_konvRat(int z, int n, int[] expecteds) {
		String msg = CLASS_NAME_RegulaereKettenBrueche + "." + METHOD_NAME_konvRat + "(..., " + z + ", " + n + ") hat ";
		BigBrother bb = new BigBrother();
		int[] actuals = RegulaereKettenBrueche.konvRat(bb, z, n);
		assertNotNull(msg + "faelschlich null zurueckgegeben.", actuals);
		assertNotEquals(msg + "faelschlich ein leeres Feld zurueckgegeben.", 0, actuals.length);
		assertNotEquals(msg + "faelschlich 0 als letzten Eintrag zurueckgegeben.", 0, actuals[actuals.length - 1]);
		int actZ = 1, actN = 0;
		for (int i = actuals.length - 1; i >= 0; i--) {
			actZ = actN + actuals[i] * (actN = actZ);
		}
		assertEquals(msg + "fehlerhaften Kettenbruch (Zaehler nicht rekonstruierbar) zurueckgegeben.", z, actZ);
		assertEquals(msg + "fehlerhaften Kettenbruch (Nenner nicht rekonstruierbar) zurueckgegeben.", n, actN);
		if (expecteds != null) {
			assertArrayEquals(msg + "fehlerhaften Kettenbruch zurueckgegeben.", expecteds, actuals);
			assertEquals(msg + "falschen Basisfall.", expecteds.length - 1, bb.stackTraceLog.size());
			assertEquals(msg + "falsche Rekursionstiefe.", expecteds.length - 1, bb.stackTraceLog.get(0).intValue());
		}
	}

	protected static final void checkChained_konvPseudoIrrat(double x, int l, long[] expecteds) {
		String msg = CLASS_NAME_RegulaereKettenBrueche + "." + METHOD_NAME_konvPseudoIrrat + "(..., " + x + ", " + l + ") hat ";
		BigBrother bb = new BigBrother();
		long[] actuals = RegulaereKettenBrueche.konvPseudoIrrat(bb, x, l);
		assertNotNull(msg + "faelschlich null zurueckgegeben.", actuals);
		assertNotEquals(msg + "faelschlich ein leeres Feld zurueckgegeben.", 0, actuals.length);
		assertNotEquals(msg + "faelschlich 0 als letzten Eintrag zurueckgegeben.", 0, actuals[actuals.length - 1]);
		if (expecteds != null) {
			assertArrayEquals(msg + "fehlerhaften Kettenbruch zurueckgeben.", expecteds, actuals);
			assertEquals(msg + "falschen Basisfall.", expecteds.length - 1, bb.stackTraceLog.size());
			assertEquals(msg + "falsche Rekursionstiefe.", expecteds.length - 1, bb.stackTraceLog.get(0).intValue());
		} else {
			double actX = Double.MAX_VALUE;
			for (int i = actuals.length - 1; i >= 0; i--) {
				actX = actuals[i] + 1 / actX;
			}
			System.out.println(x +"    " + actX);
			assertEquals(msg + "fehlerhaften Kettenbruch (Zahl nicht annaehernd genau rekonstruierbar) zurueckgegeben.", x, actX, x * 1e-13);
		}
	}

	// ========== Big Brother ==========
	private static final class BigBrother implements RekursiveBrueche {
		protected final LinkedList<Integer> stackTraceLog = new LinkedList<>();

		@Override
		public int[][] initialeFareyFolge() {
			log(CLASS_NAME_FareyFolge, METHOD_NAME_berechneFareyFolge);
			return new int[][] { { 0, 1 }, { 1, 1 } };
		}

		@Override
		public int[] ergaenze(int x, int[] y) {
			log(CLASS_NAME_RegulaereKettenBrueche, METHOD_NAME_konvRat);
			int[] z = new int[y.length + 1];
			z[0] = x;
			System.arraycopy(y, 0, z, 1, y.length);
			return z;
		}

		@Override
		public long[] ergaenze(long x, long[] y) {
			log(CLASS_NAME_RegulaereKettenBrueche, METHOD_NAME_konvPseudoIrrat);
			long[] z = new long[y.length + 1];
			z[0] = x;
			System.arraycopy(y, 0, z, 1, y.length);
			return z;
		}

		private void log(String className, String methodeName) {
			int stackTiefe = 0;
			StackTraceElement[] st = Thread.currentThread().getStackTrace();
			for (StackTraceElement ste : st) {
				if (ste.getClassName().equals(className)) {
					if (ste.getMethodName().equals(methodeName)) {
						stackTiefe++;
					}
				}
			}
			stackTraceLog.add(stackTiefe);
		}
	}

	protected static final int ggT(int a, int b) {
		return (b == 0) ? a : ggT(b, a % b);
	}

	private static final int phi(int n) {
		int phi = 0;
		for (int i = 1; i <= n; i++) {
			if (ggT(n, i) == 1) {
				phi++;
			}
		}
		return phi;
	}

	private static final int cardinality(int n) {
		return n <= 1 ? 2 : cardinality(n - 1) + phi(n);
	}
}