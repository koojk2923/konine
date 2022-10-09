import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {            
    public static void main(String[] args) throws IOException {
        		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		
		int test_case = Integer.parseInt(br.readLine());	// 테스트 케이스
		
		
		for(int i=0; i < test_case; i++) {
			
			int cnt = 0;
			int sum = 0;
			// getBytes() 메소드는 입력 문자열을 byte 단위의 배열로 반환시켜주는 메소드
			for(byte value : br.readLine().getBytes()) {
				
				if(value == 'O') {
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
	} // end of OX
}