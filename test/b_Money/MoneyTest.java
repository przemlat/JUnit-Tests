package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MoneyTest {
	Currency SEK, DKK, NOK, EUR;
	Money SEK100, EUR10, SEK200, EUR20, SEK0, EUR0, SEKn100;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
		SEK100 = new Money(10000, SEK);
		EUR10 = new Money(1000, EUR);
		SEK200 = new Money(20000, SEK);
		EUR20 = new Money(2000, EUR);
		SEK0 = new Money(0, SEK);
		EUR0 = new Money(0, EUR);
		SEKn100 = new Money(-10000, SEK);
	}

	@Test
	public void testGetAmount() {
		assertEquals(10000, SEK100.getAmount(), 0);
	}

	@Test
	public void testGetCurrency() {
		assertEquals(SEK, SEK100.getCurrency());
	}

	@Test
	public void testToString() {
		assertEquals("100.0 SEK" , SEK100.toString());
	}

	@Test
	public void testGlobalValue() {
		assertEquals(1500, SEK100.universalValue(), 0);
	}

	@Test
	public void testEqualsMoney() {
		assertTrue(SEK0.equals(EUR0));
	}

	@Test
	public void testAdd() {
		Money EUR30 = new Money(3000, EUR);
		assertEquals(EUR30.getAmount(), EUR20.add(EUR10).getAmount());
	}

	@Test
	public void testSub() {
		Money EUR30 = new Money(3000, EUR);
		assertEquals(EUR10.getAmount(), EUR30.sub(EUR20).getAmount());
	}

	@Test
	public void testIsZero() {
		assertTrue(SEK0.isZero());
	}

	@Test
	public void testNegate() {
		Money EURmin10 = new Money(-1000, EUR);
		assertEquals(EURmin10.getAmount(), EUR10.negate().getAmount());
	}

	@Test
	public void testCompareTo() {
		assertEquals(0, EUR10.compareTo(EUR10));
		assertEquals(1, EUR20.compareTo(EUR10));
		assertEquals(-1, EUR10.compareTo(EUR20));
	}
}
