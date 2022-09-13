import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{
    	public static void main(String[] args) throws IOException {
		 /*
		  * BufferedReader
		  * 입력 스트림에서 문자를 읽는 함수
		  * 문자나 배열, 라인들을 효율적으로 읽기 위해 문자들을 버퍼에 저장하고 (버퍼링) 읽는 방법을 취한다.
		  */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 
		/*
		 * readLine()
		 * 데이터 라인 단위로 읽기
		 * return값이 String으로 고정되기 때문에 String이 아닌 다른 타입으로 입력 받으려면 형변환을 해줘야한다.
		 * text를 줄 단위로 읽어들인다.
		 */
		int N = Integer.parseInt(br.readLine());
		
		int copy = N;
		int count = 0;
		
		do {
			N = ((N % 10) * 10) + (((N / 10) + (N % 10)) % 10);
			count++;
		} while(copy != N);
		
		System.out.println(count);
	}
}