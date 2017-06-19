package com.pte.liquid.relay.model;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * JPA Entity class for flows.
 * 
 * @author Paul Tegelaar
 *
 */
@Entity
@Table(name="etmflows")
@XmlRootElement(name="Flow")
public class Flow {
	
	public Flow() {
	
	}
	
	public Flow(String id, String name) {
		this.id = id;
		this.name = name;
	}
		
	@Expose
	@SerializedName("FlowStep")
	private List<FlowStep> flowSteps = new ArrayList<FlowStep>();	
	
	@Expose
	@SerializedName("ID")
	private String id;
	
	@Expose
	@SerializedName("Name")
	private String name;
	
	@Expose
	@SerializedName("NrInstance")
	private Long nrInstances;
	
	@Expose
	@SerializedName("NrInstance24")
	private Long nrInstances24;
	
	@Expose
	@SerializedName("NrInstance12")
	private Long nrInstances12;
	
	@Expose
	@SerializedName("NrInstance6")
	private Long nrInstances6;
	
	@Expose
	@SerializedName("NrInstance1")
	private Long nrInstances1;
	
	@Expose
	@SerializedName("IsWarning")
	private Boolean warning;
	
	@Expose
	@SerializedName("IsError")
	private Boolean error;

	
	@Expose
	@SerializedName("ProcessID")
	private Integer processID;
	
	
	
	@Id
	@Column(name = "id")
	@XmlElement(name="ID")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "name")
	@XmlElement(name="Name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void createFlowStep(String location, int position){
		FlowStep flowStep = new FlowStep(id, location, position);
		flowSteps.add(flowStep);
	}
	
	@OneToMany(mappedBy="flowkey",cascade = {CascadeType.ALL}, fetch=FetchType.LAZY)
	@XmlElementWrapper(name="FlowSteps")
	@XmlElement(name="FlowStep")
	public List<FlowStep> getFlowSteps() {
		return flowSteps;
	}

	public void setFlowSteps(List<FlowStep> flowStep) {
		this.flowSteps = flowStep;
	}

	@Column(name = "nrinstances")
	@XmlElement(name="NrInstances")
	public Long getNrInstances() {
		return nrInstances;
	}

	public void setNrInstances(Long nrInstances) {
		this.nrInstances = nrInstances;
	}
	
	
	
	@Column(name = "nrinstances24")
	@XmlElement(name="NrInstances24")
	public Long getNrInstances24() {
		return nrInstances24;
	}

	public void setNrInstances24(Long nrInstances24) {
		this.nrInstances24 = nrInstances24;
	}

	@Column(name = "nrinstances12")
	@XmlElement(name="NrInstances12")
	public Long getNrInstances12() {
		return nrInstances12;
	}

	public void setNrInstances12(Long nrInstances12) {
		this.nrInstances12 = nrInstances12;
	}

	@Column(name = "nrinstances6")
	@XmlElement(name="NrInstances6")
	public Long getNrInstances6() {
		return nrInstances6;
	}

	public void setNrInstances6(Long nrInstances6) {
		this.nrInstances6 = nrInstances6;
	}

	@Column(name = "nrinstances1")
	@XmlElement(name="NrInstances1")
	public Long getNrInstances1() {
		return nrInstances1;
	}

	public void setNrInstances1(Long nrInstances1) {
		this.nrInstances1 = nrInstances1;
	}

	@Column(name = "processid")
	@XmlElement(name="ProcessID")
	public Integer getProcessID() {
		return processID;
	}

	public void setProcessID(Integer processID) {
		this.processID = processID;
	}

	@Column(name = "iswarning")
	@XmlElement(name="IsWarning")
	public Boolean isWarning() {
		if(warning==null){
			return false;
		}
		return warning;
	}

	public void setWarning(Boolean isWarning) {
		this.warning = isWarning;
	}

	@Column(name = "iserror")
	@XmlElement(name="IsError")
	public Boolean isError() {
		
		if(error==null){
			return false;
		}
		return error;
	}

	public void setError(Boolean isError) {
		this.error = isError;
	}
	
	
	
	
	

}
