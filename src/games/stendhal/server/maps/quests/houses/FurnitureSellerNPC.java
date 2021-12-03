package games.stendhal.server.maps.quests.houses;




import java.util.Map;

import games.stendhal.common.Direction;
import games.stendhal.server.core.config.ZoneConfigurator;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.npc.ConversationPhrases;
import games.stendhal.server.entity.npc.ConversationStates;
import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.entity.npc.action.SetQuestAction;
import games.stendhal.server.entity.npc.condition.AndCondition;
import games.stendhal.server.entity.npc.condition.GreetingMatchesNameCondition;
import games.stendhal.server.entity.npc.condition.QuestNotCompletedCondition;

public class FurnitureSellerNPC implements ZoneConfigurator {

	@Override
	public void configureZone(final StendhalRPZone zone, final Map<String, String> attributes) {
		buildNPC(zone);
	}

	private void buildNPC(final StendhalRPZone zone) {
		final SpeakerNPC npc = new SpeakerNPC("James Oakheart") { 
			
			@Override
			protected void createPath() {
				setPath(null);
			}
			
			@Override
			public void createDialog() {
				addJob("I sell furniture, but my shop isn't open yet, so I don't have much stock right now.");
				addGoodbye();
				
				add(ConversationStates.IDLE,
					ConversationPhrases.GREETING_MESSAGES,
					new AndCondition(new GreetingMatchesNameCondition(getName()),
							new QuestNotCompletedCondition("Oakheart")),
					ConversationStates.ATTENDING,
					"Oh hey there, sorry I'm new in town. "
					+ "My shop isn't open yet as a result, but when everything's up and running I'll be selling #furniture! "
					+ "I can also tell you about my #future plans.",
					new SetQuestAction("Oakheart", "done"));
				
				add(ConversationStates.ATTENDING, "furniture",
					null,
					ConversationStates.SERVICE_OFFERED,
					"Unfortunately, my furniture shipments from Ados have been delayed so I don't have much to sell. "
					+ "However, I do have this chair - would you like to buy it?",
					null);
				
				add(ConversationStates.SERVICE_OFFERED, 
					ConversationPhrases.YES_MESSAGES,
					null,
					ConversationStates.ATTENDING,
					"Great, let me check some things...",
					new BuyFurnitureChatAction(10, "oak chair"));
				
				add(ConversationStates.ATTENDING, "future",
					null,
					ConversationStates.ATTENDING,
					"Once my shipments arrive, I'll be able to sell you all kinds of furniture, and buy furniture from you. "
					+ "Also, I'm thinking of opening some sort of furniture giveaway at some point... "
					+ "Why? Because I love furniture!",
					null);
				
			}
		};

		npc.addGreeting("Welcome to Deniran's (future) furniture shop.");
		npc.addJob("I am the local furniture dealer. And it looks like you need some redecorating. Ask me about my #furniture.");
		npc.addHelp("If you would like to sell something, ask me about my #future plans and I will tell you what I have.");
		npc.addQuest("I have no quests, but you can"
				+ " just ask and I'll tell you the #furniture I sell.");
		
		npc.setPosition(17, 26);
		npc.setDirection(Direction.DOWN);
		npc.setEntityClass("man_000_npc");
		npc.setDescription("You see James Oakheart. He's pretty unassuming.");
		zone.add(npc);
	}

	private String getName() {
		return "James Oakheart";
	}
}




