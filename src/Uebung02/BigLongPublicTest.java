import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

public class BigLongPublicTest {
	// ========== SYSTEM ==========
	protected static final String EX_NAME_add = "add";
	protected static final String EX_NAME_negate = "negate";
	protected static final String EX_NAME_subtract = "subtract";
	protected static final String EX_NAME_toString = "toString";
	// --------------------

	// ========== TEST-DATA ==========
	private static final Random RND = new Random(4711_0815_666L);
	private static final int SIZE = 8;

	// ========== PUBLIC TEST ==========
	// ---------- add ----------
	@Test(timeout = 666)
	public void pubTest__add__smallParts_noCarry() {
		for (int pass = 0; pass < 42; pass++) {
			int[] a = new int[SIZE], b = new int[SIZE], expecteds = new int[SIZE];
			for (int i = 0; i < SIZE; i++) {
				a[i] = RND.nextInt(Integer.MAX_VALUE);
				b[i] = Integer.MAX_VALUE - a[i];
				expecteds[i] = a[i] + b[i];
			}
			BigLongPublicTest.check_add(a, b, expecteds);
		}
	}

	@Test(timeout = 666)
	public void pubTest__add__bigParts_heavyCarry() {
		for (int pass = 0; pass < 42; pass++) {
			int[] a = new int[SIZE], b = new int[SIZE], expecteds = new int[SIZE];
			int c = 0, delta;
			for (int i = 0; i < SIZE; i++) {
				a[i] = RND.nextInt(Integer.MAX_VALUE);
				b[i] = Integer.MAX_VALUE - a[i] - c;
				expecteds[i] = a[i] + b[i] + c;
				if (i != SIZE - 1 && RND.nextBoolean()) {
					if (a[i] < b[i]) {
						delta = 1 + RND.nextInt(Integer.MAX_VALUE - a[i]);
						a[i] = a[i] + delta;
					} else {
						delta = 1 + RND.nextInt(Integer.MAX_VALUE - b[i]);
						b[i] = b[i] + delta;
					}
					expecteds[i] = delta - 1;
					c = 1;
				} else {
					if (a[i] > 0) {
						delta = 1 + RND.nextInt(a[i]);
						a[i] = a[i] - delta;
					} else {
						delta = 1 + RND.nextInt(b[i]);
						b[i] = b[i] - delta;
					}
					expecteds[i] -= delta;
					c = 0;
				}
			}
			BigLongPublicTest.check_add(a, b, expecteds);
		}

	}

	// ---------- negate ----------
	@Test(timeout = 666)
	public void pubTest__negate__neg_1_eq_m1__and__neg_m1_eq_1() {
		int[] a = new int[SIZE], expecteds = new int[SIZE];
		for (int i = 0; i < SIZE; i++) {
			a[i] = 0;
			expecteds[i] = 0x7FFFFFFF;
		}
		a[0] = 1;
		BigLongPublicTest.check_negate(a, expecteds);
		for (int i = 0; i < SIZE; i++) {
			a[i] = 0x7FFFFFFF;
			expecteds[i] = 0;
		}
		expecteds[0] = 1;
		BigLongPublicTest.check_negate(a, expecteds);
	}

	// ---------- subtract ----------
	@Test(timeout = 666)
	public void pubTest__subtract__smallParts_noCarry() {
		for (int pass = 0; pass < 42; pass++) {
			int[] a = new int[SIZE], b = new int[SIZE], expecteds = new int[SIZE];
			for (int i = 0; i < SIZE; i++) {
				a[i] = 1 + RND.nextInt(Integer.MAX_VALUE);
				b[i] = a[i] - RND.nextInt(a[i]);
				expecteds[i] = a[i] - b[i];
			}
			BigLongPublicTest.check_subtract(a, b, expecteds);
		}
	}

