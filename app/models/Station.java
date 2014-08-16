package models;

import handle.KeyValue;
import handle.StringHandler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import play.db.jpa.Model;

@Entity
public class Station extends Model implements MetroModel {

	private Integer stationId;
	private Double latitude;
	private Double longitude;
	private String name;
	private String display_name;
	private Double zone;
	private Integer totalLines;
	private Integer rail;

	@OneToMany(mappedBy = "station1")
	private Collection<LineDefinition> lineDefinitions1 = new ArrayList<LineDefinition>();
	@OneToMany(mappedBy = "station2")
	private Collection<LineDefinition> lineDefinitions2 = new ArrayList<LineDefinition>();

	@Transient
	private Set<LineDefinition> lineDefinitions = new LinkedHashSet<LineDefinition>();
	
	@Override
	public void setContent(String content) {
		String[] attributes = content.split(",");
		Integer index = 0;

		this.stationId = Integer.parseInt(attributes[index++]);
		this.latitude = Double.parseDouble(attributes[index++]);
		this.longitude = Double.parseDouble(attributes[index++]);

		StringHandler stringHandler = new StringHandler();
		KeyValue keyValue = stringHandler.concatAttribute(attributes[index], attributes, index);
		this.name = keyValue.value;
		index = keyValue.key;

		keyValue = stringHandler.concatAttribute(attributes[index], attributes, index);
		this.display_name = keyValue.value;
		index = keyValue.key;

		this.zone = Double.parseDouble(attributes[index++]);
		this.totalLines = Integer.parseInt(attributes[index++]);
		this.rail = Integer.parseInt(attributes[index++]);
	}

	public Set<LineDefinition> getLineDefinitions() {
		lineDefinitions.addAll(lineDefinitions1);
		lineDefinitions.addAll(lineDefinitions2);
		return lineDefinitions;
	}
	
	public void setLineDefinitions(Set<LineDefinition> lineDefinitions){
		this.lineDefinitions = lineDefinitions;
	}

	public Map<Integer, Line> getLines() {
		Map<Integer, Line> list = new LinkedHashMap<Integer, Line>();
		Iterator<LineDefinition> iterator = getLineDefinitions().iterator();
		while (iterator.hasNext()) {
			LineDefinition next = iterator.next();
			System.out.println(next);
			Line line = next.getLine();
			list.put(line.getLineId(), line);
		}
		return list;
	}

	@Override
	public String[] getHeaders() {
		return new String[] { "id", "latitude", "longitude", "name", "display_name", "zone", "total_lines", "rail" };
	}

	public Integer getStationId() {
		return stationId;
	}

	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplay_name() {
		return display_name;
	}

	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}

	public Double getZone() {
		return zone;
	}

	public void setZone(Double zone) {
		this.zone = zone;
	}

	public Integer getTotalLines() {
		return totalLines;
	}

	public void setTotalLines(Integer totalLines) {
		this.totalLines = totalLines;
	}

	public Integer getRail() {
		return rail;
	}

	public void setRail(Integer rail) {
		this.rail = rail;
	}

	@Override
	public void saveOrUpdate() {
		JPAQuery jpaQuery = find("stationId", stationId);
		Station first = jpaQuery.first();
		if (first == null) {
			this.save();
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((stationId == null) ? 0 : stationId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Station other = (Station) obj;
		if (stationId == null) {
			if (other.stationId != null)
				return false;
		} else if (!stationId.equals(other.stationId))
			return false;
		return true;
	}

	 @Override
	 public String toString() {
	 return "Station [stationId=" + stationId + ", latitude=" + latitude
	 + ", longitude=" + longitude + ", name=" + name
	 + ", display_name=" + display_name + ", zone=" + zone
	 + ", total_lines=" + totalLines + ", rail=" + rail
	 + ", lineDefinitionsSize=" + lineDefinitions.size()
	 + "]";
	 }

	public Station detached() {
		Station stationToList = new Station();
		stationToList.setStationId(getStationId());
		stationToList.setName(getName());
		return stationToList;
	}

}
