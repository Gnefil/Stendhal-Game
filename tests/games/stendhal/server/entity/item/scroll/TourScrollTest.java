package games.stendhal.server.entity.item.scroll;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.server.core.engine.StendhalRPWorld;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.player.Player;
import games.stendhal.server.maps.MockStendlRPWorld;
import utilities.PlayerTestHelper;

public class TourScrollTest {
	
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
	
	@Test
	public void tourTeleportTest() {
		Player player = PlayerTestHelper.createPlayer("bob");
		TourScroll scroll = new TourScroll("test scroll", "", "", null);
		StendhalRPWorld world = MockStendlRPWorld.get();
		StendhalRPZone zone = new StendhalRPZone("0_semos_city", 50, 50);
		world.addRPZone(zone);		
		PlayerTestHelper.registerPlayer(player, zone);

		assertTrue(scroll.useScroll(player));
	}

}
