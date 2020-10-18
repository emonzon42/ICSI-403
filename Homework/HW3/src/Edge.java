/**
 * Eli Monzon
 * 10.17.20
 * CSI 403
 * Edge
 */

public class Edge implements Comparable<Edge>{
	
	public final Integer u, v, cost;
	
	/** 
	 * Constructor
	 */
	public Edge(Integer u, Integer v, Integer cost) {
		this.u = u;
		this.v = v;
		this.cost = cost;
	}
	
	/** 
	 * Prints a string representation of the edge
	 */
	public String toString() {
		return "("+u.toString() + "," + v.toString()+ "): "+ cost.toString();
	}
	
	public int compareTo(Edge e) {
        return cost.compareTo(e.cost);
    }
}
