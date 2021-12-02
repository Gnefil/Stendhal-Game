package games.stendhal.server.entity.item.scroll;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.core.engine.StendhalRPWorld;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.player.Player;
import games.stendhal.server.maps.MockStendlRPWorld;
import utilities.PlayerTestHelper;

public class TourScrollTest {
	
	private Player player;
	private TourScroll scroll;
	private List<String> locationList;
	
	// Using the mock stendl world
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		MockStendlRPWorld.get();
	}
	
	@Before
	public void setup() {
		player = PlayerTestHelper.createPlayer("bob");
		scroll = new TourScroll("test scroll", "", "", null);
		locationList = scroll.getlocations();
	}

	// Reset the mock stendl world once used
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		MockStendlRPWorld.reset();
	}
	
	@Test
	public void tourTeleportTest() {
		StendhalRPWorld world = SingletonRepository.getRPWorld();
		StendhalRPZone startingPos = new StendhalRPZone("0_semos_city");
		world.addRPZone(startingPos);
		world.addRPZone(new StendhalRPZone("0_athor_island", 500, 500));
		PlayerTestHelper.registerPlayer(player, startingPos);

		assertTrue(scroll.useScroll(player));
	}
	
	@Test
	public void tourGetItemTest() {
		TourScroll scroll= (TourScroll) SingletonRepository.getEntityManager().getItem("tour scroll");
		assertNotEquals(null, scroll);
	}
	
	@Test
	public void locationTest() {
		assertNotEquals(null, locationList);
	}
	
	@Test
	public void firstLocationisAthorTest() {
		assertNotEquals(null, locationList);
		String firstLocation = locationList.get(0);
		assertEquals("0_athor_island 60 32", firstLocation);
		
	}

}
