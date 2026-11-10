package inaki.sw.lib.exchange;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import inaki.sw.lib.dto.BooleanDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.With;

/**
 * @author inaki
 * @since 1.0
 * @deprecated use {@link BooleanDTO}
 */
@Deprecated
@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
@ToString
@With
public class BooleanExchange extends Exchange {

	private static final long serialVersionUID = -4153138054476309244L;

	private Boolean value;

	@Deprecated
	public static BooleanExchange jsonStringToExchange(String json) {
		ObjectMapper mapper = new ObjectMapper();
		BooleanExchange value = new BooleanExchange();
		try {
			value = mapper.readValue(json, BooleanExchange.class);
		} catch (IOException ex) {
			Logger.getLogger(BooleanExchange.class.getName()).log(Level.SEVERE, null, ex);
		}
		return value;
	}
}
