/**
 * $Id: Hunter.java,v 1.5 2011/11/01 18:23:30 acote Exp $
 * $Log: Hunter.java,v $
 * Revision 1.5  2011/11/01 18:23:30  acote
 * added some stuff to make duplicates identifiable, with their peers, in the root object selection jtree.
 *
 * Revision 1.4  2008/04/19 01:16:58  acote
 * Getting closer to being able to report the unreadable files.
 *
 * Revision 1.3  2008/04/18 13:15:07  acote
 * Trying to get the progress pane to display correctly on Fedora.
 *
 * Revision 1.2  2008/04/17 18:53:45  acote
 * Attempt to slow down GUI update a bit; might help with Linux compatibility.
 *
 * Revision 1.1  2008/04/16 13:06:48  acote
 * Reorganized source code.
 *
 * Revision 1.1  2008/04/14 12:02:21  acote
 * Renamed packages to make the evolved DupFileFinder implementation the main
 * one, but keep the old one around.
 *
 * Revision 1.14  2008/04/14 11:56:02  acote
 * Commented out some printlns.
 *
 * Revision 1.13  2008/04/14 11:49:53  acote
 * Prune off obsolete/unused code.
 *
 * Revision 1.12  2008/04/12 16:10:48  acote
 * Added code to clear results of old searches during new ones.
 *
 * Revision 1.11  2008/04/12 15:56:58  acote
 * Got progress pane updated appropriately on search cancellation.
 *
 * Revision 1.10  2008/04/12 13:43:23  acote
 * Enabled cancellation of the forest search phase.
 *
 * Revision 1.9  2008/04/12 13:17:17  acote
 * Provided the phases access to the isCancelled() method of SwingWorker.
 *
 * Revision 1.8  2008/04/12 11:46:36  acote
 * Made checksumming step optional.  Enabled/disabled start/stop search buttons
 * to reflect their actual function availability.
 *
 * Revision 1.7  2008/04/12 10:41:55  acote
 * Integrated code to present the results.
 *
 * Revision 1.6  2008/04/11 21:50:56  acote
 * Grouping by content comparison incorporated.
 *
 * Revision 1.5  2008/04/11 21:20:51  acote
 * Corrected failure to extract unique files from phase results and failure to update the relevant display.
 *
 * Revision 1.4  2008/04/11 21:04:33  acote
 * Grouping by checksum incorporated.
 *
 * Revision 1.3  2008/04/11 20:23:33  acote
 * Grouping by size incorporated.
 *
 * Revision 1.2  2008/04/11 20:01:11  acote
 * Checkpoint.
 *
 * Revision 1.1  2008/04/11 18:10:13  acote
 * Checkpoint.
 *
 */
package net.sf.cotelab.app.dupfilefinder.hunter;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.SwingWorker;

import net.sf.cotelab.app.dupfilefinder.beans.PhaseStats;
import net.sf.cotelab.util.FileComparator;

/**
 * A duplicate file finder worker.
 * 
 * @author cote
 */
