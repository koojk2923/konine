import java.util.Scanner;

public class Main{

public static void main(String[] args) { 
		
	Scanner scanner = new Scanner(System.in);
	
	int[] arr = new int[10];
	int count = 0;
	
	
	for(int i=0; i < 10; i++) {	// 10개의 숫자 입력 받고 42로 나눈다.
		arr[i] = scanner.nextInt() % 42;
		}
	
	for(int i=0; i < 10; i++) { // 10개의 중복된 값이 있는지 확인한다.
		int tmp = 0;
		for(int	j=i+1; j < 10; j++) { // i와 j(i+1)을 비교하여 같으면 tmp를 증가
			
			if(arr[i] == arr[j]) {  // i의 0번째와 j 1번째를 비교
				tmp++;		
			}
		}
		if(tmp == 0) { //	i와 j가 값이 같지 않은 경우 count를 더해준다.
			count++;	
		}
	}
	scanner.close();	
    	System.out.println(count);
	}
} // end of main