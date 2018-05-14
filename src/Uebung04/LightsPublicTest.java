import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.util.*;
import org.junit.*;

public class LightsPublicTest {
	// ========== SYSTEM ==========
	protected static final String EX_NAME_Lights_push = "Lights.push";
	protected static final String EX_NAME_Lights_solve = "Lights.solve";
	protected static final String CLASS_NAME_Lights = "Lights";
	protected static final String METHOD_NAME_push = "push";
	protected static final String METHOD_NAME_solve = "solve";
	protected static final String CLASS_METHOD_NAME_LightsTester_test = "LightsTester.test";
	// --------------------

	// ========== TEST-DATA ==========
	private static final Random RND = new Random();

	// ========== PUBLIC TEST ==========
	@Test(timeout = 666)
	public void pubTest__Lights__push__simple() {
		String msg = LightsPublicTest.CLASS_NAME_Lights + "." + LightsPublicTest.METHOD_NAME_solve + " failed: ";
		for (int pass = 0; pass < 42; pass++) {
			boolean[] actuals = new boolean[10 + RND.nextInt(16)];
			boolean[] expecteds = new boolean[actuals.length];
			int button = 3 + RND.nextInt(actuals.length - 6);
			for (int i = 0; i < actuals.length; i++) {
				actuals[i] = RND.nextBoolean();
				expecteds[i] = (i == button - 1 || i == button || i == button + 1) ? !actuals[i] : actuals[i];
			}
			Lights.push(actuals, button);
			assertEquals(msg + " LEDs wrong.", Arrays.toString(expecteds), Arrays.toString(actuals));
		}
	}

	// ------------------------------------------------------------

	@Test(timeout = 9666)
	public void pubTest__Lights__solve__hasSol_rnd__RECURSION_CHECK__THIS_TEST_IS_VERY_IMPORTANT__IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		for (int pass = 0; pass < 12; pass++) {
			System.out.println(pass);
			boolean[] lights = new boolean[6 + RND.nextInt(12)];
			for (int light = 0; light < lights.length; light++) {
				lights[light] = RND.nextBoolean();
			}
			boolean[] target = Arrays.copyOf(lights, lights.length);
			for (int button = 1; button < lights.length - 1; button++) {
				if (RND.nextBoolean()) {
					target[button - 1] = !target[button - 1];
					target[button] = !target[button];
					target[button + 1] = !target[button + 1];
				}
			}
			System.out.println(Arrays.toString(lights));
			System.out.println(Arrays.toString(target));
			LightsPublicTest.check__solve(lights, target, true);
		}
	}

	@Test(timeout = 9666)
	public void pubTest__Lights__solve__solved_or_noSol__RECURSION_CHECK__THIS_TEST_IS_VERY_IMPORTANT__IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		for (int pass = 0; pass < 12; pass++) {
			boolean[] lights = new boolean[6 + RND.nextInt(10)];
			for (int light = 0; light < lights.length; light++) {
				lights[light] = RND.nextBoolean();
			}
			LightsPublicTest.check__solve(lights, lights, true);
		}
		// -----
		LightsPublicTest.check__solve(new boolean[] { true, false, false, true, true }, new boolean[] { true, true, true, true, true }, false);
		LightsPublicTest.check__solve(new boolean[] { true, false, false, true, true }, new boolean[] { false, false, false, false, false }, false);
		// -----
		LightsPublicTest.check__solve(new boolean[] { false, false, false, false, true }, new boolean[] { true, true, true, true, true }, false);
		LightsPublicTest.check__solve(new boolean[] { false, false, false, false, true }, new boolean[] { false, false, false, false, false }, false);
	}

	// ========== HELPER ==========
	protected static final void check__solve(boolean[] lights, boolean[] target, boolean hasSol) {
		String msg = LightsPublicTest.CLASS_NAME_Lights + "." + LightsPublicTest.METHOD_NAME_solve + " failed: ";
		boolean[] lightsClone = Arrays.copyOf(lights, lights.length);
		LightsPublicTest.BigBrother lt = new LightsPublicTest.BigBrother(target);
		int[] actuals = Lights.solve(lt, lightsClone, 0);
		System.out.println(Arrays.toString(actuals));
		assertEquals(msg + "input modified illegally.", Arrays.toString(lights), Arrays.toString(lightsClone));
		assertEquals(msg + "wrong recursion depth.", lights.length + 1, lt.getMaxStackTraceDepth());
		System.out.println("Anzahl Calls: " + lt.getNumberOfCalls());
		System.out.println("Anzahl Calls erwünscht:" +(1 << lights.length));
		System.out.println(lt.getNumberOfCalls() <= (1 << lights.length));
		assertTrue(msg + "wrong number of calls to " + LightsPublicTest.CLASS_METHOD_NAME_LightsTester_test, 0 < lt.getNumberOfCalls() && lt.getNumberOfCalls() <= (1 << lights.length));
		if (actuals != null) { // return value claims to be a solution
			boolean[] lightsCheck = Arrays.copyOf(lights, lights.length);
			int last = -1;
			for (int a : actuals) {
				assertTrue(msg + "result contains illegal indices: " + Arrays.toString(actuals), 0 <= a && a < lightsCheck.length);
				assertTrue(msg + "result is not in ascending order: " + Arrays.toString(actuals), a > last);
				if (a > 0) {
					lightsCheck[a - 1] = !lightsCheck[a - 1];
				}
				lightsCheck[a] = !lightsCheck[a];
				if (a + 1 < lightsCheck.length) {
					lightsCheck[a + 1] = !lightsCheck[a + 1];
				}
				last = a;
			}
			System.out.println(Arrays.toString(lightsCheck));
			System.out.println(Arrays.toString(target));
			assertTrue(msg + "result does not achieve target.", Arrays.equals(lightsCheck, target));
		} else if (hasSol) {
			fail(msg + "returned null, although a solution is known." + Arrays.toString(lights) + Arrays.toString(target));
		} else {
			assertEquals(msg + "wrong number of calls to " + LightsPublicTest.CLASS_METHOD_NAME_LightsTester_test, 1 << lights.length, lt.getNumberOfCalls());
		}
	}

	// ========== Big Brother ==========
	protected static final class BigBrother implements LightsTester {
		private final LinkedList<Integer> stackTraceLog = new LinkedList<>();
		private final boolean[] target;

		protected BigBrother(boolean[] target) {
			this.target = target;
		}

		@Override
		public boolean test(boolean[] current) {
			int stackTiefe = 0;
			StackTraceElement[] st = Thread.currentThread().getStackTrace();
			for (StackTraceElement ste : st) {
				if (ste.getClassName().equals(CLASS_NAME_Lights)) {
					if (ste.getMethodName().equals(METHOD_NAME_solve)) {
						stackTiefe++;
					}
				}
			}
			stackTraceLog.add(stackTiefe);
			return Arrays.equals(target, current);
		}

		protected int getNumberOfCalls() {
			return stackTraceLog.size();
		}

		protected int getMaxStackTraceDepth() {
			int max = -1;
			for (Integer i : stackTraceLog) {
				max = max < i ? i : max;
			}
			return max;
		}
	}
}