
import java.util.ArrayList;
public class CodingTest6_5 {

	public static void main(String[] args) {
		
		ArrayList ar = new ArrayList<>();
		
		ar.add("객체지향프로그래밍");
		ar.add("하둡");
		ar.add("MongoDB");
		
		int i = 0;
		while(i<ar.size()){
			if(i==0){
				System.out.println("<" + ar.get(i) + ">");
			} else {
				System.out.println(ar.get(i));
			}
			i++;
		}
	}
}