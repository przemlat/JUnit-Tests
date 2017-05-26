package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CurrencyTest {
	Currency SEK, DKK, NOK, EUR;
	
	@Before
	public void setUp() throws Exception {
		/* Setup currencies with exchange rates */
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
	}

	@Test
	public void testGetName() {
		assertEquals("SEK", SEK.getName());
		assertEquals("DKK", DKK.getName());
		assertEquals("EUR", EUR.getName());
	}
	
	@Test
	public void testGetRate() {
		assertEquals(0.15, (double)SEK.getRate(), 0.0);
		assertEquals(0.2, (double)DKK.getRate(), 0.0);
		assertEquals(1.5, (double)EUR.getRate(), 0.0);
	}
	
	@Test
	public void testSetRate() {
		assertEquals(0.15, SEK.getRate(), 0.0);
		
	}
	
	@Test
	public void testGlobalValue() {
		
		assertEquals(150, SEK.universalValue(1000), 0.0);
	}
	
	@Test
	public void testValueInThisCurrency() {
		
		assertEquals(750, DKK.valueInThisCurrency(1000, SEK), 0.0);
	}

}
