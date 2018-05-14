
public class CollatzProblem {

	public static void f(CollatzSeq cs, long n, int x) {
		if (n != (x * x * x) * 4) {
			cs.add(n);
			if (n % 2 == 0) {
				f(cs, n / 2, x);
			} else {
				long l = (long) Math.pow(3, x);
				f(cs, (3 * n) + l, x);
			}
		}
	}
}
