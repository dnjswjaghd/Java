package algo1;
import java.util.Vector;

public class PickUpDoll {
	public static int[][] board = {{0,0,0,0,0},{0,0,1,0,3},{0,2,5,0,1},{4,2,4,4,2},{3,5,1,3,1}};
	public static int[] moves = {1,5,3,5,1,2,1,4};
	public static int k=0;
	public static Vector<Integer> v = new Vector<>();
	public int solution(int[][] board, int[] moves) {
		int answer=0;
		
		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board[i].length; j++) {
				if(k<moves.length && board[i][moves[k]-1]!=0 && moves.length<=1000 && board[i][moves[k]-1]<=100 && board[i][moves[k]-1]>=1) {
					v.add(board[i][moves[k]-1]);
					board[i][moves[k]-1]=0;
					i=0;
					j=0;
					System.out.println("포문안k:"+ k);
					k++;
					continue;
				}else if(i==board.length-1&&j==board[board.length-1].length-1&&k<moves.length) {
					k++;
					i=0;
					j=0;
					System.out.println("두번째 if k:"+ k);
					continue;
				}
			}
		}
		for(int i=0; i<v.size(); i++) {
			System.out.println("v.get("+i+"):"+v.get(i));
		}
		System.out.println("k:"+ k);
		
		 for(int b=0; b<v.size(); b++) {
			for(int a=1; a<v.size(); a++) {
				if(v.size()>=2 && v.get(a-1)==v.get(a)) {
					v.remove(a);
					v.remove(a-1);
					v.trimToSize();
					answer +=2;
				}
			}
		 }
		return answer;
	}
	public static void main(String[] args) {
		
		PickUpDoll pd = new PickUpDoll();
		System.out.println(pd.solution(board, moves));
	}

}

