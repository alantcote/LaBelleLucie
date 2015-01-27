package net.sf.cotelab.testutils;

import org.jmock.Expectations;
import org.jmock.Sequence;
import org.jmock.syntax.ReceiverClause;

public class SequentialExpectations extends Expectations {

	private final Sequence sequence;

	public SequentialExpectations(Sequence sequence) {
		this.sequence = sequence;
	}

	@Override
	public ReceiverClause exactly( int count ) {
		ReceiverClause returnValue = super.exactly(count);
		inSequence(sequence);
		return returnValue;
	}
}
