
public class Listener {

	public String name;
	public String message;
	public String secretKey;

	public Listener(String name, String message, String secretKey) {
		super();
		this.name = name;
		this.message = message;
		this.secretKey = secretKey;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return message;
	}

	public void setSurname(String surname) {
		this.message = surname;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	@Override
	public String toString() {

		return "Listener: [name=" + name + ", message=" + message + ", key=" + secretKey + "]";
	}

	General gen = new General();
	Spy s = new Spy();


}
