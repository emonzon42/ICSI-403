import java.util.*;
import java.io.*;
/**
 * Eli Monzon
 * 10.16.20
 * CSI 403
 * MST
 * Utility class to create a MST from a weighted graph 
 */
public final class MST {
    
    //parses through file and returns the graph in question 
    public static Graph gothru(File file) throws FileNotFoundException{
        Scanner fr = new Scanner(file);
        
        if(!fr.hasNextLine()){
            System.out.println("Unexpected/empty file, please format correctly");
            fr.close();
            return null;
        }
        LinkedList<Edge> edges = new LinkedList<>();
        HashSet<Node> nodes = new HashSet<Node>();
        fr.nextLine();
        
        while (fr.hasNextLine()){
            String line[] = fr.nextLine().split(" ");
            edges.add(new Edge(Integer.parseInt(line[0]), Integer.parseInt(line[1]),Integer.parseInt(line[2].split("\n")[0])));
            
            nodes.add(new Node(Integer.parseInt(line[0])));
            nodes.add(new Node(Integer.parseInt(line[1])));      
        }
        fr.close();
        return new Graph(nodes, edges);
    }

    public static HashSet<Edge> prim(Graph G){
        long tick = System.currentTimeMillis();
        HashSet<Edge> T = new HashSet<>();
        PriorityQueue<Node> unvisted = new PriorityQueue<>(G.nodes);
        boolean[] visted = new boolean[G.nodes.size() + unvisted.peek().key];
        double key[] = new double[G.nodes.size() + unvisted.peek().key];

        for (Node u : unvisted) {
            key[u.key] = Double.POSITIVE_INFINITY;
        }
        key[unvisted.peek().key] = 0;
        visted[unvisted.peek().key] = true;
        //System.out.println(unvisted.toString());

        while (!unvisted.isEmpty()){
            Node currentNode = unvisted.poll(); //! doesn't extract the closest node
            double lowestCost = Double.POSITIVE_INFINITY;
            Edge best = null;
            
            for (Edge e : G.edges) {
                //System.out.println();
                //System.out.println(e.toString() + " | "+ lowestCost + " | "+ currentNode);
               // if(visted[e.u] && visted[e.v])continue;
                
                if (e.u == currentNode && e.cost < key[e.v.key]){
                    //System.out.println(" ....changes to be made");
                    //System.out.println(e.cost+" < "+ lowestCost + ": " + (e.cost < lowestCost));
                    best = e;   
                    key[e.v.key] = e.cost;
                    
                }else if(e.v == currentNode && e.cost < key[e.u.key]){
                    best = e;
                    key[e.u.key] = e.cost;
                    
                }
            }
          //  System.out.println(best);
            T.add(best);
            System.out.println(unvisted.toString());
            System.out.println(best.u);
            System.out.println(best.v);
            if (best.u == currentNode) {
                currentNode = best.v;
            }else if(best.v == currentNode){
                currentNode = best.u;
            }
            
            System.out.println(unvisted.toString());
        }

        long tock = System.currentTimeMillis();
        System.out.println("Execution Time: (" + (tock-tick) + "ms)");
        System.out.println("Total cost of edges: " + totalCost(T));
        return T;
    }

    //Kruskal's algorithm for a MST
    public static HashSet<Edge> kruskal(Graph G){
        LinkedList<Edge> edges = new LinkedList<>(G.edges);
        long tick = System.currentTimeMillis();
        Collections.sort(edges); //sorts by cost

        HashSet<Edge> T = new HashSet<>();

        HashMap <Integer,DisjointSet> nodes = new HashMap<>();
        for (Node node : G.nodes)
            nodes.put(node.key, new DisjointSet(node));

        while (edges.size() >= 2) {
            Edge currEdge = edges.removeFirst();
            
            if (find(nodes, currEdge.u.key) != find(nodes, currEdge.v.key)){
                union(nodes, currEdge.u.key, currEdge.v.key);
                T.add(currEdge);
            }
        }
        long tock = System.currentTimeMillis();
        System.out.println("Execution Time: (" + (tock-tick) + "ms)");
        System.out.println("Total cost of edges: " + totalCost(T));
        
        return T;
    }

    private static int totalCost(HashSet<Edge> edges){
        int count = 0;
        for (Edge edge : edges) {
            count += edge.cost;
        }
        return count;
    }

    private static void union(HashMap <Integer,DisjointSet> nodes, int a, int b){
        Node x = find(nodes, a);
        Node y = find(nodes, b);
        nodes.get(x.key).parent = y;
    }

    private static Node find(HashMap <Integer,DisjointSet> nodes, int key){
        Node parent = nodes.get(key).parent;
        
        if (parent.key.equals(key))
            return parent;
        else
            return find(nodes, parent.key);

    }
}