package validate;

import org.junit.Assert;
import org.junit.Test;

public class HeaderLenghtValidatorTest {

	private HeaderLenghtValidator validator;
	
	@Test
		public void testHasErrorWhenFalse(){
			String[] headers = new String[]{"id", "latitude"};
			String[] headersFromFile = new String[]{"id", "latitude"};
			validator = new HeaderLenghtValidator(headers, headersFromFile);
			Assert.assertFalse(validator.hasError());
		}
	
	@Test
		public void testHasErrorWhenTrue(){
			String[] headers = new String[]{"id", "latitude"};
			String[] headersFromFile = new String[]{"id"};
			validator = new HeaderLenghtValidator(headers, headersFromFile);
		
			Assert.assertTrue(validator.hasError());
			
			String expected = getErrorMessage(headers, headersFromFile);
			Assert.assertEquals(expected, validator.getErrorMessage());
		}
	
	public String getErrorMessage(String[] headers, String[] headersFromFile) {
		return new StringBuilder()
		.append("Invalid File. Must have ")
		.append(headers.length)
		.append(" headers in file. I've found ")
		.append(headersFromFile.length)
		.append("/n")
		.toString();
	}
}
