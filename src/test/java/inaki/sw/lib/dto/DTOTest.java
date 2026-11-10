package inaki.sw.lib.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

class DTOTest {

	private final ObjectMapper mapper = new ObjectMapper();

	@Test
	void serializesAndDeserializesIntegerDTO() throws Exception {
		IntegerDTO dto = new IntegerDTO(42);

		assertEquals("{\"value\":42}", mapper.writeValueAsString(dto));
		assertEquals(dto, mapper.readValue("{\"value\":42}", IntegerDTO.class));
		assertEquals(dto, IDTO.jsonStringToDTO("{\"value\":42}", IntegerDTO.class));
	}

	@Test
	void serializesAndDeserializesStringDTO() throws Exception {
		StringDTO dto = new StringDTO("hello");

		assertEquals("{\"value\":\"hello\"}", mapper.writeValueAsString(dto));
		assertEquals(dto, mapper.readValue("{\"value\":\"hello\"}", StringDTO.class));
		assertEquals(dto, IDTO.jsonStringToDTO("{\"value\":\"hello\"}", StringDTO.class));
	}

	@Test
	void serializesAndDeserializesBooleanDTO() throws Exception {
		BooleanDTO dto = new BooleanDTO(true);

		assertEquals("{\"value\":true}", mapper.writeValueAsString(dto));
		assertEquals(dto, mapper.readValue("{\"value\":true}", BooleanDTO.class));
		assertEquals(dto, IDTO.jsonStringToDTO("{\"value\":true}", BooleanDTO.class));
	}

	@Test
	void returnsNullWhenJsonIsInvalid() {
		assertNull(IDTO.jsonStringToDTO("{\"value\":}", IntegerDTO.class));
		assertNull(IDTO.jsonStringToDTO("{\"value\":}", StringDTO.class));
		assertNull(IDTO.jsonStringToDTO("{\"value\":}", BooleanDTO.class));
	}
}
