/**
 * 
 */
package net.sf.cotelab.app.dupfilefinder.hunter;

import static org.junit.Assert.*;

import java.util.concurrent.Future;

import net.sf.cotelab.app.dupfilefinder.beans.PhaseStats;
import net.sf.cotelab.app.dupfilefinder.hunter.Hunter;
import net.sf.cotelab.app.dupfilefinder.hunter.Phase;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author US80653H
 *
 */
public class PhaseTest {

	/**
	 * @author US80653H
	 *
	 */
	protected class SamplePhase extends Phase {

		/**
		 * @param input
		 * @param output
		 * @param worker
		 */
		public SamplePhase(PhaseStats input, PhaseStats output,
				Future<Void> worker) {
			super(input, output, worker);
		}

		/* (non-Javadoc)
		 * @see net.cote.app.dupfilefinder.hunter.Phase#runPhase()
		 */
		@Override
		protected void runPhase() {
			// NOTHING
		}
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetProgressWeight() {
		PhaseStats stats = new PhaseStats("presentation name");
		Hunter hunter = new Hunter(null, false);
		Phase fixture = new SamplePhase(stats, stats, hunter);
		double expected = 39.5;
		double actual;
		
		fixture.progressWeight = expected;
		
		actual = fixture.getProgressWeight(false);
		
		assertTrue(expected == actual);
	}
}
