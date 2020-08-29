import java.util.Scanner;
class Untitled {
	public static void main(String[] args) {
		// 1부터 n까지 더하기 
		Scanner sc = new Scanner(System.in);
		
		int i = 1;
		int res = 0;
		
		int n = sc.nextInt();
		
		while(i<=n){
			res += i;
			i++;	
		}
		System.out.println(res);
	}
}