import java.util.Scanner;

public class Main{
    
    	public static void main(String [] agrs) {
		
		Scanner scanner = new Scanner(System.in);
		
		// 45분전의 시간을 구하면 되는 문제
		// 입력받은 분(minute)을 45 기준으로 작으면(hour)을 -1, 아닐경우 입력받은 분(minute)에 -45
		// 시(hour)가 0보다 작아질 경우 (hour)을 23으로 수정해주면 된다.
		
		int h = scanner.nextInt(); // 시(hour) 
		int m = scanner.nextInt(); // 분(minute)
		
		if(m < 45) { // 분(minute)이 45분보다 작은지 여부 확인 
			// 45분(minute)보다 작다고 하면
			h--;	// 시(hour) 1 감소			
			m = 60 - (45 - m); // 분(minute) 감소
			
			if(h < 0) { // 시(hour)이 0보다 작다면
				h = 23;  // 시(hour) 23으로 설정
			}
			System.out.println(h + " " + m);
		} else {
			System.out.println(h + " " + (m-45));
		}
		
	}
	
}
