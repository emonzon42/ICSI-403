/**
 * Eli Monzon
 * 10.17.20
 * CSI 403
 * Edge
 */

public class Edge{
	
	/** 
	 * public members for coordinates
	 */
	public final Integer u, v;
	public final Integer cost;
	
	/** 
	 * Constructor
	 */
	public Edge(Integer u, Integer v, Integer cost) {
		this.u = u;
		this.v = v;
		this.cost = cost;
	}
	
	/** 
	 * Prints a string representation of a point
	 */
	public String toString() {
		return "("+u.toString() + "," + v.toString()+ ")";
	}
	
}
