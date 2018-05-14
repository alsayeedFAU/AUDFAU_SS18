import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

public class StringToLongSqueezerPublicTest {
	// ========== SYSTEM ==========
	protected static final String EX_NAME_squeezeBlock = "squeezeBlock";
	protected static final String EX_NAME_squeeze = "squeeze";
	protected static final String EX_NAME_stretchBlock = "stretchBlock";
	protected static final String EX_NAME_stretch = "stretch";
	// --------------------
	// ========== TEST-DATA ==========
	private static final Random RND = new Random(4711_0815_666L);

	// ========== PUBLIC TEST ==========
	@Test(timeout = 666)
	public void pubTest__squeezeBlock() {
		for (int pass = 0; pass < 42; pass++) {
			int c = RND.nextInt(26);
			StringToLongSqueezerPublicTest.check("a " + (char) ('a' + c) + " z", 107405245646019036L + c * 2916);
		}
		for (int pass = 0; pass < 42; pass++) {
			int c = RND.nextInt(26);
			StringToLongSqueezerPublicTest.check("a " + (char) ('A' + c) + " z", 107405245646019036L + (c + 28) * 2916);
		}
		for (int pass = 0; pass < 42; pass++) {
			int c = RND.nextInt(26);
			StringToLongSqueezerPublicTest.check("ab " + (char) ('a' + c) + " yz" + "fuuuuuuuuuu", 79539483212997822L + c * 157464);
		}
		for (int pass = 0; pass < 42; pass++) {
			int c = RND.nextInt(26);
			StringToLongSqueezerPublicTest.check("ab " + (char) ('A' + c) + " yz" + "fuuuuuuuuuu", 79539483212997822L + (c + 28) * 157464);
		}
	}

	// ----------------------------------------------------------------------------------------------------
	@Test(timeout = 666)
	public void pubTest__squeeze() {
		for (int pass = 0; pass < 42; pass++) {
			int c = RND.nextInt(26);
			StringToLongSqueezerPublicTest.check("AuD macht jetzt VIEL mehr Spass mit " + (char) ('a' + c), new long[] { 102895113158105248L, 154631021705126613L, 70298742949666730L, 107404575670431342L + c * 24794911296L });
		}
		for (int pass = 0; pass < 42; pass++) {
			int c = RND.nextInt(26);
			StringToLongSqueezerPublicTest.check("AuD macht jetzt VIEL mehr Spass mit " + (char) ('A' + c), new long[] { 102895113158105248L, 154631021705126613L, 70298742949666730L, 107404575670431342L + (c + 28) * 24794911296L });
		}
	}

	// ----------------------------------------------------------------------------------------------------
	@Test(timeout = 666)
	public void pubTest__stretchBlock() {
		for (int pass = 0; pass < 42; pass++) {
			int c = RND.nextInt(26);
			StringToLongSqueezerPublicTest.check(107405245646019036L + c * 2916, "a " + (char) ('a' + c) + " z");
		}
		for (int pass = 0; pass < 42; pass++) {
			int c = RND.nextInt(26);
			StringToLongSqueezerPublicTest.check(107405245646019036L + (c + 28) * 2916, "a " + (char) ('A' + c) + " z");
		}
		for (int pass = 0; pass < 42; pass++) {
			int c = RND.nextInt(26);
			StringToLongSqueezerPublicTest.check(79539483212997822L + c * 157464, "ab " + (char) ('a' + c) + " yz" + "fuu");
		}
		for (int pass = 0; pass < 42; pass++) {
			int c = RND.nextInt(26);
			StringToLongSqueezerPublicTest.check(79539483212997822L + (c + 28) * 157464, "ab " + (char) ('A' + c) + " yz" + "fuu");
		}
	}

	// ----------------------------------------------------------------------------------------------------
	@Test(timeout = 666)
	public void pubTest__stretch() {
		for (int pass = 0; pass < 42; pass++) {
			int c = RND.nextInt(26);
			StringToLongSqueezerPublicTest.check(new long[] { 102895113158105248L, 154631021705126613L, 70298742949666730L, 107404575670431342L + c * 24794911296L }, "AuD macht jetzt VIEL mehr Spass mit " + (char) ('a' + c));
		}
		for (int pass = 0; pass < 42; pass++) {
			int c = RND.nextInt(26);
			StringToLongSqueezerPublicTest.check(new long[] { 102895113158105248L, 154631021705126613L, 70298742949666730L, 107404575670431342L + (c + 28) * 24794911296L }, "AuD macht jetzt VIEL mehr Spass mit " + (char) ('A' + c));
		}
	}

	// ========== HELPER ==========
	protected static final void check(String input, long expected) {
		long actual = StringToLongSqueezer.squeezeBlock(input);
		assertEquals(StringToLongSqueezerPublicTest.EX_NAME_squeezeBlock + "(\"" + input + "\")", expected, actual);
	}

	protected static final void check(String input, long[] expected) {
		long[] actual = StringToLongSqueezer.squeeze(input);
		System.out.println("ex:" + expected.length + "  act:" + actual.length);
		for(int i = 0; i < expected.length; i++){
			System.out.println(actual[i]);
			System.out.println(expected[i] - actual[i]);
		}
		assertArrayEquals(StringToLongSqueezerPublicTest.EX_NAME_squeeze + "(\"" + input + "\")", expected, actual);
	}

	protected static final void check(long input, String expected) {
		String actual = StringToLongSqueezer.stretchBlock(input);
		assertEquals(StringToLongSqueezerPublicTest.EX_NAME_stretchBlock + "(\"" + input + "\")", expected, actual);
	}

	protected static final void check(long[] input, String expected) {
		String actual = StringToLongSqueezer.stretch(input);
		assertEquals(StringToLongSqueezerPublicTest.EX_NAME_stretch + "(\"" + Arrays.toString(input) + "\")", expected, actual);
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