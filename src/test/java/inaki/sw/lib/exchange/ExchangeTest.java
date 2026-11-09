package inaki.sw.lib.exchange;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ExchangeTest {

	@Test
	void parsesIntegerExchangeFromJson() {
		IntegerExchange exchange = IntegerExchange.jsonStringToExchange("{\"value\":42}");

		assertEquals(42, exchange.getValue());
	}

	@Test
	void parsesStringExchangeFromJson() {
		StringExchange exchange = StringExchange.jsonStringToExchange("{\"value\":\"hello\"}");

		assertEquals("hello", exchange.getValue());
	}

	@Test
	void parsesBooleanExchangeFromJson() {
		BooleanExchange exchange = BooleanExchange.jsonStringToExchange("{\"value\":true}");

		assertTrue(exchange.getValue());
	}

	@Test
	void handlesIOExceptionWhenJsonIsInvalid() {
		assertDoesNotThrow(() -> {
			IntegerExchange integerExchange = IntegerExchange.jsonStringToExchange("{\"value\":}");
			assertNull(integerExchange.getValue());
		});

		assertDoesNotThrow(() -> {
			StringExchange stringExchange = StringExchange.jsonStringToExchange("{\"value\":}");
			assertNull(stringExchange.getValue());
		});

		assertDoesNotThrow(() -> {
			BooleanExchange booleanExchange = BooleanExchange.jsonStringToExchange("{\"value\":}");
			assertNull(booleanExchange.getValue());
		});
	}
}