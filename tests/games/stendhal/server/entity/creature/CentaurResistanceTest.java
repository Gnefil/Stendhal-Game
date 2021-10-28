/**
 * 
 */
package games.stendhal.server.entity.creature;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.maps.MockStendhalRPRuleProcessor;
import games.stendhal.server.maps.MockStendlRPWorld;
import games.stendhal.common.constants.Nature;

/**
 * @author Ruben Bos
 *
 */
public class CentaurResistanceTest {
	/* a solar centaur creature */
	Creature solarCentaur;
	/* a glacier centaur creature */
	Creature iceCentaur;
	
	/* create mock world for investigation */
	@BeforeClass
	public static void setUpBeforeClass() {
		MockStendlRPWorld.get();
		MockStendhalRPRuleProcessor.get();
	}
	
	/* reset mock world */
	@AfterClass
	public static void tearDownAfterClass() {
		MockStendlRPWorld.reset();
	}
	
	/* get the solar and glacier centaur from the default entity manager */
	@Before
	public void setUp() {
		this.solarCentaur = SingletonRepository.getEntityManager().getCreature("solar centaur");
		this.iceCentaur = SingletonRepository.getEntityManager().getCreature("glacier centaur");
	}
	
	
	
	@Test
	public void testCentaurResistance() {
		/* solar centaur should be resistance to fire, weak to ice, vice versa for glacier */
		/* susceptibilty < 1 means resistance */
		assertThat(solarCentaur.getSusceptibility(Nature.FIRE), lessThan(Double.valueOf(1)));
		assertThat(iceCentaur.getSusceptibility(Nature.ICE), lessThan(Double.valueOf(1)));
		assertThat(solarCentaur.getSusceptibility(Nature.ICE), greaterThan(Double.valueOf(1)));
		assertThat(iceCentaur.getSusceptibility(Nature.FIRE), greaterThan(Double.valueOf(1)));
	}

}