public class Hunter
        extends SwingWorker<Void, PropertyChangeEvent>
        implements PropertyChangeListener {

    public static final long HRS_PER_DAY = 24;
    public static final long MILLIS_PER_MIN = 60 * 1000;
    public static final long MILLIS_PER_SEC = 1000;
    public static final long MINS_PER_HR = 60;
    public static final String PHASE_CONSTRUCTED = "Idle.";
    public static final String PHASE_FINDING_FILES = "Finding files . . .";
    public static final String PHASE_FINISHED = "Finished.";
    public static final String PHASE_GROUP_BY_CHECKSUM =
            "Grouping files by checksum . . .";
    public static final String PHASE_GROUP_BY_CONTENT_COMPARISON =
            "Grouping files by content comparison . . .";
    public static final String PHASE_GROUP_BY_SIZE =
            "Grouping files by size . . .";
    public static final String PHASE_GROUP_SUBTREES =
            "Identifying duplicate subtrees . . .";
    public static final String PHASE_STOPPED = "Stopped.";
    
    private static final Logger logger =
            Logger.getLogger(Hunter.class.getName());
    
    /**
     * Timestamp on entry to <code>doInBackground()</code>.
     */
    protected Date entryStamp = new Date();
    /**
     * Timestamp on exit from <code>doInBackground()</code>.
     */
    protected Date exitStamp = new Date();
    protected FileComparator fileComparator = new FileComparator();
	protected int nbrPhasesFinished = 0;
    protected NumberFormat numberFormat = NumberFormat.getNumberInstance();
    /**
     * The phase of execution.
     */
    protected String phase = PHASE_CONSTRUCTED;
    /**
     * Standard output from most-recently-started phase.
     */
    protected PhaseStats phaseOutput = null;
    /**
     * The pipeline of phases to execute.
     */
    protected LinkedList<Phase> pipeline = new LinkedList<Phase>();
    /**
     * The root(s) of the subtree(s) to search.
     */
    protected Collection<File> searchRootFileList;
    protected NumberFormat secsFormat = NumberFormat.getInstance();

    protected boolean useChecksums = false;

    /**
     * 
     */
    public Hunter(Collection<File> searchRootFileList, boolean useChecksums) {
        super();

        this.searchRootFileList = searchRootFileList;
        this.useChecksums = useChecksums;
        
        secsFormat.setMaximumFractionDigits(1);
        secsFormat.setMinimumFractionDigits(1);
    }

    /**
	 * @return the entryStamp
	 */
	public Date getEntryStamp() {
		return entryStamp;
	}

    /**
     * @return the phase
     */
    public String getPhase() {
        return phase;
    }

    /**
     * @return the pipeline
     */
    public LinkedList<Phase> getPipeline() {
        return pipeline;
    }

    /**
	 * @return the searchRootFileList
	 */
	public Collection<File> getSearchRootFileList() {
		return searchRootFileList;
	}

	@Override
    public void propertyChange(PropertyChangeEvent evt) {
        try {
        	if (PhaseStats.PN_PERCENT_COMPLETE.equals(evt.getPropertyName())) {
        		int pp = ((Integer) evt.getNewValue()).intValue();
        		
        		updateProgress(pp);
        	}
        	
            if (evt.getSource() instanceof PhaseStats) {
                publish(evt);
            } else {
                logger.warning("!(evt.getSource() instanceof PhaseStats)");
            }
        } catch (Throwable t) {
            logger.log(Level.WARNING, "forwarding Throwable", t);
            throw (new RuntimeException(t));
        }
    }

    /**
     * @param phase the phase to set
     */
    public void setPhase(String phase) {
        this.phase = phase;
    }

    /**
     * Assemble the pipeline to be executed.
     */
    protected void assemblePipeline() {
    	ForestSearchPhaseUsingPath initialPhase;
        PhaseStats stats;
        PhaseStats prevStats;
        Phase phase;

        stats = new PhaseStats(PHASE_FINDING_FILES);
        initialPhase = new ForestSearchPhaseUsingPath(null, stats, this,
                searchRootFileList);
        pipeline.add(initialPhase);

        prevStats = stats;
        stats = new PhaseStats(PHASE_GROUP_BY_SIZE);
        phase = new GroupBySizePhase(prevStats, stats, this);
        pipeline.add(phase);

        if (useChecksums) {
            prevStats = stats;
            stats = new PhaseStats(PHASE_GROUP_BY_CHECKSUM);
            phase = new GroupByChecksumPhase(prevStats, stats, this);
            pipeline.add(phase);
        }

        prevStats = stats;
        stats = new PhaseStats(PHASE_GROUP_BY_CONTENT_COMPARISON);
        phase = new GroupByContentPhase(prevStats, stats, this);
        pipeline.add(phase);

        prevStats = stats;
        stats = new PhaseStats(PHASE_GROUP_SUBTREES);
        phase = new GroupSubtreesPhase(prevStats, stats, this);
        pipeline.add(phase);
    }

	/*
     * (non-Javadoc)
     * 
     * @see javax.swing.SwingWorker#doInBackground()
     */
    @Override
    protected Void doInBackground() throws Exception {
        try {
            entryStamp = new Date();

            assemblePipeline();

            if (isCancelled()) {
                logger.info("cancelled.");
                return null;
            }

            executePipeline();

            setPhase(isCancelled() ? PHASE_STOPPED : PHASE_FINISHED);

            exitStamp = new Date();
        } catch (Throwable t) {
            logger.log(Level.WARNING, "forwarding Throwable", t);
            throw (new RuntimeException(t));
        }

        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.SwingWorker#done()
     */
    @Override
    protected void done() {
        setPhase(isCancelled() ? PHASE_STOPPED : PHASE_FINISHED);

//        logger.info("exit the method.");
    }

    /**
     * Execute the pipeline.
     */
    protected void executePipeline() {
        try {
        	nbrPhasesFinished = 0;
//        	double fullScale = 100;
//        	double increment = fullScale / pipeline.size();
        	
            for (Phase step : pipeline) {
                phaseOutput = step.getOutput();

                phaseOutput.addPropertyChangeListener(this);
                phaseOutput.setPhaseEntryStamp(new Date());
                step.run();
                phaseOutput.removePropertyChangeListener(this);

                if (isCancelled()) {
                    logger.info("cancelled.");
                    return;
                }
                
                ++nbrPhasesFinished;
                
//                setProgress((int) Math.round(increment * nbrPhasesFinished));
            }
        } catch (Throwable t) {
            logger.log(Level.WARNING, "caught Throwable", t);
        }

//        logger.info("exit the method.");
    }

    protected String formatElapsed(Date begin, Date end) {
        long beginMillis = begin.getTime();
        long endMillis = end.getTime();

        if (beginMillis > endMillis) {
            endMillis = (new Date()).getTime();
        }

        return formatMillis(endMillis - beginMillis);
    }
    protected String formatMillis(long millis) {
        String retValue = null;
        long mins = 0;
        double millisNoMins = 0;
        double secs = 0;
        long hrs = 0;
        long days = 0;

        mins = millis / MILLIS_PER_MIN;
        millisNoMins = millis % MILLIS_PER_MIN;
        secs = millisNoMins / MILLIS_PER_SEC;
        hrs = mins / MINS_PER_HR;
        mins %= MINS_PER_HR;
        days = hrs / HRS_PER_DAY;
        hrs = hrs % HRS_PER_DAY;

        retValue = Long.toString(days) + "d "
                + Long.toString(hrs) + "h "
                + Long.toString(mins) + "m "
                + secsFormat.format(secs) + "s";

        return retValue;
    }

    protected String makePctString(long num, long denom, Date begin) {
        double numDbl = num;
        double fractionCompleted = numDbl / denom;
        double percentCompleted = (fractionCompleted + 0.005) * 100;
        int pct = (int) percentCompleted;
        long nowMillis = (new Date()).getTime();
        double millisUsed = nowMillis - begin.getTime();
        double millisTotal = millisUsed / fractionCompleted;
        double millisRemain = millisTotal - millisUsed;
        String retValue = numberFormat.format(pct) + "%";

        if (pct > 20) {
            retValue = retValue + " (~"
                    + formatMillis((long) millisRemain) + " remaining)";
        }

        return retValue;
    }
    
    /* (non-Javadoc)
     * @see javax.swing.SwingWorker#process(java.util.List)
     */

    @Override
    protected void process(List<PropertyChangeEvent> chunks) {
    	try {
    		PropertyChangeSupport pcs = getPropertyChangeSupport();
//    		String message = "reporting on " + chunks.size() + " chunks.";
//    		
//    		logger.log(Level.INFO, message);
        	
        	for (PropertyChangeEvent evt : chunks) {
        		pcs.firePropertyChange(evt);
        	}
    	} catch (Throwable t) {
    		logger.log(Level.WARNING, "Caught Throwable", t);
    	}
    }

	protected void updateProgress(int phaseProgress) {
		int nbrPhases = pipeline.size();
		double finishedWeight = 0;
		double maxWeight = 0;
		double curProRataWeight = 0;
		double totalProRataWeight = 0;
		double fractDone = 0;
		double pctDoneDouble = 0;
		int progressRollUp = 0;
		boolean isUsingCksum = nbrPhases == 5;
		
		// collect some weight info
		for (int i = 0; i < nbrPhases; ++i) {
			double pw = pipeline.get(i).getProgressWeight(isUsingCksum);
			
			if (i < nbrPhasesFinished) {
				finishedWeight += pw;
			}
			
			maxWeight += pw;
		}
		
		if (nbrPhasesFinished < nbrPhases) {
			double curWeight =
					pipeline.get(nbrPhasesFinished).getProgressWeight(
							isUsingCksum);
			
			curProRataWeight = (curWeight * phaseProgress) / 100;
		}
		
		// wrap up the math
		totalProRataWeight = finishedWeight + curProRataWeight;
		fractDone = totalProRataWeight / maxWeight;
		pctDoneDouble = fractDone * 100;
		progressRollUp = (int) Math.round(pctDoneDouble);
		
		setProgress(progressRollUp);
	}
}
