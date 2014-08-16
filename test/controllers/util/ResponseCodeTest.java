package controllers.util;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class ResponseCodeTest {

	@Test
	public void testApproved(){
		Assert.assertEquals(new Integer(0), ResponseCode.APPROVED.getCode());
	}
	
	@Test
	public void testGeneric(){
		Assert.assertEquals(new Integer(10), ResponseCode.GENERIC.getCode());
	}

}
