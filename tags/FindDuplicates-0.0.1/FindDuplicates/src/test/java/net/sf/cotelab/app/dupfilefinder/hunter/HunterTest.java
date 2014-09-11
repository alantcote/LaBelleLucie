/**
 * 
 */
package net.sf.cotelab.app.dupfilefinder.hunter;

import static org.junit.Assert.*;

import java.util.Random;

import net.sf.cotelab.app.dupfilefinder.hunter.Hunter;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author US80653H
 *
 */
public class HunterTest {
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

	/*
	 * updateProgress(int phaseProgress) is expected to work as follows:
	 * finishedWeight = sum(phase[i].getProgressWeight()), for
	 * 		i < nbrPhasesFinished
	 * b = phase[nbrPhasesFinished].getProgressWeight(), if
	 *		nbrPhasesFinished < pipeline.size(), else 0
	 * curProRataWeight = (b * phaseProgress) / 100
	 * totalProRataWeight = finishedWeight + curProRataWeight
	 * maxWeight = sum(phase[i].getProgressWeight), for i < pipeline.size()
	 * fractDone = totalProRataWeight / maxWeight
	 * pctDoneDouble = fractDone * 100
	 * pctDoneInt = Math.round(pctDoneDouble)
	 * setProgress(pctDoneInt)
	 */
	@Test
	public void testUpdateProgress() {
		int nbrTrials = 50;
		Random rand = new Random();
		
		for (int i = 0; i < nbrTrials; ++i) {
			boolean useChkSums = rand.nextBoolean();
			int nbrPhases = useChkSums ? 5 : 4;
			int phaseProgress = rand.nextInt(100);
			int phasesFinished = rand.nextInt(nbrPhases);
			double[] phaseWeights =new double[nbrPhases];
			
			for (int j = 0; j < nbrPhases; ++j) {
				phaseWeights[j] = rand.nextDouble();
			}
			
			testUpdateProgress_Helper(
					useChkSums, phaseProgress, phasesFinished, phaseWeights);
		}
	}
	
	protected void testUpdateProgress_Helper(
			boolean useChkSums, int phaseProgress,
			int phasesFinished, double[] phaseWeights) {
		Hunter fixture = new Hunter(null, useChkSums);
		
		fixture.assemblePipeline();
		fixture.nbrPhasesFinished = phasesFinished;
		
		int nbrPhases = fixture.pipeline.size();
		double finishedWeight = 0;
		double maxWeight = 0;
		double curProRataWeight = 0;
		double totalProRataWeight = 0;
		double fractDone = 0;
		double pctDoneDouble = 0;
		int expected = 0;
		
		// iniz the phase progressWeight values
		for (int i = 0; i < nbrPhases; ++i) {
			fixture.pipeline.get(i).progressWeight = phaseWeights[i];
		}
		
		// collect some weight info
		for (int i = 0; i < nbrPhases; ++i) {
			double pw = fixture.pipeline.get(i).getProgressWeight(useChkSums);
			
			if (i < fixture.nbrPhasesFinished) {
				finishedWeight += pw;
			}
			
			maxWeight += pw;
		}
		
		if (fixture.nbrPhasesFinished < nbrPhases) {
			double curWeight = fixture.pipeline.get(
					fixture.nbrPhasesFinished).getProgressWeight(useChkSums);
			
			curProRataWeight = (curWeight * phaseProgress) / 100;
		}
		
		// wrap up the math
		totalProRataWeight = finishedWeight + curProRataWeight;
		fractDone = totalProRataWeight / maxWeight;
		pctDoneDouble = fractDone * 100;
		expected = (int) Math.round(pctDoneDouble);
		
		assertTrue(0 <= expected);
		assertTrue(expected <= 100);
		
		fixture.updateProgress(phaseProgress);
		
		assertEquals(expected, fixture.getProgress());
	}
}
