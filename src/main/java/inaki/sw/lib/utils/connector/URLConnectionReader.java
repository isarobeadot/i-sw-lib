package inaki.sw.lib.utils.connector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.HttpsURLConnection;

/**
 * 
 * @author inaki
 *
 */
public class URLConnectionReader {

	private URLConnectionReader() {
	}

	public static String readURL(String url) {
		return readURL(url, null, null, null);
	}

	public static String readURL(String url, String json) {
		return readURL(url, null, null, json);
	}

	public static String readURL(String url, String username, String password) {
		return readURL(url, username, password, null);
	}

	public static String readURL(String url, String username, String password, String json) {
		StringBuilder output = new StringBuilder();
		try {
			URL address = new URI(url).toURL();
			HttpsURLConnection connection = (HttpsURLConnection) address.openConnection();

			if (username != null && password != null) {
				// Set up basic authentication
				String userCredentials = username + ":" + password;
				String basicAuth = "Basic " + java.util.Base64.getEncoder().encodeToString(userCredentials.getBytes());
				connection.setRequestProperty("Authorization", basicAuth);
			}

			// Write JSON data to the connection's output stream
			if (json != null && !json.isEmpty()) {

				// Set up the connection for sending JSON data
				connection.setRequestMethod("POST");
				connection.setRequestProperty("Content-Type", "application/json");
				connection.setRequestProperty("Accept", "application/json");
				connection.setDoOutput(true);

				try (java.io.OutputStream os = connection.getOutputStream()) {
					byte[] input = json.getBytes(StandardCharsets.UTF_8);
					os.write(input, 0, input.length);
				}
			}

			// Read the response
			try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
				String inputLine;
				while ((inputLine = in.readLine()) != null) {
					output.append(inputLine).append("\n");
				}
			}
		} catch (IOException | URISyntaxException ex) {
			Logger.getLogger(URLConnectionReader.class.getName()).log(Level.SEVERE, null, ex);
		}
		return output.toString();
	}
}
