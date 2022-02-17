package io.github.alantcote.labellelucie.controller.facade;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test case for {@link io.github.alantcote.labellelucie.controller.facade.Move}.
 */
public class MoveTest {

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.controller.facade.Move#Move(int, int, io.github.alantcote.labellelucie.controller.facade.MoveType)}.
	 */
	@Test
	public void testMove() {
		Move fixture = new Move(0, 1, MoveType.TABLEAU_TO_TABLEAU);
		
		assertTrue(0 == fixture.destFanIndex);
		assertTrue(1 == fixture.srcFanIndex);
		assertTrue(MoveType.TABLEAU_TO_TABLEAU == fixture.type);
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.controller.facade.Move#getDestFanIndex()}.
	 */
	@Test
	public void testGetDestFanIndex() {
		Move fixture = new Move(0, 1, MoveType.TABLEAU_TO_TABLEAU);
		
		assertTrue(fixture.destFanIndex == fixture.getDestFanIndex());
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.controller.facade.Move#getSrcFanIndex()}.
	 */
	@Test
	public void testGetSrcFanIndex() {
		Move fixture = new Move(0, 1, MoveType.TABLEAU_TO_TABLEAU);
		
		assertTrue(fixture.srcFanIndex == fixture.getSrcFanIndex());
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.controller.facade.Move#getType()}.
	 */
	@Test
	public void testGetType() {
		Move fixture = new Move(0, 1, MoveType.TABLEAU_TO_TABLEAU);
		
		assertTrue(fixture.type == fixture.getType());
	}

	/**
	 * Test method for {@link io.github.alantcote.labellelucie.controller.facade.Move#toString()}.
	 */
	@Test
	public void testToString() {
		Move fixture = new Move(0, 1, MoveType.TABLEAU_TO_TABLEAU);
		
		assertNotNull(fixture.toString());
	}

}
