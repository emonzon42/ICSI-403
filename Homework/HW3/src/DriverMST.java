/**
 * Eli Monzon
 * 10.16.20
 * CSI 403
 * DriverMST
 * Driver to demonstrate usage of MST
 */
import java.io.*;
import java.util.Collection;
public class DriverMST {
    
    //* javac -d "../bin" "DriverMST.java" 
    //* java DriverMST ../infile.txt -e

    public static void main(String[] args) {
        
        if (args.length < 1){
            System.out.println("Please start the program like this: \"DriverMST InputFilePathName [options]\"");
            return;
        }
        
        Graph g = null;
        try {
            File file = new File(args[0]);
            g = MST.gothru(file);
            if (g == null)
                return;
        } catch (FileNotFoundException e) {
            System.err.println("File not found, pathname may be incorrect. ");
            return;
        }
        System.out.println("Now executing Kruskal's algorithm...");
        Collection<Edge> T = MST.kruskal(g);
        if (args.length > 1 && args[1].equals("-e"))
            System.out.println("Edges: " + T.toString());

        System.out.println();

        System.out.println("Now executing Prim's algorithm...");
        T = MST.prim(g);
        
        if (args.length > 1 && args[1].equals("-e"))
            System.out.println("Edges: " + T.toString());
            
    }
}