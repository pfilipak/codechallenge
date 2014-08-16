package validate;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class HeaderNameValidatorTest {

	private HeaderNameValidator validator;

	@Test
	public void testHasErrorWhenFalse() {
		String[] headers = new String[] { "id", "latitude" };
		String[] headersFromFile = new String[] { "id", "latitude" };
		validator = new HeaderNameValidator(headers, headersFromFile);

		Assert.assertFalse(validator.hasError());
	}

	@Test
	public void testHasErrorWhenTrue() {
		String[] headers = new String[] { "id", "latitude" };
		String[] headersFromFile = new String[] { "id", "longitude" };
		validator = new HeaderNameValidator(headers, headersFromFile);

		Assert.assertTrue(validator.hasError());
		String expected = "Invalid File. There is not header: latitude\n";
		Assert.assertEquals(expected, validator.getErrorMessage());
	}
}
