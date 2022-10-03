import java.util.Scanner;

public class Main {
    
    public static void main(String[] argrs) {
		Scanner scanner = new Scanner(System.in);

		int N = scanner.nextInt();
		int [] arr = new int[N];
		double max = 0;
		double sum = 0;
		double avg = 0.0;
		
		for(int i = 0; i < arr.length; i++) {
			arr[i] = scanner.nextInt();
			if(arr[i] > max) {
				max = arr[i];
			}
			sum = sum + arr[i];
		}
		scanner.close();
		
		avg = 100 * sum / max / N;
		System.out.println(avg);
	
    }
}