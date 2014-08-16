package models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import play.db.jpa.Model;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"stationId1", "stationId2", "lineId"})})
public class LineDefinition extends Model implements MetroModel {

	@JoinColumn(name = "stationId1", referencedColumnName = "stationId")
    @ManyToOne
	private Station station1;
	
	@JoinColumn(name = "stationId2", referencedColumnName = "stationId")
    @ManyToOne
	private Station station2;
	
	@JoinColumn(name = "lineId", referencedColumnName = "lineId")
    @ManyToOne
	private Line line;
	
	@Override
	public void setContent(String content) {
		String[] attributes = content.split(",");
		JPAQuery jpaQuery = Station.find("stationId", Integer.parseInt(attributes[0]));
		this.station1 = jpaQuery.first();
		jpaQuery = Station.find("stationId", Integer.parseInt(attributes[1]));
		this.station2 = jpaQuery.first();
		jpaQuery = Line.find("lineId", Integer.parseInt(attributes[2]));
		this.line = jpaQuery.first();
	}

	@Override
	public String[] getHeaders() {
		return new String[]{"station1", "station2", "line"};
	}
	
	public Station getStation1() {
		return station1;
	}

	public void setStation1(Station station1) {
		this.station1 = station1;
	}

	public Station getStation2() {
		return station2;
	}

	public void setStation2(Station station2) {
		this.station2 = station2;
	}

	public Line getLine() {
		return line;
	}

	public void setLine(Line line) {
		this.line = line;
	}

	@Override
	public void saveOrUpdate(){
		this.save();
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((line == null) ? 0 : line.hashCode());
		result = prime * result
				+ ((station1 == null) ? 0 : station1.hashCode());
		result = prime * result
				+ ((station2 == null) ? 0 : station2.hashCode());
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
		LineDefinition other = (LineDefinition) obj;
		if (line == null) {
			if (other.line != null)
				return false;
		} else if (!line.equals(other.line))
			return false;
		if (station1 == null) {
			if (other.station1 != null)
				return false;
		} else if (!station1.equals(other.station1))
			return false;
		if (station2 == null) {
			if (other.station2 != null)
				return false;
		} else if (!station2.equals(other.station2))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LineDefinition [station1=" + station1 + ", station2="
				+ station2 + ", line=" + line + "]";
	}

}
