package games.stendhal.server.entity.item;

import java.util.Map;

import games.stendhal.server.entity.player.Player;
import games.stendhal.server.core.events.TurnListener;
import games.stendhal.server.core.events.TurnNotifier;
import games.stendhal.server.entity.RPEntity;
import games.stendhal.server.entity.status.SleepingStatus;

public class SleepingBag extends Item {
	public SleepingBag(final SleepingBag item) {
		super(item);
	}

	/**
	 * Creates a new sleeping bag
	 *
	 * @param name
	 * @param clazz
	 * @param subclass
	 * @param attributes
	 */
	public SleepingBag(final String name, final String clazz, final String subclass, final Map<String, String> attributes) {
		super(name, clazz, subclass, attributes);
	}
	
	@Override
	public boolean onUsed(final RPEntity user) {
		
		if (user instanceof Player) {
			final Player player = (Player) user;
		
		if (!this.isContained()) {
			// the sleeping bag is on the ground, but not next to the player
			if (!this.nextTo(user)) {
				user.sendPrivateText("The " + this.getName() + " is too far away");
				return false;
			}
			SleepingStatus sleepingStatus;
			sleepingStatus = new SleepingStatus(100, 3, 10);
			player.getStatusList().inflictStatus(sleepingStatus, player);
			TurnNotifier.get().notifyInTurns(3, (TurnListener) sleepingStatus);
			return true;
			}
		}
		// the seed was 'contained' in a slot and so it cannot be planted
		user.sendPrivateText("You have to put the " + this.getName() + " on the ground to use it");
		return false;
		}
	
	@Override
	public String describe() {
		return "You see a sleeping bag. You can sleep in it to regenerate health.";
	}
}
