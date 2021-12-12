/**
 * 
 */
package net.sf.cotelab.lbl.model.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.Sequence;
import org.jmock.imposters.ByteBuddyClassImposteriser;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import net.sf.cotelab.jfxrunner.JavaFxJUnit4ClassRunner;
import net.sf.cotelab.playingcards.Card;

@RunWith(JavaFxJUnit4ClassRunner.class)
public class FanImplTest {
	protected Mockery context;
	protected Sequence sequence;
	
	@Before
	public void runBeforeTests() throws Exception {
		context = new Mockery() {{
			setThreadingPolicy( new Synchroniser());
			setImposteriser( ByteBuddyClassImposteriser.INSTANCE );
		}};
		
		sequence = context.sequence( getClass().getName());
	}
	
	@After
	public void runAfterTests() throws Exception {
		context.assertIsSatisfied();
	}
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
