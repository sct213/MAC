class Tv {
	boolean power;// 전원상태(on/off)
	int channel; // 채널
	
	void power(){ power = !power; } 	
	void channelUp(){ ++channel; }  
	void channelDown(){ --channel; }
}

class CaptionTv extends Tv {
	boolean caption; // 캡션 상태 (ON/OFF)
	void displayCaption(String text){
		if(caption){ // 캡션 상태가 ON(true)일 때만 text를 보여준다.
			System.out.println(text);
		}
	}
}

class abstractTv1 {
	public static void main(String[] args){
		CaptionTv ctv = new CaptionTv();
		ctv.channel = 10;
		ctv.channelUp();
		System.out.println(ctv.channel);
		ctv.displayCaption("Hello, world");
		ctv.caption = true;
		ctv.displayCaption("Hello, world");
	}
}