/**
 * Eli Monzon
 * 10.20.20
 * CSI 403
 * DisjointSet
 */
public class DisjointSet {
    
    public Node key, parent;

    public DisjointSet(Node key, Node parent){
        this.key = key;
        this.parent = parent;
    }

    public DisjointSet(Node key){
        this.key = key;
        this.parent = key;
    }

    public DisjointSet(Integer key){
        this(new Node(key));
    }

    public DisjointSet(Integer key, Integer parent){
        this(new Node(key),new Node(parent));
    }
}
