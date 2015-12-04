package com.cultuzz.utilities;

import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository("jaxb2MarshallerUtility")
public class Jaxb2MarshallerUtility  {
	
	private Marshaller marshaller;
    private Unmarshaller unmarshaller;

    public void setMarshaller(Marshaller marshaller) {
        this.marshaller = marshaller;
    }

    public Marshaller getMarshaller() {
		return marshaller;
	}

	public Unmarshaller getUnmarshaller() {
		return unmarshaller;
	}

	public void setUnmarshaller(Unmarshaller unmarshaller) {
        this.unmarshaller = unmarshaller;
    }
	
	
//    //Converts Object to XML file
//    public void objectToXML(String fileName, Object graph) throws IOException {
//        FileOutputStream fos = null;
//        try {
//            fos = new FileOutputStream(fileName);
//            marshaller.marshal(graph, new StreamResult(fos));
//        } finally {
//        	fos.close();
//        }
//    }
//    //Converts XML to Java Object
//    public Object xmlToObject(String fileName) throws IOException {
//        FileInputStream fis = null;
//        try {
//            fis = new FileInputStream(fileName);
//            return unmarshaller.unmarshal(new StreamSource(fis));
//        } finally {
//        	fis.close();
//        }
//    }
}
