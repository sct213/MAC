package Personal;

public class CodingTest12 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] num = new int[90];
		int hap = 0;
		int j = 11;
		while(j<=100) {
		for(int i=0; i<=100; i++) {
			num[i] = j;
			hap += num[i];
		}
		System.out.printf("hap은 %d ", hap);
		}
	}
}
