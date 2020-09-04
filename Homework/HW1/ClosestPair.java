/** 
 * Eli Monzon
 * 9.1.20
 * 
 * 	A main class for the Closest Pair algorithms
 *  Programming assignment for
 *  CSI403 Algorithms and Data Structures
 *  University at Albany - SUNY
 *  
 * Instructions: Implement methods: 
 * 1) getCPBruteForce()
 * 2) getCPDivideAndConquer()
 * As discussed in class and in the assignment part (a)
 */

import java.util.HashSet;
////import java.util.Arrays;
public class ClosestPair {
	
	/** TODO: IMPLEMENT 
	 *  A brute force method for the closest pair
	 *  @returns an array of exactly the two closest points
	 *  IMPORTANT: DO NOT CHANGE THIS METHOD SIGNATURE,
	 *  ONLY THE BODY WILL BE CONSIDERED FOR GRADING
	 */
	public static Point[] getCPBruteForce (Point[] pts)  {
		Point[] returnArr = new Point[2];
		double smallDist = 500000;
		for (int i = 0; i < pts.length ; i++){

			
			for (int j = 0; j < pts.length; j++) {
				if (j == i)
					continue;
				//on first iteration or when smallest distance is > the current comparisons distance
				if(j == 0 || smallDist >= pts[i].dist(pts[j])){ 
					smallDist = pts[i].dist(pts[j]);
					returnArr[0] = pts[i];
					returnArr[1] = pts[j];
				}
				////System.err.println(smallDist + "|p : "+ pts[i].toString() + " ~ q : "+ pts[j].toString());
			}
		}
		////System.err.println(Arrays.toString(returnArr));
		return returnArr;
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
