package graph;


public class Graph {
	private int[][] graph;
	private int size = -1;

	public Graph(int size) {
		this.size = size;
		graph = new int[size][size];
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				graph[i][j] = 0;
			}
		}
	}

	public void addEdge(int a, int b) {
		graph[a][b] = 1;
		graph[b][a] = 1; // Ôö¼ÓË«Ïò±ß
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int[][] getGraph() {
		return graph;
	}

	public void setGraph(int[][] graph) {
		this.graph = graph;
	}
	
}
