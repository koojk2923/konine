import java.util.Scanner;

public class Main {
public static void main(String[] args) {
    
    	Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt();
		
		String str;
		for(int i=0; i<N; i++) {
			str = scanner.next();
			
			int sum=0; int count=0;
			for(int j=0; j<str.length(); j++) {
				
				if(str.charAt(j) == 'O') {
					count++;
					sum = sum + count;
				} else if(str.charAt(j) == 'X') {
					count = 0;
				}
			} // 두번째 for문
            System.out.println(sum);
		}// 첫번째 for문
 }
}