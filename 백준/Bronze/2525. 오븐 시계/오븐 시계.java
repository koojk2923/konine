import java.util.Scanner;

public class Main {
    
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		int a = scanner.nextInt();  // 시(hour)
		int b = scanner.nextInt();  // 분(minute)
		int c = scanner.nextInt();	// 조리시간(minute)
		
		if(a >= 0 && a <= 23) {	// 시(hour) : [0 <= a <= 23]
			if(b >= 0 && b <= 59) {	// 분(minute) : [0 <= b <= 23]
				if(c >= 0 && c <= 1000) {	// 분(minute) - 요리시간 : [0 <= c <= 1000]
					
					// C의 입력값은 0 ~ 1000으로 이를 시분으로 표현하자면 0시간0분 ~ 16시간40분
					
					// 풀이방법 두가지
					// 1.a(시)와 b(분)을 하나의 분으로 변환한 뒤, c를 더하고 나온 결과 값을 다시 시와 분으로 변환하는 방법
					// 2.c를 시와 반으로 변환환 뒤, 각 나위어진 시와 분을 A와 B에 각각 더하여 연산하는 방법
					
					// 첫번째 방법 풀이
					int min = a * 60; // min = a(시)를 분으로 변경
					min = min + b; // 분으로 변경된 min에 b(분)을 더해준다.
					min = min + c; // a*60+b 에 c(분) 요리시간을 더해준다.
					
					// 주의사항 - hour(24 이상일 경우 다시 0부터 시작하도록 24를 나눈 나머지 연산을 필요로 한다.)
					int hour = (min / 60) % 24;	// 다시 시간으로 변경
					int minute = min % 60;	// 다시 분으로 변경
					
					System.out.println(hour + " " + minute);
					
			} // end of minute(요리시간)
		} // end of minute
		
	} // end of hour
	
	} // end of main
} // end of class