/**
 * Eli Monzon
 * 10.17.20
 * CSI 403
 * Edge
 */

public class Edge implements Comparable<Edge>{
	
	public final Node u, v;
	public final Integer cost;

	/** 
	 * Constructor
	 */
	public Edge(Node u, Node v, Integer cost) {
		this.u = u;
		this.v = v;
		this.cost = cost;
	}

	public Edge(Integer u, Integer v, Integer cost) {
		this(new Node(u), new Node(v), cost);
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
