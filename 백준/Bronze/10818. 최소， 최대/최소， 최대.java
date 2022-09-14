import java.util.Scanner;
import java.util.Arrays;

public class Main {
    	public static void  main(String[] agrs) {
		
		Scanner scanner = new Scanner(System.in);
		
		int N = scanner.nextInt();
		int[] arr = new int[N]; // 입력받을 N의 정수로 배열의 길이 생성
		
		//System.out.println(Arrays.toString(arr)); // 입력받은 길이는 3개 들어온 값은 없으므로 0출력
		
		for(int i=0; i < N; i++) {
			// arr[i] : 배열의 인덱스 번호는 0부터 시작한다. for문을 써서 배열의 인덱스의 값을 확인하다.
			arr[i] = scanner.nextInt(); // 입력받은 정수를 for문의 반복횟수만큼 arr배열에 값을 넣어준다.
			
		}
		scanner.close();
		// Arrays.sort()메서드의 매개값으로 기본 타입 배열이나 String배열을 지정해주면 자동으로 오름차순 정렬이 됩니다.
		Arrays.sort(arr);
		System.out.println(arr[0] + " " + arr[N-1]);
		
	}
} // end of class