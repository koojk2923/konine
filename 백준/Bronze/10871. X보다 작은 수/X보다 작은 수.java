import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main {
    
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] sts = br.readLine().split(" ");
		// 입력받을 정수 N (총개수 지정)
		int N = Integer.parseInt(sts[0]);
		// 입력받고 비교할 정수 X
		int X = Integer.parseInt(sts[1]);
		
		// StringTokenizer 클래스는 문자열을 우리가 지정한 구분자로 문자열을 쪼개주는 클래스
		/*
		 * 주관적인
		 * StringTokenizer를 for문 바깥에 작성시 여러개의 숫자를 입력받으면 for문안에 nextToke() 하나씩 꺼내서
		 * for문의 횟수만큼 출력해준다.
		 */
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		for(int i=1; i <= N; i++) {
//		 StringTokenizer st = new StringTokenizer(br.readLine()," ");
			/**
			 * 주관적인
			 * 만약 for문 안에 StringTokenizer 를 사용하면 반복회는 회숫만큼 입력도 받아야하기 때문에
			 * 입력받은 숫자의 개수만큼 nextToken을 사용해줘야 한다.???
			 */
			int a = Integer.parseInt(st.nextToken());
			if(a < X) {
				System.out.print(a + " ");
			}

		}
	}
	
	/*
	 * 두번째 방법
	 * 정수 N개로 이루어진 수열 A와 정수 X가 주어진다. 이때, A에서 X보다 작은 수를 모두 출력하는 프로그램을 작성하시오.
	 */
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] sts = br.readLine().split(" ");
		// 입력받을 정수 N (총개수 지정)
		int N = Integer.parseInt(sts[0]);
		// 입력받고 비교할 정수 X
		int X = Integer.parseInt(sts[1]); 
		
		// StringTokenizer 클래스는 문자열을 우리가 지정한 구분자로 문자열을 쪼개주는 클래스
		/*
		 * 주관적인
		 * StringTokenizer를 for문 바깥에 작성시 여러개의 숫자를 입력받으면 for문안에 nextToke() 하나씩 꺼내서
		 * for문의 횟수만큼 출력해준다.
		 */
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		for(int i=1; i <= N; i++) {
//		 StringTokenizer st = new StringTokenizer(br.readLine()," ");
			/**
			 * 주관적인
			 * 만약 for문 안에 StringTokenizer 를 사용하면 반복회는 회숫만큼 입력도 받아야하기 때문에
			 * 입력받은 숫자의 개수만큼 nextToken을 사용해줘야 한다.???
			 */
			int a = Integer.parseInt(st.nextToken());
			if(a < X) {
				System.out.print(a + " ");
			}

		}
	}
	
	
} // end of class
