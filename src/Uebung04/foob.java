import java.util.Arrays;

public class foob {

	static int foob(int x) {
		return x < 3 ? x : foob(x - 3) + foob(x - 1);
	}

	static int foobIter(int x) {
		if (x < 3) {
			return x;
		}
		int[] table = new int[] { 0, 1, 2 };
		for (int i = 3; i <= x; i++) {
			int[] tmp = new int[3];
			int t = table[0] + table[2];
			System.arraycopy(table, 1, tmp, 0, 2);
			table = tmp;
			table[2] = t;
		}
		return table[2];
	}

	static int fibWin(int x, int win) {
		if (x < win + 1) {
			return x;
		}
		return fibWin(x - (1 + win), win) + fibWin(x - 1, win);
	}

	static int fibWinIterativ(int x, int win) {
		int[] merker = new int[win + 1];
		for (int i = 0; i < merker.length; i++) {
			merker[i] = i;
		}
		for (int i = win + 1; i <= x; i++) {
			int[] next = new int[win + 1];
			int t = merker[0] + merker[merker.length - 1];
			for (int j = 0; j < merker.length - 1; j++) {
				next[j] = merker[j + 1];
			}
			next[next.length - 1] = t;
			merker = next;
		}
		return x < win + 1 ? x : merker[merker.length - 1];
	}
}
