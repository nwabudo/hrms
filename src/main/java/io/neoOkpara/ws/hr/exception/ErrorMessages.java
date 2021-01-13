package io.neoOkpara.ws.hr.exception;

public enum ErrorMessages {
	
	MISSING_REQUIRED_FIELD("Missing required field. Please check documentation for required fields"),
	RECORD_ALREADY_EXISTS("Record Already Exists"),
	INTERNAL_SERVER_ERROR("Internal Server Error"),
	NO_RECORD_FOUND("Record with Provided Id/UserName is not Found"),
	AUTHENTICATION_FAILED("Incorrect Email and/or Password"),
	COULD_NOT_UPDATE_RECORD("Could not Update record"),
	COULD_NOT_DELETE_RECORD("Could not Delete record"),
	EMAIL_ADDRESS_NOT_VERIFIED("Email Address could not be verified"), 
	COULD_NOT_INSERT_RECORD("Could not Insert record"),
	NOT_EQUAL("Record is not Equal");
	
	private String errorMessage;
	
	ErrorMessages(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
