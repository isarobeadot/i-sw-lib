package inaki.sw.lib.exchange;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.With;

/**
 * 
 * @author inaki
 *
 */
@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
@ToString
@With
public class IntegerExchange extends Exchange {

	private static final long serialVersionUID = -4153138054476309244L;

	private Integer value;

	public static IntegerExchange jsonStringToExchange(String json) {
		ObjectMapper mapper = new ObjectMapper();
		IntegerExchange value = new IntegerExchange();
		try {
			value = mapper.readValue(json, IntegerExchange.class);
		} catch (IOException ex) {
			Logger.getLogger(IntegerExchange.class.getName()).log(Level.SEVERE, null, ex);
		}
		return value;
	}
}
