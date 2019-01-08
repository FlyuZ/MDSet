package graph;

import java.util.Arrays;

public class Graph {
	private int[][] graph;
	private Node[] neighborNumPQ;
	private int sumNeighborNum;
	private int size = -1;

	public Graph(int size) {
		this.size = size;
		sumNeighborNum = 0;
		graph = new int[size][size];
		neighborNumPQ = new Node[size];
		for(int i=0 ;i<size; i++) {
			neighborNumPQ[i] = new Node(i);
		}
	}

	public void addEdge(int a, int b) {
		graph[a][b] = 1;
		graph[b][a] = 1; // Ôö¼ÓË«Ïò±ß
		if (neighborNumPQ[a].getNodeNum() == 0) {
			neighborNumPQ[a].setNodeNum(a);
		}
		if (neighborNumPQ[b].getNodeNum() == 0) {
			neighborNumPQ[b].setNodeNum(b);
		}
		neighborNumPQ[a].neighborNum++;
		neighborNumPQ[b].neighborNum++;
		sumNeighborNum += 2;
	}

	public void sortNeighborNum() {
		Arrays.sort(neighborNumPQ);
		for (Node n : neighborNumPQ) {
			System.out.println(n.toString());
		}
	}

	public int[][] getGraph() {
		return graph;
	}

	public Node[] getNeighborNumPQ() {
		return neighborNumPQ;
	}

	public int getSumNeighborNum() {
		return sumNeighborNum;
	}

	public int getSize() {
		return size;
	}
}
