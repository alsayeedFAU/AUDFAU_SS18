import java.util.Arrays;

public class KochSnowflake {

	public static final double SIN60 = Math.sin(Math.PI / 3);
	public static final double COS60 = Math.cos(Math.PI / 3);

	public static double[] getAE(double i, double j, double k, double l) {
		return new double[] { k - i, l - j };
	}

	public static double[] getB(double i, double j, double[] ds) {
		return new double[] { i + (ds[0] / 3), j + (ds[1] / 3) };
	}

	public static double[] getD(double i, double j, double[] ds) {
		return new double[] { (i + (ds[0] / 3 * 2)), (j + (ds[1] / 3 * 2))};
	}

	public static double[] getBC(double[] ds) {
		return new double[] { (ds[0] * COS60 - ds[1] * SIN60) / 3, (ds[1] * COS60 + ds[0] * SIN60) / 3 };
	}

	public static double[] getC(double i, double j, double[] ds) {
		return new double[] { ds[0] + i, ds[1] + j };
	}

	public static double[] bendSegment(double i, double j, double k, double l) {
		return new double[] { i, j, getB(i, j, getAE(i, j, k, l))[0], getB(i, j, getAE(i, j, k, l))[1],
				getC(getB(i, j, getAE(i, j, k, l))[0], getB(i, j, getAE(i, j, k, l))[1], getBC(getAE(i, j, k, l)))[0],
				getC(getB(i, j, getAE(i, j, k, l))[0], getB(i, j, getAE(i, j, k, l))[1], getBC(getAE(i, j, k, l)))[1],
				getD(i, j, getAE(i, j, k, l))[0], getD(i, j, getAE(i, j, k, l))[1], k, l

		};
	}

	public static void draw(KochSnowflakeCanvas sc, double i, double j, double k, double l, int iters) {
		if (iters == 0) {
			sc.drawLine(i, j, k, l);
		} else {
			double[] tmp = bendSegment(i, j, k, l);
			System.out.println(Arrays.toString(tmp));
			draw(sc, tmp[0], tmp[1], tmp[2], tmp[3], iters - 1);
			draw(sc, tmp[2], tmp[3], tmp[4], tmp[5], iters - 1);
			draw(sc, tmp[4], tmp[5], tmp[6], tmp[7], iters - 1);
			draw(sc, tmp[6], tmp[7], tmp[8], tmp[9], iters - 1);
		}
	}
}
