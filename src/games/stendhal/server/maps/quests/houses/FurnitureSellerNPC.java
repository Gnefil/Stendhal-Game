package games.stendhal.server.maps.quests.houses;

import java.util.Map;

import games.stendhal.server.core.config.ZoneConfigurator;
import games.stendhal.server.core.engine.StendhalRPZone;

import games.stendhal.server.entity.npc.SpeakerNPC;
//import games.stendhal.server.entity.npc.SpeakerNPC;

public class FurnitureSellerNPC implements ZoneConfigurator {
	
	@Override
	public void configureZone(StendhalRPZone zone, Map<String, String> attributes) {
		buildNPC(zone);
	}
	
	private void buildNPC(StendhalRPZone zone) {
		final SpeakerNPC npc = new SpeakerNPC("James Oakheart") { };
		zone.add(npc);
	}
}
