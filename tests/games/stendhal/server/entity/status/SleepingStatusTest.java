package games.stendhal.server.entity.item.behavior;

import static org.junit.Assert.*;
import org.junit.Test;
import games.stendhal.server.entity.player.Player;
import utilties.PlayerTestHelper;

public class SleepingStatusTest {
	
	@Test
	public void sleepStatusExists {
		Player bob = PlayerTestHelper.createPlayer("bob");
		SleepingStatus sleep = new SleepingStatus();
		bob.getStatusList().inflictStatus(sleep, null);
		assertTrue(bob.hasStatus(StatusType.SLEEPING));
		
	}
}