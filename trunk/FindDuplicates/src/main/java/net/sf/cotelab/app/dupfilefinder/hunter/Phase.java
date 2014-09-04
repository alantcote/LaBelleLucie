/**
 * $Id: Phase.java,v 1.1 2008/04/16 13:06:48 acote Exp $
 * $Log: Phase.java,v $
 * Revision 1.1  2008/04/16 13:06:48  acote
 * Reorganized source code.
 *
 * Revision 1.2  2008/04/15 17:12:12  acote
 * Cleaned up some javadoc comments.
 *
 * Revision 1.1  2008/04/14 12:02:22  acote
 * Renamed packages to make the evolved DupFileFinder implementation the main
 * one, but keep the old one around.
 *
 * Revision 1.6  2008/04/12 13:17:17  acote
 * Provided the phases access to the isCancelled() method of SwingWorker.
 *
 * Revision 1.5  2008/04/11 20:01:11  acote
 * Checkpoint.
 *
 * Revision 1.4  2008/04/11 18:10:13  acote
 * Checkpoint.
 *
 * Revision 1.3  2008/04/10 19:50:32  acote
 * Still evolving.
 *
 * Revision 1.2  2008/04/10 18:59:30  acote
 * Still evolving.
 *
 * Revision 1.1  2008/04/10 15:28:34  acote
 * Creating a place for advanced development.
 *
 */
package net.sf.cotelab.app.dupfilefinder.hunter;

import java.util.Date;
import java.util.concurrent.Future;

import net.sf.cotelab.app.dupfilefinder.beans.PhaseStats;

/**
 * A duplicate file finder phase.
 * 
 * @author cote
 */
public abstract class Phase implements Runnable {
	/**
	 * Standard input to the phase.
	 */
	protected PhaseStats input;

	/**
	 * Standard output from the phase.
	 */
	protected PhaseStats output;
	protected double progressWeight = 1;
	protected double progressWeightUsingCksum = 1;
	
	/**
	 * The owner of the thread in which <tt>run()</tt> is executed.
	 */
	protected Future<Void> worker;

	/**
	 * Construct a new object.
	 * 
	 * @param input standard input to the phase.
	 * @param output standard output from the phase.
	 * @param worker the <tt>SwingWorker</tt> in charge.
	 */
	public Phase(PhaseStats input, PhaseStats output, Future<Void> worker) {
		super();

		this.input = input;
		this.output = output;
		this.worker = worker;
	}

	/**
	 * @return the output
	 */
	public PhaseStats getOutput() {
		return output;
	}

	public double getProgressWeight(boolean isUsingCksum) {
		return isUsingCksum ? progressWeightUsingCksum : progressWeight;
	}

	/**
	 * @return the value of <tt>worker</tt>'s <tt>cancelled</tt> property.
	 * @see javax.swing.SwingWorker#isCancelled()
	 */
	public final boolean isCancelled() {
		return worker.isCancelled();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		String pn = output.getPresentationName();
		
		output.setPhaseEntryStamp(new Date());
		
		if (input != null) {
			output.propagateGlobalsFrom(input);
		}

		output.setPresentationName("");
		output.setPresentationName(pn);
		output.setPhaseExitStamp(output.getPhaseEntryStamp());
		output.setMeaningfulProgress(true);
		output.setMeaningfulProgress(false);

		runPhase();

		output.setPhaseExitStamp(new Date());
	}

	/**
	 * Do the Real Work&tm; of the phase.
	 */
	protected abstract void runPhase();
}
