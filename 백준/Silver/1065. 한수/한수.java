import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println(aritmetic_sequence(scanner.nextInt()));
        scanner.close();
    }
    
    public static int aritmetic_sequence(int num) {
        int count = 0;        // 한수 카운팅 
        
        if(num < 100) {
            return num;
        }
        else { // 100 이상의 수들의 각 자릴수가 등차수열인지
			
			// 100 이상의 수가 들어오면 한수의 최소 개수는 99개다.
			count = 99;
			
			for(int i = 100; i <= num; i++) {
				int hun = i / 100;			// 백의 자릿수 -> [결과값 = i / 100]
				int ten = (i / 10) % 10;	// 십의 자릿수 -> [결과값 = (i / 10) % 10]
				int one = i % 10;			// 일의 자릴수 -> [결과값 = i % 10]
				
				if((hun - ten) == (ten - one)) {	// 각 자리숫가 수열을 이루면
					count++;
				}                     
        }
    }
    return count;
 }
}