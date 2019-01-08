package algorithm;

import graph.Graph;
import util.GraphLoader;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String fileName = "Data2.txt";
		Graph graph = GraphLoader.loadGraph(fileName);
		graph.sortNeighborNum();
		MDSet mdset = new MDSet(graph);
		mdset.getUpperBound();
		graph.sortNeighborNum();
	}
}
