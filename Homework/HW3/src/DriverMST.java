/**
 * Eli Monzon
 * 10.16.20
 * CSI 403
 * DriverMST
 * Driver to demonstrate usage of MST
 */
import java.io.*;
import java.util.HashSet;
public class DriverMST {

    //* javac -d "Homework/HW3/bin" "Homework/HW3/src/DriverMST.java" Homework/HW3/infile.txt
    private static final String TEST_FILE = "Homework/HW3/infile3.txt";
    public static void main(String[] args) {
        
       /* if (args.length < 1){
            System.out.println("Please start the program like this: \"MST.py InputFilePathName options\"");
            return;
        }*/
        
        Graph g = null;
        try {
            File file = new File(TEST_FILE);//= new File(args[0]) //!NEEDS TO WORK W COMMAND LINE BUT COMMAND LINE WAS GMT
            g = MST.gothru(file);
            if (g == null)
                return;
        } catch (FileNotFoundException e) {
            System.err.println("File not found, pathname may be incorrect. ");
            return;
        }
        System.out.println("Now executing Kruskal's algorithm...");
        HashSet<Edge> T = MST.kruskal(g);
        //if (args.length > 1 && args[1] == "-e")
            System.out.println("Edges: " + T.toString());

        System.out.println();

        System.out.println("Now executing Prim's algorithm...");
        T = MST.prim(g);
        //if (args.length > 1 && args[1] == "-e")
            System.out.println("Edges: " + T.toString());
            
    }
}