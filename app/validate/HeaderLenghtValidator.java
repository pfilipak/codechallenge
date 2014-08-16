package validate;

public class HeaderLenghtValidator implements Validator {

	private String[] headers;
	private String[] headersFromFile;

	public HeaderLenghtValidator(String[] headers, String[] headersFromFile){
		this.headers = headers;
		this.headersFromFile = headersFromFile;
	}
	
	@Override
	public boolean hasError() {
		return headers.length != headersFromFile.length;
	}

	@Override
	public String getErrorMessage() {
		return new StringBuilder()
		.append("Invalid File. Must have ")
		.append(headers.length)
		.append(" headers in file. I've found ")
		.append(headersFromFile.length)
		.append("/n")
		.toString();
	}

}
