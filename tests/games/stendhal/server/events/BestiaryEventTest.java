package games.stendhal.server.events;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import games.stendhal.server.entity.player.Player;
import utilities.PlayerTestHelper;

public class BestiaryEventTest {

	private Player test_player;
	
	@Before
	public void setUpThePlayer() {
		
		PlayerTestHelper.generatePlayerRPClasses();
		test_player = PlayerTestHelper.createPlayer("player");

	}

	
	
	@Test
	public void testNoQuestionMarksAppearInBestiary() {
		
		BestiaryEvent bestiary_item_event = new BestiaryEvent(test_player, true, true);
		
		String list_of_enemies = bestiary_item_event.get("enemies");

		assertFalse(list_of_enemies.contains("???"));
	}

}
