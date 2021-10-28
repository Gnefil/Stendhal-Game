package games.stendhal.server.maps.quests;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.entity.npc.fsm.Engine;
import games.stendhal.server.entity.player.Player;
import games.stendhal.server.maps.semos.library.HistorianGeographerNPC;
import utilities.PlayerTestHelper;
import utilities.QuestHelper;

/**
 * Test for the MeetZynnTest quest
 */
public class MeetZynnTest {
	private Player player = null;
	private SpeakerNPC npc = null;
	private Engine en = null;
	private AbstractQuest quest;

	private final String[] triggers = {
			"history", 
			"news", 
			"geography", 
			"places", 
			"Faiumoni", 
			"Semos", 
			"history",
			"Semos",
			"Ados",
			"Or'ril",
			"Nalwor",
			"Deniran",
			"use",
			"levels",
			"naming",
			"positioning",
			"get",
			"SPS",
			"Io"
			};
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		QuestHelper.setUpBeforeClass();
	}

	@Before
	public void setUp() {
		final StendhalRPZone zone = new StendhalRPZone("admin_test");
		new HistorianGeographerNPC().configureZone(zone, null);
		npc = SingletonRepository.getNPCList().get("Zynn Iwuhos");

		en = npc.getEngine();

		quest = new MeetZynn();
		quest.addToWorld();

		player = PlayerTestHelper.createPlayer("Jeeves");
	}

	@Test
	public void testForXPGained() {
		int xp = player.getXP();
		
		en.step(player, "hi");
		for(int x = 0; x < triggers.length; x++) {
			en.step(player, triggers[x]);
			assertTrue(xp < player.getXP());
			xp = player.getXP();
		}
	}
}