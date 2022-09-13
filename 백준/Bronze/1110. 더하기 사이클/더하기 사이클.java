import java.util.Scanner;

public class Main{
    	public static void main(String[] args) {
			
		Scanner scanner = new Scanner(System.in);
		
		int N = scanner.nextInt();
		scanner.close();
		int copy = N;	// 처음입력된 값을 비교해야 하므로 변수를 복사(변수값 복사)
		int count = 0;	//반복문이 몇 번 반복되었는지 세어주는 변수 count;
		
		while(true){
			// N의 일의 자릿수는 새로운 수(T)의 십의자릿수
			// N = (N % 10) * 10;

			// 각 자릿수의 합 : 
			// 10으로 나눈 나머지가 N의 각 자릿수의 합의 일의 자릿수가 T의 일의 자릿수
			// N = ((N / 10) + (N % 10)) % 10
			N = ((N % 10) * 10) + (((N / 10) + (N % 10)) % 10); 
			count++;
			
			if(copy == N) {
				break;
			}
		}
		System.out.println(count);

	}
	
	/*
	 * 1110번
	 * 더하기 사이클 (두번째 방법)
	 */
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		int N = scanner.nextInt();
		scanner.close();
		
		int copy = N;	// 들어온 입력값을 저장해야 한다.
		int count = 0;	// 반복문 몇 번 반복이 되었는지 세어주는 변수;
		
		// do-while문
		do {
			N = ((N % 10) * 10) + (((N / 10) + (N % 10)) % 10);
			count++;
			System.out.println(N);
		} while(copy != N);
		
		System.out.println(count);
	}
	
	
} // end of class
