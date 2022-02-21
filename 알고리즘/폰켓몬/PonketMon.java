package algo2;

import java.util.Arrays;
import java.util.Stack;
import java.util.Vector;

public class PonketMon {
	public int[] nums = {3,3,3,2,2,4};
	public Stack<Integer> stack = new Stack<>();
	public int solution(int[] nums) {
		
		Arrays.sort(nums);
		for(int num : nums) {
			System.out.println("num: "+num);
		}
		stack.push(0);
        int answer = 0;
        for(int i=0; i<nums.length; i++) {       	
        	if(stack.peek()!=nums[i])
        	{
        		stack.push(nums[i]);
        		answer++;
        		if(answer>=nums.length/2) {
        			break;
        		}
        		continue;
        	}else {
        	stack.push(nums[i]);
        	}
        	
        }
    
        System.out.println(answer);
        return answer;
    }
	public static void main(String[] args) {
		PonketMon pm = new PonketMon();
		pm.solution(pm.nums);
		
	}

}
