import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class UserConfig {
	private static final String propertiesFile = "chat.properties";
	private static final String hostPropertieName = "host";
	private static final String portPropertieName = "port";
	private static final String userPropertieName = "user";
	
	static String host;
	static int port;
	static String korisnik;
	
	public static String getHost() {
		return host;
	}

	public static int getPort() {
		return port;
	}

	public static String getKorisnik() {
		return korisnik;
	}

	public static void setHost(String host) {
		UserConfig.host = host;
	}

	public static void setPort(int port) {
		UserConfig.port = port;
	}

	public static void setKorisnik(String korisnik) {
		UserConfig.korisnik = korisnik;
	}

	public static void loadParams() {
		Properties props = new Properties();
		InputStream is = null;
		// Najprije poku�avamo u�itati iz lokalnog direktorija
		//
		try {
			File f = new File(propertiesFile);
			is = new FileInputStream(f);
		}
		catch (Exception e) {
			e.printStackTrace();
			is = null;
		}
		try {
			// poku�avaju se u�itati parametri
			props.load(is);
		} 
		catch (Exception e) {}
		// prvi parametar: naziv postavke
		// drugi parametar: ako nije na�ena vrijednost onda se vra�a drugi
		// parametar
		host = props.getProperty(hostPropertieName, "127.0.0.1");
		port = new Integer(props.getProperty(portPropertieName, "4444"));
		korisnik = props.getProperty(userPropertieName, "anonymous");
	}

	public static void saveParamChanges() {
		try {
			Properties props = new Properties();
			props.setProperty(hostPropertieName, host);
			props.setProperty(portPropertieName, "" + port);
			props.setProperty(userPropertieName, korisnik);
			File f = new File(propertiesFile);
			OutputStream out = new FileOutputStream(f);
			props.store(out, "Opcionalni header komentar");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}
