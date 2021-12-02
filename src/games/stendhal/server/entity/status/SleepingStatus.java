package games.stendhal.server.entity.status;

import games.stendhal.server.entity.Killer;

public class SleepingStatus extends ConsumableStatus implements Killer {
	
	
	/**
	 * 
	 * @param amount
	 * @param frequency
	 * @param regen
	 */
	public SleepingStatus(int amount, int frequency, int regen) {
		super("sleeping", amount, frequency, regen);
	}
	
	@Override
	public StatusType getStatusType() {
		return StatusType.SLEEPING;
	}
}
