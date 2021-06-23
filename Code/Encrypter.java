
// @AUTHOR: Gorkem Toprak

import java.awt.List;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;
import java.io.*;

public class Encrypter implements IEncryption{

	static ArrayList<IEncryption> list = new ArrayList<IEncryption>();
	static ArrayList<Listener> list2 = new ArrayList<Listener>();

	public static void main(String[] args) throws IOException {

		// list.add(new ShiftEncryption());
	
		
		if (register() == true) {
			sendAll(list);
		}

	}
	
	// Shift encryption
	@Override
	public String encrypt(String plainText, byte key) {
		
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

	
	// Binary encryption
	@Override
	public String binEncr(String plainText, String key) {
		
		byte[] by_msg = plainText.getBytes();
		StringBuilder bin_msg = new StringBuilder();
		for (byte b : by_msg) {
			int val = b;
			for (int i = 0; i < 8; i++) {
				bin_msg.append((val & 128) == 0 ? 0 : 1);
				val <<= 1;
				
			}
			bin_msg.append(' ');
			
		}	

		byte[] by_key = key.getBytes();
		StringBuilder bin_key = new StringBuilder();
		for (byte b : by_key) {
			int val = b;
			for (int i = 0; i < 8; i++) {
				bin_key.append((val & 128) == 0 ? 0 : 1);
				val <<= 1;
			}
			bin_key.append(' ');
		}

		System.out.println("Encrypted: " + bin_msg);
		System.out.println("Key is: " + bin_key);

		return bin_msg.toString();
		
	}

	
	public static void sendAll(ArrayList<IEncryption> enc) throws FileNotFoundException {
		Encrypter encr = new Encrypter();
		Scanner input = new Scanner(System.in);
		System.out.println("***************************************************************\n");
		int modifyChoice;
		
		do {
			
			System.out.println("(1) Shift Encryption and Decryption, (2) Binary Encryption and Decryption, (3) Exit to Save");
			modifyChoice = input.nextInt();
			
			switch (modifyChoice) {
			case 1:
				System.out.println("Enter name: ");
				String name = input.next();
				System.out.println("Enter text for Shift Encryption: ");
				String text = input.next();
				System.out.println("Enter the Key: ");
				byte key = input.nextByte();
				String recc = encr.encrypt(text, key);
				General gn = new General();
				gn.decrypt(recc, key);
				Listener listener = new Listener(name, text, Byte.toString(key)); 
				gn.write(name,recc, key);
				enc.add(encr);
				System.out.println(listener);
				break;
			
			case 2: 
				System.out.println("Enter name: ");
				String name1 = input.next();
				System.out.println("Enter text for Binary Encryption: ");
				String txt = input.next();
				System.out.println("Enter the Key: ");
				String keyBin = input.next();
				String rec = encr.binEncr(txt, keyBin);
				System.out.println("----------------------------------------------------------------------");
				Spy sp = new Spy();
				sp.decBin(rec, keyBin);
				Listener listener2 = new Listener(name1, txt, keyBin); 
				sp.write(name1, rec, keyBin);
				enc.add(encr);
				System.out.println(listener2);
				break;	
				
			case 3:
				
				System.out.println("***************************************************************\n");
				System.out.println("Program exited.");
				break;
			
			default:
				System.out.println("Invalid option.");
				break;				
			}			
			
		}
		while (modifyChoice != 3);		
	}

	
	//First the user has to register and their info's record the users.txt

	public static boolean register() throws IOException {

		boolean isLogIn = true;

		Scanner input = new Scanner(System.in);

		int signInChoice;
		String username, password, hashedPassword;
		System.out.println("\t-----WELCOME TO TOPRAK SECURITY SYSTEM-----");
		System.out.println("***************************************************************");
		System.out.println("Please Click 1 to log in, 2 to log out, and 3 to log out. \n");

		do {
			System.out.println("(1) Register, (2) Login, (3) Exit");
			signInChoice = input.nextInt();

			switch (signInChoice) {
			case 1:
				
				System.out.println("Enter a new username: ");
				username = input.next();
				System.out.println("Enter a new password: ");
				password = input.next();

				hashedPassword = sha256(password);
				writeToUsersFile(username, hashedPassword);
				System.out.println("*******************************************");
				break;

			case 2:
				System.out.println("Enter your username: ");
				username = input.next();
				System.out.println("Enter your password: ");
				password = input.next();

				hashedPassword = sha256(password);

				File file = new File("users.txt");
				Scanner sc_file = new Scanner(file);
				while (sc_file.hasNextLine()) {
					String line = sc_file.nextLine();
					String[] split = line.split("#");
					if (username.equals(split[0])) {
						if (hashedPassword.equals(split[1])) {
							System.out.println("Login successful.");
							isLogIn = true;
						}
					}
				}

				if (isLogIn) {
					return isLogIn;
				}
				break;
			case 3:
				System.out.println("Program exited.");
				break;
			default:
				System.out.println("Invalid option");
				// break;
			}

		} while (signInChoice != 3);

		return isLogIn;
	}

	public static void writeToUsersFile(String username, String password) throws IOException {
		PrintWriter pw = new PrintWriter(new FileOutputStream(new File("users.txt"), true));

		pw.printf("%s" + "%n", username + "#" + password);
		pw.close();
	}

	// Sha256 Hashing for password. This method encrypt the passwords randomly.
	public static String sha256(String base) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(base.getBytes("UTF-8"));
			StringBuffer hexString = new StringBuffer();

			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}

			return hexString.toString();

		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

}
