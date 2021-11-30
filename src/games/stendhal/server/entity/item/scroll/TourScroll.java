/*
 * $Id$
 */
/***************************************************************************
 *                      (C) Copyright 2003 - Marauroa                      *
 ***************************************************************************
 ***************************************************************************
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *                                                                         *
 ***************************************************************************/
package games.stendhal.server.entity.item.scroll;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.player.Player;

/**
 * Represents a tour teleport scroll.
 */
public class TourScroll extends TeleportScroll {
	
	private List<String> locations;

	public TourScroll(final String name, final String clazz, final String subclass,
			final Map<String, String> attributes) {
		super(name, clazz, subclass, attributes);
		locations = new ArrayList<String>();
		locations.add("0_athor_island 60 32");
	}

	/**
	 * Copy constructor.
	 */
	public TourScroll(final TourScroll item) {
		super(item);
	}

	@Override
	/**
	 * For further implementation, iterate through the locations. If not empty, give another tour scroll
	 * to the player with the next location in the list
	 */
	protected boolean useTeleportScroll(final Player player) {
		// init as home_scroll
		StendhalRPZone zone = SingletonRepository.getRPWorld().getZone("0_semos_city");
		int x = 30;
		int y = 40;
		
		String[] locationInfo = locations.get(0).split(" ");
		zone = SingletonRepository.getRPWorld().getZone(locationInfo[0]);
		// player.getKeyedSlot("!visited", zoneName) != null
		// int x = 60;
		// int y = 32;
		x = Integer.parseInt(locationInfo[1]);
		y = Integer.parseInt(locationInfo[2]);
		return player.teleport(zone, x, y, null, player);
	}

	@Override
	public String describe() {
		return "";
	}
	
	public List<String> getlocations() {
		return locations;
	}
}
