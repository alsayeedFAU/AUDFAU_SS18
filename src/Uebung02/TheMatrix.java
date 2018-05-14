import java.lang.reflect.Array;
import java.util.Arrays;

public class TheMatrix {

	public static int maxLength(double[][] ds) {
		if (ds == null) {
			return 0;
		}
		int i = 0;
		for (double[] d : ds) {
			if (d != null && d.length > i) {
				i = d.length;
			}
		}
		return i;
	}

	public static int[] maxLength(double[][][] ds) {
		if (ds == null) {
			return new int[] { 0, 0, 0 };
		}
		int[] tmp = new int[3];
		tmp[0] = ds.length;

		for (double[][] d1 : ds) {
			if (d1 != null) {
				if (d1.length > tmp[1]) {
					tmp[1] = d1.length;
				}
				for (double[] d2 : d1) {
					if (d2 != null && d2.length > tmp[2]) {
						tmp[2] = d2.length;
					}
				}
			}
		}
		return tmp;
	}

	public static double[] sumInRow(double[][] ds) {
		if (ds == null) {
			return new double[] {};
		}
		double tmp[] = new double[ds.length];
		if (tmp.length == 0) {
			return tmp;
		}

		for (int i = 0; i < ds.length; i++) {
			double d = 0;
			if (ds[i] != null) {
				for (double d1 : ds[i]) {
					d += d1;
				}

			}
			tmp[i] = d;
		}
		return tmp;
	}

	public static double[] sumInColumn(double[][] ds) {
		if (ds == null) {
			return new double[] {};
		}
		double tmp[] = new double[maxLength(ds)];

		for (int i = 0; i < ds.length; i++) {
			for (int j = 0; ds[i] != null && j < ds[i].length; j++) {
				tmp[j] += ds[i][j];
			}
		}
		return tmp;
	}

	public static double[][] fold(double[][][] ds, Dimension row) {
		if (ds == null) {
			return new double[][] {};
		}
		double[][] tmp = null;

		switch (row) {
		case LAYER:
			tmp = new double[maxLength(ds)[0]][maxLength(ds)[1]];

			for (int i = 0; i < maxLength(ds)[0]; i++) {
				for (int j = 0; j < maxLength(ds)[1]; j++) {
					if (ds[i] != null && ds[i][j] != null) {
						for (double d : ds[i][j]) {
							tmp[i][j] += d;
						}
					}
				}
			}
			break;
		case ROW:
			tmp = new double[maxLength(ds)[1]][maxLength(ds)[2]];
			for (int i = 0; i < maxLength(ds)[0]; i++) {
				for (int j = 0; j < maxLength(ds)[1]; j++) {
					if (ds[i] != null && ds[i][j] != null) {
						for (int k = 0; k < maxLength(ds)[2]; k++) {
							tmp[j][k] += ds[i][j][k];
						}
					}
				}
			}

			break;
		case COLUMN:
			tmp = new double[maxLength(ds)[0]][maxLength(ds)[2]];

			for (int i = 0; i < maxLength(ds)[0]; i++) {
				for (int j = 0; j < maxLength(ds)[1]; j++) {
					if (ds[i] != null && ds[i][j] != null) {
						for (int k = 0; k < maxLength(ds)[2]; k++) {
							tmp[i][k] += ds[i][j][k];
							if (k == 0 && j == 0) {
								System.out.println(tmp[k][j]);
							}
						}
					}
				}
			}

			break;

		}

		return tmp;
	}

}
