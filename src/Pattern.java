import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Pattern {
	private short[][] object;
	public Pattern(String filename) {
		object = setObject(filename);
		try {
			File file = new File("./"+filename);
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String str = br.readLine();
			int i=0;
			while(str != null) {
				
				for(int j=0;j<str.length();j++) {
					if(str.charAt(i)=='1') {
						object[i][j] = 1;
					}else {
						object[i][j] = 0;
					}
				}
				i++;
			}
			br.close();
		}catch(FileNotFoundException e) {
			System.err.println("Couldn't open file.");
		}catch(IOException e) {
			System.err.println("Failed reading file");
		}
	}

	public short[][] getObject(){
		return object;
	}

	private short[][] setObject(String filename) {
		int row = 0;
		int collum = 0;
		try {
			File file = new File("./"+filename);
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String str = br.readLine();
			
			while(str!=null) {
				collum = Math.max(collum, str.length());
				row++;
				str = br.readLine();
			}
			br.close();
		}catch(FileNotFoundException e) {
			System.err.println("Couldn't open file.");
		}catch(IOException e) {
			System.err.println("Failed reading file");
		}
		return new short[row][collum];
	}
}


