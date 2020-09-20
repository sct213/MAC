// https://velog.io/@max9106/Java-클래스Class-상속-jpk4c9l01x
public class main{
	public static void main(String[] args){
		
		character marin = new character();
		
		marin.age = 24;
		marin.name = "yang";
		
		marin.hello();
		String attackResult = marin.attack();
		System.out.println(attackResult);
		
		//자식 클래스 
		mainCharacter marin2 = new mainCharacter();
		
		marin2.age = 25;
		marin2.name = "testName";
		marin2.move();
	}
}