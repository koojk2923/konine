import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        
        int N = scanner.nextInt();
        
        int[] arr = new int[N];
        for(int i = 0 ; i < N; i++) {
            arr[i] = scanner.nextInt();
        }
        
        int V = scanner.nextInt();
        
        int count = 0;
        for(int i = 0; i < arr.length; i++) {
            if(V == arr[i]) {
                count++;
            }
        }
        System.out.println(count);
    }
}