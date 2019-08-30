package astarAlgorism;

import java.util.LinkedList;
import java.util.Queue;

public class LinkQueue {
	
	


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Queue<Integer> queue = new LinkedList<Integer>();
		
		queue.offer(1);//queue에 주입
		queue.offer(2);
		queue.offer(3);
		
		
		int header = queue.element(); // 1 반환
		int tail = queue.peek(); // 1 반환
		
		System.out.println(header);
		System.out.println(tail);
		
		while(!queue.isEmpty()) {
			int re = queue.remove(); //앞에서 부터 꺼내옴
			
			System.out.println(re);
		}
	}

}
