package algo1;

import java.util.Stack;

public class PickUpDollPrac {
	public static int[][] board = {{0,0,0,0,0},{0,0,1,0,3},{0,2,5,0,1},{4,2,4,4,2},{3,5,1,3,1}};
	public static int[] moves = {1,5,3,5,1,2,1,4};
	public int solution(int[][] board, int[] moves) {
		int answer=0;
		Stack<Integer> s = new Stack<>();
		s.push(0);
		for(int move : moves) {
			for(int i=0; i<board.length; i++) {
				if(board[i][move-1]!=0) {
					if(s.peek()==board[i][move-1]){
						s.pop();
						board[i][move-1]=0;
						answer+=2;
					}else {
						s.push(board[i][move-1]);
						board[i][move-1]=0;
					}
					break;
				}
			}
		}
		return answer;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PickUpDollPrac p1d = new PickUpDollPrac();
		System.out.println(p1d.solution(board, moves));
	}

}
