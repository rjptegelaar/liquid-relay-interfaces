//Copyright 2014 Paul Tegelaar

//
//Licensed under the Apache License, Version 2.0 (the "License");
//you may not use this file except in compliance with the License.
//You may obtain a copy of the License at
//
//http://www.apache.org/licenses/LICENSE-2.0
//
//Unless required by applicable law or agreed to in writing, software
//distributed under the License is distributed on an "AS IS" BASIS,
//WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//See the License for the specific language governing permissions and
//limitations under the License.
package com.pte.liquid.relay.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pte.liquid.xml.date.DateAdapter;

@Entity
@Table(name="etmmessages")
@XmlRootElement(name="Message")
public class Message {
	
	public Message(){
		setId(UUID.randomUUID().toString());
	}
	
	@Expose
	@SerializedName("NumberOfTimesCrawled")
	private long numberOfTimesCrawled = 0;
	
	@Expose
	@SerializedName("Location")
	private String location;
	
	@Expose
	@SerializedName("ID")
	private String id;	

	@Expose
	@SerializedName("ParentID")
	private String parentID;
	
	@Expose
	@SerializedName("Order")
	private int order;
	
	@Expose
	@SerializedName("CorrelationID")
	private String correlationID;	
	
	@Expose
	@SerializedName("SnapshotTime")
	private Date snapshotTime;
	
	@Expose
	@SerializedName("SnapshotTimeMillis")
	private long snapshotTimeMillis;
	
	
	@Expose
	@SerializedName("Header")
	private List<MessageHeader> headers = new ArrayList<MessageHeader>();
		
	@Expose
	@SerializedName("Part")
	private List<MessagePart> parts;		
	
	@Column(name = "location")
	@XmlElement(name="Location")
	public String getLocation() {
		return location;
	}
		

	public void setLocation(String location) {
		this.location = location;
	}
	
	
	@Column(name = "nrcrawled")
	@XmlElement(name="nrcrawled")
	public long getNumberOfTimesCrawled() {
		return numberOfTimesCrawled;
	}


	public void setNumberOfTimesCrawled(long numberOfTimesCrawled) {
		this.numberOfTimesCrawled = numberOfTimesCrawled;
	}


	@Id
	@Column(name = "messageid")
	@XmlElement(name="ID")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "snapshottime")
    @Temporal(TemporalType.TIMESTAMP)
	@XmlElement(name="SnapshotTime")
	@XmlSchemaType(name="date")
	@XmlJavaTypeAdapter(DateAdapter.class)
	public Date getSnapshotTime() {
		return snapshotTime;
	}

	public void setSnapshotTime(Date snapshotTime) {
		this.snapshotTime = snapshotTime;
	}

	@OneToMany(mappedBy="messageid",cascade = {CascadeType.ALL})
	@XmlElementWrapper(name="Headers")
	@XmlElement(name="Header")
	public List<MessageHeader> getHeaders() {
		return headers;
	}
	
	@OneToMany(mappedBy="messageid",cascade = {CascadeType.ALL})
	@XmlElement(name="Part")
	public List<MessagePart> getParts() {
		return parts;
	}

	public void setParts(List<MessagePart> parts) {
		this.parts = parts;
	}

	public void setHeaders(List<MessageHeader> headers) {
		this.headers = headers;
	}	
	
	public void createPart(String label, String content){
		MessagePart part = new MessagePart(label, content, this.id);
		part.setMessagePartIndex(getNumberOfParts());
		if(parts==null){
			parts = new ArrayList<MessagePart>();
		}		
		parts.add(part);
	}
	
	public void setHeader(String key, String value){
		this.setHeader(key, value, headers);
	}
	
	
	private void setHeader(String key, String value, List<MessageHeader> list){
		if(key!=null && !"".equals(key)){				
			
			if(containsHeader(key)==true){
				MessageHeader newHeader = removeHeader(key);
				newHeader.setMessageHeaderValue(value);
				newHeader.setMessageid(this.id);
				list.add(newHeader);
			}else{
				MessageHeader newHeader = new MessageHeader(key, value, this.id);
				list.add(newHeader);
			}
		}
	}
	
	
	
	public boolean containsHeader(String key){
		return containsHeader(key, headers);
	}
	
	
	private boolean containsHeader(String key, List<MessageHeader> list){
		if(list==null){
			return false;
		}		
		for (MessageHeader header : list) {
			if(header.getMessageHeaderKey().equalsIgnoreCase(key)){
				return true;
			}
		}		
		return false;		
	}
	
	@Transient
	public String getHeaderValue(String key){
		return getHeaderValue(key, headers);
	}
	
	
	private String getHeaderValue(String key, List<MessageHeader> list){		
		if(list==null){
			return null;
		}		
		for (MessageHeader header : list) {
			if(header.getMessageHeaderKey().equalsIgnoreCase(key)){
				return header.getMessageHeaderValue();
			}
		}		
		return null;		

	}	
	
	@Transient
	public MessageHeader getHeader(String key){
		return getHeader(key, headers);
	}

	
	private MessageHeader getHeader(String key, List<MessageHeader> list){
		if(list==null){
			return null;
		}		
		for (MessageHeader header : list) {
			if(header.getMessageHeaderKey().equalsIgnoreCase(key)){
				return header;
			}
		}		
		return null;		
	}
	
	public MessageHeader removeHeader(String key){
		return removeHeader(key, headers);
	}
	
	private MessageHeader removeHeader(String key, List<MessageHeader> list){
		if(list==null){
			return null;
		}		
		for (int i = 0; i < list.size(); i++) {
			MessageHeader header = list.get(i);
			if(header.getMessageHeaderKey().equalsIgnoreCase(key)){
				list.remove(i);
				return header;
			}				
		}
		return null;		
	}

	@Column(name = "correlationid")
	@XmlElement(name="CorrelationID")
	public String getCorrelationID() {
		return correlationID;
	}

	public void setCorrelationID(String correlationID) {
		this.correlationID = correlationID;
	}
	
	@Transient
	public int getNumberOfParts(){
		if(parts!=null){
			return parts.size();
		}else{
			return 0;
		}
	}			
	
	@Override
	public String toString() {
		return "Message [location=" + location + ", id=" + id + ", parentID="
				+ parentID + ", order=" + order + ", correlationID="
				+ correlationID + ", snapshotTime=" + snapshotTime
				+ ", snapshotTimeMillis=" + snapshotTimeMillis + ", headers="
				+ headers + ", parts=" + parts + "]";
	}

	@Column(name = "parentid")
	@XmlElement(name="ParentID")
	public String getParentID() {
		return parentID;
	}

	public void setParentID(String parentID) {
		this.parentID = parentID;
	}

	@Column(name = "messageorder")
	@XmlElement(name="Order")
	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	
	
	@Column(name = "snapshottimemillis")
	@XmlElement(name="SnapshotTimeMillis")
	public long getSnapshotTimeMillis() {
		return snapshotTimeMillis;
	}

	public void setSnapshotTimeMillis(long snapshotTimeMillis) {
		this.snapshotTimeMillis = snapshotTimeMillis;
	}
	
	
	
	
	
	

}
