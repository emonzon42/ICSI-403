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
		double smallDist = Double.POSITIVE_INFINITY;
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
		////System.err.println("ptsx: "+ Arrays.toString( ptsX));
		if(ptsX.length <= 3){
			return getCPBruteForce(ptsX);
		} else {
			Point[] pl = getCPDivideAndConquer(subarray(ptsY, 0, ptsX.length/2), subarray(ptsY, 0, ptsY.length/2));
			Point[] pr = getCPDivideAndConquer(subarray(ptsX, ptsX.length/2, ptsX.length), subarray(ptsY, ptsY.length/2, ptsY.length));	
			double delta = min(pl,pr);
			Point[] ps = closestSplitPair(ptsX, ptsY, delta);
			////System.err.println("pL: "+ Arrays.toString(pl));
			////System.err.println("pR: "+ Arrays.toString(pr));
			////System.err.println("pS: "+ Arrays.toString(ps));

			if(ps[0] != null && ps[0].dist(ps[1]) <= delta)
				return ps;
			else if(pl[0].dist(pl[1]) <= delta)
				return pl;
			else
				return pr;
		}
	}

	//returns the pair of points closest to eachother within distance delta
	private static Point[] closestSplitPair(Point[] px, Point[] py, double delta){
		double xbar = px[px.length/2].x;
		Point[] sy = new Point[py.length];

		//fills sy with all points in py within delta of vertical strip xbar
		for(int i = 0, j=0; i < py.length; i++){
			if (py[i].x > xbar - delta && py[i].x < xbar + delta){
				sy[j] = py[i];
				j++;
			}
			if(i == py.length-1 && j<i){  //deallocates null space in sy
				dealloc(sy, j);
			}
		}
		double bestDist = delta;
		Point[] bestPair = new Point[2];
		////System.err.println("sY: "+ Arrays.toString(sy));
		////System.err.println(xbar);
		
		//looks for closest points in sy
		for(int i = 0 ; i < sy.length - 1; i++){
			for (int j = 0; j < min(7,sy.length-i); j++){
				if(i == i+j)
					continue;
				Point p = sy[i], q = sy[i+j];
				///System.err.println(bestDist + "|p : "+ p.toString() + " ~ q : "+ q.toString());
				if(p == null ||  q == null)
					break;
				if(p.dist(q) < bestDist){
					bestDist = p.dist(q);
					bestPair[0] = p;
					bestPair[1] = q;
 				}
			}
		}
		////System.err.println("bestpair: "+ Arrays.toString(bestPair));

		return bestPair;
	}

	//returns minimum closest distance between pairs
	private static double min(Point[] p1, Point[] p2){
		if (p1[0].dist(p1[1]) < p2[0].dist(p2[1]))
			return p1[0].dist(p1[1]);
		else
			return p2[0].dist(p2[1]);
	}

	//returns the minimum between two nums
	private static double min(double p1, double p2){
		if (p1<p2)
			return p1;
		else
			return p2;
	}

	//deallocates space after arr[n] in array
	private static void dealloc(Point[] arr, int n){
		arr = subarray(arr, 0, n);
	}

	//returns a subarray of original[from,..,to]
	private static Point[] subarray(Point[] original, int from, int to)
   {
    if (from > to)
      throw new IllegalArgumentException("The initial index is after " +
                      "the final index.");
    Point[] newArray = new Point[to - from];
    if (to > original.length){
     	System.arraycopy(original, from, newArray, 0, original.length - from);
     	for (int i = original.length; i < newArray.length; i++)
			newArray[i] = null;
    } else
       	System.arraycopy(original, from, newArray, 0, to - from);
     	return newArray;
   }
}
