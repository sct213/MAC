import java.io.File;
import java.util.Date;

public class FileMaker {
	public static void main(String[] args) {
		File f = new File("aaa");
		f.mkdir();
		
		File ff = new File("bbb/ccc/ddd/eee");
		
		ff.mkdirs();
		
		f.setLastModified(new Date().getTime());
		f.setReadOnly();
	}
}