public class Zeitrechner {
	public static long sekunden(int h11, int m12, int s13, int h21, int m22, int s23) {
		if (h11 < 0 || h11 > 23) {
			return -11;
		}

		if (m12 < 0 || m12 > 59) {
			return -12;
		}

		if (s13 < 0 || s13 > 59) {
			return -13;
		}

		if (h21 < 0 || h21 > 23) {
			return -21;
		}

		if (m22 < 0 || m22 > 59) {
			return -22;
		}

		if (s23 < 0 || s23 > 59) {
			return -23;
		}

		long s = 0;

		if (h11 > h21 || h11 == h21 && m12 > m22 || h11 == h21 && m12 == m22 && s13 > s23) {
			s = 24 * 60 * 60 - ((((h11 - h21) * 60 + (m12 - m22)) * 60) + (s13 - s23));
		} else {
			s = ((((h11 - h21) * 60) + (m12 - m22)) * 60) + (s13 - s23);
		}

		return s < 0 ? s * -1 : s;
	}
}