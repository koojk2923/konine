import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// 테스트케이스의 개수 : num
		int num = Integer.parseInt(br.readLine());
		int sum = 0;
		
		for(int i=1; i <= num; i++) {
			// StringTokenizer(문자열,구분자) -> 구분자를 기준으로 문자열을 분리
			StringTokenizer st = new StringTokenizer(br.readLine()); //공백단위로 읽어드릴수 있는 라인 추가
			// nextToken() : 객체에서 다음 토큰을 반환
			// 1-1. 첫번째 방법
//			int a = Integer.parseInt(st.nextToken()); 
//			int b = Integer.parseInt(st.nextToken());
			
			// 1-2. 두번째 방법
			bw.write((Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken())) + "\n");
			
			//bw.write : 문자 또는 문자열 s를 출력한다.
//			bw.write(a+b+"\n");			
		}
		br.close();
		
		bw.flush(); // 남은 값들을 모두 출시킨다.
		bw.close(); // 스트림을 종료한다.
	} // end of main

} // end of class