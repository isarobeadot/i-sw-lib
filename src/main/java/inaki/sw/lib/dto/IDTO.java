package inaki.sw.lib.dto;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author inaki
 * @since 1.1
 */
public interface IDTO {

	static <T extends IDTO> T jsonStringToDTO(String json, Class<T> dtoClass) {
		try {
			return new ObjectMapper().readValue(json, dtoClass);
		} catch (IOException ex) {
			Logger.getLogger(IDTO.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}
}
