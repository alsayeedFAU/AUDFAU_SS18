import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

public class TheMatrixPublicTest {
	// ========== SYSTEM ==========
	protected static final String EX_NAME_maxLength2D = "maxLength[2D]";
	protected static final String EX_NAME_maxLength3D = "maxLength[3D]";
	protected static final String EX_NAME_sumInRow = "sumInRow";
	protected static final String EX_NAME_sumInColumn = "sumInColumn";
	protected static final String EX_NAME_fold = "fold";
	// --------------------
	// ========== TEST-DATA ==========
	private static final Random RND = new Random(4711_0815_666L);

	// ========== PUBLIC TEST ==========
	@Test(timeout = 666)
	public void pubTest__maxLength__2D__null__empty() {
		assertEquals(TheMatrixPublicTest.EX_NAME_maxLength2D + " failed.", 0, TheMatrix.maxLength((double[][]) null));
		assertEquals(TheMatrixPublicTest.EX_NAME_maxLength2D + " failed.", 0, TheMatrix.maxLength(new double[0][]));
		for (int pass = 0; pass < 42; pass++) {
			double[][] m = new double[1 + RND.nextInt(666)][];
			int actual = TheMatrix.maxLength(m);
			assertEquals(TheMatrixPublicTest.EX_NAME_maxLength2D + " failed.", 0, actual);
		}
		for (int pass = 0; pass < 42; pass++) {
			double[][] m = new double[1 + RND.nextInt(666)][0];
			int actual = TheMatrix.maxLength(m);
			assertEquals(TheMatrixPublicTest.EX_NAME_maxLength2D + " failed.", 0, actual);
		}
	}

	@Test(timeout = 666)
	public void pubTest__maxLength__2D__nonNull_nonEmpty() {
		for (int pass = 0; pass < 42; pass++) {
			int expected = 0, dim1 = 1 + RND.nextInt(666), dim2;
			double[][] m = new double[dim1][];
			for (int row = 0; row < dim1; row++) {
				dim2 = 1 + RND.nextInt(666);
				m[row] = new double[dim2];
				expected = expected < dim2 ? dim2 : expected;
			}
			int actual = TheMatrix.maxLength(m);
			assertEquals(TheMatrixPublicTest.EX_NAME_maxLength2D + " failed.", expected, actual);
		}
	}

	// ----------------------------------------------------------------------------------------------------
	@Test(timeout = 6666)
	public void pubTest__maxLength__3D__null__empty() {
		assertArrayEquals(TheMatrixPublicTest.EX_NAME_maxLength3D + " failed.", new int[] { 0, 0, 0 }, TheMatrix.maxLength((double[][][]) null));
		assertArrayEquals(TheMatrixPublicTest.EX_NAME_maxLength3D + " failed.", new int[] { 0, 0, 0 }, TheMatrix.maxLength(new double[0][][]));
		for (int pass = 0; pass < 42; pass++) {
			int dim1 = 1 + RND.nextInt(666);
			double[][][] m = new double[dim1][][];
			int[] actuals = TheMatrix.maxLength(m);
			assertArrayEquals(TheMatrixPublicTest.EX_NAME_maxLength3D + " failed.", new int[] { dim1, 0, 0 }, actuals);
		}
		for (int pass = 0; pass < 42; pass++) {
			int dim1 = 1 + RND.nextInt(666);
			double[][][] m = new double[dim1][0][];
			int[] actuals = TheMatrix.maxLength(m);
			assertArrayEquals(TheMatrixPublicTest.EX_NAME_maxLength3D + " failed.", new int[] { dim1, 0, 0 }, actuals);
		}
		for (int pass = 0; pass < 42; pass++) {
			int dim1 = 1 + RND.nextInt(666), dim2 = 1 + RND.nextInt(666);
			double[][][] m = new double[dim1][dim2][];
			int[] actuals = TheMatrix.maxLength(m);
			assertArrayEquals(TheMatrixPublicTest.EX_NAME_maxLength3D + " failed.", new int[] { dim1, dim2, 0 }, actuals);
		}
		for (int pass = 0; pass < 42; pass++) {
			int dim1 = 1 + RND.nextInt(666), dim2 = 1 + RND.nextInt(666);
			double[][][] m = new double[dim1][dim2][0];
			int[] actuals = TheMatrix.maxLength(m);
			assertArrayEquals(TheMatrixPublicTest.EX_NAME_maxLength3D + " failed.", new int[] { dim1, dim2, 0 }, actuals);
		}
	}

