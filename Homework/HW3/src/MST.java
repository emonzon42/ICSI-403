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
        Edge edges[] = new Edge[10000];
        HashSet<Integer> nodes = new HashSet<Integer>();
        fr.nextLine();
        
        int i = 0;
        while (fr.hasNextLine()){
            String line[] = fr.nextLine().split(" ");
            edges[i] = new Edge(Integer.parseInt(line[0]), Integer.parseInt(line[1]),Integer.parseInt(line[2].split("\n")[0]));
            
            nodes.add(Integer.parseInt(line[0]));
            nodes.add(Integer.parseInt(line[1]));

            i++;
            if (!fr.hasNextLine())
                freeSpace(edges, i);
        }
        System.out.println(nodes.toString());
        fr.close();
        return new Graph(nodes, edges);
    }

    public static void kruskal(Graph G){

        long tick = System.currentTimeMillis();
        //Arrays.sort(edges);
        HashSet<Integer> T = new HashSet<>();

        while (true) {
           //!stuff 
        }
        //long tock = System.currentTimeMillis();

       //System.out.println("Execution Time: (" + (tock-tick) + "ms)");
    }

    //frees space after arr[n] in array (sets the array to a copy of itself excluding points arr[j>n])
    private static <T> void freeSpace(T[] arr, int n) {
		arr = Arrays.copyOfRange(arr, 0, n);
	}
}