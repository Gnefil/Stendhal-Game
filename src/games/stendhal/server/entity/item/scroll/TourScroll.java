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
		locations.add("0_athor_island");
	}

	/**
	 * Copy constructor.
	 */
	public TourScroll(final TourScroll item) {
		super(item);
	}

	@Override
	protected boolean useTeleportScroll(final Player player) {
		return false;
	}

	@Override
	public String describe() {
		return "";
	}
	
	public List<String> getlocations() {
		return locations;
	}
}
