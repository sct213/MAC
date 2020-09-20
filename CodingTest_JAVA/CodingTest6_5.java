package Personal;
import java.util.*;
public class CodingTest6_5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList ar = new ArrayList();
		
		ar.add("객체지향 프로그래밍");
		ar.add("하둡");
		ar.add("MongoDB");
		
		int i = 1;
		System.out.println("<" + ar.get(0) + ">");
		while(i<ar.size()) {
			System.out.println(ar.get(i));
			i++;
		}
//		int a = 1;
		
//		Iterator it = ar.iterator();
//		
//		while(it.hasNext()) {
//			
//			System.out.println(it.next());
//			if(a==1) {
//				System.out.println("<" + it.next() + ">");
//			}else {
//				System.out.println(it);
//			}
		
	}

}