	@Test(timeout = 6666)
	public void pubTest__maxLength__3D__nonNull_nonEmpty() {
		for (int pass = 0; pass < 42; pass++) {
			int[] expecteds = new int[3];
			int dim1 = 1 + RND.nextInt(42), dim2, dim3;
			expecteds[0] = dim1;
			double[][][] m = new double[dim1][][];
			for (int row = 0; row < dim1; row++) {
				dim2 = 1 + RND.nextInt(42);
				expecteds[1] = expecteds[1] < dim2 ? dim2 : expecteds[1];
				m[row] = new double[dim2][];
				for (int col = 0; col < dim2; col++) {
					dim3 = 1 + RND.nextInt(42);
					expecteds[2] = expecteds[2] < dim3 ? dim3 : expecteds[2];
					m[row][col] = new double[dim3];
				}
			}
			int[] actuals = TheMatrix.maxLength(m);
			assertArrayEquals(TheMatrixPublicTest.EX_NAME_maxLength3D + " failed.", expecteds, actuals);
		}
	}

	// ----------------------------------------------------------------------------------------------------
	@Test(timeout = 666)
	public void pubTest__sumInRow__null_empty() {
		assertArrayEquals(TheMatrixPublicTest.EX_NAME_sumInRow + " failed.", new double[0], TheMatrix.sumInRow((double[][]) null), 1e-42);
		assertArrayEquals(TheMatrixPublicTest.EX_NAME_sumInRow + " failed.", new double[0], TheMatrix.sumInRow(new double[0][]), 1e-42);
		assertArrayEquals(TheMatrixPublicTest.EX_NAME_sumInRow + " failed.", new double[0], TheMatrix.sumInRow(new double[0][0]), 1e-42);
		for (int pass = 0; pass < 3; pass++) {
			double[][] m = { //
					{ 0, 1, 2, 3, 4, 5, 6 }, //
					{ 7, 8, 9, 10, 11 }, //
					{ 12, 13, 14, 15, 16, 17 } //
			};
			double[] expecteds = { 21, 45, 87 };
			m[pass % 3] = pass < 3 ? null : new double[0];
			expecteds[pass % 3] = 0;
			double[] actuals = TheMatrix.sumInRow(m);
			assertArrayEquals(TheMatrixPublicTest.EX_NAME_sumInRow + " failed.", expecteds, actuals, 1e-42);
		}
	}

	@Test(timeout = 666)
	public void pubTest__sumInRow__exercise_sheet_example() {
		double[][] m = { //
				{ 0, 1, 2, 3, 4, 5, 6 }, //
				{ 7, 8, 9, 10, 11 }, //
				{ 12, 13, 14, 15, 16, 17 } //
		};
		double[] expecteds = { 21, 45, 87 };
		double[] actuals = TheMatrix.sumInRow(m);
		assertArrayEquals(TheMatrixPublicTest.EX_NAME_sumInRow + " failed.", expecteds, actuals, 1e-42);
	}

	// ----------------------------------------------------------------------------------------------------
	@Test(timeout = 666)
	public void pubTest__sumInColumn__null_empty() {
		assertArrayEquals(TheMatrixPublicTest.EX_NAME_sumInColumn + " failed.", new double[0], TheMatrix.sumInRow((double[][]) null), 1e-42);
		assertArrayEquals(TheMatrixPublicTest.EX_NAME_sumInColumn + " failed.", new double[0], TheMatrix.sumInRow(new double[0][]), 1e-42);
		assertArrayEquals(TheMatrixPublicTest.EX_NAME_sumInColumn + " failed.", new double[0], TheMatrix.sumInRow(new double[0][0]), 1e-42);
		for (int pass = 0; pass < 3; pass++) {
			double[][] m = { //
					{ 0, 1, 2, 3, 4, 5, 6 }, //
					{ 7, 8, 9, 10, 11 }, //
					{ 12, 13, 14, 15, 16, 17 } //
			};
			m[pass % 3] = pass < 3 ? null : new double[0];
			double[] expecteds = new double[][] { //
					{ 19 - 0, 22 - 1, 25 - 2, 28 - 3, 31 - 4, 22 - 5 }, //
					{ 19 - 7, 22 - 8, 25 - 9, 28 - 10, 31 - 11, 22, 6 }, //
					{ 19 - 12, 22 - 13, 25 - 14, 28 - 15, 31 - 16, 22 - 17, 6 } //
			}[pass % 3];
			double[] actuals = TheMatrix.sumInColumn(m);
			assertArrayEquals(TheMatrixPublicTest.EX_NAME_sumInColumn + " failed.", expecteds, actuals, 1e-42);
		}
	}

