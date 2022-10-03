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
	
		/*
	 * 나머지 구하기
	 * 3052번 - 두번째 방법
	 * HashSet : Set 인터페이스의 구현 클랙스 이다.
	 * 			 Set은 객체를 중복해서 저장할 수 없고 하나의 null값만 저장, 또한 저장 순서가 유지되지 않습니다.
	 */
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		HashSet<Integer> h = new HashSet<Integer>();
		
		for(int i= 0; i < 10; i++) {
			h.add(scanner.nextInt() % 42);
			// 입력받은 값의 나머지 값을 add메소드를 통해 HashSet에 저장
		}
		
		System.out.println(h.size());
		
	}
	
	/*
	 * 나머지 구하기
	 * 3052번 - 세번째 방법
	 * HashSet : Set 인터페이스의 구현 클랙스 이다.
	 * 			 Set은 객체를 중복해서 저장할 수 없고 하나의 null값만 저장, 또한 저장 순서가 유지되지 않습니다.
	 */
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		HashSet<Integer> hashSet = new HashSet<Integer>();
		
		for(int i=0; i<10; i++) {
			hashSet.add(Integer.parseInt(br.readLine()) %  42);
		}
		
		System.out.println(hashSet.size());
	}
	
	/*
	 * 나머지 구하기
	 * 3052번 - 네번째 방법
	 * boolean 배열을 생성한다.(boolean 배열의 default 값은 fasle 이다.)
	 * 니머지가 나올수 있는 수는 0~41 이므로 길이가 42배열 생성
	 */
	
	public static void main(String[] args) throws IOException {
		
		boolean[] blArr = new boolean[42];
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i=0; i < 10; i++) {
			blArr[Integer.parseInt(br.readLine()) % 42] = true;
			System.out.println(Arrays.toString(blArr));
		}
		
		int count = 0;
		for(boolean value : blArr) {
			
			if(value) { // value가  true라면
				count++;
			}
		}
		System.out.println(count);

	}
} // end of main
