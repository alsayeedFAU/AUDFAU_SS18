public interface RekursiveBrueche {
	public int[][] initialeFareyFolge();

	public int[] ergaenze(int x, int[] y); // erzeugt {x, y[0], y[1], ..., y[n]}

	public long[] ergaenze(long x, long[] y); // erzeugt {x, y[0], y[1], ..., y[n]}
}