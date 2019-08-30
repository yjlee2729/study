package astarAlgorism;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class AStar {
	
	private PriorityQueue<Node> openList;
	private ArrayList<Node> closedList;
	
	HashMap<Node, Integer> gMaps;
	HashMap<Node, Integer> fMaps;
	
	private int initalCapacity = 100;
	private int distanceBetwweenNodes = 1;

	public AStar() {
		gMaps = new HashMap<Node, Integer>();
		fMaps = new HashMap<Node, Integer>();
		openList = new PriorityQueue<Node>(initalCapacity, new fCompare());
		closedList = new ArrayList<Node>();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Node[] n = new Node[10];
		
		for (int i = 0 ; i < n.length ; i++) {
			n[i] = new Node();
			n[i].setData("n-" + i);
		}
		
		n[0].setXY(0, 0);
		n[1].setXY(0, 1);
		n[2].setXY(0, 2);
		n[3].setXY(1, 0);
		n[4].setXY(1, 2);
		n[5].setXY(2, 0);
		n[6].setXY(2, 1);
		n[7].setXY(2, 2);
		n[8].setXY(3, 0);
		n[9].setXY(3, 2);
		
		n[0].addNeighbors(n[1],n[3]);
		n[1].addNeighbors(n[0],n[2]);
		n[2].addNeighbors(n[1],n[4]);
		n[3].addNeighbors(n[0],n[5]);
		n[4].addNeighbors(n[2],n[7]);
		n[5].addNeighbors(n[3],n[8]);
		n[6].addNeighbors(n[7],n[5]);
		n[7].addNeighbors(n[4],n[6],n[9]);
		n[8].addNeighbors(n[5]);
		n[9].addNeighbors(n[7]);
		
		new AStar().serch(n[1], n[8]);
		
	}
	
	public void serch(Node start, Node end) {
		openList.clear();
		closedList.clear();
		
		gMaps.put(start, 0);
		openList.add(start);
		
		while(!openList.isEmpty()) {
			Node current = openList.element();
			if(current.equals(end)) {
				System.out.println("GOAL!");
				printPath(current);
				
				return ;
			}
			
			closedList.add(openList.poll());
			
			ArrayList<Node> neighbors = current.getNeighbors();
			
			for (Node neighbor : neighbors) {
				int gScore = gMaps.get(current) + distanceBetwweenNodes;
				int fScore = gScore + h(neighbor, current);
				
				if(closedList.contains(neighbor)) {
					if(gMaps.get(neighbor) == null) {
						gMaps.put(neighbor, gScore);
					}
					
					if(fMaps.get(neighbor) == null) {
						fMaps.put(neighbor, fScore);
					}
					
					if(fScore >= fMaps.get(neighbor)) {
						continue;
					}
				}
				
				if(! openList.contains(neighbor) || fScore < fMaps.get(neighbor)) {
					neighbor.setParent(current);
					gMaps.put(neighbor, gScore);
					fMaps.put(neighbor, fScore);
					if(!openList.contains(neighbor)) {
						openList.add(neighbor);
					}
				}
			}
		}
		
		System.out.println("FAIL");
	}
	
	private int h(Node node, Node goal) {
		int x = node.getX() - goal.getX();
		int y = node.getY() - goal.getY();
		
		return x*x + y*y;
	}
	
	private void printPath(Node node) {
		System.out.println(node.getData());
		
		while(node.getParent() != null) {
			node = node.getParent();
			System.out.println(node.getData());
		}
	}
	
	class fCompare implements Comparator<Node>{
		public int compare(Node o1, Node o2) {
			if (fMaps.get(o1) < fMaps.get(o2)) {
				return -1;
			}else{
				return 1;
			}
		}
	}

}
