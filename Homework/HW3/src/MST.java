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
    
    //Prim's algorithm for a MST
    public static Collection<Edge> prim(Graph G){//!Returns a different mst from kruskal (figure out how to throw out edges and replace them with better ones)
        long tick = System.currentTimeMillis();
        HashSet<Edge> T = new HashSet<>();
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
            
            //Edge best = null;
            Edge best[] = findLowestAdjacent(G.edges, nodes, currentNode);

            //find better edge for second node of second edge if possible
            if (best[1] != null && best[1].u.equals(currentNode)) {
                best[1] = min(findLowestAdjacent(G.edges, nodes, best[1].v)[0], best[1]);
            } else if(best[1] != null && best[1].v.equals(currentNode)){
                best[1] = min(findLowestAdjacent(G.edges, nodes, best[1].u)[0], best[1]);
            }
            Edge chosen;

           //! System.out.println((best[0] == null )+"|"+(best[1] == null));
            
            if(best[0] != null && best[1] != null){
                T.addAll(Arrays.asList(best));
                chosen = min(best[0],best[1]);
            }else if(best[0] != null){
                T.add(best[0]);
                chosen = best[0];
            }else{
                currentNode = unvistedNode(nodes.values());
                continue;
            }
         //!   System.out.println(T.toString());
         //!   System.out.println(chosen+"<------- best");
            
            if (chosen.u.equals(currentNode)) {
                currentNode = chosen.v;

            }else if(chosen.v.equals(currentNode)){
                currentNode = chosen.u;
            }
        }

        long tock = System.currentTimeMillis();
        System.out.println("Execution Time: (" + (tock-tick) + "ms)");
        System.out.println("Total cost of edges: " + totalCost(T));
        return T;
    }

    //returns lowest cost of two edges
    private static Edge min(Edge a, Edge b){
        if(a == null)
            return b;
        else if(b == null)
            return a;

        return a.compareTo(b) <= 0 ? a : b;
    }

    private static Edge[] findLowestAdjacent(LinkedList<Edge> edges, HashMap <Integer,Node> nodes, Node node){
        double lowestCost = Double.POSITIVE_INFINITY;
        byte i = 0;
        Edge lowest[] = new Edge[2];
        for (Edge e : edges) {
            //System.out.println();
            
            
           // if(visted[e.u] && visted[e.v])continue;
           if (nodes.get(e.u.key).visted && nodes.get(e.v.key).visted)
                continue;
         //! System.out.println(e.toString() + " | "+ lowestCost + " | "+ node);
    ////  System.out.println(((e.u.equals(currentNode))||(e.v.equals(currentNode)))+"" +(!nodes.get(e.u.key).visted||!nodes.get(e.v.key).visted) +""+ (e.cost < lowestCost));

    ////        System.out.println(nodes.get(e.u.key).visted+","+nodes.get(e.v.key).visted);
    ////        System.out.println(e.u.equals(currentNode) +""+ !nodes.get(e.v.key).visted +""+ (e.cost < lowestCost));
        ////    System.out.println(e.v.equals(currentNode) +""+ !nodes.get(e.u.key).visted +""+ (e.cost < lowestCost));
            if ((e.u.equals(node) || e.v.equals(node)) && (!nodes.get(e.v.key).visted && !nodes.get(e.u.key).visted) && e.cost < lowestCost){
              //  System.out.println(" ....changes to be made");
                
                
               if(lowest[0] != null && lowest[1] != null && lowest[0].cost < lowest[1].cost){
                    lowest[1] = lowest[0];
                    lowest[0] = e;
              } else if (lowest[0] != null && lowest[1] != null){
                        lowest[1] = e;
                }else{
                    lowest[i] = e;   
                    i++;
                    if(lowest[0] != null && lowest[1] != null && lowest[0].cost > lowest[1].cost){
                        lowest[1] = lowest[0];
                        lowest[0] = e;  
                    }
                }
                if(lowest[1] == null) lowestCost = Double.POSITIVE_INFINITY;
                else 
                lowestCost = e.cost;

               //! System.out.println(Arrays.toString(lowest));
            }
        }

        nodes.get(node.key).visted = true;  
        
        return lowest;
    }

    //returns whether any nodes in a collection are marked as unvisted
    private static boolean nodesUnvisted(Collection<Node> nodes){
        return unvistedNode(nodes) == null ? false : true;
    }
    
    //returns a unvisted node in a collection
    private static Node unvistedNode(Collection<Node> nodes){
        for (Node n : nodes) {
            if (!n.visted)
                return n;
        }
        return null;
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

    //Returns total cost of a collection of edges
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