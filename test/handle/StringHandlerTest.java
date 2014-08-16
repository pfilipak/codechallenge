package handle;

import static org.junit.Assert.*;

import models.Line;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StringHandlerTest {

	private StringHandler handler;

	@Before
	public void setUp(){
		handler = new StringHandler();
	}
	
	@Test
	public void testNullHandlerWhenNULLString(){
		Assert.assertEquals("", handler.nullHandle("NULL"));
	}
	
	@Test
	public void testNullHandlerWhenHasQuotes(){
		Assert.assertEquals("Paulo", handler.nullHandle("\"Paulo\""));
	}
	
	@Test
	public void testConcatAttribute(){
		KeyValue keyValue = handler.concatAttribute("Paulo 1", new String[]{"1","\"Paulo 1",",2\"","9"}, 1);
		Assert.assertEquals("Paulo 1,2", keyValue.value);
	}
	
}
