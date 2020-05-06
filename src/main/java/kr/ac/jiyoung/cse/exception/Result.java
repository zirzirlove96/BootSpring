package kr.ac.jiyoung.cse.exception;

public class Result {
	
	private boolean valid;
	
	private String errorMsg;
	
	private Result(boolean valid, String errorMsg) {
		this.valid = valid;
		this.errorMsg = errorMsg;
	}
	
	public boolean isValid() {
		return valid;
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}
	
	public static Result success() {
		return new Result(true, null);
	}
	
	public static Result failed(String errorMsg) {
		return new Result(false, errorMsg);
	}

}
