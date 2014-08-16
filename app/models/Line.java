package models;

import handle.StringHandler;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Line extends Model implements MetroModel {

	private Integer lineId;
	private String name;
	private String colour;
	private String stripe;

	@OneToMany(mappedBy = "line")
	private Set<LineDefinition> lineDefinitions;

	@Override
	public void setContent(String content) {
		String[] attributes = content.split(",");
		this.lineId = Integer.parseInt(attributes[0]);
		this.name = new StringHandler().nullHandle(attributes[1]);
		this.colour = new StringHandler().nullHandle(attributes[2]);
		this.stripe = new StringHandler().nullHandle(attributes[3]);
	}

	public Integer getLineId() {
		return lineId;
	}

	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public String getStripe() {
		return stripe;
	}

	public void setStripe(String stripe) {
		this.stripe = stripe;
	}

	@Override
	public void saveOrUpdate() {
		JPAQuery jpaQuery = find("lineId", lineId);
		Line first = jpaQuery.first();
		if (first == null) {
			this.save();
		}
	}

	@Override
	public String[] getHeaders() {
		return new String[] { "line", "name", "colour", "stripe" };
	}

	@Override
	public String toString() {
		return "Line [lineId=" + lineId + ", name=" + name + ", colour="
				+ colour + ", stripe=" + stripe + "]";
	}

	public Set<LineDefinition> getLineDefinitions() {
		return lineDefinitions;
	}

	public void setLineDefinitions(Set<LineDefinition> lineDefinitions) {
		this.lineDefinitions = lineDefinitions;
	}

}
