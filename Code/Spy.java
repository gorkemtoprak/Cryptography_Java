import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;

public class Spy implements IDecryption{

	@Override
	public String decrypt(String encryptedText, byte key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String decBin(String encryptedText, String key) {
		

		String[] ss = encryptedText.split(" ");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < ss.length; i++) {
			sb.append((char)Integer.parseInt(ss[i], 2));
		}
		
		byte[] byteskey = key.getBytes();
		StringBuilder binarykey = new StringBuilder();
		for (byte b : byteskey) {
			int val = b;
			for (int i = 0; i < 8; i++) {
				binarykey.append((val & 128) == 0 ? 0 : 1);
				val <<= 1;
			}
			binarykey.append(' ');
		}

		System.out.println("Decrypted key: " + binarykey);
		System.out.println("Decrypted Message: " + sb);
		return null;
	}
	
	public static void update(String s) {

		
		
	}
	
	// Write the encrypted message to the file with user's name message and key
	public void write(String name, String msg, String key) throws FileNotFoundException {

		PrintWriter pw = new PrintWriter(new FileOutputStream(new File("file.txt"), true));		
		pw.printf("%s" + " "+ "@" + name + " " + "%n ", msg + " " +  "#" + key);
		pw.close();
		
	}
	

}
