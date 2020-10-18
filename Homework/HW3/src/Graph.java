/**
 * Eli Monzon
 * 10.17.20
 * CSI 403
 * Graph
 */
import java.util.HashSet;
public class Graph {
    public HashSet<Integer> nodes;
    public Edge[] edges;

    public Graph(HashSet<Integer> _nodes, Edge[] _edges){
        nodes = _nodes;
        edges = _edges;
    }

    public Graph(){
        nodes = new HashSet<Integer>();
        edges = new Edge[1];
    }
}
