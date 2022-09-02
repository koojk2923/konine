import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
    // Scanner 객체 sc 선언
    Scanner scanner = new Scanner(System.in);    

    int a = scanner.nextInt(); // 값을 입력 받아 int형 변수 a에 저장
    int b = scanner.nextInt(); // 값을 입력 받아 int형 변수 b에 저장
    int c = scanner.nextInt(); // 값을 입력 받아 int형 변수 c에 저장
    
    if(a >= 2 && b <=10000 && c <=10000) {
    
        int result1 = (a+b)%c;
        int result2 = ((a%c)+(b%c))%c;
        int result3 = (a*b)%c;
        int result4 =  ((a%c)*(b%c))%c;
        
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
        System.out.println(result4);
        
    }
    }
}