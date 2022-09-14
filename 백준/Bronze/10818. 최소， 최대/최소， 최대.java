import java.io.BufferedReader;
import java.util.Arrays;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {	
public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 입력받를 정수 N
		//  readLine() 메서드는 값을 읽어올 때, String값으로 개행문자(엔터값)를 포함해 한줄을 전부 읽어오는 방식.
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int index = 0;
		int[] arr = new int[N];
		
		//- StringTokenizer 클래스 객체에서 다음에 읽어 들일 token이 있으면 true, 없으면 false를 return한다.
		while(st.hasMoreTokens()) {
			// st.nextToken() 은 문자열을 반환하니 Integer.parseInt()로 int 형으로 변환
			arr[index] = Integer.parseInt(st.nextToken()); // 토큰
			index++;
		}
		
		Arrays.sort(arr);
		System.out.print(arr[0] + " " + arr[N - 1]);
	}
}