	@Test(timeout = 666)
	public void pubTest__subtract__2_sub_5_eq_m3_PUBLIC_TEST() {
		int[] a = new int[SIZE], b = new int[SIZE], expecteds = new int[SIZE];
		a[0] = 2;
		b[0] = 5;
		for (int i = 0; i < SIZE; i++) {
			expecteds[i] = 0x7FFFFFFF;
		}
		expecteds[0] = 0x7FFFFFFD;
		BigLongPublicTest.check_subtract(a, b, expecteds);
	}

	// ---------- toString ----------
	@Test(timeout = 666)
	public void pubTest__toString__1() {
		int[] a = new int[SIZE];
		a[0] = 1;
		BigLongPublicTest.check_toString(a, "1");
	}

	@Test(timeout = 666)
	public void pubTest__toString__MIN_VALUE() {
		int[] a = new int[SIZE];
		a[SIZE - 1] = 0x40000000;
		BigLongPublicTest.check_toString(a, "-226156424291633194186662080095093570025917938800079226639565593765455331328");
	}

	// ========== HELPER ==========
	protected static final String msg(String ex, int[] a, int[] b, int[] r, String msg) {
		return ex + "(" + Arrays.toString(a) + ", " + Arrays.toString(b) + ") = " + Arrays.toString(r) + "): " + msg;
	}

	protected static final String msg(String ex, int[] a, int[] r, String msg) {
		return ex + "(" + Arrays.toString(a) + ") = " + Arrays.toString(r) + "): " + msg;
	}

	protected static final String msg(String ex, int[] a, String r, String msg) {
		return ex + "(" + Arrays.toString(a) + ") = " + r + "): " + msg;
	}

	protected static final void check_add(int[] a, int[] b, int[] expecteds) {
		int[] aCopy = Arrays.copyOf(a, a.length);
		int[] bCopy = Arrays.copyOf(b, b.length);
		int[] actuals = BigLong.add(a, b);
		assertArrayEquals(msg(BigLongPublicTest.EX_NAME_add, a, b, expecteds, "a changed illegally."), aCopy, a);
		assertArrayEquals(msg(BigLongPublicTest.EX_NAME_add, a, b, expecteds, "b changed illegally."), bCopy, b);
		assertArrayEquals(msg(BigLongPublicTest.EX_NAME_add, a, b, expecteds, "failed."), expecteds, actuals);
	}

	protected static final void check_negate(int[] a, int[] expecteds) {
		int[] aCopy = Arrays.copyOf(a, a.length);
		int[] actuals = BigLong.negate(a);
		assertArrayEquals(msg(BigLongPublicTest.EX_NAME_negate, a, expecteds, "a changed illegally."), aCopy, a);
		assertArrayEquals(msg(BigLongPublicTest.EX_NAME_negate, a, expecteds, "failed."), expecteds, actuals);
	}

	protected static final void check_subtract(int[] a, int[] b, int[] expecteds) {
		int[] aCopy = Arrays.copyOf(a, a.length);
		int[] bCopy = Arrays.copyOf(b, b.length);
		int[] actuals = BigLong.subtract(a, b);
		assertArrayEquals(msg(BigLongPublicTest.EX_NAME_subtract, a, b, expecteds, "a changed illegally."), aCopy, a);
		assertArrayEquals(msg(BigLongPublicTest.EX_NAME_subtract, a, b, expecteds, "b changed illegally."), bCopy, b);
		assertArrayEquals(msg(BigLongPublicTest.EX_NAME_subtract, a, b, expecteds, "failed."), expecteds, actuals);
	}

	protected static final void check_toString(int[] a, String expecteds) {
		int[] aCopy = Arrays.copyOf(a, a.length);
		String actuals = BigLong.toString(a);
		assertArrayEquals(BigLongPublicTest.msg(BigLongPublicTest.EX_NAME_toString, a, expecteds, "a changed illegally."), aCopy, a);
		assertEquals(BigLongPublicTest.msg(BigLongPublicTest.EX_NAME_toString, a, expecteds, "failed."), expecteds, actuals);
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