import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class General implements IDecryption {

	@Override
	public String decrypt(String encryptedText, byte key) {

		String decryptMessage = "";

		char ch;

		for (int i = 0; i < encryptedText.length(); i++) {

			ch = encryptedText.charAt(i); // Shift one character

			if (ch >= 'a' && ch <= 'z') { // Check if alphabet lies between a and z
				ch = (char) (ch - key); // shift right alphabet
				if (ch < 'a') { // Again checks if shift alphabet lesser than a, reshifting.. harf a ise decrypt
								// yapamicak onun kontrolunu yapiyo cunku a dan kucuk harf yok
					ch = (char) (ch - 'a' + 'z' + 1);
				}
				decryptMessage += ch;
			} 
			else {
				decryptMessage += ch;
			}
		}

		System.out.println("Decrypted Message: " + decryptMessage);
		return null;
	}

	@Override
	public String decBin(String encryptedText, String key) {

		return null;

	}
	
	public static void update(String s) {

	}
	
	
	// Write the encrypted message to the file with user's name message and key
	public void write(String name, String msg, byte key) throws FileNotFoundException {

		PrintWriter pw = new PrintWriter(new FileOutputStream(new File("file.txt"), true));		
		pw.printf("%s" + " "+ "@" + name + " " + "%n ", msg + " " +  "#" + key);
		pw.close();
		
	}

}
