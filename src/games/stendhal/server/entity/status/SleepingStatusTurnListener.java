package games.stendhal.server.entity.status;

import games.stendhal.common.constants.Nature;
import games.stendhal.server.core.events.TurnListener;
import games.stendhal.server.core.events.TurnNotifier;
import games.stendhal.server.entity.RPEntity;
import games.stendhal.server.entity.player.Player;
import games.stendhal.server.entity.spell.effect.HealingEffect;

public class SleepingStatusTurnListener implements TurnListener {
	private StatusList statusList;
	private static final String ATTRIBUTE_NAME = "sleeping";

	/**
	 * SleepingStatusTurnListener
	 *
	 * @param statusList StatusList
	 */
	public SleepingStatusTurnListener(StatusList statusList) {
		this.statusList = statusList;
	}

	@Override
	public void onTurnReached(int turn) {
		RPEntity entity = statusList.getEntity();
		SleepingStatus status = statusList.getFirstStatusByClass(SleepingStatus.class);
		Player player = (Player)entity;

		// check that the entity exists and has this status
		if ((entity == null) || (status == null)) {
			return;
		}
		
		if (player.getHP() <= player.getBaseHP()) {
		player.forceStop();
		player.clearPath();
		HealingEffect healing = new HealingEffect(Nature.LIGHT, status.getRegen(), 0, 0, 0, 0, status.getRegen(), 0);
		healing.act(player, entity);
		healing.onTurnReached(turn);
		player.notifyWorldAboutChanges();
			
		TurnNotifier.get().notifyInTurns(0, this);
		}
	}

	@Override
	public int hashCode() {
		return statusList.hashCode() * 31;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if ((obj == null) || (getClass() != obj.getClass())) {
			return false;
		}
		SleepingStatusTurnListener other = (SleepingStatusTurnListener) obj;
		return statusList.equals(other.statusList);
	}

}
