package graph;

public class Node implements Comparable<Node>{
	public int nodeNum;
	public int neighborNum;
	
	public Node(int nodeNum) {
		this.nodeNum = nodeNum;
		this.neighborNum = 0;
	}
	public Node(Node node) {
		this.nodeNum = node.nodeNum;
		this.neighborNum = node.neighborNum;
	}
	public int getNodeNum() {
		return nodeNum;
	}
	public void setNodeNum(int nodeNum) {
		this.nodeNum = nodeNum;
	}
	public int getNeighborNum() {
		return neighborNum;
	}
	public void setNeighborNum(int neighborNum) {
		this.neighborNum = neighborNum;
	}

	@Override
	public int compareTo(Node arg0) {
		if(arg0.getNeighborNum() > neighborNum)
			return 1;
		else if(arg0.getNeighborNum() == neighborNum)
			return nodeNum - arg0.getNodeNum();
		return -1;
	}

	@Override
	public String toString() {
		return "Node [nodeNum=" + nodeNum + ", neighborNum=" + neighborNum + "]";
	}
}
