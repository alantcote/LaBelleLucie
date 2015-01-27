/**
 * 
 */
package net.sf.cotelab.lbl.model.impl;

import static org.junit.Assert.*;
import net.sf.cotelab.playingcards.Card;
import net.sf.cotelab.testutils.jMockTestHelper;

import org.jmock.Expectations;
import org.junit.Test;

public class FanImplTest extends jMockTestHelper {
	// Most of FanImpl is generated delegate-pattern code, and thus requires no
	// test. Custom methods of FanImpl are tested here.
	
	@Test
	public void testFanImpl() {
		FanImpl fixture = new FanImpl();
		
		assertNotNull(fixture);
		assertNotNull(fixture.cards);
		assertTrue(fixture.cards.isEmpty());
	}
	
	@Test
	public void testGetTopCard() {
		final Card mockCard = context.mock(Card.class);
		final FanImpl mockFanImpl = context.mock(FanImpl.class);
		
		context.checking(new Expectations() {{
			// test with no cards at all
			oneOf(mockFanImpl).size();
			will(returnValue(0));
			
			// test with one card
			oneOf(mockFanImpl).size();
			will(returnValue(1));

			oneOf(mockFanImpl).get(with(0));
			will(returnValue(mockCard));
			
			// test with two cards
			oneOf(mockFanImpl).size();
			will(returnValue(2));

			oneOf(mockFanImpl).get(with(1));
			will(returnValue(mockCard));
		}});

		FanImpl fixture = new FanImpl() {
			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.FanImpl#get(int)
			 */
			@Override
			public Card get(int index) {
				return mockFanImpl.get(index);
			}

			/* (non-Javadoc)
			 * @see net.sf.cotelab.lbl.model.impl.FanImpl#size()
			 */
			@Override
			public int size() {
				return mockFanImpl.size();
			}
		};
		
		// test with no cards at all
		assertNull(fixture.getTopCard());
		
		// test with one card
		assertEquals(mockCard, fixture.getTopCard());
		
		// test with two cards
		assertEquals(mockCard, fixture.getTopCard());
	}
}
