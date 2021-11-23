package games.stendhal.server.maps.tourguide;

import static org.junit.Assert.*;

import org.junit.Test;

import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.core.engine.StendhalRPWorld;
import games.stendhal.server.core.engine.StendhalRPZone;


public class TourguideTest {

	@Test
	public void test() {
		StendhalRPWorld world = SingletonRepository.getRPWorld();
		StendhalRPZone zone = world.getZone("waitingroom");
		assertNotEquals(null, zone);
	}

}
