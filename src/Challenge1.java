import java.io.*;
import java.net.URL;

//Write a Web service to fetch an email ID and get a person's name
public class Challenge1 {

	final public static String WEB_ADDRESS = "https://www.ecs.soton.ac.uk/people/";
	final public static int TITLE_TAG_LENGTH = 7;
	
	public static void main(String[] args) throws IOException {
		System.out.println("Enter email id:");
		String emailId = getEmailId();
		String myWebAddress = WEB_ADDRESS + emailId;
		URL myURL = new URL(myWebAddress);
		System.out.println(getName(myURL));
	}
	
	private static String getEmailId() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		return reader.readLine();
	}
	
	private static String getName(URL myURL) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(myURL.openStream()));
		String line;
		
		while ((line = reader.readLine()) != null) {
			if (line.indexOf("<title>") != -1) {
				line = line.trim();
				return line.substring(TITLE_TAG_LENGTH, line.indexOf('|')).trim().equals("People") ? "Not found" : line.substring(TITLE_TAG_LENGTH, line.indexOf('|')).trim();
			}
		}
		return null;
	}
}