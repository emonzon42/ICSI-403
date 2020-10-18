/**
 * Eli Monzon
 * 10.17.20
 * CSI 403
 * Graph
 */
import java.util.*;
public class Graph {
    public Set<Integer> nodes;
    public List<Edge> edges;

    public Graph(Set<Integer> _nodes, List<Edge> _edges){
        nodes = _nodes;
        edges = _edges;
    }

    public Graph(){
        nodes = new HashSet<Integer>();
        edges = new LinkedList<Edge>();
    }
}
