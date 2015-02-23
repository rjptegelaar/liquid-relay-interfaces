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

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pte.liquid.xml.date.DateAdapter;

@XmlRootElement(name="Message")
public class Message {
	
	public Message(){
		setId(UUID.randomUUID().toString());
	}
	
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
	@SerializedName("SystemHeader")
	private List<MessageHeader> systemHeaders = new ArrayList<MessageHeader>();
		
	@Expose
	@SerializedName("Part")
	private List<MessagePart> parts;		
	

	@XmlElement(name="Location")
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@XmlElement(name="ID")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@XmlElement(name="SnapshotTime")
	@XmlSchemaType(name="date")
	@XmlJavaTypeAdapter(DateAdapter.class)
	public Date getSnapshotTime() {
		return snapshotTime;
	}

	public void setSnapshotTime(Date snapshotTime) {
		this.snapshotTime = snapshotTime;
	}

	@XmlElementWrapper(name="Headers")
	@XmlElement(name="Header")
	public List<MessageHeader> getHeaders() {
		return headers;
	}
	
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
		MessagePart part = new MessagePart(label, content);
		part.setMessagePartIndex(getNumberOfParts());
		if(parts==null){
			parts = new ArrayList<MessagePart>();
		}		
		parts.add(part);
	}
	
	public void setHeader(String key, String value){
		this.setHeader(key, value, headers);
	}
	
	public void setSystemHeader(String key, String value){
		this.setHeader(key, value, systemHeaders);
	}
	
	private void setHeader(String key, String value, List<MessageHeader> list){
		if(key!=null && !"".equals(key)){				
			
			if(containsHeader(key)==true){
				MessageHeader newHeader = removeHeader(key);
				newHeader.setMessageHeaderValue(value);
				list.add(newHeader);
			}else{
				MessageHeader newHeader = new MessageHeader(key, value);
				list.add(newHeader);
			}
		}
	}
	
	
	
	public boolean containsHeader(String key){
		return containsHeader(key, headers);
	}
	
	public boolean containsSystemHeader(String key){
		return containsHeader(key, systemHeaders);
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
	
	public String getHeaderValue(String key){
		return getHeaderValue(key, headers);
	}
	
	public String getSystemHeaderValue(String key){
		return getHeaderValue(key, systemHeaders);
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
	
	public MessageHeader getHeader(String key){
		return getHeader(key, headers);
	}

	public MessageHeader getSystemHeader(String key){
		return getHeader(key, systemHeaders);
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
	
	public MessageHeader removeSystemHeader(String key){
		return removeHeader(key, systemHeaders);
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

	@XmlElement(name="CorrelationID")
	public String getCorrelationID() {
		return correlationID;
	}

	public void setCorrelationID(String correlationID) {
		this.correlationID = correlationID;
	}
	
	public int getNumberOfParts(){
		if(parts!=null){
			return parts.size();
		}else{
			return 0;
		}
	}		
	
	@XmlElementWrapper(name="SystemHeaders")
	@XmlElement(name="SystemHeader")
	public List<MessageHeader> getSystemHeaders() {
		return systemHeaders;
	}

	public void setSystemHeaders(List<MessageHeader> systemHeaders) {
		this.systemHeaders = systemHeaders;
	}

	@Override
	public String toString() {
		return "Message [location=" + location + ", id=" + id + ", parentID="
				+ parentID + ", order=" + order + ", correlationID="
				+ correlationID + ", snapshotTime=" + snapshotTime
				+ ", headers=" + headers + ", systemHeaders=" + systemHeaders
				+ ", parts=" + parts + "]";
	}
	
	@XmlElement(name="ParentID")
	public String getParentID() {
		return parentID;
	}

	public void setParentID(String parentID) {
		this.parentID = parentID;
	}


	@XmlElement(name="Order")
	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	
	@XmlElement(name="SnapshotTimeMillis")
	public long getSnapshotTimeMillis() {
		return snapshotTimeMillis;
	}

	public void setSnapshotTimeMillis(long snapshotTimeMillis) {
		this.snapshotTimeMillis = snapshotTimeMillis;
	}
	
	
	
	
	
	

}
