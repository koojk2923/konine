import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

     	Scanner scanner = new Scanner(System.in);
		
		int N = scanner.nextInt();		// 시험 본 과목의 개수 
		double[] arr = new double[N]; 	// 과목의 개수만큼 배열길이 생성
		
		for(int i=0; i<arr.length; i++) {	// 과목의 점수를 입력하기 위해서 배열의 길보다 작게 해서 for문을 돌려준다.
			arr[i] = scanner.nextInt();		// i=0 부터 돌려야 인덱스 0번부터 값을 넣어주기 때문에 i=0으로 설정
		}
		scanner.close();
		
		double sum = 0;
		// Arrays패키지에 있는 sort()를 사용하여 정렬(오름차순으로 정렬이 되고 최댓값을 배열의 마지막 원소이다.)
		Arrays.sort(arr);  
		for(int i = 0; i < arr.length; i++) {
				sum = sum + ((arr[i] / arr[arr.length -1]) * 100);	// 각 점수를 최대값으로 나누어준다. 그리고 다 더해준다.
		}
		System.out.println(sum / arr.length);		// 총합계 나누기 과목의 개수
        
        
    }    
}