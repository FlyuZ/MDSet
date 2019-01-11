package graph;

import java.util.ArrayList;
import java.util.List;

public class Node implements Comparable<Node>{
	public int nodeNum;
	public List<Integer> neighbor;
	public int Assignment = 0;
	
	public Node(int nodeNum) {
		this.nodeNum = nodeNum;
		neighbor = new ArrayList<Integer>();
	}
	public Node(Node node) {
		this.nodeNum = node.nodeNum;
		this.neighbor = node.neighbor;
	}
	
	public int getNodeNum() {
		return nodeNum;
	}
	public void setNodeNum(int nodeNum) {
		this.nodeNum = nodeNum;
	}
	public List<Integer> getNeighbor() {
		return neighbor;
	}
	public void setNeighbor(List<Integer> neighbor) {
		this.neighbor = neighbor;
	}
	@Override
	public int compareTo(Node arg0) {
		if(arg0.getNeighbor().size() > neighbor.size())
			return 1;
		else if(arg0.getNeighbor().size() == neighbor.size())
			return nodeNum - arg0.getNodeNum();
		return -1;
	}
	@Override
	public String toString() {
		return "Node [nodeNum=" + nodeNum + ", neighbor=" + neighbor + "]";
	}
}
