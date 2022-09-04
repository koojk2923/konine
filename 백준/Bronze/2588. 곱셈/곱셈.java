import java.util.Scanner;

public class Main {
    
    	public static void main(String [] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		
		int a = scanner.nextInt(); // 세자리 자연수 입력
		int b = scanner.nextInt(); // 두번째 세자리 자연수 입력
		

		// 두번째 세자리 자연수의 1의자리 구하기 과정
		System.out.println(a*(b%10)); // (3)
		// 두번째 세자리 자연수의 10의자리 구하기 과정
		System.out.println(a*((b/10)%10)); // (4)
		// 두번째 세자리 자연수의 100의자리 구하기 과정
		System.out.println(a*(b/100)); // (5)
		// 전체 과정
		System.out.println(a*b);
	}
	
}