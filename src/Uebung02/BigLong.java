public class BigLong {

	public static int[] add(int[] a, int[] b) {
		int[] tmp = new int[8];
		boolean bool = false;
		for (int i = 0; i < 8; i++) {
			if (a[i] + b[i] < 0) {
				tmp[i] = Integer.MAX_VALUE + (a[i] + b[i]) + 1;
				if (bool) {
					tmp[i] += 1;
				}
				bool = true;
			} else {
				tmp[i] = a[i] + b[i];
				if (bool) {
					tmp[i] += 1;
					bool = false;
				}
			}
		}
		return tmp;
	}

	public static int[] negate(int[] a) {
		int[] tmp = new int[a.length];
		for (int i = 0; i < tmp.length; i++) {
			//tmp[i] = Integer.MAX_VALUE - a[i];
			tmp[i] = Integer.MAX_VALUE - a[i];
		}
		tmp[0] += 1;

		return tmp;
	}

	public static int[] subtract(int[] a, int[] b) {
		int[] tmp = new int[8];
		boolean bool = false;
		for (int i = 0; i < 8; i++) {
			if (a[i] - b[i] < 0) {
				tmp[i] = Integer.MAX_VALUE + (a[i] - b[i]) + 1;
				bool = true;
			} else {
				tmp[i] = a[i] - b[i];
				if (bool) {
					tmp[i] = Integer.MAX_VALUE - (a[i] - b[i]);
				}
			}

		}
		return tmp;
	}

	public static String toString(int[] a) {
		String tmp = "";
		for (int i = 7; i >= 0; i--) {
			if (a[i] != 0)
				tmp += a[i];

		}
		return tmp;
	}

}
