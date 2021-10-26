/* $Id$ */
/***************************************************************************
 *                   (C) Copyright 2003-2010 - Stendhal                    *
 ***************************************************************************
 ***************************************************************************
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *                                                                         *
 ***************************************************************************/
package games.stendhal.server.maps.quests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.server.entity.item.token.Token;
import games.stendhal.server.entity.player.Player;

import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.mapstuff.portal.Door;
import games.stendhal.server.entity.mapstuff.sign.Sign;
import games.stendhal.server.entity.npc.NPCList;
import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.maps.MockStendlRPWorld;
import marauroa.common.game.RPClass;
import utilities.PlayerTestHelper;
import utilities.QuestHelper;

import static utilities.SpeakerNPCTestHelper.getReply;

import java.util.*;

public class ReverseArrowTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		MockStendlRPWorld.get();
		QuestHelper.setUpBeforeClass();
		StendhalRPZone zone = new StendhalRPZone("int_ados_reverse_arrow");
		MockStendlRPWorld.get().addRPZone(zone);
		MockStendlRPWorld.get().addRPZone(new StendhalRPZone("0_semos_mountain_n2"));

		if (!RPClass.hasRPClass("door")) {
			Door.generateRPClass();
		}
		if (!RPClass.hasRPClass("sign")) {
			Sign.generateRPClass();
		}

	}
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		MockStendlRPWorld.reset();
		NPCList.get().clear();
	}


	/**
	 * Tests for getSlotName.
	 */
	@Test
	public void testGetSlotName() {
		assertEquals("reverse_arrow", new ReverseArrow().getSlotName());
	}

	/**
	 * Tests for addToWorld.
	 */
	@Test
	public void testAddToWorld() {

		ReverseArrow arrowquest = new ReverseArrow();
		arrowquest.addToWorld();
	}

	/**
	 * Tests for finish.
	 */
	@Test
	public void testFinish() {
		ReverseArrow arrowquest = new ReverseArrow();
		arrowquest.addToWorld();
		arrowquest.player = PlayerTestHelper.createPlayer("bob");
		assertNotNull(arrowquest.player);
		arrowquest.finish(false, null);
		assertNotNull(arrowquest.player);

		arrowquest.finish(true, null);
		assertNull(arrowquest.player);
	}
	

	private void addToken(LinkedList<Token> tokens,final int x, final int y){
		final Token token = new Token("Token","Token","Yellow", null);
		token.setPosition(x, y);
		tokens.add(token);
		
	}
	// * * 0 * *
	// * 1 * 2 *
	// 3 4 5 6 7
	// * * 8 * *
	@Test
	public void testCheckBoard() throws Exception {
		
		ReverseArrow arrowquest = new ReverseArrow();
		arrowquest.addToWorld();
		Player player = PlayerTestHelper.createPlayer("bob");
		PlayerTestHelper.registerPlayer(player, "int_ados_reverse_arrow");
		player.setQuest("reverse_arrow", "done");
		arrowquest.player = player;
		SpeakerNPC npc = SingletonRepository.getNPCList().get("Gamblos");
		arrowquest.npc = npc;

		LinkedList<Token> tokens = new LinkedList<Token>();
		addToken(tokens, 3, 1); //adding topmost zero
		addToken(tokens, 3, 4); //adding token eight
		addToken(tokens, 2, 2); //adding token one
		addToken(tokens, 4, 2); //adding token two
		for (int i = 1; i <= 5; i++) { //adding tokens three, four, five, six and seven
			addToken(tokens,i, 3);
		}
		arrowquest.tokens = tokens;
		ReverseArrow.ReverseArrowCheck checker = arrowquest.new ReverseArrowCheck();
		checker.onTurnReached(3);
		assertEquals("Congratulations, you solved the quiz again. But unfortunately I don't have any further rewards for you.", getReply(npc));
	}


}
