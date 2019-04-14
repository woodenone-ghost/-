package zhangjie.exception;

public class MyException extends RuntimeException{

	private static final long serialVersionUID = 1661085533698666123L;
	
	public String errorMessage;
	
	public MyException(String errorMessage) {
		super(errorMessage);
		this.errorMessage=errorMessage;
	}
	
	public MyException(String errorMessage,Throwable t) {
		super(errorMessage,t);
		this.errorMessage=errorMessage;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public String toString() {
		return "MyException ["+errorMessage+"]";
	}
}
