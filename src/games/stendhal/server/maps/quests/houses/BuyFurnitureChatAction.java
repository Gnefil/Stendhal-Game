package games.stendhal.server.maps.quests.houses;

import games.stendhal.common.parser.Sentence;
import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.entity.npc.ChatAction;
import games.stendhal.server.entity.npc.EventRaiser;
import games.stendhal.server.entity.player.Player;
import games.stendhal.server.maps.quests.houses.HouseUtilities;
import games.stendhal.server.entity.item.Item;
import games.stendhal.server.entity.mapstuff.chest.StoredChest;
import games.stendhal.server.entity.mapstuff.portal.HousePortal;

public class BuyFurnitureChatAction implements ChatAction {
	
	private int cost;
	private String itemName;
	
	BuyFurnitureChatAction(final int cost, final String itemName) {
		//this.cost = cost;
		//this.itemName = itemName;
	}
	
	@Override
	public void fire(Player player, Sentence sentence, EventRaiser raiser) {
		// TODO Auto-generated method stub

	}

}
