/**
 * Eli Monzon
 * 10.17.20
 * CSI 403
 * Graph
 */
import java.util.*;
public class Graph {
    public HashSet<Integer> nodes;
    public LinkedList<Edge> edges;

    public Graph(HashSet<Integer> _nodes, LinkedList<Edge> _edges){
        nodes = _nodes;
        edges = _edges;
    }

    public Graph(){
        nodes = new HashSet<Integer>();
        edges = new LinkedList<Edge>();
    }
}
