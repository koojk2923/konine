import java.util.Scanner;

public class Main{
    
    	// 2753번
	// 연도가 주어졌을 때, 윤년이면 1, 아니면 0을 출력하는 프로그램을 작성하시오.
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		int a = scanner.nextInt();
		
		
		if( a >= 1 && a <= 4000) {
			
			if(a % 4 == 0) { // 4의 배수
				if(a % 100 != 0 || a % 400 == 0) { // 나머지가 0이아니여야 ture || 나머지가 0 이여야 ture
					System.out.println(1);
				} else {
					// a%100 == 0 : [false] 이면서  a % 400 =! 0 : [false] 이면 0을 출력
					System.out.println(0);
				}
			} else {
				System.out.println(0);
			}
		} else {
			System.out.println("잘못된 연도를 입력했습니다. 1~4000사이의 연도를 입력해주세요.");
		}
		
	}
	
}