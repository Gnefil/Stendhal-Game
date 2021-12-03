package games.stendhal.server.entity.status;


public class SleepingStatus extends Status{
	
	
	public SleepingStatus() {
		super("sleeping");
	}
	
	@Override
	public StatusType getStatusType() {
		return StatusType.SLEEPING;
	}
}
