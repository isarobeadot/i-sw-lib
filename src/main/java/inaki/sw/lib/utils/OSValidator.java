package inaki.sw.lib.utils;

import java.util.Locale;

/**
 * 
 * @author inaki
 *
 */
public class OSValidator {

	private static final String OS = System.getProperty("os.name").toLowerCase(Locale.ENGLISH);

	private OSValidator() {
	}

	public static boolean isWindows() {
		return OS.contains("win");
	}

	public static boolean isMac() {
		return OS.contains("mac");
	}

	public static boolean isUnixOrLinux() {
		return OS.contains("nix") || OS.contains("nux") || OS.contains("aix");
	}

	public static boolean isSolaris() {
		return OS.contains("sunos");
	}

	public static String returnOSString() {
		if (isWindows()) {
			return "Windows";
		}
		if (isMac()) {
			return "Mac";
		}
		if (isUnixOrLinux()) {
			return "Linux";
		}
		if (isSolaris()) {
			return "Solaris";
		}

		return "Other";
	}

}
