package kata.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import kata.exception.WithdrawException;
import kata.model.Account;
import kata.model.Operation;
import kata.model.OperationType;
import kata.service.AccountService;

public class AccountServiceImpl implements AccountService {
	
	public static final String DATE_PATTERN = "dd/MM/yyyy";

	@Override
	public Account deposit(Account account, BigDecimal amount) {
		if(account != null){
			account.setBalance(account.getBalance().add(amount));
			addOperation(account, amount, OperationType.CREDIT);
		}
		return account;
	}

	@Override
	public Account withdraw(Account account, BigDecimal amount) throws WithdrawException {
		if(account != null){
			if(amount.compareTo(account.getBalance()) == 1){
				throw new WithdrawException();
			}
			account.setBalance(account.getBalance().subtract(amount));
			addOperation(account, amount, OperationType.DEBIT);
		}
		return account;
	}
	
	private void addOperation(Account account, BigDecimal amount, OperationType type){
		Operation operation = new Operation();
		operation.setType(type);
		operation.setDate(LocalDate.now());
		operation.setAmount(amount);
		if(type.getCode().equalsIgnoreCase(OperationType.CREDIT.getCode())){
			operation.setPreviousBalance(account.getBalance().subtract(amount));
		} else {
			operation.setPreviousBalance(account.getBalance().add(amount));
		}
		account.getOperations().add(operation);
	}

	@Override
	public ArrayList<String> printHistory(Account account) {
		ArrayList<String> history = new ArrayList<String>();
		if(account != null) {
			for (Operation operation : account.getOperations()) {
				StringBuilder op = new StringBuilder();
				op.append(operation.getType().getCode());
				op.append(",");
				op.append(operation.getDate().format(DateTimeFormatter.ofPattern(DATE_PATTERN)));
				op.append(",");
				op.append(operation.getAmount());
				op.append(",");
				if(operation.getType().getCode().equalsIgnoreCase(OperationType.CREDIT.getCode())){
					op.append(operation.getPreviousBalance().add(operation.getAmount()));
				} else {
					op.append(operation.getPreviousBalance().subtract(operation.getAmount()));
				}
				history.add(op.toString());
			}
		}
		return history;
		
	}

}
