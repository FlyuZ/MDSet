package util;

import java.io.File;
import java.util.Scanner;
import graph.*;
public class GraphLoader{
    public static Graph loadGraph(String filename) {
        //Set<Integer> seen = new HashSet<Integer>();
    	int size = 0;
        Scanner sc;
        try {
            sc = new Scanner(new File(filename));
        } catch (Exception e) {
            e.printStackTrace();
            Graph graph = new Graph(-1);
            return graph;
        }
        if(sc.hasNext()) {
        	size = sc.nextInt();
        }
        Graph graph = new Graph(size);
        while (sc.hasNext())
        {
        	if (sc.hasNextInt())
        	{
	            int a = sc.nextInt();
	            int b = sc.nextInt();
	            graph.addEdge(a, b);
	        }
	        else
	        {
	        	sc.nextLine();
	        }
        }
        sc.close();
        return graph;
    }
}
