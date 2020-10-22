/**
 * Eli Monzon
 * 10.20.20
 * CSI 403
 * DisjointSet
 */
public class DisjointSet {
    
    public Integer key, parent;

    public DisjointSet(Integer key, Integer parent){
        this.key = key;
        this.parent = parent;
    }

    public DisjointSet(Integer key){
        this.key = key;
        this.parent =  key;
    }
}
