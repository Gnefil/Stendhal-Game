package games.stendhal.server.entity.status;

import games.stendhal.server.core.events.TurnListener;
import games.stendhal.server.core.events.TurnNotifier;
import games.stendhal.server.entity.Entity;

public class SleepingStatusHandler implements StatusHandler<SleepingStatus> {

	@Override
	public void inflict(SleepingStatus status, StatusList statusList, Entity attacker) {
		statusList.addInternal(status);
		int count = statusList.countStatusByType(status.getStatusType());
		if (count <= 6) {
			statusList.addInternal(status);
		}
		if (count == 1) {
			TurnListener turnListener = new EatStatusTurnListener(statusList);
			TurnNotifier.get().dontNotify(turnListener);
			TurnNotifier.get().notifyInTurns(0, turnListener);
		}
		TurnNotifier.get().notifyInSeconds(120, new StatusRemover(statusList, status));
	}

	@Override
	public void remove(SleepingStatus status, StatusList statusList) {
		statusList.removeInternal(status);
	}
}
