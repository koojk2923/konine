import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        		Scanner scanner = new Scanner(System.in);
		
		int[] arr = new int[26];	// 알파벳 a-z까지 총26개 이므로 26개 배열 생성
		
		// 문자열 S 에 각 문자의 위치를 가리킬 배열
		for(int i = 0; i < arr.length; i++) {
			arr[i] = -1;	// 배열의 값을 모두 -1로 초기화
		}
		
		String S = scanner.nextLine();	// 입력 받을 소문자 변수 S
		
		// 단어의 길이만큼 반복문을 돌려준다.
		for(int i = 0; i < S.length(); i++) {
			// 반복문을 통해 문자열의 각 문자를 검사한다 
			char ch = S.charAt(i); 
			
			if(arr[ch - 'a'] == -1) {	// arr 원소 값이 -1인 경우에만 초기화
				arr[ch - 'a'] = i;
			}		
		}
		for(int val : arr) {	// 배열 출력
			System.out.print(val + " ");
		}	
    }
}