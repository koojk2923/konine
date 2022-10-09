import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {            
    public static void main(String[] args) throws IOException {
	
	/*
	 * OX퀴즈
	 * 8958번
	 * 첫번째 방법
	 */
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
			System.out.println(sum);
		} // 두번째 for문
	}// 첫번째 for문	
	    
	/*
	 * OX퀴즈
	 * 8958번
	 * 두번재 방법
	 */
		
	 Scanner scanner = new Scanner(System.in);
	 int N = scanner.nextInt();			//입력 받을 전체갯수 N
	 String[] arr = new String[N];			//입력 받은 개수 만큼 배열을 생성 (두번째로 받는 타입은 문자열)
	 
	 for(int i=0; i<arr.length; i++) {
		arr[i] = scanner.next();
		
	 }
	 System.out.println(arr.length);
	 System.out.println();
	scanner.close();
	
	for(int i=0; i < arr.length; i++) {
		  
		int cnt = 0; 	// 연속횟수
		int sum = 0; 	// 누적합산
		
		for(int j=0; j<arr[i].length(); j++) {
			// length() :  length()는 문자열의 길이를 알고자 할때 사용된다.
			if(arr[i].charAt(j) == 'O' ) {
				// charAt() : String으로 저장된 문자열 중에서 한 글자만 선택해서 char타입으로 변환
				cnt++;
				} else {
					cnt = 0;
				}
			sum = sum + cnt;
		} // end of for
		System.out.println(sum);
	} // end for j	
	    
	/*
 	 * OX퀴즈
	 * 8958번
	 * 세번째 방법
	 */
		
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
	StringBuilder sb = new StringBuilder();
		
	int test_case = Integer.parseInt(br.readLine());	// 테스트 케이스
		
		
	for(int i=0; i < test_case; i++) {
						
		int cnt = 0;
		int sum = 0;
		// getBytes() 메소드는 입력 문자열을 byte 단위의 배열로 반환시켜주는 메소드
		// String문자열을 byte[] 배열로 반환하기 때문에 '한글'을 쓰고자 한다면 toCharArray()를 쓰거나 charAt() 써야한다.
		for(byte value : br.readLine().getBytes()) {
				
			f(value == 'O') {
				cnt++;
				sum = sum + cnt;
			}
			else {
				cnt = 0;
			}
		}
		sb.append(sum).append('\n');
	}
	System.out.println(sb);	    
	    	   
    }// end of main
} // end of class	
