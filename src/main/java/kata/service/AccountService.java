package kata.service;

import java.math.BigDecimal;
import java.util.ArrayList;

import kata.exception.WithdrawException;
import kata.model.Account;

public interface AccountService {
	
	/**
	 * Credit account balance with the given amount
	 * @param account account to credit
	 * @param amount amount to add in the account
	 * @return Account account with the new balance
	 */
	public Account deposit(Account account, BigDecimal amount);

	/**
	 * Subtract account balance with the given amount
	 * @param account client account 
	 * @param amount amount to subtract from the account
	 * @return Account account with the new balance
	 * @throws Exception
	 */
	public Account withdraw(Account account, BigDecimal amount) throws WithdrawException;

	/**
	 * Print history of operation on account
	 * @param account client account
	 */
	public ArrayList<String> printHistory(Account account);

}
