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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
@Table(name="etmheaders")
public class MessageHeader {

	
	
	public MessageHeader(String messageid){
		this.messageid = messageid;
	}
		
	public MessageHeader(String messageHeaderKey,String messageHeaderValue, String messageid) {
		this.messageHeaderKey = messageHeaderKey;
		this.messageHeaderValue = messageHeaderValue;
		this.messageid = messageid;
	}		
	
	private long id;
	private String messageid;
	
	@Column(name = "key")
	@Expose
	@SerializedName("key")
	private String messageHeaderKey;
	
	@Column(name = "value")
	@Expose
	@SerializedName("value")
	private String messageHeaderValue;
		
	@XmlAttribute(name="key", required=true)
	public String getMessageHeaderKey() {
		return messageHeaderKey;
	}

	public void setMessageHeaderKey(String messageHeaderKey) {
		this.messageHeaderKey = messageHeaderKey;
	}
	
	@XmlAttribute(name="value", required=false)
	public String getMessageHeaderValue() {
		return messageHeaderValue;
	}

	public void setMessageHeaderValue(String messageHeaderValue) {
		this.messageHeaderValue = messageHeaderValue;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	@XmlAttribute(name="ID", required=true)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
		
	@Column(name = "messageid")
	@XmlAttribute(name="MessageID", required=true)
	public String getMessageid() {
		return messageid;
	}

	public void setMessageid(String messageid) {
		this.messageid = messageid;
	}	
	
	
	
}
