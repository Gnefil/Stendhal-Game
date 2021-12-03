package games.stendhal.server.entity.status;
import static org.junit.Assert.*;
import org.junit.Test;
import games.stendhal.server.entity.player.Player;
import utilities.PlayerTestHelper;

public class SleepingStatusTest{
	
	@Test
	public void sleepStatusExists() {
		Player bob = PlayerTestHelper.createPlayer("bob");
		Status sleep = new SleepingStatus();
		bob.getStatusList().inflictStatus(sleep, null);
		assertTrue(bob.hasStatus(StatusType.SLEEPING));
		
	}
}