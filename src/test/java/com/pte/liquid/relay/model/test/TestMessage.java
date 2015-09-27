package com.pte.liquid.relay.model.test;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;
import java.util.UUID;

import javax.xml.bind.JAXBException;
import javax.xml.transform.stream.StreamResult;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.XmlMappingException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pte.liquid.relay.model.Message;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="test-application-context.xml")
public class TestMessage {

	@Autowired
	private Marshaller marshaller;
	
	@Autowired
	private GsonBuilder gsonBuilder;
	
	private static Logger logger = Logger.getLogger(TestMessage.class);
	
	private Message message;
	
	@Before
	public void init(){
		message = new Message();
		message.setId(UUID.randomUUID().toString());
		message.setSnapshotTime(new Date());
		message.setLocation("niveau1|||niveau2|||niveau3");		
		message.setHeader("test", "testvalue");
		message.setHeader("test1", "");
		message.setHeader("", "test");
		message.setHeader(null, "test");
		message.setHeader("hallo", null);

		message.createPart(null, null);
		message.createPart(null, "hallo");
		message.createPart("test", null);
		message.createPart("test", "hallo");
		
	}		
	
	@Test
	public void testEtmMessageMarshall() throws JAXBException, XmlMappingException, IOException{
		StringWriter sw = new StringWriter();
		marshaller.marshal(message, new StreamResult(sw));		
		logger.info(sw.toString());
		assert(sw.toString()!=null);
	}
	
	
	@Test
	public void testEtmMessageGsonMarshall(){
		gsonBuilder.excludeFieldsWithoutExposeAnnotation();
		Gson gson = gsonBuilder.create();
		String jsonMessage1 = gson.toJson(message);
		logger.info(jsonMessage1);
		assert(jsonMessage1!=null);		
		Message newMessage = gson.fromJson(jsonMessage1, Message.class);
		logger.info(newMessage);
		assert(newMessage!=null);
		String jsonMessage2 = gson.toJson(newMessage);
		logger.info(jsonMessage2);
		assert(jsonMessage2!=null);
		Assert.assertEquals(jsonMessage1, jsonMessage2);
	}

	public Marshaller getMarshaller() {
		return marshaller;
	}

	public void setMarshaller(Marshaller marshaller) {
		this.marshaller = marshaller;
	}
	
	
	
}
