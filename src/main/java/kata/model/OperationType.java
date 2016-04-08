package kata.model;

public enum OperationType {
	
	CREDIT("C"), DEBIT("D");
	
	private String code;
	
	private OperationType(String code){
		this.code = code;
	}
	
	public String getCode(){
		return code;
	}

}
