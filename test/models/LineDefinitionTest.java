package models;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LineDefinitionTest {

	private LineDefinition lineDefinition;

	@Before
	public void setUp(){
		lineDefinition = new LineDefinition();
	}
	
	@Test
	public void testHeaders(){
		Assert.assertArrayEquals(new String[] { "station1", "station2", "line"}, lineDefinition.getHeaders());
	}

}
