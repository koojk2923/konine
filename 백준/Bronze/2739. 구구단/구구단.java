import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		// 2739번
		// N을 입력받은 뒤, 구구단 N단을 출력하는 프로그램을 작성하시오. 출력 형식에 맞춰서 출력하면 된다.
		
		Scanner scanner = new Scanner(System.in);
		
		int result = 0;
		int N = scanner.nextInt();
		
		// N은 1보다 크거나 같고, 9보다 작거나 같다.
		
		if(N >= 1 && N <= 9) {
			for(int i = 1; i < 10; i++ ) {
				result = i;
				// scanner로 입력받은 N도 for문의 갯구만큼 출력해준다.
				System.out.println(N + " * " + result + " = "  + N*result);
			}
			}
	}
} // end of Main