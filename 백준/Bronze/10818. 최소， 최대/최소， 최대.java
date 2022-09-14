import java.io.BufferedReader;
import java.util.Arrays;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {	
public static void main(String[] args) throws Exception {
			/*
			 * 1차원배열 - 첫번째 방법
			 * 10818번
			 * N개의 정수가 주어진다. 이때, 최솟값과 최댓값을 구하는 프로그램을 작성하시오.
			 */
		public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		// 입력 받을 N개의 정수
		int N = scanner.nextInt();
		// 배열의 크기를 만들어준다.(입력받은 N개의 정수)
		int[] arr = new int[N];
		
		// 배열에 값을 넣기 위해서 i는 0으로 지정 (배열은 인덱스가 0번부터 시작)
		for(int i=0; i < N; i++) {
			// 배열에 값을 넣어줘야 하기 때문에 scanner 사용
			// for문의 반복횟수 만큼 값을 넣어줘야 한다.
			arr[i] = scanner.nextInt();
		}
		scanner.close();
		/*
		 *  자바에서 배열이나 리스트를 정렬하고자 할 때 java.util.Arrays 클래스의 sort() 메서드를 사용하여 간편하게 정렬할 수 있다.
		 *  rrays 클래스는 배열의 복사, 항목, 정렬, 검색과 같은 배열 조작 기능을 가지고 있다.
		 */
		// Arrays.sort() 메서드의 매개 값으로 기본타입 배열이나 String배열을 지정해주면 자동으로 오름차순으로 정렬이 된다.
		Arrays.sort(arr);
		System.out.println(arr[0] + " " + arr[N - 1]);		
	}
	
	
			/*
			 * 1차원배열 - 두번째 방법
			 * 10818번
			 * N개의 정수가 주어진다. 이때, 최솟값과 최댓값을 구하는 프로그램을 작성하시오.
			 */
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
	
		/*
	 * 1차원배열 - 세번째 방법 (배열을 사용하지 않는다.)
	 * 10818번
	 * N개의 정수가 주어진다. 이때, 최솟값과 최댓값을 구하는 프로그램을 작성하시오.
	 * 배열을 사용하지 않는다.
	 * 입력받은 문자를 즉시 비교한다. ( 그러면 시간복잡도가 N 으로 정렬할 필요 없어 시간을 단축시킬 수 있음 ) 
	 */
	
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
				System.out.println("1** " + min);
			}
			if(value > max) {
				max = value;
				System.out.println("2** " + max);
			}
		}
			System.out.println(min + " " + max);	
	}
	
		/*
	 * 1차원배열 - 네번째 방법 (배열을 사용하지 않는다.)
	 * 10818번
	 * N개의 정수가 주어진다. 이때, 최솟값과 최댓값을 구하는 프로그램을 작성하시오.
	 * 배열을 사용하지 않는다.
	 * 입력받은 문자를 즉시 비교한다. ( 그러면 시간복잡도가 N 으로 정렬할 필요 없어 시간을 단축시킬 수 있음 ) 
	 */
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// readLine() 메서드는 값을 읽어올 때, String값으로 개행문자(엔터값)를 포함해 한줄을 전부 읽어오는 방식.
		int N = Integer.parseInt(br.readLine());
		
		int max = -1000000;
		int min = 1000000;
		
		// 
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		// hasMoreTokens()
		// StringTokenizer 클래스 객체에서 다음에 읽어 들일 token이 있으면 true, 없으면 false를 return한다.
		while(st.hasMoreTokens()) {
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
