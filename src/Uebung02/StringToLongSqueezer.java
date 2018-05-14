
public class StringToLongSqueezer {

	public static long squeezeBlock(String input) {
		String s = input;
		if (input.length() != 10) {
			for (int i = 0; i < 10 - input.length(); i++) {
				s += '!';
			}
		}
		
		
		System.out.println(s);
		
		long tmp = 0;
		for (int i = 0; i < 10; i++) {
			if (s.charAt(i) <= 'Z' && s.charAt(i) >= 'A') {
				tmp += (s.charAt(i) - 37) * p(i);
			} else if (s.charAt(i) <= 'z' && s.charAt(i) >= 'a') {
				tmp += (s.charAt(i) - 97) * p(i);
			} else {
				if (s.charAt(i) == ' ') {
					tmp += 26 * p(i);
				} else if (s.charAt(i) == '!') {
					tmp += 27 * p(i);
				}
			}
		}
		return tmp;
	}

	public static long[] squeeze(String input) {
		long[] tmp = input.length() % 10 == 0 ? new long[input.length() / 10] : new long[input.length() / 10 + 1];

		String s = input;
		for (int i = 0; i < tmp.length; i++) {
			if (input.length() >= 10 * (i + 1)) {
				tmp[i] = squeezeBlock(s.substring(10 * i, 10 * (i + 1)));
			} else {
				tmp[i] = squeezeBlock(input.substring(10 * i, s.length()));
			}
		}
		return tmp;
	}

	public static String stretchBlock(long input) {
		String s = "";
		long l = input;
		for (int i = 0; i < 10; i++) {
			if (l % 54 < 26) {
				s += (char) (l % 54 + 97);
			} else if (l % 54 > 27) {
				s += (char) (l % 54 + 37);
			} else if (l % 54 == 26) {
				s += ' ';
			}
			l /= 54;
		}
		return s;
	}

	public static String stretch(long[] input) {
		String s = "";
		for (int i = 0; i < input.length; i++) {
			s += stretchBlock(input[i]);
		}
		return s;
	}

	private static long p(int i) {
		if (i == 0) {
			return 1;
		}
		long tmp = 54;
		for (int j = 0; j < i - 1; j++) {
			tmp *= 54;
		}
		return tmp;
	}

	
	public static void main(String[] args) {
	}
	
}
