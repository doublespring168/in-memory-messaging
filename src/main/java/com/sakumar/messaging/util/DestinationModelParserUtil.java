package com.sakumar.messaging.util;

import com.sakumar.messaging.model.Destinations;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class DestinationModelParserUtil {

    public static Destinations getDestinations(String location) throws JAXBException {
        File file = new File(location);
        JAXBContext jaxbContext = JAXBContext.newInstance(Destinations.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Destinations destinations = (Destinations) jaxbUnmarshaller.unmarshal(file);
        return destinations;
    }
}
