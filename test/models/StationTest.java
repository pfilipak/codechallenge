package models;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StationTest {

	private Station station;

	@Before
	public void setUp(){
		station = new Station();
	}
	
	@Test
	public void testHeaders(){
		String[] expected = new String[] {"id", "latitude", "longitude", "name", "display_name", "zone", "total_lines", "rail" };
		Assert.assertArrayEquals(expected, station.getHeaders());
	}
	
	@Test
	public void testSetContent(){
		station.setContent("1,51.5028,-0.2801,\"Acton Town\",\"Acton<br />Town\",3,2,0");
		Assert.assertEquals(new Integer(1), station.getStationId());
		Assert.assertEquals("Acton Town", station.getName());
		Assert.assertEquals("Acton<br />Town", station.getDisplay_name());
		Assert.assertEquals(new Double(51.5028), station.getLatitude());
		Assert.assertEquals(new Double(-0.2801), station.getLongitude());
		Assert.assertEquals(new Double(3), station.getZone());
		Assert.assertEquals(new Integer(2), station.getTotalLines());
		Assert.assertEquals(new Integer(0), station.getRail());
	}
	
}
