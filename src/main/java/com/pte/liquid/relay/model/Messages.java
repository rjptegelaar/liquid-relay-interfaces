//Copyright 2015 Paul Tegelaar
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
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Messages {

	@Expose
	@SerializedName("Messages")
	private List<Message> msgs;
	
	public Messages(){
		msgs = new ArrayList<Message>();
	}
	
	public void addMessage(Message msg){
		msgs.add(msg);
	}

	public List<Message> getMsgs() {
		return msgs;
	}

	public void setMsgs(List<Message> msgs) {
		this.msgs = msgs;
	}
	
	public int size(){
		return msgs.size();
	}
	
	public void truncList(int maxSize){
		if(size()>maxSize && maxSize>0){
			msgs = msgs.subList(0, maxSize);
		}		
	}
	
	

	
}
