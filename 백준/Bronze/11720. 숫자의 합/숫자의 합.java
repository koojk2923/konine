import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
	    
	    	/*
		 * 문제 : 숫자의 합
		 * 번호 : 11720번
		 * 첫번째 방법
		 */
		
        
		int N = scanner.nextInt();		// 입력받을 전체 N의 개수 
		String a = scanner.next();		// 입력받을 전체 숫자를 문자열로 입력을 받는다. 
		scanner.close();
		
		int sum = 0;	// 전체 숫자의 합 초기화
		for(int i = 0; i < N; i++) {
			// charAt()은 해당 문자의 아스키코드 값을 반환하므로 -48 또는 -'0'을 해주어야 우리가 입력받은 숫자 값 그대로를 사용할 수 있다.
			sum = sum + a.charAt(i)-'0'; 
		}
		System.out.println(sum);
	    
	    	/*
		 * 문제 : 숫자의 합
		 * 번호 : 11720번
		 * 두번째 방법
		 */
		
		// BufferReader을 쓰는 방법이자 배열을 사용하지 않고 하는 방법이다.
		// getBytes() : 문자열을 입력받을 때 해당 문자열을 쉽게 각 자릿값을 반환받을 수 있는 방법
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br.readLine();		// N은 쓸모가 없으로 입력만 받는다.
		
		int sum = 0;
		
		// getBytes()는 문자열을 byte 배열로 반환한다.
		// readLine() 으로 읽어 들인 문자를 byte[]로 변환하여 반환되며 for-each 구문을 통해 문자열의 문자를 하나하니씩 읽어들일 수 있다.
		for(byte value : br.readLine().getBytes()) {
			sum = sum + (value - '0');		// 또는 (a-48)
		}
	
		System.out.println(sum);
	    
	    	/*
		 * 문제 : 숫자의 합
		 * 번호 : 11720번
		 * 세번째 방법
		 */
		
		Scanner scanner = new Scanner(System.in);
		scanner.nextInt();
		
		int sum = 0;
		
		for(byte value : scanner.next().getBytes()) {
			sum += (value - '0');
		}
		System.out.println(sum);
	}

    }
}