	@Test(timeout = 666)
	public void pubTest__sumInColumn__exercise_sheet_example() {
		double[][] m = { //
				{ 0, 1, 2, 3, 4, 5, 6 }, //
				{ 7, 8, 9, 10, 11 }, //
				{ 12, 13, 14, 15, 16, 17 } //
		};
		double[] expecteds = { 19, 22, 25, 28, 31, 22, 6 };
		double[] actuals = TheMatrix.sumInColumn(m);
		assertArrayEquals(TheMatrixPublicTest.EX_NAME_sumInColumn + " failed.", expecteds, actuals, 1e-42);
	}

	// ----------------------------------------------------------------------------------------------------
	@Test(timeout = 666)
	public void pubTest__fold__null_empty() {
		assertDeepArrayEquals(TheMatrixPublicTest.EX_NAME_fold + " failed.", new double[0][0], TheMatrix.fold((double[][][]) null, Dimension.ROW), 1e-42);
		assertDeepArrayEquals(TheMatrixPublicTest.EX_NAME_fold + " failed.", new double[0][0], TheMatrix.fold(new double[0][][], Dimension.ROW), 1e-42);
		assertDeepArrayEquals(TheMatrixPublicTest.EX_NAME_fold + " failed.", new double[0][0], TheMatrix.fold(new double[1][][], Dimension.ROW), 1e-42);
		assertDeepArrayEquals(TheMatrixPublicTest.EX_NAME_fold + " failed.", new double[0][0], TheMatrix.fold(new double[1][0][], Dimension.ROW), 1e-42);
		assertDeepArrayEquals(TheMatrixPublicTest.EX_NAME_fold + " failed.", new double[1][0], TheMatrix.fold(new double[1][1][], Dimension.ROW), 1e-42);
		assertDeepArrayEquals(TheMatrixPublicTest.EX_NAME_fold + " failed.", new double[1][0], TheMatrix.fold(new double[1][1][0], Dimension.ROW), 1e-42);
		// -----
		assertDeepArrayEquals(TheMatrixPublicTest.EX_NAME_fold + " failed.", new double[0][0], TheMatrix.fold((double[][][]) null, Dimension.COLUMN), 1e-42);
		assertDeepArrayEquals(TheMatrixPublicTest.EX_NAME_fold + " failed.", new double[0][0], TheMatrix.fold(new double[0][][], Dimension.COLUMN), 1e-42);
		assertDeepArrayEquals(TheMatrixPublicTest.EX_NAME_fold + " failed.", new double[1][0], TheMatrix.fold(new double[1][][], Dimension.COLUMN), 1e-42);
		assertDeepArrayEquals(TheMatrixPublicTest.EX_NAME_fold + " failed.", new double[1][0], TheMatrix.fold(new double[1][0][], Dimension.COLUMN), 1e-42);
		assertDeepArrayEquals(TheMatrixPublicTest.EX_NAME_fold + " failed.", new double[1][0], TheMatrix.fold(new double[1][1][], Dimension.COLUMN), 1e-42);
		assertDeepArrayEquals(TheMatrixPublicTest.EX_NAME_fold + " failed.", new double[1][0], TheMatrix.fold(new double[1][1][0], Dimension.COLUMN), 1e-42);
		// -----
		assertDeepArrayEquals(TheMatrixPublicTest.EX_NAME_fold + " failed.", new double[0][0], TheMatrix.fold((double[][][]) null, Dimension.LAYER), 1e-42);
		assertDeepArrayEquals(TheMatrixPublicTest.EX_NAME_fold + " failed.", new double[0][0], TheMatrix.fold(new double[0][][], Dimension.LAYER), 1e-42);
		assertDeepArrayEquals(TheMatrixPublicTest.EX_NAME_fold + " failed.", new double[1][0], TheMatrix.fold(new double[1][][], Dimension.LAYER), 1e-42);
		assertDeepArrayEquals(TheMatrixPublicTest.EX_NAME_fold + " failed.", new double[1][0], TheMatrix.fold(new double[1][0][], Dimension.LAYER), 1e-42);
		assertDeepArrayEquals(TheMatrixPublicTest.EX_NAME_fold + " failed.", new double[1][1], TheMatrix.fold(new double[1][1][], Dimension.LAYER), 1e-42);
		assertDeepArrayEquals(TheMatrixPublicTest.EX_NAME_fold + " failed.", new double[1][1], TheMatrix.fold(new double[1][1][0], Dimension.LAYER), 1e-42);
	}

