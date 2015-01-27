package net.sf.cotelab.testutils;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.OutputStream;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.Sequence;
import org.jmock.api.ExpectationError;
import org.jmock.lib.concurrent.Synchroniser;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Test;

public class SequentialExpectationsTest extends jMockTestHelper {

	@Test
	public void create() {
		
		Expectations expectations = new SequentialExpectations( sequence ); 
		
		assertNotNull( expectations );
	}
	
	@Test
	public void oneOf() {
		final Sequence seq = context.mock( Sequence.class, "mocked-sequence" );
		final Expectations delegate = context.mock( Expectations.class );
		final OutputStream mock = context.mock( OutputStream.class );
		context.checking( new Expectations() {{
			oneOf(delegate).inSequence(with(seq));
		}});
		
		Expectations expectations = new SequentialExpectations( seq ) {
			@Override
			public void inSequence(Sequence sequence) {
				delegate.inSequence(sequence);
			}
		};
		
		expectations.oneOf(mock);
	}
	
	@Test
	public void exactly() {		
		final Sequence seq = context.mock( Sequence.class );
		final Expectations delegate = context.mock( Expectations.class );
		context.checking( new Expectations() {{
			oneOf(delegate).inSequence(with(seq));
		}});
		
		Expectations expectations = new SequentialExpectations( seq ) {
			@Override
			public void inSequence(Sequence sequence) {
				delegate.inSequence(sequence);
			}
		};
		
		expectations.exactly(2);
	}
	
	@Test
	public void sequenceInOrderSucceeds() throws IOException {
		final OutputStream out = context.mock( OutputStream.class );
		context.checking( new SequentialExpectations( sequence ) {{
			exactly(2).of(out).flush();
			oneOf(out).close();
		}});
	
		out.flush();
		out.flush();
		out.close();
	}
	
	@Test
	public void sequenceOutOfOrderFails() throws IOException {
		Mockery mockery = new Mockery() {{
			setThreadingPolicy( new Synchroniser());
			setImposteriser( ClassImposteriser.INSTANCE );
		}};
		Sequence seq = mockery.sequence("force-failure");
		
		final OutputStream out = mockery.mock( OutputStream.class );
		
		mockery.checking( new SequentialExpectations( seq ) {{
			oneOf(out).flush();
			oneOf(out).close();
		}});
	
		try {
			out.close();
			out.flush();
			fail( "should have failed" );
		}
		catch( ExpectationError error ) {
			assertEquals( "unexpected invocation", error.getMessage());
		}
	}

}
