

public interface IEncryption {
	
	
	String encrypt(String plainText, byte key);
	
	String binEncr(String plainText, String key);

	
}
