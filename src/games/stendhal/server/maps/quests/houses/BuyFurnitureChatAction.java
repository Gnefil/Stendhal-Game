package games.stendhal.server.maps.quests.houses;

import games.stendhal.common.parser.Sentence;
import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.entity.npc.ChatAction;
import games.stendhal.server.entity.npc.EventRaiser;
import games.stendhal.server.entity.player.Player;
import marauroa.common.game.SlotIsFullException;
import games.stendhal.server.entity.item.Item;
import games.stendhal.server.entity.mapstuff.portal.HousePortal;

public class BuyFurnitureChatAction implements ChatAction {
	
	private int cost;
	private String itemName;
	
	BuyFurnitureChatAction(final int costIn, final String itemNameIn) {
		cost = costIn;
		itemName = itemNameIn;
	}
	
	@Override
	public void fire(Player player, Sentence sentence, EventRaiser raiser) {
		
		HousePortal playerHouse = HouseUtilities.getPlayersHouse(player);
		if(playerHouse == null) {
			raiser.say("You don't own a house for me to deliver this " + itemName + " to!");
			return;
		}
		final Item item = SingletonRepository.getEntityManager().getItem(itemName);
		if(item != null) {
			if(!player.isEquipped("money", this.cost)) {
				raiser.say("Hang on, you can't afford this " + itemName + "!");
				return;
			}
			
			try{
				HouseUtilities.findChest(playerHouse).add(SingletonRepository.getEntityManager().getItem(itemName));//adds item to chest found in playerhouse
				raiser.say("I will deliver this " + itemName + " to " + playerHouse.getDoorId() 
						+ ". You should find it in your chest, though it won't be ready to use yet.");
				player.drop("money", cost);
			}
			catch(SlotIsFullException e){
				raiser.say("Sorry, I couldn't make this delivery. "
						+ "Make sure that you've got space in your chest and ask me again.");
			}
		}
		else {
			raiser.say("Sorry, I don't have a(n) " + itemName + ".");
		}
		
	}

}
