package net.sf.cotelab.util.math;

public class ArithProg {

	protected long delta;
	protected long first;

	public ArithProg(long first, long delta) {
		super();
		
		this.first = first;
		this.delta = delta;
	}

	public long getTerm(long index) {
		long term = first + (delta * index);
		
		return term;
	}

	public long sumNTerms(int nbrTerms) {
		long sum = 0;
		
		if (nbrTerms > 0) {
			long last = getTerm(nbrTerms - 1);
			
			sum = ((first + last) * nbrTerms) / 2;
		}
		
		return sum;
	}

}
