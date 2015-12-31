package com.sakumar.messaging.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement( name = "Destinations" )
public class Destinations implements Serializable {

	private List<Destination> destinations;

	/**
	 * 
	 */
	private static final long serialVersionUID = -7047414052693754501L;

	public List<Destination> getDestinations() {
		return destinations;
	}
    
	@XmlElement( name = "Destination" )
 	public void setDestinations(List<Destination> destinations) {
		this.destinations = destinations;
	}

	@Override
	public String toString() {
		return "Destinations [destinations=" + destinations + "]";
	}
	

}
