package com.sakumar.messaging.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "Destinations")
public class Destinations implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -7047414052693754501L;
    private List<Destination> destinations;

    public List<Destination> getDestinations() {
        return destinations;
    }

    @XmlElement(name = "Destination")
    public void setDestinations(List<Destination> destinations) {
        this.destinations = destinations;
    }

    @Override
    public String toString() {
        return "Destinations [destinations=" + destinations + "]";
    }


}
