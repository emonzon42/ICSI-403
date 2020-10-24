/**
 * Eli Monzon
 * 10.24.20
 * CSI 403
 * Node
 */
public class Node implements Comparable<Node>{

    public Integer key;
    public Boolean visted;
    
    public Node(Integer key){
        this(key, null);
    }

    public Node(Integer key, Boolean visted){
        this.key = key;
        this.visted = visted;
    }

    public int compareTo(Node e) {
        return key.compareTo(e.key);
    }

    public String toString(){
        return key.toString();
    }
}