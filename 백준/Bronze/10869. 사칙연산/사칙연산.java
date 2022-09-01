import java.util.Scanner;

public class Main {
	public static void main(String [] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		int a = scanner.nextInt();
		int b = scanner.nextInt();
		
		if(a >= 1 && b <= 10000) {
			System.out.println(a+b);
			System.out.println(a-b);
			System.out.println(a*b);
			System.out.println(a/b);
			System.out.println(a%b);
		} else {
			System.out.println("잘못된 자연수를 입력 했다.");
		}
	}
}