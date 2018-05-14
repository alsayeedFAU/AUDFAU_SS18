
public class Catalan {
	static long cNEnt(int n) {
		long tmp = 1;
		for (int i = 1; i <= n; i++) {
			tmp = ((4 * (i - 1) + 2) * tmp) / (i + 1);
		}
		return tmp;
	}

	static long cn(int n) {
		return n == 0 ? 1 : (4 * (n - 1) + 2) * cn(n - 1) / (n + 1);
	}

	static long cnEnd(int start, int n, long next) {
		if (start == 0 || start == 1) {
			return cnEnd(start + 1, n, 1);
		} else if (start > n) {
			return next;
		}
		long l = ((next * (4 * (start - 1) + 2)) / (start + 1));
		return cnEnd(start + 1, n, l);
	}
}
