package controllers;

import play.mvc.Before;
import models.Station;
import controllers.jobs.FastestPathHelper;
import controllers.jobs.InsertBatchHelper;
import controllers.jobs.ShortestPathJob;
import controllers.models.MetroResponse;
import controllers.util.ResponseCode;


public class Stations extends MetroController {

	public static void insertBatch() throws Exception {
		String content = params.get("body");
		MetroResponse metroResponse = await(new InsertBatchHelper(content, Station.class).now());
		responseType(metroResponse);
	}

	public static void fromTo(String from, String to) {
		MetroResponse metroResponse = await(new ShortestPathJob(from, to).now());
		responseType(metroResponse);
	}
	
	public static void shortestStopped(String from, String to) {
		MetroResponse metroResponse = await(new ShortestPathJob(from, to).now());
		responseType(metroResponse);
	}

	public static void fastestTime(String from, String to) {
		MetroResponse metroResponse = await(new FastestPathHelper(from, to).now());
		responseType(metroResponse);
	}
	
}