	@Test(timeout = 666)
	public void pubTest__fold__ROW__exercise_sheet_example() {
		double[][][] m = new double[4][5][3];
		long v = 0;
		for (int row = 0; row < m.length; row++) {
			for (int col = 0; col < m[row].length; col++) {
				for (int lay = 0; lay < m[row][col].length; lay++) {
					m[row][col][lay] = v++;
				}
			}
		}
		double[][] expecteds = new double[5][3];
		long expV = 90;
		for (int row = 0; row < expecteds.length; row++) {
			for (int col = 0; col < expecteds[row].length; col++) {
				expecteds[row][col] = expV;
				expV += 4;
			}
		}
		double[][] actuals = TheMatrix.fold(m, Dimension.ROW);
		assertDeepArrayEquals(TheMatrixPublicTest.EX_NAME_fold + " failed.", expecteds, actuals, 1e-42);
	}

	@Test(timeout = 666)
	public void pubTest__fold__COLUMN__exercise_sheet_example() {
		double[][][] m = new double[4][5][3];
		long v = 0;
		for (int row = 0; row < m.length; row++) {
			for (int col = 0; col < m[row].length; col++) {
				for (int lay = 0; lay < m[row][col].length; lay++) {
					m[row][col][lay] = v++;
				}
			}
		}
		double[][] expecteds = new double[4][3];
		long expV = 30;
		for (int row = 0; row < expecteds.length; row++) {
			for (int col = 0; col < expecteds[row].length; col++) {
				expecteds[row][col] = expV;
				expV += 5;
			}
			expV += 60;
		}
		double[][] actuals = TheMatrix.fold(m, Dimension.COLUMN);
		assertDeepArrayEquals(TheMatrixPublicTest.EX_NAME_fold + " failed.", expecteds, actuals, 1e-42);
	}

	@Test(timeout = 666)
	public void pubTest__fold__LAYER__exercise_sheet_example() {
		double[][][] m = new double[4][5][3];
		long v = 0;
		for (int row = 0; row < m.length; row++) {
			for (int col = 0; col < m[row].length; col++) {
				for (int lay = 0; lay < m[row][col].length; lay++) {
					m[row][col][lay] = v++;
				}
			}
		}
		double[][] expecteds = new double[4][5];
		long expV = 3;
		for (int row = 0; row < expecteds.length; row++) {
			for (int col = 0; col < expecteds[row].length; col++) {
				expecteds[row][col] = expV;
				expV += 9;
			}
		}
		double[][] actuals = TheMatrix.fold(m, Dimension.LAYER);
		assertDeepArrayEquals(TheMatrixPublicTest.EX_NAME_fold + " failed.", expecteds, actuals, 1e-42);
	}

	// ========== HELPER ==========
	protected static final void assertDeepArrayEquals(String message, double[][] expecteds, double[][] actuals, double delta) {
		assertNotNull(message + " [null check expecteds]", expecteds);
		assertNotNull(message + " [null check actuals]", actuals);
		assertEquals(message + " [dim1 check]", expecteds.length, actuals.length);
		for (int row = 0; row < expecteds.length; row++) {
			assertNotNull(message + " [null check expecteds at [" + row + "]]", expecteds);
			assertNotNull(message + " [null check actuals at [" + row + "]]", actuals);
			assertEquals(message + " [dim2 check]", expecteds[row].length, actuals[row].length);
			for (int col = 0; col < expecteds[row].length; col++) {
				assertEquals(message + " [content check at [" + row + "][" + col + "]]", expecteds[row][col], actuals[row][col], delta);
			}
		}
	}
}