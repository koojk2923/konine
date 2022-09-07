import java.util.Scanner;

public class Main {
    
    	// 10950번
	// 두 정수 A와 B를 입력받은 다음, A+B를 출력하는 프로그램을 작성하시오.
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		int n = scanner.nextInt();	// 입력 받을 테스트 개수 설정(전체 갯수를 설정)
		
		for(int i=0; i < n; i++) {
			int a = scanner.nextInt(); // 입력 받을 정수 a
			int b = scanner.nextInt(); // 입력 받을 정수 b
			
			System.out.println(a+b);
		}
	}
	
}
