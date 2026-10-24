package inaki.sw.lib.utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import inaki.sw.lib.exchange.BooleanExchange;
import inaki.sw.lib.utils.connector.URLConnectionReader;

/**
 * 
 * @author inaki
 *
 */
public class VChecker implements Runnable {

	public static final String VC_VERSION_AVAILABLE = "VC_VERSION_AVAILABLE";

	private static final String URL_MAIN = "https://inaki-sw.xyz/";
	private ActionListener actionlistener;
	private final String appId;
	private final String version;

	public VChecker(String appId, String version) {
		this.appId = appId;
		this.version = version;
	}

	@Override
	public void run() {
		if (isWebAlive() && isNewerVersionAvailable()) {
			actionlistener.actionPerformed(new ActionEvent(this, 0, VC_VERSION_AVAILABLE));
		}
	}

	public void setActionlistener(ActionListener actionlistener) {
		this.actionlistener = actionlistener;
	}

	private boolean isNewerVersionAvailable() {
		String response = URLConnectionReader.readURL(URL_MAIN + "app/version?appId=" + appId + "&version=" + version
				+ "&os=" + OSValidator.returnOSString());
		return Boolean.TRUE.equals(BooleanExchange.jsonStringToExchange(response).getValue());
	}

	private boolean isWebAlive() {
		boolean isAlive = false;
		try {
			URL u = new URI(URL_MAIN).toURL();
			HttpsURLConnection huc = (HttpsURLConnection) u.openConnection();
			huc.connect();
			isAlive = true;
		} catch (IOException | URISyntaxException ex) {
			//
		}
		return isAlive;
	}

}
