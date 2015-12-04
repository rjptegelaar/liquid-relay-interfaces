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
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
@Table(name="etmparts")
public class MessagePart {

	
	private long id;	
	
	
	@Expose
	@SerializedName("messageid")
	private String messageid;
	
	
	@Expose
	@SerializedName("label")
	private String messagePartLabel;
	
	@Expose
	@SerializedName("Content")
	private String messagePartContent;
	
	@Expose
	@SerializedName("index")
	private int messagePartIndex;
	
	public MessagePart(){
	}
	
	public MessagePart(String messagePartLabel,String messagePartContent,String messageid) {
		this.messagePartLabel = messagePartLabel;
		this.messagePartContent = messagePartContent;
		this.messageid=messageid;
	}

	

	@Column(name = "label")
	@XmlAttribute(name="label", required=true)
	public String getMessagePartLabel() {
		return messagePartLabel;
	}

	public void setMessagePartLabel(String messagePartLabel) {
		this.messagePartLabel = messagePartLabel;
	}

	@Column(name = "content")	
	@Lob 
	@XmlElement(name="Content")
	public String getMessagePartContent() {
		return messagePartContent;
	}

	public void setMessagePartContent(String messagePartContent) {
		this.messagePartContent = messagePartContent;
	}

	@Column(name = "partindex")
	@XmlAttribute(name="Index", required=true)
	public int getMessagePartIndex() {
		return messagePartIndex;
	}

	public void setMessagePartIndex(int messagePartIndex) {
		this.messagePartIndex = messagePartIndex;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	@XmlTransient
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "messageid")
	@XmlAttribute(name="messageid", required=true)
	public String getMessageid() {
		return messageid;
	}

	public void setMessageid(String messageid) {
		this.messageid = messageid;
	}
	
	
	
	
	
}
