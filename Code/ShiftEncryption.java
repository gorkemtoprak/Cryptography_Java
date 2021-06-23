
/*
import java.nio.ByteBuffer;
import java.util.Scanner;

// harfleri bir saga kaydiricaksin

public class ShiftEncryption implements IEncryption{

	@Override
	public String encrypt(String plainText, byte key) {
		

		Scanner in = new Scanner(System.in);

		System.out.print("Enter message for shift encryption: ");
		plainText = in.nextLine();
		
		System.err.println("Enter the key: ");
		key = in.nextByte();

		String cipherText = "";
		char ch;

		for (int i = 0; i < plainText.length(); i++) {

			ch = plainText.charAt(i);

			if (ch >= 'a' && ch <= 'z') { // Checks if the letters are between a and z
				ch = (char) (ch + key); // Shift letter-character
				if (ch > 'z') { // Again checks if shift alphabet greater than z, reshifting
					ch = (char) (ch + 'a' - 'z' - 1);
				}

				cipherText += ch;
			}

			else {
				cipherText += ch;
			}
		}
		

		System.out.println("Encrypted Message: " + cipherText);
		System.out.println("Key is : " + key);

		return cipherText.toString();

	}

	@Override
	public String binEncr(String plainText, String key) {
		// TODO Auto-generated method stub
		return null;
	}

}
*/