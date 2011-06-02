/*
 * Cities
 * Copyright (C) 2011 dontpanic_42 <hi_manuel@web.de>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

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
