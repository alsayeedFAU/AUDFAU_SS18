import static org.junit.Assert.*;
import java.lang.reflect.*;
import java.util.*;
import org.junit.*;

public class LandminesPublicTest {
	// ========== SYSTEM ==========
	protected static final String EX_NAME_Landmines_findPath = "Landmines.findPath";
	protected static final String CLASS_NAME_Landmines = "Landmines";
	protected static final String METHOD_NAME_findPath = "findPath";
	protected static final String METHOD_NAME_helper = "helper";
	// --------------------

	// ========== TEST-DATA ==========
	private static final boolean[][] SHEET_FIELD = new boolean[12][10];
	static {
		SHEET_FIELD[1][1] = SHEET_FIELD[2][3] = SHEET_FIELD[3][4] = SHEET_FIELD[5][5] = SHEET_FIELD[6][8] = true;
		SHEET_FIELD[6][1] = SHEET_FIELD[9][0] = SHEET_FIELD[9][5] = SHEET_FIELD[11][3] = true;
	}
	private static final int[][] SHEET_SOLUTION = { //
			{ 4, 0 }, { 4, 1 }, { 4, 2 }, { 4, 3 }, { 5, 3 }, { 6, 3 }, { 7, 3 }, { 7, 4 }, { 7, 5 }, { 7, 6 }, { 7, 7 }, { 8, 7 }, { 8, 8 }, { 8, 9 } //
	};
	private static final Random RND = new Random();

