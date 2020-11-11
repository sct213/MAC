import java.io.File;
import java.io.IOException;

class createFile {
	public static void main(String[] args){
	
		File newFile = new File("/Users/dead_line/DEV");
		try {
			if(newFile.createNewFile()){
				System.out.println("파일이 생성되었습니다.");
			}else {
				System.out.println("파일이 생성하지 못했습니다.");
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("예외 처리");
			System.out.println("파일을 생성하는 과정에서 오류가 발생했습니다.");
			}
		}
	}
