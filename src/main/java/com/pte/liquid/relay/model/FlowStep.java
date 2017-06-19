package com.pte.liquid.relay.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * JPA entity class for 
 * 
 * @author Paul Tegelaar
 *
 */
@Entity
@Table(name="etmflowsteps")
@XmlRootElement(name="FlowStep")
public class FlowStep {
	
	private long id;
	
	@Expose
	@SerializedName("FlowKey")
	private String flowkey;
	
	
	@Expose
	@SerializedName("Location")
	private String location;
	
	@Expose
	@SerializedName("Position")
	private int position;
		
	public FlowStep(){
		
	}			
	
	public FlowStep(String flowkey, String location, int position) {				
		this.flowkey = flowkey;
		this.location = location;
		this.position = position;
	}

	@Id
	@Column(name = "id")
	@XmlTransient
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name = "flowkey")
	@XmlElement(name="FlowKey")
	public String getFlowkey() {
		return flowkey;
	}
	
	public void setFlowkey(String flowkey) {
		this.flowkey = flowkey;
	}
	
	@Column(name = "location")
	@XmlElement(name="Location")
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	@Column(name = "position")
	@XmlElement(name="Position")
	public int getPosition() {
		return position;
	}
	
	public void setPosition(int position) {
		this.position = position;
	}		

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((flowkey == null) ? 0 : flowkey.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result
				+ ((location == null) ? 0 : location.hashCode());
		result = prime * result + position;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FlowStep other = (FlowStep) obj;
		if (flowkey == null) {
			if (other.flowkey != null)
				return false;
		} else if (!flowkey.equals(other.flowkey))
			return false;
		if (id != other.id)
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (position != other.position)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FlowStep [id=" + id + ", flowkey=" + flowkey + ", location="
				+ location + ", position=" + position + "]";
	}
	

	
}
