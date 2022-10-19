import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);		
		
		// 입력받을 전체 테스트 개수 
		int testCase = scanner.nextInt(); 	// 개수는 int로 받는다.
		int[] arr;	// 학생의 점수를 배열로 생성해서 받는다.
		

		// 전체 테스트 개수 
		for(int i = 0; i < testCase; i++) {	
		
			int N = scanner.nextInt(); 	// 각 학생의 수(입력)
			arr = new int[N];	// 입력받은 학생의 수만큼 점수 개수를 배열로 생성
			
			
			// 학생의 점수 입력 부분			
			int sum = 0;	// 지역변수 초기화
			for(int j = 0; j < N; j++) {
				
				int score = scanner.nextInt();
	
				arr[j] = score;				// 입력 받은 점수를 배열에 저장 
				sum = sum +  arr[j];		// 전체 점수 더하기
			}
			
			double avg = 0;
			avg = sum / N;		// 입력 받은 점수 평균점수 구하기(학생의 수 N)				
								
			// 평균이상 학생의 비율
			int count = 0; 	// 지역변수 평균 점수 카운트
			for(int k = 0; k < arr.length; k++) {
				if(arr[k] > avg) {
					count++;
				}
			}
			
			double	avg_Over = 0;	//  평균 점수는 넘는 학생의 비율
			avg_Over = (double) count / N * 100; // (평균넘는 점수 개수 / 학생의 수 * 100);
			
			System.out.printf("%.3f", avg_Over);	// 소수점 셋째 자리까지 출력한다.
			System.out.println("%");
			
		}	

		/*
		 * 문제 : 평균은 넘겠지
		 * 번호 : 4344번
		 * 두번째 방법
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 
		int testCase = Integer.parseInt(br.readLine());
		int[] arr;
		
		for(int i = 0; i < testCase; i++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " "); // 학생의 수 및 점수 입력
			
			int N = Integer.parseInt(st.nextToken());	
			arr = new int[N];
			
			// 점수 부분
			int sum = 0;	// 점수 총 합계 
			for(int j = 0; j < N; j++) {
				
				int score = Integer.parseInt(st.nextToken());
				arr[j] = score;
				sum = sum + score;
				
			}
			
			int avg = sum / N;	//(총점수 / 학생의 수)
			int count = 0;	// 평균 넘는 학생의 수
			
			for(int j = 0; j < N; j++) {
				if(arr[j] > avg) {
					count++;
				}
			}
			
			double val = ((double)count / N) * 100;
			System.out.printf("%.3f%%\n", val);
		}
			         
        }
}
