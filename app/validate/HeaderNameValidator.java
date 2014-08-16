package validate;

public class HeaderNameValidator implements Validator {

	private String[] headers;
	private String[] headersFromFile;
	private StringBuilder messageError;

	public HeaderNameValidator(String[] headers, String[] headersFromFile){
		this.headers = headers;
		this.headersFromFile = headersFromFile;
	}
	
	@Override
	public boolean hasError() {
		boolean hasError = false;
		messageError = new StringBuilder();
		for (int i = 0; i < headers.length; i++) {
			if (!headers[i].equals(headersFromFile[i].replace("\"", ""))){
				messageError.append("Invalid File. There is not header: ").append(headers[i]).append("\n");
				hasError = true;
			}
		}
		return hasError;
	}

	@Override
	public String getErrorMessage() {
		return messageError.toString();
	}
	 
}
