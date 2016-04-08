package kata.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Operation {
	
	private BigDecimal amount;
	
	private LocalDate date;	
	
	private BigDecimal previousBalance;
	
	private Account account;
	
	private OperationType type;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public BigDecimal getPreviousBalance() {
		return previousBalance;
	}

	public void setPreviousBalance(BigDecimal previousBalance) {
		this.previousBalance = previousBalance;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public OperationType getType() {
		return type;
	}

	public void setType(OperationType type) {
		this.type = type;
	}
	
	

}
