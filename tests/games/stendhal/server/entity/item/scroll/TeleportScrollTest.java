package games.stendhal.server.entity.item.scroll;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

//import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.maps.MockStendlRPWorld;
import games.stendhal.server.core.engine.StendhalRPWorld;
//import games.stendhal.common.Direction;
import games.stendhal.server.core.engine.StendhalRPZone;
//import games.stendhal.server.core.rp.StendhalRPAction;

import games.stendhal.server.entity.item.ConsumableItem;
import games.stendhal.server.entity.player.Player;
import games.stendhal.server.entity.status.PoisonAttacker;
import games.stendhal.server.core.engine.SingletonRepository;
import utilities.PlayerTestHelper;


public class TeleportScrollTest {
	
	// A teleport scroll, world and zone are needed for the test
	MarkedScroll scroll;
	StendhalRPWorld world;
	StendhalRPZone zone;

	// Using the mock stendl world
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		MockStendlRPWorld.get();
	}

	// Reset the mock stendl world once used
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		MockStendlRPWorld.reset();
	}

	// A teleport scroll instantiated from home scroll 
	@Before
	public void setUp() throws Exception {
		// Set up the marked scroll to teleport into test zone
		scroll = new MarkedScroll("marked scroll", "", "", null);
		scroll.setInfoString("my_test_zone 20 20");
		// Get a mock world and create our own test zone to add into it
		world = MockStendlRPWorld.get();
		zone = new StendhalRPZone("my_test_zone", 50, 50);
		world.addRPZone(zone);
	}
	
	
	/**
	 * Tests for teleport scroll when poisoned.
	 */
	
	@Test
	public void testTeleportScrollWhenPoisoned() {
		// Poison the player first
		final Player player = PlayerTestHelper.createPlayer("bob");
		final String poisontype = "greater poison";
		final ConsumableItem poison = (ConsumableItem) SingletonRepository.getEntityManager().getItem(poisontype);
		final PoisonAttacker poisoner = new PoisonAttacker(100, poison);
		poisoner.onAttackAttempt(player, SingletonRepository.getEntityManager().getCreature("snake"));
		
		PlayerTestHelper.registerPlayer(player, zone);

		assertFalse(scroll.useScroll(player));
		
	}
	

}
