import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
    // 필요한 말의 계수를 연산해서 반환하면 되는 단순한 문제이다.
    Scanner scanner = new Scanner(System.in);    
    int[] chess = {1,1,2,2,2,8}; // 배열의 길이 : 6
    int[] arr = new int[6]; 
    for(int i=0; i<chess.length; i++){
       arr[i] = scanner.nextInt();
        System.out.println(chess[i]-arr[i]);
    }
    }
}