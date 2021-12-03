package games.stendhal.server.maps.quests.houses;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;
import static utilities.SpeakerNPCTestHelper.getReply;

import java.util.Iterator;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.common.parser.ConversationParser;
import games.stendhal.common.parser.Sentence;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.item.Item;
import games.stendhal.server.entity.mapstuff.chest.Chest;
import games.stendhal.server.entity.mapstuff.chest.StoredChest;
import games.stendhal.server.entity.mapstuff.portal.HousePortal;
import games.stendhal.server.entity.mapstuff.portal.Portal;
import games.stendhal.server.entity.npc.EventRaiser;
import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.entity.player.Player;
import games.stendhal.server.maps.MockStendhalRPRuleProcessor;
import games.stendhal.server.maps.MockStendlRPWorld;
import marauroa.common.game.RPObject;
import utilities.PlayerTestHelper;

public class BuyFurnitureChatActionTest {
	private static HousePortal housePortal;
	private static StoredChest chest;
	private static BuyHouseChatAction houseAction;
	private static BuyFurnitureChatAction action;
	private static SpeakerNPC engine;
	private static EventRaiser raiser;
	private static Player player;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PlayerTestHelper.generateNPCRPClasses();
		Chest.generateRPClass();
		Portal.generateRPClass();
		HousePortal.generateRPClass();
		MockStendlRPWorld.get();
		
		houseAction = new BuyHouseChatAction(1, HouseSellerNPCBase.QUEST_SLOT);
		action = new BuyFurnitureChatAction(2, "oak chair");
		engine = new SpeakerNPC("bob");
		raiser = new EventRaiser(engine);
		player = PlayerTestHelper.createPlayer("Jeeves");
	}
	
	private static void housePurchaseHelper() {
		String zoneName = "0_ados_city_n";
		StendhalRPZone ados = new StendhalRPZone(zoneName);
		MockStendlRPWorld.get().addRPZone(ados);
		housePortal = new HousePortal("schnick bla 51");
		housePortal.setIdentifier("keep rpzone happy");
		housePortal.setDestination(zoneName, "schnick bla 51");
		ados.add(housePortal);
		chest = new StoredChest();
		ados.add(chest);
		HouseUtilities.clearCache();
		Sentence sentence = ConversationParser.parse("51");
		PlayerTestHelper.equipWithMoney(player, 1);
		houseAction.fire(player, sentence, raiser);
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		HouseUtilities.clearCache();
		MockStendhalRPRuleProcessor.get().clearPlayers();
	}
	
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Remove added stored entities.
	 * <p>
	 * stored entities can pollute the database
	 * if a server is ran on the same system as the tests.
	 */
	@After
	public void clearStored() {
		if (housePortal != null) {
			StendhalRPZone zone = housePortal.getZone();
			if (zone != null) {
				zone.remove(housePortal);
				housePortal = null;
			}
		}

		if (chest != null) {
			StendhalRPZone zone = chest.getZone();
			if (zone != null) {
				zone.remove(chest);
				chest = null;
			}
		}
	}
	
	@Test
	public void testNeedHouseToBuyFurniture() {
		Sentence sentence = ConversationParser.parse("");
		Player homelessPlayer = PlayerTestHelper.createPlayer("NotJeeves");
		action.fire(homelessPlayer, sentence, raiser);
		assertThat(getReply(engine), containsString("You don't own a house for me to deliver this oak chair to!"));
	}
	
	@Test
	public void testNeedMoneyToBuyFurniture() {
		if(housePortal == null) {
			housePurchaseHelper();
		}
		StoredChest chest = HouseUtilities.findChest(housePortal);
		assertTrue(chest != null);

		Sentence sentence = ConversationParser.parse("");
		action.fire(player, sentence, raiser);
		assertThat(getReply(engine), containsString("Hang on, you can't afford this oak chair!"));
	}
	
	@Test
	public void testCanBuyFurniture() {
		if(housePortal == null) {
			housePurchaseHelper();
		}
		Sentence sentence = ConversationParser.parse("");
		PlayerTestHelper.equipWithMoney(player, 2);
		action.fire(player, sentence, raiser);
		
		boolean containsItem = false;
		for(Iterator<RPObject> contents = chest.getContent(); contents.hasNext();) {
			Item item = (Item)contents.next();
			if(item.getName().equals("oak chair")) {
				containsItem = true;
			}
		}
		assertTrue(containsItem);
	}

}
