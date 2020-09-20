// 기본 character 기능
public class character {
	int age;
	String name;
	
	public String attack() {
		String result = "attack success!";
		return result;
	}
	
	public void hello(){
		System.out.println("hello, my name is " + this.name);
	}
}