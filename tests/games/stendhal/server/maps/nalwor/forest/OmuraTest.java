package games.stendhal.server.maps.nalwor.forest;

import static org.junit.Assert.assertEquals;
import static utilities.SpeakerNPCTestHelper.getReply;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.entity.npc.fsm.Engine;
import utilities.QuestHelper;
import utilities.ZonePlayerAndNPCTestImpl;


public class OmuraTest extends ZonePlayerAndNPCTestImpl {
	
	private static final String ZONE_NAME = "dojo test zone";
	private static final String npcName = "Omura Sumitada";

	private static Dojo dojoPlace;
	private static SpeakerNPC samurai;
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		QuestHelper.setUpBeforeClass();
		setupZone(ZONE_NAME);
	}

	@Override
	@Before
	public void setUp() throws Exception {
		setZoneForPlayer(ZONE_NAME);
		setNpcNames(npcName);
		dojoPlace = new Dojo();

		addZoneConfigurator(dojoPlace, ZONE_NAME);

		super.setUp();

		samurai = getNPC("Omura Sumitada");
		
	}
	
	@Test
	public void testPlayerAtkIsTooHighToTrainSpeech() {
		
		Engine en = samurai.getEngine();
		
		// atk higher than level
		player.setLevel(60);
		player.setAtk(57); 
		en.step(player, "hi");
		assertEquals("This is the assassins' dojo.", getReply(samurai));
		en.step(player, "fee");
		assertEquals("At your level of experience, your attack strength is too high to train here at this time.", getReply(samurai));
		
		en.step(player, "bye");
		
		// atk equal to level
		player.setLevel(60);
		player.setAtk(60); 
		en.step(player, "hi");
		assertEquals("This is the assassins' dojo.", getReply(samurai));
		en.step(player, "fee");
		assertEquals("At your level of experience, your attack strength is too high to train here at this time.", getReply(samurai));
		
		
		
	}
}
