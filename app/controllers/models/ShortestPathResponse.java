package controllers.models;

import java.util.ArrayList;

import models.Station;

public class ShortestPathResponse {

	private ArrayList<Station> stations;

	public ShortestPathResponse(ArrayList<Station> stations) {
		this.setStations(stations);
	}

	public ArrayList<Station> getStations() {
		return stations;
	}

	public void setStations(ArrayList<Station> stations) {
		this.stations = stations;
	}

}
