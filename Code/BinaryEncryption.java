// keyde normal text onuda byte olarak alman lazim//
/*
public class BinaryEncryption implements IEncryption{

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

	@Override
	public String encrypt(String plainText, byte key) {
		// TODO Auto-generated method stub
		return null;
	}


}*/
