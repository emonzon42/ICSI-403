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

    public static Collection<Edge> prim(Graph G){
        long tick = System.currentTimeMillis();
        LinkedList<Edge> T = new LinkedList<>();
     //   PriorityQueue<Node> unvisted = new PriorityQueue<>(G.nodes);
     //   boolean[] visted = new boolean[G.nodes.size() + unvisted.peek().key];
     //   double key[] = new double[G.nodes.size() + unvisted.peek().key];
        //LinkedList<Node> nodes = new LinkedList<>(G.nodes);
        HashMap <Integer,Node> nodes = new HashMap<>();
        
        for (Node n : G.nodes)
            nodes.put(n.key, new Node(n,false));

        //System.out.println(unvisted.toString());
        Node currentNode = nodes.get(G.edges.getFirst().u.key);

        while (nodesUnvisted(nodes.values())){
            double lowestCost = Double.POSITIVE_INFINITY;
            Edge best = null;
            
            for (Edge e : G.edges) {
                //System.out.println();
                
               // if(visted[e.u] && visted[e.v])continue;
               if (nodes.get(e.u.key).visted && nodes.get(e.v.key).visted)
                    continue;
        ////        System.out.println(e.toString() + " | "+ lowestCost + " | "+ currentNode);
        ////  System.out.println(((e.u.equals(currentNode))||(e.v.equals(currentNode)))+"" +(!nodes.get(e.u.key).visted||!nodes.get(e.v.key).visted) +""+ (e.cost < lowestCost));

        ////        System.out.println(nodes.get(e.u.key).visted+","+nodes.get(e.v.key).visted);
        ////        System.out.println(e.u.equals(currentNode) +""+ !nodes.get(e.v.key).visted +""+ (e.cost < lowestCost));
            ////    System.out.println(e.v.equals(currentNode) +""+ !nodes.get(e.u.key).visted +""+ (e.cost < lowestCost));
                if (e.u.equals(currentNode) && !nodes.get(e.v.key).visted && e.cost < lowestCost){
                  //  System.out.println(" ....changes to be made");
                    best = e;   
                    lowestCost = e.cost;
                    
                }else if(e.v.equals(currentNode) && !nodes.get(e.u.key).visted && e.cost < lowestCost){
                    best = e;
                    lowestCost = e.cost;
                }
            }
            ////System.out.println(best+"<------- best");
            
            nodes.get(currentNode.key).visted = true;
            if(best == null){
                currentNode = T.get(0).u;
                continue;
            }
            T.add(best);
            if (best.u.equals(currentNode)) {
                currentNode = best.v;

            }else if(best.v.equals(currentNode)){
                currentNode = best.u;
            }
        }

        long tock = System.currentTimeMillis();
        System.out.println("Execution Time: (" + (tock-tick) + "ms)");
        System.out.println("Total cost of edges: " + totalCost(T));
        return T;
    }

    private static boolean nodesUnvisted(Collection<Node> nodes){
        for (Node n : nodes) {
            if (!n.visted)
                return true;
        }
        return false;
    }

    //Kruskal's algorithm for a MST
    public static Collection<Edge> kruskal(Graph G){
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

    private static int totalCost(Collection<Edge> edges){
        int count = 0;
        for (Edge edge : edges) {
            count += edge.cost;
        }
        return count;
    }
    //unionizes two sets
    private static void union(HashMap <Integer,DisjointSet> nodes, int a, int b){
        Node x = find(nodes, a);
        Node y = find(nodes, b);
        nodes.get(x.key).parent = y;
    }
    //Finds parent of a node searched by it's key
    private static Node find(HashMap <Integer,DisjointSet> nodes, int key){
        Node parent = nodes.get(key).parent;
        
        if (parent.key.equals(key))
            return parent;
        else
            return find(nodes, parent.key);

    }
}