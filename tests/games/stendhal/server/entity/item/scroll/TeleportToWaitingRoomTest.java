package games.stendhal.server.entity.item.scroll;

import static org.junit.Assert.*;

import org.junit.Test;

import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.core.engine.StendhalRPWorld;
import games.stendhal.server.entity.player.Player;
import utilities.PlayerTestHelper;

public class TeleportToWaitingRoomTest {

	@Test
	public void scrollTest() {
		final Player player = PlayerTestHelper.createPlayer("bob");
		Scroll scroll = new MarkedScroll("marked scroll", "", "", null);
		scroll.setInfoString("waitingroom 20 20");
		
		StendhalRPWorld world = SingletonRepository.getRPWorld();
		PlayerTestHelper.registerPlayer(player, world.getZone("0_semos_city"));
		
		assertTrue(scroll.useScroll(player));
	}
}