	// ========== CHECK INTESTINES ==========
	@Test(timeout = 666)
	public void pubTest__Intestines__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		check__Intestines();
	}

	// ========== PUBLIC TEST ==========
	@Test(timeout = 6666)
	public void pubTest__exampleFromExerciseSheet__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		check(SHEET_FIELD, 14, HasSolution.YES);
		
		for (int i = 0; i < SHEET_SOLUTION.length - 1; i++) {
			boolean[][] f = clone(SHEET_FIELD);
			f[SHEET_SOLUTION[i][0]][SHEET_SOLUTION[i][1]] = true;
			if (i <= 5) {
				check(f, 15, HasSolution.YES);
			} else if (i <= 12) {
				check(f, -1, HasSolution.NO);
			} else {
				check(f, 16, HasSolution.YES);
			}
		}
		
		
	}

	@Test(timeout = 26666)
	public void pubTest__tricky_and_random__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		
		for (int pass = 0; pass < 2; pass++) {
			boolean[][] f = new boolean[9][10];
			f[1][0] = f[2][0] = f[6][0] = f[7][0] = f[2][4] = f[5][5] = f[6][5] = f[7][9] = true;
			check(f, 14, HasSolution.YES);
		}
		
		for (int pass = 0; pass < 2; pass++) {
			int h = pass % 2 == 0 ? 6 + RND.nextInt(3) : 4 + RND.nextInt(2), w = pass % 2 == 0 ? 4 + RND.nextInt(2) : 6 + RND.nextInt(3);
			boolean[][] f = new boolean[h][w];
			for (int r = 0; r < h; r += 2) {
				f[r][w - 2 + RND.nextInt(2) - 1] = true;
			}
			check(f, -1, HasSolution.NO);
		}
		for (int pass = 0; pass < 3; pass++) {
			boolean[][] f = generate(0);
			check(f, f[0].length, HasSolution.YES);
		}
		
		for (int pass = 0; pass <= 13; pass++) {
			check(generate((1d + pass) / 15d), -1, HasSolution.MAYBE);
		}
		
		for (int pass = 0; pass < 3; pass++) {
			check(generate(1), -1, HasSolution.NO);
		}
		
	}

	// ========== HELPER: Intestines ==========
	// @AuD-STUDENT: DO NOT USE REFLECTION IN YOUR OWN SUBMISSION! BITTE KEINE REFLECTION IN DER EIGENEN ABGABE VERWENDEN! => "0 Punkte"!
	private static final Class<?>[] getDeclaredClasses(Class<?> clazz) {
		java.util.List<Class<?>> declaredClasses = new java.util.ArrayList<>();
		for (Class<?> c : clazz.getDeclaredClasses()) {
			if (!c.isSynthetic()) {
				declaredClasses.add(c);
			}
		}
		return declaredClasses.toArray(new Class[0]);
	}

	private static final Field[] getDeclaredFields(Class<?> clazz) {
		java.util.List<Field> declaredFields = new java.util.ArrayList<>();
		for (Field f : clazz.getDeclaredFields()) {
			if (!f.isSynthetic()) {
				declaredFields.add(f);
			}
		}
		return declaredFields.toArray(new Field[0]);
	}

	private static final Constructor<?>[] getDeclaredConstructors(Class<?> clazz) {
		java.util.List<Constructor<?>> declaredConstructors = new java.util.ArrayList<>();
		for (Constructor<?> c : clazz.getDeclaredConstructors()) {
			if (!c.isSynthetic()) {
				declaredConstructors.add(c);
			}
		}
		return declaredConstructors.toArray(new Constructor[0]);
	}

	private static final Method[] getDeclaredMethods(Class<?> clazz) {
		java.util.List<Method> declaredMethods = new java.util.ArrayList<>();
		for (Method m : clazz.getDeclaredMethods()) {
			if (!m.isBridge() && !m.isSynthetic()) {
				declaredMethods.add(m);
			}
		}
		return declaredMethods.toArray(new Method[0]);
	}

	private void check__Intestines() {
		Class<Landmines> clazz = Landmines.class;
		assertSame("Du sollst genau eine bestimmte Super-Klasse haben!", Object.class, clazz.getSuperclass());
		assertEquals("Du sollst keine Schnittstelle implementieren!", 0, clazz.getInterfaces().length);
		assertEquals("Du sollst keine inneren Klassen haben!", 0, getDeclaredClasses(clazz).length);
		assertEquals("Du sollst keine inneren Annotationen haben!", 0, clazz.getDeclaredAnnotations().length);
		assertEquals("Du sollst keine Attribute haben!", 0, getDeclaredFields(clazz).length);
		Constructor<?>[] constructors = getDeclaredConstructors(clazz);
		assertEquals("Du sollst genau einen Konstruktor haben (und zwar den >public< default cons)!", 1, constructors.length);
		Method[] methods = getDeclaredMethods(clazz);
		assertEquals("Du sollst genau die vorgegebenen Methoden haben!", 2, methods.length);
	}

	// ========== HELPER ==========
	protected static enum HasSolution {
		YES, MAYBE, NO
	}

	private static final boolean[][] clone(boolean[][] f) {
		boolean[][] fClone = new boolean[f.length][];
		for (int r = 0; r < f.length; r++) {
			fClone[r] = Arrays.copyOf(f[r], f[r].length);
		}
		return fClone;
	}

	private static final boolean[][] generate(double density) {
		int h = 9 + RND.nextInt(5), w = 9 + RND.nextInt(5);
		boolean[][] f = new boolean[h][w];
		for (int r = 0; r < h; r++) {
			for (int c = 0; c < w; c++) {
				f[r][c] = RND.nextDouble() < density;
			}
		}
		return f;
	}

	protected static final void check(boolean[][] f, int maxLen, HasSolution hs) {
		String msg = LandminesPublicTest.CLASS_NAME_Landmines + "." + LandminesPublicTest.METHOD_NAME_findPath + " failed: ";
		BigBrother lt = new BigBrother();
		boolean[][] fClone = clone(f);
		int[][] actuals = Landmines.findPath(lt, fClone);
		for (int i = 0; i < f.length; i++) {
			assertNotNull(msg + "input illegally modified at row [" + i + "].", fClone[i]);
			assertEquals(msg + "input illegally modified at row [" + i + "].", Arrays.toString(f[i]), Arrays.toString(fClone[i]));
		}
		assertTrue(msg + "recursion depth is wrong.", lt.getMinStackTraceDepth() >= f[0].length);
		if (hs == HasSolution.NO) {
			assertNull(msg + "result is not null, although a solution cannot exist.", actuals);
		} else if (hs == HasSolution.YES) {
			assertNotNull(msg + "result is null, although a solution exists.", actuals);
		}
		if (actuals != null) { // return value claims to be a solution
			if (maxLen >= 0) {
				assertTrue(msg + "result (path) has wrong length (expected " + maxLen + " but was " + actuals.length + ").", maxLen >= actuals.length);
			}
			for (int i = 0; i < actuals.length; i++) {
				assertNotNull(msg + "result contains null entry at [" + i + "].", actuals[i]);
				assertEquals(msg + "result contains entry of wrong size at [" + i + "].", 2, actuals[i].length);
				assertTrue(msg + "result entry is out of bounds (height) at [" + i + "].", 0 <= actuals[i][0] && actuals[i][0] < f.length);
				assertTrue(msg + "result entry is out of bounds (width) at [" + i + "].", 0 <= actuals[i][1] && actuals[i][1] < f[actuals[i][0]].length);
				assertFalse(msg + "result path steps on a mine at [" + i + "].", f[actuals[i][0]][actuals[i][1]]);
				if (i > 0) {
					int deltaY = Math.abs(actuals[i][0] - actuals[i - 1][0]);
					int deltaX = Math.abs(actuals[i][1] - actuals[i - 1][1]);
					assertEquals(msg + "result contains illegal step at [" + (i - 1) + "] => [" + i + "].", 1, deltaX + deltaY);
				}
				for (int dN = 0; dN < 4; dN++) {
					int rN = actuals[i][0] - (dN - 2) % 2, cN = actuals[i][1] - (dN - 1) % 2;
					if (0 <= rN && rN < f.length && 0 <= cN && cN < f[rN].length && f[rN][cN]) {
						assertFalse(msg + "result path steps immediately near a mine at [" + i + "].", f[rN][cN]);
					}
				}
			}
			assertNotEquals(msg + "result contains no entries.", 0, actuals.length);
			assertEquals(msg + "result does not start left.", 0, actuals[0][1]);
			assertEquals(msg + "result does not end right.", f[actuals[actuals.length - 1][0]].length - 1, actuals[actuals.length - 1][1]);
		}
	}

	// ========== Big Brother ==========
	private static final class BigBrother implements LandminesTrail {
		private final LinkedList<Integer> stackTraceLog = new LinkedList<>();

		@Override
		public int[][] finished() {
			int stackTiefe = 0;
			StackTraceElement[] st = Thread.currentThread().getStackTrace();
			for (StackTraceElement ste : st) {
				if (ste.getClassName().equals(LandminesPublicTest.CLASS_NAME_Landmines)) {
					if (ste.getMethodName().equals(LandminesPublicTest.METHOD_NAME_helper)) {
						stackTiefe++;
					}
				}
			}
			stackTraceLog.add(stackTiefe);
			return new int[][] {};
		}

		private int getMinStackTraceDepth() {
			int min = Integer.MAX_VALUE;
			for (Integer i : stackTraceLog) {
				min = i < min ? i : min;
			}
			return min;
		}
	}
}