/**
 * Eli Monzon
 * 10.16.20
 * CSI 403
 * DriverMST
 */
import java.io.*;
public class DriverMST {

    //* javac -d "Homework/HW3/bin" "Homework/HW3/src/DriverMST.java" Homework/HW3/infile.txt

    public static void main(String[] args) {
        
       /* if (args.length < 1){
            System.out.println("Please start the program like this: \"MST.py InputFilePathName options\"");
            return;
        }*/

        Graph g = new Graph();
        try {
            File file = new File("Homework/HW3/infile.txt");//= new File(args[0]) //!NEEDS TO WORK W COMMAND LINE BUT COMMAND LINE WAS GMT
            g = MST.gothru(file);
            if (g == null)
                return;
        } catch (FileNotFoundException e) {
            System.err.println("File not found, pathname may be incorrect. ");
            return;
        }
        MST.kruskal(g);
        if (args.length > 1){
            //todo:  step 7:
        //todo: when args[1] = '-e' print list of edges and costs
        }
    }
}