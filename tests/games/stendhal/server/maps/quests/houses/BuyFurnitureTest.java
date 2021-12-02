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
import games.stendhal.server.maps.deniran.cityinterior.potionsshop.FurnitureSellerNPC;
import games.stendhal.server.maps.quests.AbstractQuest;
import utilities.PlayerTestHelper;
import utilities.QuestHelper;

public class BuyFurnitureTest {

	private Player player = null;
	private SpeakerNPC npc = null;
	private Engine en = null;
	private AbstractQuest quest;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		QuestHelper.setUpBeforeClass();
	}

	@Before
	public void setUp() {
		final StendhalRPZone zone = new StendhalRPZone("admin_test");
		new FurnitureSellerNPC().configureZone(zone, null);
		npc = SingletonRepository.getNPCList().get("Oakheart");

		en = npc.getEngine();

		quest = new BuyFurniture();
		quest.addToWorld();

		player = PlayerTestHelper.createPlayer("Jeeves");
	}
	
	@Test
	public void testFurnitureResponse() {
		en.step(player, "hello");
		assertEquals("Welcome to Deniran's furniture shop.", getReply(npc));
	}
	

}
