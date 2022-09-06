import java.util.Scanner;

public class Main{
    
    	public static void main(String[] args) { /* 접근제어자 public, 메모리에 항상 상주하게, 반환값이 없게 함수 main를 선언한다. */
		
		
		Scanner scanner = new Scanner(System.in);
		// Scanner 사용을 위해서 객체 scanner를 선언한다.
		
		int x = scanner.nextInt(); // 값을 입력받아 변수 x에 저장한다.
		int y = scanner.nextInt(); // 값을 입력받아 변수 y에 저장한다.
		
		if(x >= -1000 && x <= 1000 && x != 0) {
			if(y >= -1000 && y <= 1000 && y != 0) {
				if(x >= 1 && y >= 1) { 	// x, y 가 양수일때
					System.out.println(1);
				} else if(x <= -1 && y >= 1) { // x는 음수, y는 양수
					System.out.println(2);
				} else if(x <= -1 && y <= -1) { // x는 음수, y는 음수
					System.out.println(3);
				} else {						// x는 양수, y는 음수
					System.out.println(4);
				}
			} else {
				System.out.println("잘못된 값을 일력했습니다.");
			}
		} else {
			System.out.println("잘못된 값을 입력했습니다.");
		}
	}
}