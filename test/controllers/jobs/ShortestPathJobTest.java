package controllers.jobs;

import java.util.ArrayList;

import models.Station;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

import controllers.models.MetroResponse;
import controllers.util.ResponseCode;

import structure.ShortestPath;

public class ShortestPathJobTest {

	private static final String TO = "B";
	private static final String FROM = "A";

	@Test
	public void testDoJob() throws Exception{
		ShortestPathJob job = new ShortestPathJob(FROM, TO);
		job.shortestPath = createShortestPathMock();
		MetroResponse response = job.doJobWithResult();
		Assert.assertEquals(ResponseCode.APPROVED.getCode(), response.getResponseCode());
	}

	private ShortestPath createShortestPathMock() throws Exception {
		ShortestPath mock = EasyMock.createMock(ShortestPath.class);
		EasyMock.expect(mock.path(FROM, TO)).andReturn(createStationList());
		EasyMock.replay(mock);
		return mock;
	}

	private ArrayList<Station> createStationList() {
		ArrayList<Station> list = new ArrayList<Station>();
		list.add(createStation("2,51.5143,-0.0755,\"Aldgate\",NULL,1,2,0"));
		list.add(createStation("7,51.5322,-0.1058,\"Angel\",NULL,1,1,0"));
		return list;
	}

	private Station createStation(String content) {
		Station station = new Station();
		station.setContent(content);
		return station;
	}
}
