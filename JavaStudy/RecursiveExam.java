public class RecursiveExam {
	
	private int count(int idx){
		System.out.println("idx" + idx);
		if(idx == 0) return 0;
		return count(idx -1);
	}
	
	public void run() {
		int result = count(10);
		System.out.println("result" + result);
	}
	
	public static void main(String[] args){
		new RecursiveExam().run();
	}
}