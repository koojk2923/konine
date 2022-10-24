import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
       
        /*
		 * 문제 : 개수 세기
		 * 번호 : 10807번
		 * 첫번째 방법 
		 */
        
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
        
        /*
		 * 문제 : 개수 세기
		 * 번호 : 10807번
		 * 두번째 방법 
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 총 전체 개수 N
		int N = Integer.parseInt(br.readLine());
		// N개의 크기 만큼 배열
		int[] arr = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int  i = 0; i < N; i++) {
			
			arr[i] = Integer.parseInt(st.nextToken());
			
		}
		
		int V = Integer.parseInt(br.readLine());
		
		int count = 0;
		for(int i = 0; i < arr.length; i++) {
			
			if(V == arr[i]) {
				count++;
			}
		}
		System.out.println(count);
        
		/*
		 * 문제 : 개수 세기
		 * 번호 : 10807번
		 * 세번째 방법 
		 */

		// hashMap , getOrDefault (Object Key, V defaultValue) 이용
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		Map<Integer, Integer> map = new HashMap<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		while(st.hasMoreTokens()) {
			int m = Integer.parseInt(st.nextToken());
			// m을 map에 count를 1씩 증가 시킨다.
			map.put(m, map.getOrDefault(m, 0) + 1);
		}
		
		int V = Integer.parseInt(br.readLine());
		//map에 있으면 map에 저장되어 있는 값을 출력하고 없으면 0 출력
		if(map.containsKey(V)) {
			System.out.print(map.get(V));
		} else {
			System.out.print(0);
		}
		
		System.out.println(map);
		
    }
}
