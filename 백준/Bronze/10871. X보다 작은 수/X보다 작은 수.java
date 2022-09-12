import java.util.Scanner;

public class Main {
    
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		// 입력받을 정수 n
		int n = scanner.nextInt();
		// 입력받을 정수 x
		int x = scanner.nextInt();
		// 배열을 이용해서 정수 받기
		int[] arr = new int[n];
		
		for(int i=0; i < n; i++) {
			arr[i] = scanner.nextInt();	
		}
		scanner.close();
		
		for(int i=0; i < n; i++) {
			if(arr[i] < x) {
			System.out.print(arr[i] + " ");
			}
		}
	} // end of main
} // end of class
