/** A main class for the Closest Pair algorithms
 *  Programming assignment for
 *  CSI403 Algorithms and Data Structures
 *  University at Albany - SUNY
 *  
 * Instructions: Implement methods: 
 * 1) getCPBruteForce()
 * 2) getCPDivideAndConquer()
 * As discussed in class and in the assignment part (a)
 */
package closestpair;
import java.util.HashSet;

public class ClosestPair {
	
	/** TODO: IMPLEMENT 
	 *  A brute force method for the closest pair
	 *  @returns an array of exactly the two closest points
	 *  IMPORTANT: DO NOT CHANGE THIS METHOD SIGNATURE,
	 *  ONLY THE BODY WILL BE CONSIDERED FOR GRADING
	 */
	public static Point[] getCPBruteForce (Point[] pts)  {
		//TODO: Implement this method for part (a) of the assignment 
		return null;
	}
	
	/** A driver for the Divide-And-Conquer method for the closest pair
	 *  takes unsorted array of points, sorts them and invokes 
	 *  the recursive method you are required to implement
	 *  
	 *  @returns an array of exactly the two closest points
	 *  IMPORTANT: DO NOT CHANGE THIS METHOD
	 */
	public static Point[] getCPDivideAndConquer(Point[] pts) {
		Point[] ptsX = Point.sortByX(pts); 
		Point[] ptsY = Point.sortByY(pts);
		return getCPDivideAndConquer(ptsX, ptsY);
	}
	
	/** TODO: IMPLEMENT 
	 *  A Divide-And-Conquer method for the closest pair
	 *  takes as input the points sorted by increasing x
	 *  and y coordinates in ptsX and ptsY respectively
	 *  @returns an array of exactly the two closest points.
	 *  IMPORTANT: DO NOT CHANGE THIS METHOD SIGNATURE,
	 *  ONLY THE BODY WILL BE CONSIDERED FOR GRADING
	 */
	public static Point[] getCPDivideAndConquer(Point[] ptsX, Point[] ptsY) {
		return null;
	}
}
