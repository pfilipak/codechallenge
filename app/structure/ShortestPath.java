package structure;

import java.util.ArrayList;
import java.util.List;

import models.LineDefinition;
import models.Station;
import play.cache.Cache;

public class ShortestPath {

	public ArrayList<Station> path(String from, String to) throws Exception {
			Graph inicial = new Graph();
			Graph resultado = new Graph();
			List<LineDefinition> findAll = LineDefinition.findAll();
			for (LineDefinition lineDefinition : findAll) {
				String station1 = lineDefinition.getStation1().getStationId() + "";
				String station2 = lineDefinition.getStation2().getStationId() + "";
				inicial.addAresta(1, station1, station2);
			}
			Vertice origin = inicial.acharVertice(from);
			Vertice destination = inicial.acharVertice(to);
			resultado.setVertices(inicial.encontrarMenorCaminhoDijkstra(origin,	destination));
			ArrayList<Station> stations = new ArrayList<Station>();
			for (Vertice vertice : resultado.getVertices()) {
				Integer stationId = Integer.parseInt(vertice.getNome());
				Station station = Station.find("stationId", stationId).first();
				stations.add(station);
			}
			Cache.set("shortestPath_" + from + "_" + to, stations, "30s");
			return stations;
	}
	
}
