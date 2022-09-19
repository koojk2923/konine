import java.util.Scanner;

public class Main {
	public static void main(String[] args) {	
		Scanner scanner = new Scanner(System.in);
	
		int[] arr = { scanner.nextInt(), scanner.nextInt(), scanner.nextInt(),
				scanner.nextInt(), scanner.nextInt(), scanner.nextInt(),
				scanner.nextInt(), scanner.nextInt(), scanner.nextInt() };
		scanner.close();
		
		int max = 0; // 가장 큰수 담을 변
		int count = 0;
		int index = 0;
		 	
		//향상된 for문의 주의점은 배열변수만 사용이 가능하다는 점입니다!
		for(int value : arr) {
			count++;
			
			if(value > max) {
				max = value;
				index = count;
			}
		}
		System.out.println(max + "\n" + index);
			
			}
}