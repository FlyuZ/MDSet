package graph;

import java.util.Arrays;

public class Neighbor {
	private Node[] neighborPQ;
	private int size = -1;
	
	public Neighbor(int size) {
		this.size = size;
		neighborPQ = new Node[size];
		for(int i=0 ;i<size; i++) {
			neighborPQ[i] = new Node(i);
		}
	}
	public void addEdge(int a, int b) {
		neighborPQ[a].getNeighbor().add(b);
		neighborPQ[b].getNeighbor().add(a);
	}
	public void sortNeighbor() {
		Arrays.sort(neighborPQ);
		for (Node n : neighborPQ) {
			System.out.println(n.toString());
		}
	}
	public Node[] getNeighborPQ() {
		return neighborPQ;
	}
	public void setNeighborPQ(Node[] neighborPQ) {
		this.neighborPQ = neighborPQ;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
}
