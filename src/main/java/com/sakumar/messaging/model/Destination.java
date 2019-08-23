package com.sakumar.messaging.model;

import com.sakumar.messaging.constant.Type;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Destination")
public class Destination {
    private String name;
    private Type type;
    private int listenerCount;
    private int capacity;

    public String getName() {
        return name;
    }

    @XmlElement(name = "Name")
    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    @XmlElement(name = "Type")
    public void setType(Type type) {
        this.type = type;
    }

    public int getListenerCount() {
        return listenerCount;
    }

    @XmlElement(name = "Listener_Count")
    public void setListenerCount(int listenerCount) {
        this.listenerCount = listenerCount;
    }

    public int getCapacity() {
        return capacity;
    }

    @XmlElement(name = "Capacity")
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Destination other = (Destination) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Destination [name=" + name + ", type=" + type + ", listenerCount=" + listenerCount + ", capacity="
                + capacity + "]";
    }
}
