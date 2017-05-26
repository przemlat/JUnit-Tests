package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BankTest {
	Currency SEK, DKK;
	Bank SweBank, Nordea, DanskeBank;
	
	@Before
	public void setUp() throws Exception {
		DKK = new Currency("DKK", 0.20);
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		Nordea = new Bank("Nordea", SEK);
		DanskeBank = new Bank("DanskeBank", DKK);
		SweBank.openAccount("Ulrika");
		SweBank.openAccount("Bob");
		Nordea.openAccount("Bob");
		DanskeBank.openAccount("Gertrud");
	}

	@Test
	public void testGetName() {
		assertEquals("SweBank", SweBank.getName());
	}

	@Test
	public void testGetCurrency() {
		assertEquals(SEK, SweBank.getCurrency());
	}

	@Test
	public void testOpenAccount() throws AccountExistsException, AccountDoesNotExistException {
		try
		{
			SweBank.openAccount("Bob");

		}
		catch (AccountExistsException e)
		{
			System.out.println("Juz istnieje");
		}
		
	}

	@Test
	public void testDeposit() throws AccountDoesNotExistException {
		
		try
		{
			SweBank.deposit("Bob", new Money(10000, SEK));
			assertEquals(10000, SweBank.getBalance("Bob"), 0);

		}
		catch (AccountDoesNotExistException e)
		{
			System.out.println(e);
		}
	}

	@Test
	public void testWithdraw() throws AccountDoesNotExistException {
		
		try
		{
			SweBank.deposit("Bob", new Money(100, SEK));
			assertEquals(100, SweBank.getBalance("Bob"), 0);
			SweBank.withdraw("Bob", new Money(20, SEK));
			
			// Powinno byc 80 a nie 120 - metoda withdraw jest wadliwa
			assertEquals(80, SweBank.getBalance("Bob"), 0);
		
		}
		catch (AccountDoesNotExistException e)
		{
			System.out.println(e);
		}
	}
	
	@Test
	public void testGetBalance() throws AccountDoesNotExistException {

		try
		{
			SweBank.deposit("Bob", new Money(100, SEK));
			assertEquals(100, SweBank.getBalance("Bob"), 0);
			
		}
		catch (AccountDoesNotExistException e)
		{
			System.out.println(e);
		}
	}
	
	@Test
	public void testTransfer() throws AccountDoesNotExistException {
		
		try{
			SweBank.deposit("Bob", new Money(1000, SEK));
			assertEquals(1000, SweBank.getBalance("Bob"), 0);
			assertEquals(0, SweBank.getBalance("Ulrika"), 0);
			
			SweBank.transfer("Bob", "Ulrika", new Money(100, SEK));
			// Mialo byc 900 a jest 1100, czyli cos jest nie tak z metoda transfer	
			assertEquals(900, SweBank.getBalance("Bob"), 0);
			assertEquals(100,  SweBank.getBalance("Ulrika"), 0);
		}
		catch(AccountDoesNotExistException e){
			System.out.println(e);
		}
		

	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		
				try
				{
					SweBank.addTimedPayment("Bob", "TP1", 30, 100, new Money(5000, SEK), DanskeBank, "Gertrud");
					
				}
				catch (AccountDoesNotExistException e)
				{
					System.out.println(e);
				}	

				try
				{
					SweBank.removeTimedPayment("Bob","TP1");
				
				}
				catch (AccountDoesNotExistException e)
				{
					System.out.println(e);
				}
				
	
	}
}
