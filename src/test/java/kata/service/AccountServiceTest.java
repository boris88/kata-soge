package kata.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import kata.exception.WithdrawException;
import kata.model.Account;
import kata.model.Client;
import kata.model.OperationType;
import kata.service.impl.AccountServiceImpl;

public class AccountServiceTest {
	
	/**
	 * AccountService
	 */
	private AccountService accountService;
	
	private Client client;
	
	private Account account;
	
	public static final String DATE_PATTERN = "dd/MM/yyyy";
	
	/**
	 * Set up method execute before each test
	 * Initialize input for test
	 */
	@Before
	public void setUp(){
		accountService = new AccountServiceImpl();
		client = new Client();
		client.setId("cleint1");
		client.setName("name");
		
		account = new Account();
		account.setId("account1");
		account.setClient(client);
		account.setBalance(BigDecimal.valueOf(100));
	}
	/**
	 * Test deposit method for AccountService in order to save money in account
	 */
	@Test
	public void testDeposit(){
		//Given
		BigDecimal amountToSave = BigDecimal.valueOf(50.1);
		
		//When
		Account accountUpdated = accountService.deposit(account, amountToSave);
		
		//Then
		assertNotNull(accountUpdated);
		assertEquals(account.getId(), accountUpdated.getId());
		assertNotNull(accountUpdated.getClient());
		assertEquals(client.getId(), accountUpdated.getClient().getId());
		assertEquals(BigDecimal.valueOf(150.1), accountUpdated.getBalance());
		
	}
	/**
	 * Test withdraw method for AccountService in order to withdrawal money from account
	 * @throws Exception 
	 */
	@Test
	public void testWithdraw() throws WithdrawException{
		//Given
		BigDecimal amount = BigDecimal.valueOf(55);
		
		//When
		Account accountUpdated = accountService.withdraw(account, amount);
		
		//Then
		assertNotNull(accountUpdated);
		assertEquals(account.getId(), accountUpdated.getId());
		assertNotNull(accountUpdated.getClient());
		assertEquals(client.getId(), accountUpdated.getClient().getId());
		assertEquals(BigDecimal.valueOf(45), accountUpdated.getBalance());
		
	}
	
	/**
	 * Test withdraw method for AccountService in order to withdrawal money from account
	 * throws an exception when amount to withdrawal is great than current saving
	 * @throws Exception
	 */
	@Test(expected = WithdrawException.class)
	public void testWithdrawalThrowsWithdrawException() throws WithdrawException{
		//Given
		BigDecimal amount = BigDecimal.valueOf(100.1);
		
		//When
		accountService.withdraw(account, amount);
	}
	
	@Test
	public void testDepositAndOperation() throws WithdrawException{
		//Given
		BigDecimal amount = BigDecimal.valueOf(50);
		
		//When
		Account accountUpdated = accountService.deposit(account, amount);
		
		//Then
		assertNotNull(accountUpdated);
		assertEquals(account.getId(), accountUpdated.getId());
		assertEquals(BigDecimal.valueOf(150), accountUpdated.getBalance());
		assertNotNull(accountUpdated.getOperations());
		assertEquals(1, accountUpdated.getOperations().size());
		assertEquals(LocalDate.now(), accountUpdated.getOperations().get(0).getDate());
		assertEquals(OperationType.CREDIT.getCode(), accountUpdated.getOperations().get(0).getType().getCode());
		
	}
	
	@Test
	public void testWithdrawAndOperation() throws WithdrawException{
		//Given
		BigDecimal amount = BigDecimal.valueOf(50);
		
		//When
		Account accountUpdated = accountService.withdraw(account, amount);
		
		//Then
		assertNotNull(accountUpdated);
		assertEquals(account.getId(), accountUpdated.getId());
		assertEquals(BigDecimal.valueOf(50), accountUpdated.getBalance());
		assertNotNull(accountUpdated.getOperations());
		assertEquals(1, accountUpdated.getOperations().size());
		assertEquals(LocalDate.now(), accountUpdated.getOperations().get(0).getDate());
		assertEquals(OperationType.DEBIT.getCode(), accountUpdated.getOperations().get(0).getType().getCode());
	}
	
	@Test
	public void testPrintHistory() throws WithdrawException{
		//Given
		BigDecimal amount = BigDecimal.valueOf(50);
		
		//When
		Account accountUpdated = accountService.deposit(account, amount);
		accountUpdated = accountService.deposit(account, amount);
		accountUpdated = accountService.withdraw(account, amount);
		
		ArrayList<String> history = accountService.printHistory(account);
		
		//Then
		assertNotNull(accountUpdated);
		assertEquals(account.getId(), accountUpdated.getId());
		assertEquals(BigDecimal.valueOf(150), accountUpdated.getBalance());
		assertNotNull(accountUpdated.getOperations());
		assertEquals(3, accountUpdated.getOperations().size());
		assertEquals(LocalDate.now(), accountUpdated.getOperations().get(0).getDate());
		assertEquals(LocalDate.now(), accountUpdated.getOperations().get(2).getDate());
		assertEquals(OperationType.CREDIT.getCode(), accountUpdated.getOperations().get(0).getType().getCode());
		assertEquals(OperationType.DEBIT.getCode(), accountUpdated.getOperations().get(2).getType().getCode());
		assertNotNull(history);
		assertEquals(3, history.size());
		assertEquals(new StringBuilder("C")
				.append(",")
				.append(LocalDate.now().format(DateTimeFormatter.ofPattern(DATE_PATTERN)))
				.append(",")
				.append("50")
				.append(",")
				.append("150").toString(), history.get(0));
		assertEquals(new StringBuilder("D")
				.append(",")
				.append(LocalDate.now().format(DateTimeFormatter.ofPattern(DATE_PATTERN)))
				.append(",")
				.append("50")
				.append(",")
				.append("150").toString(), history.get(2));
		assertEquals(new StringBuilder("C")
				.append(",")
				.append(LocalDate.now().format(DateTimeFormatter.ofPattern(DATE_PATTERN)))
				.append(",")
				.append("50")
				.append(",")
				.append("200").toString(), history.get(1));
		
		
		
	}
	
}
