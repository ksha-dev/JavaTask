package runner;

public class PropertiesRunner {
	public static void main(String[] args) {
		String address = System.getProperty("address");
		System.out.println(address);
	}
}
