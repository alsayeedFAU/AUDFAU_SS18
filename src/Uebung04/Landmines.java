public class Landmines {

	public static int[][] findPath(LandminesTrail lt, boolean[][] feld) {
		if (feld == null) {
			return new int[][] {};
		}
		boolean[][] neuesFeld = new boolean[feld.length][feld[0].length];
		for (int i = 0; i < neuesFeld.length; i++) {
			for (int j = 0; j < neuesFeld[i].length; j++) {
				if (feld[i][j] == true) {
					if (i > 0) {
						neuesFeld[i - 1][j] = true;
					}
					if (i + 1 < feld.length) {
						neuesFeld[i + 1][j] = true;
					}

					if (j > 0) {
						neuesFeld[i][j - 1] = true;
					}

					if (j + 1 < feld[i].length) {
						neuesFeld[i][j + 1] = true;
					}
				}
			}
		}
		int[][] helper = null;
		for (int i = 0; i < feld.length; i++) {
			int tmp[][] = null;
			if (neuesFeld[i][0] == false) {
				neuesFeld[i][0] = true;
				tmp = helper(lt, neuesFeld, 0, i);
			}
			if (tmp != null) {
				if (helper == null || helper.length > tmp.length) {
					helper = tmp;
				}
			}
			if (helper != null && helper.length <= feld[0].length + 1) {
				return helper;
			}
		}
		return helper;
	}

	public static int[][] helper(LandminesTrail lt, boolean[][] feld, int x, int y) {
		int[][] helper = null;

		if (x >= feld[0].length) {
			return lt.finished();
		} else {
			for (int i = 0; i < 3; i++) {
				int tmp[][] = null;
				try {
					if (i == 0) {
						if (feld[y][x + 1] == false) {
							feld[y][x + 1] = true;
							tmp = helper(lt, feld, x + 1, y);
							feld[y][x + 1] = false;
						}
					} else if (i == 1) {
						if (feld[y - 1][x] == false) {
							feld[y - 1][x] = true;
							tmp = helper(lt, feld, x, y - 1);
							feld[y - 1][x] = false;
						}
					} else if (i == 3) {
						if (feld[y][x - 1] == false) {
							feld[y][x - 1] = true;
							tmp = helper(lt, feld, x - 1, y);
							feld[y][x - 1] = false;
						}
					} else {
						if (feld[y + 1][x] == false) {
							feld[y + 1][x] = true;
							tmp = helper(lt, feld, x, y + 1);
							feld[y + 1][x] = false;
						}
					}

				} catch (Exception e) {
					if (i == 0) {
						int[][] t = helper(lt, feld, x + 1, y);
						return new int[][] { { y, x } };
					}
					continue;
				}
				if (tmp != null) {
					if (helper == null || helper.length > tmp.length) {
						if (tmp.length == (feld[0].length - x - 1)) {
							helper = tmp;
							break;
						}
						helper = tmp;
					}
				}
			}
		}
		if (helper == null) {
			return null;
		}

		int[][] loesung = new int[helper.length + 1][];
		for (int i = 0; i < helper.length; i++) {
			loesung[i + 1] = helper[i];
		}
		loesung[0] = new int[] { y, x };
		return loesung;
	}
}
