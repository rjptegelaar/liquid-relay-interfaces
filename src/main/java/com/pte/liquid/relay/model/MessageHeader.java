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

import javax.xml.bind.annotation.XmlAttribute;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class MessageHeader {

	public MessageHeader(){
		
	}
		
	public MessageHeader(String messageHeaderKey,String messageHeaderValue) {
		this.messageHeaderKey = messageHeaderKey;
		this.messageHeaderValue = messageHeaderValue;
	}		
	
	@Expose
	@SerializedName("key")
	private String messageHeaderKey;
	
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
	
}
