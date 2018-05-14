public interface LandminesTrail {
	/**
	 * Call me whenever you reach the right border of the mine field!
	 * 
	 * @return the empty path/trail through the mine field, i.e. an empty array of the return type
	 */
	public int[][] finished();
}