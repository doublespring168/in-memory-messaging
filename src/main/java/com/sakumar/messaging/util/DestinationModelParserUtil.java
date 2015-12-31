package com.sakumar.messaging.util;
import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.sakumar.messaging.model.Destinations;

public class DestinationModelParserUtil {
  
	public static Destinations getDestinations(String location) throws JAXBException{
		File file = new File( location );
		JAXBContext jaxbContext = JAXBContext.newInstance( Destinations.class );
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Destinations destinations = (Destinations)jaxbUnmarshaller.unmarshal(file);
		return destinations;
	}	
}
