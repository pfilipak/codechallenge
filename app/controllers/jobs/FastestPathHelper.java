package controllers.jobs;

import java.util.ArrayList;
import java.util.Map;

import models.Line;
import models.Station;
import play.Logger;
import play.cache.Cache;
import play.jobs.Job;
import structure.ShortestPath;
import controllers.models.FastestPathResponse;
import controllers.models.MetroResponse;
import controllers.util.ResponseCode;

public class FastestPathHelper extends Job<MetroResponse>{
	
	private String from;
	private String to;
	ShortestPath shortestPath = new ShortestPath();

	public FastestPathHelper(String from, String to){
		this.from = from;
		this.to = to;
	}

	@Override
	public MetroResponse doJobWithResult() throws Exception {
		Double time = 0.0;
		try {
//			Double timeFromCache = Cache.get("fastestPath_"+from+"_"+to, Double.class);
//			System.out.println(timeFromCache);
//			if (timeFromCache == null){	
				
				ArrayList<Station> stations = shortestPath.path(from, to);
				Line currentLine = null;
				Line previousLine = null;
				int numberOfStations = stations.size();
				for (int i = 0; i < numberOfStations; i++) {
					int previousLineId = 0;
					if (i+1 < numberOfStations){
						Map<Integer, Line> nextLines = stations.get(i+1).getLines();
						Map<Integer, Line> lines = stations.get(i).getLines();
						for (Integer lineDefinition : lines.keySet()) {
							if ((currentLine = nextLines.get(lineDefinition)) != null){
								if (previousLine != null) {
									previousLineId = previousLine.getLineId();
								} 
								previousLine = currentLine;
								break;
							}
						}
						if (previousLineId ==0 || currentLine.getLineId() == previousLineId ){
	//					System.out.println("Line:" + currentLine.getLineId() +", From:" + stations.get(i) + ", to: " + stations.get(i+1) + ", nao troca");
							time+=3;
						} else {
	//					System.out.println("Line:" + currentLine.getLineId() +", From:" + stations.get(i) + ", to: " + stations.get(i+1) + ", troca");
							time+=12;
						}
					}
				}
//				Cache.set("fastestPath_"+from+"_"+to, time, "30s");
//			} else {
//				time = timeFromCache;
//			}
			FastestPathResponse fastestPathResponse = new FastestPathResponse(time, from, to);
			return new MetroResponse(ResponseCode.APPROVED, fastestPathResponse, 0, 1);
		} catch (Exception e) {
			Logger.error(e, e.getMessage());
			return new MetroResponse(ResponseCode.GENERIC, e.getMessage());
		}
			
	}
}
