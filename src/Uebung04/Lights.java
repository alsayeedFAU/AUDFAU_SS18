
public class Lights {

	public static void push(boolean[] lightsClone, int i) {
		if (i >= 0 && i < lightsClone.length) {
			lightsClone[i] ^= true;
		}
		if (i > 0) {
			lightsClone[i - 1] ^= true;
		}
		if (i + 1 < lightsClone.length) {
			lightsClone[i + 1] ^= true;
		}
	}

	public static int[] solve(LightsTester l, boolean[] lichter, int i) {
		if (i < lichter.length) {
			push(lichter, i);
			int[] ergebnis = solve(l, lichter, i + 1);
			if (ergebnis != null) {
				push(lichter, i);
				int[] next = new int[ergebnis.length + 1];
				for (int k = 1; k < next.length; k++) {
					next[k] = ergebnis[k - 1];
				}
				next[0] = i;
				return next;
			}
			push(lichter, i);
			return solve(l, lichter, i + 1);
		} else {
			if (l.test(lichter)) {
				int[] in = new int[] {};
				return in;
			}
			return null;
		}
	}

}
