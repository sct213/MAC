package Personal;

public class CodingTest5_21 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i=3;
		int j=1;
		
		while(i<=8) {
			if(i==3 || i==5) {
				System.out.println("<" + i + "단>");
			}
			while(j<=9) {
				System.out.println(i + " * " + j + " = " + (i*j));
				j++;
			}
			j=1;
			i++;
			System.out.println();
		}
	}

}
