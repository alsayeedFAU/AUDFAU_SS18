
public class RegulaereKettenBrueche {

	public static int[] konvRat(RekursiveBrueche bb, int z, int n) {
		if (n == 1) {
			return new int[] { z };
		}
		int[] tmp = konvRat(bb, n, z % n);
		bb.ergaenze(z, tmp);
		int[] t = new int[tmp.length + 1];
		t[0] = z / n;
		for (int i = 0; i < tmp.length; i++) {
			t[i + 1] = tmp[i];
		}
		return t;
	}

	public static long[] konvPseudoIrrat(RekursiveBrueche bb, double x, int l) {
		if (l == 1) {
			return new long[] { Math.round((1 / x)) };
		}
		long[] tmp = konvPseudoIrrat(bb, x > 1 ? x % 1 : (1 / x) % 1, l - 1);
		bb.ergaenze(Math.round(x), tmp);  //x > 1 ? (long) x / 1 : (long) (1 / x), tmp);
		long[] t = new long[tmp.length + 1];
		t[0] =Math.round(x > 1 ? (long) x / 1 : (long) (1 / x)); //x > 1 ? (long) x / 1 : (long) (1 / x);
		for (int i = 0; i < tmp.length; i++) {
			t[i + 1] = tmp[i];
		}
		return t;
	}

}
