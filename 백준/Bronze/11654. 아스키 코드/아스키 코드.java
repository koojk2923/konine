import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		/*
		 * 문제 : 아스키코드
		 * 번호 : 11654번
		 * 첫번째 방법
		 */
		
		Scanner scanner = new Scanner(System.in);
		
		int ch = scanner.nextLine().charAt(0);
		
		System.out.println(ch);
		
		/*
		 * 문제 : 아스키코드
		 * 번호 : 11654번
		 * 두번째 방법
		 */
		
		// System.in은 byte 값으로 문자 핸 개만 읽으면서 해당 문자에 대응되는 아스키코드 값을 저장할 수 있다.
		int a = System.in.read();
		System.out.println(a);
    }
}
