package b_Money;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


//testy nie dzia³aj¹ przez b³¹d w metodzie deposit i setUp w klasie bank

public class AccountTest {
	Currency SEK, DKK;
	Bank Nordea;
	Bank DanskeBank;
	Bank SweBank;
	Account testAccount;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		SweBank.openAccount("Alice");
		testAccount = new Account("Hans", SEK);
		testAccount.deposit(new Money(10000000, SEK));

		SweBank.deposit("Alice", new Money(1000000, SEK));
	}
	
	@Test
	public void testAddRemoveTimedPayment() {
		
		//arrange
		Account myAccount = new Account("Przemek", SEK);
		//act
		myAccount.addTimedPayment("TP1", 30, 1, new Money(100, SEK), SweBank, "Hans");
		//assert
		assertTrue(myAccount.timedPaymentExists("TP1"));
		
		//act
		myAccount.removeTimedPayment("TP1");
		//assert
		assertFalse(myAccount.timedPaymentExists("TP1"));
		
		//myAccount.removeTimedPayment("TP1");	
	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		
		//arrange
		assertEquals(10000000, testAccount.getBalance().getAmount(), 0);
		assertEquals(1000000, SweBank.getBalance("Alice"), 0);
		
		//act
		testAccount.addTimedPayment("TP1", 0, 0, new Money(1000, SEK), SweBank, "Alice");
		testAccount.tick();
		
		//assert
		// Odejmuje za duzo (2x) - trzeba poprawic tick
		assertEquals(10000000 - 1000, testAccount.getBalance().getAmount(), 0);
		assertEquals(1000000 + 1000, SweBank.getBalance("Alice"), 0);
		
	}

	@Test
	public void testAddWithdraw() {
		
		
	//arrange
		Account myAcc = new Account("Patryk", SEK);
		
	//act
		myAcc.deposit(new Money(1000, SEK));
		
	//assert
		assertEquals(1000, myAcc.getBalance().getAmount(), 0);
		
	//act
		myAcc.withdraw(new Money(500, SEK));
		
	//assert
		assertEquals(500, myAcc.getBalance().getAmount(),0);
		
	}
	
	@Test
	public void testGetBalance() {
		
		//arrange
		Account myAcc2 = new Account("Przemo", SEK);
		
		//act
		myAcc2.deposit(new Money(500, SEK));
		
		//assert
		assertEquals(500, myAcc2.getBalance().getAmount(),0);

		
		
	}
	
	
}
