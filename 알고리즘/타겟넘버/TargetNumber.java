package algo9;

public class TargetNumber {
	int[] nb = {1,1,1,1,1};
	int tg = 3;
	int i = 0;
	
        static int answer=0;
        
        // 3. dfs(numbers, target, idx:�� ��° �ε�������, sum: idx���� ������ ��).
        public void dfs(int[] numbers,int target,int idx,int sum){
        	
            // 4. ��� ������ Ž������ ��,
            if(idx == numbers.length){   
                // 5. �������� target�� �����ϸ� answer++ �� �޼ҵ� ����.
                if(sum == target) answer++;
                return;
            }
            
            // 6. ���� �ε����� ������ +�� �����ش�.
            sum+=numbers[idx];
            System.out.println("sum1: "+sum+" i: "+i);
            i++;
            // 7. ���� �ε��� DFS ����.
            dfs(numbers,target,idx+1,sum);
            // 8. 6.�� ���� �ٽ� ���� ��,
            sum-=numbers[idx];
            System.out.println("sum2: "+sum+" i: "+i);
            // 9. ���� �ε����� ������ -�� �����ش�.
            sum+=(-1 * numbers[idx]);
            System.out.println("sum3: "+sum+" i: "+i);
            
            // 10. �ٽ� ���� �ε��� dfs ����.
            dfs(numbers,target,idx+1,sum);
        }
        public int solution(int[] numbers, int target) {
        	 // 1. answer�� ���������� ����. 
        	answer = 0;
             
             // 2. dfs����.
             dfs(numbers,target,0,0);
             
    		return answer;
    	}
	public static void main(String[] args) {
		TargetNumber tn = new TargetNumber();
		tn.solution(tn.nb, tn.tg);
	}

}
