package games.stendhal.server.entity.status;

import games.stendhal.common.NotificationType;
import games.stendhal.server.entity.Entity;
import games.stendhal.server.entity.RPEntity;

public class SleepingStatusHandler implements StatusHandler<SleepingStatus> {

	@Override
	public void inflict(SleepingStatus status, StatusList statusList, Entity attacker) {
		if (!statusList.hasStatus(status.getStatusType())) {
			RPEntity entity = statusList.getEntity();
			if (entity != null) {
				statusList.addInternal(status);
				entity.sendPrivateText(NotificationType.SCENE_SETTING,
						"You are sleeping.");
			}
		}
	}

	@Override
	public void remove(SleepingStatus status, StatusList statusList) {
		statusList.removeInternal(status);
	}
}
