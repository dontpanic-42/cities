package com.dontpanic.cities;

import java.util.HashMap;
import java.util.Set;

import com.dontpanic.cities.data.City;

public class CityManager {

	private HashMap<String, City> cities;
	private CitiesPlugin plugin;
	
	public CityManager(CitiesPlugin plugin) {
        this.plugin = plugin;
        cities = new HashMap<String, City>();
    }
	
	public void addCity(City city) {
		cities.put(city.getID(), city);
	}

	public boolean removeCity(String id) {
		City removed = cities.remove(id);
		if(removed != null) {
			return true;
		}
		return false;
	}

	public String[] getInformation(String id) {
		City c = cities.get(id);
		if(c == null) {
			return null;
		}
		return c.getInformation();
	}

	public City getCity(String id) {
		return cities.get(id);
	}

	public String getCityList() {
		String list = "";
		Set<String> set = cities.keySet();
		for(String s : set) {
			list += s+"  ";
		}
		return list;
	}

	
}
