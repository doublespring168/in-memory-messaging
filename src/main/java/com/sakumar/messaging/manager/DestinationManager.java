package com.sakumar.messaging.manager;

import java.util.HashMap;
import java.util.Map;

import com.sakumar.messaging.destination.AbstractDestination;

public class DestinationManager {
  private static Map<String, AbstractDestination> destinations = new HashMap<>();
  public static void put(String destination, AbstractDestination dest){
	  destinations.put(destination, dest);
  }
  public static AbstractDestination get(String destination){
	 return destinations.get(destination);
  }
}
