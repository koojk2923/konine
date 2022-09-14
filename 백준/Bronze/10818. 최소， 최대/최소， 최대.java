import java.io.BufferedReader;
import java.util.Arrays;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main {	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 입력 받을 N개의 정수
		int N = Integer.parseInt(br.readLine());
		// 입력 받을 a개의 정수(여러개 받기)
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int max = -10000000;
		int min = 1000000;
		
		for(int i=1; i <= N; i++) {
			int value = Integer.parseInt(st.nextToken());
			if(value < min) {
				min = value;
			}
			if(value > max) {
				max = value;
			}
		}
			System.out.println(min + " " + max);	
	}
}