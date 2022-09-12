import java.util.Scanner;	
    /*
	 * 10951번
	 * 문제 : 두 정수 A와 B를 입력받은 다음, A+B를 출력하는 프로그램을 작성하시오.
    */
public class Main {
	public static void main(String[] args) {
	
		Scanner scanner = new Scanner(System.in);
		
		/*
		 * java.util.Scanner.hasNextInt()
		 * public boolean hasNextInt()
		 * 반환값 : Scanner 객체에 입력값이 int값일 만 ture를 반환
		 */
		while(scanner.hasNextInt()) {
			int a = scanner.nextInt();
            int b = scanner.nextInt();
			System.out.println(a+b);
		}
		
	}
} // end of class