public class Main {
 
	public static void main(String[] args) {
		
		/*
		 * 문제 : 정수 N개의 합(언어제한,함수구현)
		 * 번호 : 15596번
		 * 첫번쨰 방법
		 */
 
		boolean[] check = new boolean[10001];	// 1부터 10000이므로
 
		for (int i = 1; i < 10001; i++){
			int n = d(i);
		
			if(n < 10001){	// 10000 이 넘는 수는 필요가 없음
				check[n] = true;
			}
		}
 
		StringBuilder sb = new StringBuilder();
        
		for (int i = 1; i < 10001; i++) {
			if (!check[i]) {	// false 인 인덱스만 출력
				sb.append(i).append('\n');
			}
		}
		System.out.println(sb);
	}
 
 
 
	public static int d(int number){
		int sum = number;
    
		while(number != 0){
			sum = sum + (number % 10); // 첫 째 자리수
			number = number/10;	// 10을 나누어 첫 째 자리를 없앤다
		}
    
		return sum;
	}
	
		/*
		 * 문제 : 정수 N개의 합(언어제한,함수구현)
		 * 번호 : 15596번
		 * 두번째 방법
		 */
	
		boolean selfNum = true; // 셀프 넘버를 구분하는 변수 선언
		
		for(int i=1; i<=10000; i++) {
			
			// j가 10000까지 돌아가는 동안 10000을 초과하는 수가 나오기 때문에 비효율적
			for(int j=1; j<=10000; j++) {
				// 셀프 넘버가 아니면 false 값을 대입, 반복문 중지
				if(i == d(j)) {
					selfNum = false; break;
				}
			}
			if(selfNum == true) {
				System.out.println(i); // 셀프 넘버이면 출력
			}
			selfNum = true; // 셀프 넘버 구분 초기화
		}
	}
	
	static int d(int n) {
		
		String str = Integer.toString(n);
		
		for(int i=0; i<str.length(); i++) {
			n += Integer.parseInt(str.substring(i, i+1));
		}
		
		return n;
	}

		/*
		 * 문제 : 정수 N개의 합(언어제한,함수구현)
		 * 번호 : 15596번
		 * 세번째 방법
		 */
		
		int index = 10000; // 1~10000까지의 수
		boolean[] nums = new boolean[index]; // 셀프 넘버를 구분할 배열 선언
		
		System.out.println(nums.length);
		for(int i=0; i<nums.length; i++) {
			if(d(i+1) < index+1)
				nums[d(i+1)-1] = true; // 셀프 넘버가 아니면 true를 대입
		}
		for(int i=0; i<nums.length; i++) {
			if(nums[i] == false)
				 // nums[i]이 i+1에 해당하기 때문에 i+1 출력
				System.out.println(i+1);
		}
	}
		
		
	
		public static int d(int n) {
			String str = Integer.toString(n);
			for(int i = 0; i < str.length(); i++) {
				n = n + Integer.parseInt(str.substring(i, i+1));
			}
			
			return n;
		}

}  
