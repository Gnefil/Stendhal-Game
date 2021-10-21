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
	Creature solarCentaur;
	Creature iceCentaur;
	/*@Test
	public void test() {
		fail("Not yet implemented");
	}*/
	@BeforeClass
	public static void setUpBeforeClass() {
		MockStendlRPWorld.get();
		MockStendhalRPRuleProcessor.get();
	}
	
	@AfterClass
	public static void tearDownAfterClass() {
		MockStendlRPWorld.reset();
	}
	
	@Before
	public void setUp() {
		this.solarCentaur = SingletonRepository.getEntityManager().getCreature("solar centaur");
		this.iceCentaur = SingletonRepository.getEntityManager().getCreature("glacier centaur");
	}
	
	
	
	@Test
	public void testLoad() {
		//solarCentaur = SingletonRepository.getEntityManager().getCreature("centaur_solar");
		//iceCentaur = SingletonRepository.getEntityManager().getCreature("centaur_glacier");
		//assertNotSame(solarCentaur, iceCentaur);
		//assertTrue(solarCentaur != null);
		//assertThat(Double.valueOf(solarCentaur.getSusceptibility(Nature.FIRE)), is(0.8));
		assertThat(solarCentaur.getSusceptibility(Nature.FIRE), lessThan(Double.valueOf(1)));
		assertThat(iceCentaur.getSusceptibility(Nature.ICE), lessThan(Double.valueOf(1)));
		assertThat(solarCentaur.getSusceptibility(Nature.ICE), greaterThan(Double.valueOf(1)));
		assertThat(iceCentaur.getSusceptibility(Nature.FIRE), greaterThan(Double.valueOf(1)));
	}

}

