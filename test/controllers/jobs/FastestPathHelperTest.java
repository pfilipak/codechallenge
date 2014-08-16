package controllers.jobs;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import models.Line;
import models.LineDefinition;
import models.Station;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

import structure.ShortestPath;
import controllers.models.MetroResponse;
import controllers.util.ResponseCode;

public class FastestPathHelperTest {

	private static final String TO = "B";
	private static final String FROM = "A";

	@Test
	public void testDoJob() throws Exception{
		FastestPathHelper job = new FastestPathHelper(FROM, TO);
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
		station.setLineDefinitions(createLineDefinitions());
		return station;
	}

	private Set<LineDefinition> createLineDefinitions() {
		LinkedHashSet<LineDefinition> set = new LinkedHashSet<LineDefinition>();
		set.add(createLineDefinition());
		set.add(createLineDefinition());
		return set;
	}

	private LineDefinition createLineDefinition() {
		LineDefinition lineDefinition = new LineDefinition();
		lineDefinition.setLine(createLine());
		return lineDefinition;
	}

	private Line createLine() {
		Line line = new Line();
		line.setLineId(1);
		return line;
	}
}
