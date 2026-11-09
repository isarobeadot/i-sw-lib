package inaki.sw.lib.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Locale;

import org.junit.jupiter.api.Test;

class OSValidatorTest {

	@Test
	void platformPredicatesMatchSystemOsName() {
		String osName = System.getProperty("os.name").toLowerCase(Locale.ENGLISH);

		assertEquals(osName.contains("win"), OSValidator.isWindows());
		assertEquals(osName.contains("mac"), OSValidator.isMac());
		assertEquals(osName.contains("nix") || osName.contains("nux") || osName.contains("aix"),
				OSValidator.isUnixOrLinux());
		assertEquals(osName.contains("sunos"), OSValidator.isSolaris());
	}

	@Test
	void returnOSStringUsesTheFirstMatchingPlatform() {
		String osName = System.getProperty("os.name").toLowerCase(Locale.ENGLISH);
		String expected = "Other";
		if (osName.contains("win")) {
			expected = "Windows";
		} else if (osName.contains("mac")) {
			expected = "Mac";
		} else if (osName.contains("nix") || osName.contains("nux") || osName.contains("aix")) {
			expected = "Linux";
		} else if (osName.contains("sunos")) {
			expected = "Solaris";
		}

		assertEquals(expected, OSValidator.returnOSString());
	}
}
