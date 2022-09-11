import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedWriter;
 
public class Main {
		public static void main(String[] args) throws IOException {
		/* BufferedReader
		 * Scanner는 1024 char만큼의 버퍼를 가지지만, Buffered는 그 8배의 버퍼를 가진다.
		 * 많의 양의 입출력을 수행할 때는 Buffered 클래스를 활용하는 것이 더 빠르게 처리할 수 있다.
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		/*
		 * BufferedWriter 
		 * 버퍼를 이용한 출력
		 * BufferedWriter 의 경우 버퍼를 잡아 놓았기 때문에 반드시 flush() / close() 를 반드시 호출해 주어 뒤처리를 해주어야 한다.
		 */
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// 테스트 케이스 num 
		int num = Integer.parseInt(br.readLine());
		
		// StringTokenizer 클래스는 문자열을 우리가 지정한 구분자로 문자열을 쪼개주는 클래스
		StringTokenizer st;
		for(int i=1; i <= num; i++) {
			st = new StringTokenizer(br.readLine()," ");  //공백단위로 읽어드릴수 있는 라인 추가
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(a > 0 && b < 10) {
				bw.write("Case #" + i + ": " +(a+b) + "\n");
			}

		}
		br.close();	// br 스트림 닫기
		bw.flush(); // 남은 값들을 모두 출력시킨다.
		bw.close(); // bw 스트림 닫기
	} // end of  main

} // end of class
