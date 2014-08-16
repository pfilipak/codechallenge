package controllers.jobs;

import java.util.ArrayList;

import models.Station;
import play.Logger;
import play.jobs.Job;
import structure.ShortestPath;
import controllers.models.MetroResponse;
import controllers.models.ShortestPathResponse;
import controllers.util.ResponseCode;

public class ShortestPathJob extends Job<MetroResponse>{

	private String from;
	private String to;
	ShortestPath shortestPath = new ShortestPath();
	
	public ShortestPathJob(String from, String to){
		this.from = from;
		this.to = to;
	}
	
	@Override
	public MetroResponse doJobWithResult() throws Exception {
		try {
			ArrayList<Station> stations = shortestPath.path(from, to);
			ArrayList<Station> shortestPathStations = new ArrayList<Station>();
			for (Station station : stations) {
				shortestPathStations.add(station.detached());
			}
			return new MetroResponse(ResponseCode.APPROVED, new ShortestPathResponse(shortestPathStations), 0, shortestPathStations.size()-1);
		} catch (Exception e) {
			Logger.error(e, e.getMessage());
			return new MetroResponse(ResponseCode.GENERIC, e.getMessage());
		}
	}

}
