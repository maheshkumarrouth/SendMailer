package com.cultuzz.handlers;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.transform.stream.StreamResult;

import kunin.protocolframework.runtime.XmlException;

import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.ota.v2006a.DateTimeSpanType;
import org.ota.v2006a.OTA_ErrorRS;
import org.ota.v2006a.ResponseTimeTPA;
import org.ota.v2006a.TPA_Extensions;
import org.ota.v2006a.WarningType;
import org.w3c.dom.Document;
import org.ota.v2006a.BookingChannel;
import org.ota.v2006a.Description;
import org.ota.v2006a.Descriptions;
import org.ota.v2006a.HotelDescriptiveContent;
import org.ota.v2006a.HotelDescriptiveContent1;
import org.ota.v2006a.HotelDescriptiveContents1;
import org.ota.v2006a.OTA_HotelDescriptiveContentNotifRQ;
import org.ota.v2006a.POS_Type;
import org.ota.v2006a.SourceType;
import org.ota.v2006a.TPA_Extensions;
import org.ota.v2006a.UniqueID_Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.XmlMappingException;
import org.springframework.stereotype.Component;

import com.cultuzz.dao.interfaces.RefreshDataQueueDAO;
import com.cultuzz.models.Distributor;
import com.cultuzz.utilities.ASyncHttpClient;
import com.cultuzz.utilities.Jaxb2MarshallerUtility;

import kunin.protocolframework.runtime.XmlException;

import org.w3c.dom.Document;
@Component
public class RefreshDataHandler {
	
	
	@Autowired 
	private RefreshDataQueueDAO refreshDataQueueDAO;
	
	@Autowired
	private ASyncHttpClient aSyncHttpClient;
	
	public void sendRefreshDataToCore(Integer cltzObjectID){
		System.out.println("sendRefreshDataToCore");
		String xmlString = getXmlString(cltzObjectID);
		System.out.println("Debug @ stmt :: xmlString"+xmlString);
		aSyncHttpClient.postXMLToCS(xmlString);
	}
	
	public String getXmlString(Integer cltzObjectID){
		try{
			List list = refreshDataQueueDAO.findConnectedObjectsByObjectID(cltzObjectID);
			OTA_HotelDescriptiveContentNotifRQ ota_HotelDescriptiveContentNotifRQ = new OTA_HotelDescriptiveContentNotifRQ();
			ota_HotelDescriptiveContentNotifRQ.setVersion(4.0);
			ota_HotelDescriptiveContentNotifRQ.setPrimaryLangID("en");
			ota_HotelDescriptiveContentNotifRQ.setAltLangID("en");
			POS_Type pos_Type = new POS_Type();
			SourceType sourceType = new SourceType();
			sourceType.setAgentDutyCode("18941305ba1e3572");
			sourceType.setAgentSine("74");
			BookingChannel bookingChannel = new BookingChannel();
			System.out.println(bookingChannel);
			bookingChannel.setType("4");
			sourceType.setBookingChannel(bookingChannel);
			UniqueID_Type uniqueID_Type = new UniqueID_Type();
			uniqueID_Type.setId(cltzObjectID.toString());
			uniqueID_Type.setType("10");
			sourceType.setRequestorID(uniqueID_Type);
			pos_Type.getSource().add(sourceType);
			ota_HotelDescriptiveContentNotifRQ.setPos(pos_Type);
			HotelDescriptiveContents1  hotelDescriptiveContents1 = new HotelDescriptiveContents1();
			hotelDescriptiveContents1.setHotelCode(cltzObjectID.toString());
			HotelDescriptiveContent1 hotelDescriptiveContent = new HotelDescriptiveContent1();
			hotelDescriptiveContent.setHotelCode(cltzObjectID.toString());
			TPA_Extensions tpa_Extensions = new TPA_Extensions();
			Descriptions dis = new Descriptions();
			for(Iterator<Distributor> it = list.iterator() ; it.hasNext() ; ){
				Distributor distributor = it.next();
				if(distributor != null){
					System.out.println("Debug @ stmt :: distributor.getCs_distributor_id()"+distributor.getCs_distributor_id());
					Description d = new Description();
					d.setCodeDetail("Refresh");
					d.setContentData(distributor.getCs_distributor_id().toString());
					d.setContentTitle("ChannelInfo");
					d.setName("channelName");
					d.setRecordID(cltzObjectID.toString());
					dis.addDescription(d);
				}
			}
			tpa_Extensions.setDescriptions(dis);
			hotelDescriptiveContent.setTPA_Extensions(tpa_Extensions);
			hotelDescriptiveContents1.getHotelDescriptiveContent().add(hotelDescriptiveContent);
			ota_HotelDescriptiveContentNotifRQ.setHotelDescriptiveContents(hotelDescriptiveContents1);
			Document otaResponse = ota_HotelDescriptiveContentNotifRQ.toDoc();
			String xmlString = getDocumentAsString(otaResponse,"UTF-8");
			return xmlString;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	 public String getDocumentAsString(Document doc, String encoding) {
	        try {
	            ByteArrayOutputStream out = new ByteArrayOutputStream();
	            OutputFormat of = new OutputFormat(doc);
	            of.setIndent(1);
	            of.setIndenting(true);
	            if(encoding != null && encoding.length() != 0) {
	                of.setEncoding(encoding);
	            }
	            
	            XMLSerializer serializer = new XMLSerializer(out, of);
	            serializer.asDOMSerializer();
	            serializer.serialize(doc);
	            return out.toString();
	        } catch(java.io.IOException e) {
	            return "Error: " + e.getMessage();
	        }
	        
	    }// getDocumentAsString
	
}
