import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
// 첫번째 방법
		/*
	 * 15552번 
	 * 빠른 A+B
	 * 첫 줄에 테스트케이스의 개수 T가 주어진다. T는 최대 1,000,000이다. 다음 T줄에는 각각 두 정수 A와 B가 주어진다. A와 B는 1 이상, 1,000 이하이다.
	 * 각 테스트케이스마다 A+B를 한 줄에 하나씩 순서대로 출력한다.
	 * 
	 * BufferedReader와 BufferedWriter 클래스의 메소드들은 입출력에러가 발생할 경우 자체적으로 IOException을 던지도록 정의되어있다.
	 */
	
//	public static void main(String[] args) throws IOException {
//		/* BufferedReader
//		 * Scanner는 1024 char만큼의 버퍼를 가지지만, Buffered는 그 8배의 버퍼를 가진다.
//		 * 많의 양의 입출력을 수행할 때는 Buffered 클래스를 활용하는 것이 더 빠르게 처리할 수 있다.
//		 */
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		/*
//		 * BufferedWriter 
//		 * 버퍼를 이용한 출력
//		 * BufferedWriter 의 경우 버퍼를 잡아 놓았기 때문에 반드시 flush() / close() 를 반드시 호출해 주어 뒤처리를 해주어야 한다.
//		 */
//		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//		
//		// 테스트케이스의 개수 : num
//		int num = Integer.parseInt(br.readLine());
//		
//		for(int i=1; i <=num; i++) {
//			String s = br.readLine();
//			
//			// split 이후에 [숫자]를 사용하면 해당하는 순서의 값만 가져온다. index[0] : 첫번째
//			int a = Integer.parseInt(s.split(" ")[0]);
//			int b = Integer.parseInt(s.split(" ")[1]);
//			
//			//bw.write : 문자 또는 문자열 s를 출력한다.
//			bw.write(a+b+ "\n");
//		}
//		bw.flush(); // 남은 값들을 모두 출력시킨다.
//		bw.close(); // 스트림을 종료한다.
	
// 두번째 방법
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
