import java.util.Scanner;

public class Main{
    
    public static void main(String[] args) {
	 Scanner scanner = new Scanner(System.in);
	 int N = scanner.nextInt();			//입력 받을 전체갯수 N
	 String[] arr = new String[N];			//입력 받은 개수 만큼 배열을 생성 (두번째로 받는 타입은 문자열)
	 
	 for(int i=0; i<arr.length; i++) {
		arr[i] = scanner.next();
		
	 }
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
			}	// end of for
		System.out.println(sum);
	}	// end for j
    }
}