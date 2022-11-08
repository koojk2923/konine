import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
		int N = scanner.nextInt();		// 입력받을 전체 N의 개수 
		String a = scanner.next();		// 입력받을 전체 숫자를 문자열로 입력을 받는다. 
		scanner.close();
		
		int sum = 0;	// 전체 숫자의 합 초기화
		for(int i = 0; i < N; i++) {
			// charAt()은 해당 문자의 아스키코드 값을 반환하므로 -48 또는 -'0'을 해주어야 우리가 입력받은 숫자 값 그대로를 사용할 수 있다.
			sum = sum + a.charAt(i)-'0'; 
		}
		System.out.println(sum);

    }
}