package games.stendhal.server.maps.quests.houses;

import static org.junit.Assert.*;
import static utilities.SpeakerNPCTestHelper.getReply;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.entity.npc.fsm.Engine;
import games.stendhal.server.entity.player.Player;
import utilities.PlayerTestHelper;
import utilities.QuestHelper;

public class FurnitureSellerNPCTest {

	private Player player = null;
	private SpeakerNPC npc = null;
	private Engine en = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		QuestHelper.setUpBeforeClass();
	}

	@Before
	public void setUp() {
		final StendhalRPZone zone = new StendhalRPZone("admin_test");
		new FurnitureSellerNPC().configureZone(zone, null);
		npc = SingletonRepository.getNPCList().get("James Oakheart");

		en = npc.getEngine();
		player = PlayerTestHelper.createPlayer("Jeeves");
	}
	
	@Test
	public void testFurnitureResponses() {
		en.step(player, "hello");
		assertEquals("Oh hey there, sorry I'm new in town. "
				+ "My shop isn't open yet as a result, but when everything's up and running I'll be selling #furniture! "
				+ "I can also tell you about my #future plans.", getReply(npc));
		
		en.step(player, "future");
		assertEquals("Once my shipments arrive, I'll be able to sell you all kinds of furniture, and buy furniture from you. "
					+ "Also, I'm thinking of opening some sort of furniture giveaway at some point... "
					+ "Why? Because I love furniture!", getReply(npc));
		
		en.step(player, "furniture");
		assertEquals("Unfortunately, my furniture shipments from Ados have been delayed so I don't have much to sell. "
					+ "However, I do have this chair - would you like to buy it?", getReply(npc));
	}
	

}
