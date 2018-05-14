
import java.util.ArrayList;

public class FareyFolge {

	public static int[][] berechneFareyFolge(RekursiveBrueche bb, int n) {
		if (n == 1) {
			bb.initialeFareyFolge();
			return new int[][] { { 0, 1 }, { 1, 1 } };
		}
		int[][] tmp = berechneFareyFolge(bb, n - 1);

		ArrayList<int[]> list = new ArrayList<>();
		list.add(tmp[0]);
		for (int i = 1; i < tmp.length; i++) {
			if (tmp[i - 1][1] + tmp[i][1] == n) {
				list.add(new int[] { (tmp[i - 1][0] + tmp[i][0]), (tmp[i - 1][1] + tmp[i][1]) });
				list.add(tmp[i]);
			} else {
				list.add(tmp[i]);
			}
		}
		tmp = list.toArray(new int[list.size()][]);
		return tmp;
	}

}
