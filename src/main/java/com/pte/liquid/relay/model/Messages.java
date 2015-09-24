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
