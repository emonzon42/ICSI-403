/**
 * Eli Monzon
 * 10.16.20
 * CSI 403
 * MST
 */
import java.util.*;
import java.io.*;
public final class MST {
    
    //parses through file and returns the graph in question 
    public static Graph gothru(File file) throws FileNotFoundException{
        //Todo: read input 
        Scanner fr = new Scanner(file);
        if(!fr.hasNextLine()){
            System.out.println("Unexpected/empty file, please format correctly");
            fr.close();
            return null;
        }
        LinkedList<Edge> edges = new LinkedList<>();
        HashSet<Integer> nodes = new HashSet<Integer>();
        fr.nextLine();
        
        while (fr.hasNextLine()){
            String line[] = fr.nextLine().split(" ");
            edges.add(new Edge(Integer.parseInt(line[0]), Integer.parseInt(line[1]),Integer.parseInt(line[2].split("\n")[0])));
            
            nodes.add(Integer.parseInt(line[0]));
            nodes.add(Integer.parseInt(line[1]));      
        }
        fr.close();
        return new Graph(nodes, edges);
    }

    public static void kruskal(Graph G){
        long tick = System.currentTimeMillis();
        Collections.sort(G.edges); //sorts by cost
        System.out.println(G.edges.toString());
        HashSet<Edge> T = new HashSet<>();

        HashMap <Integer,DisjointSet> nodes = new HashMap<>();
        for (Integer node : G.nodes)
            nodes.put(node, new DisjointSet(node));

        while (G.edges.size() >= 2) {
            Edge currEdge = G.edges.removeFirst();
            
            if (find(nodes, currEdge.u) != find(nodes, currEdge.v)){
                union(nodes, currEdge.u, currEdge.v);
                T.add(currEdge);
            }
        }
        long tock = System.currentTimeMillis();
        System.out.println(T.toString());
        System.out.println("Execution Time: (" + (tock-tick) + "ms)");
    }

    private static void union(HashMap <Integer,DisjointSet> nodes, int a, int b){
        Integer x = find(nodes, a);
        int y = find(nodes, b);
        nodes.get(x).parent = y;
    }

    private static Integer find(HashMap <Integer,DisjointSet> nodes, int key){
        Integer parent = nodes.get(key).parent;
        
        if (parent.equals(key))
            return key;
        else
            return find(nodes, parent);

    }
    

    //returns whether or not nodes from e is present in a set of edges
    private static boolean nodeExists(Edge e, HashSet<Edge> T){
        //todo: fix this function so that it only returns true if it detects a cycle
        for (Edge edge : T){
            if (edge.u.equals(e.u))
                return true;
            else if(edge.v.equals(e.v))
                return true;
        }
        return false;
    }

}