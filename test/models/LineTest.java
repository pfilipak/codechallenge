package models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LineTest {

	private Line line;

	@Before
	public void setUp(){
		line = new Line();
	}
	
	@Test
	public void testHeaders(){
		Assert.assertArrayEquals(new String[] { "line", "name", "colour", "stripe" }, line.getHeaders());
	}
	
	@Test
	public void testSetContent(){
		line.setContent("1,\"Bakerloo Line\",\"ab6612\",NULL");
		Assert.assertEquals(new Integer(1), line.getLineId());
		Assert.assertEquals("Bakerloo Line", line.getName());
		Assert.assertEquals("ab6612", line.getColour());
		Assert.assertEquals("", line.getStripe());
	}
	
}
