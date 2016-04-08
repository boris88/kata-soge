package kata.model;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Account {
	
	private String id;
	
	private Client client;
	
	private BigDecimal balance;
	
	private ArrayList<Operation> operations;
	
	public Account(){
		setBalance(BigDecimal.valueOf(0));
		setOperations(new ArrayList<Operation>());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public ArrayList<Operation> getOperations() {
		return operations;
	}

	public void setOperations(ArrayList<Operation> operations) {
		this.operations = operations;
	}

}
