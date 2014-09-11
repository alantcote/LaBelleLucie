package net.sf.cotelab.app.dupfilefinder.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

/**
 * @author US80653H
 */
public class ProgressPanel extends javax.swing.JPanel
		implements ResettableObject {
	public static final String DEFAULT_PPB_TOOL_TIP_TEXT = "Progress in phase";
	public static final long HRS_PER_DAY = 24;
	public static final long MILLIS_PER_MIN = 60 * 1000;
    public static final long MILLIS_PER_SEC = 1000;
    public static final long MINS_PER_HR = 60;
    public static final int SECS_FORMAT_FRACT_DIGITS = 3;
    
    private static final long serialVersionUID = 1L;
    
    protected ButtonAndMssgsPanel buttonAndMssgsPanel =
    		new ButtonAndMssgsPanel();
    protected ProgressBarsPanel progressBarsPanel =
    		new ProgressBarsPanel();
    protected NumberFormat secsFormat = NumberFormat.getInstance();
    protected StatsPanel statsPanel = new StatsPanel();

    public ProgressPanel() {
        initComponents();
        
        secsFormat.setMaximumFractionDigits(SECS_FORMAT_FRACT_DIGITS);
        secsFormat.setMinimumFractionDigits(SECS_FORMAT_FRACT_DIGITS);
    }
    
    /**
	 * @param l
	 * @see net.sf.cotelab.app.dupfilefinder.gui.ButtonAndMssgsPanel#addActionListener(java.awt.event.ActionListener)
	 */
	public void addActionListener(ActionListener l) {
		buttonAndMssgsPanel.addActionListener(l);
	}

	@Override
	public void reset() {
		buttonAndMssgsPanel.reset();
		progressBarsPanel.reset();
		statsPanel.reset();
	}
    
    public void resetPhaseProgressBar() {
		progressBarsPanel.reset();
	}

	/**
	 * @param t
	 * @see net.sf.cotelab.app.dupfilefinder.gui.StatsPanel#setAllDirsFileListCountDisplayText(java.lang.String)
	 */
	public void setAllDirsFileListCountDisplayText(String t) {
		statsPanel.setAllDirsFileListCountDisplayText(t);
	}

	/**
	 * @param t
	 * @see net.sf.cotelab.app.dupfilefinder.gui.StatsPanel#setAllFilesFileListCountDisplayText(java.lang.String)
	 */
	public void setAllFilesFileListCountDisplayText(String t) {
		statsPanel.setAllFilesFileListCountDisplayText(t);
	}

	/**
	 * @param b
	 * @see net.sf.cotelab.app.dupfilefinder.gui.ButtonAndMssgsPanel#setButtonEnabled(boolean)
	 */
	public void setButtonEnabled(boolean b) {
		buttonAndMssgsPanel.setButtonEnabled(b);
	}

	/**
	 * @param text
	 * @see net.sf.cotelab.app.dupfilefinder.gui.ButtonAndMssgsPanel#setDetailMessageText(java.lang.String)
	 */
	public void setDetailMessageText(String text) {
		buttonAndMssgsPanel.setDetailMessageText(text);
	}

	/**
	 * @param t
	 * @see net.sf.cotelab.app.dupfilefinder.gui.StatsPanel#setElapsedFindingDuplicateFilesDisplayText(java.lang.String)
	 */
	public void setElapsedFindingDuplicateFilesDisplayText(String t) {
		statsPanel.setElapsedFindingDuplicateFilesDisplayText(t);
	}

	/**
	 * @param t
	 * @see net.sf.cotelab.app.dupfilefinder.gui.StatsPanel#setElapsedFindingFilesDisplayText(java.lang.String)
	 */
	public void setElapsedFindingFilesDisplayText(String t) {
		statsPanel.setElapsedFindingFilesDisplayText(t);
	}

	/**
	 * @param t
	 * @see net.sf.cotelab.app.dupfilefinder.gui.StatsPanel#setElapsedGroupByChecksumDisplayText(java.lang.String)
	 */
	public void setElapsedGroupByChecksumDisplayText(String t) {
		statsPanel.setElapsedGroupByChecksumDisplayText(t);
	}

	/**
	 * @param t
	 * @see net.sf.cotelab.app.dupfilefinder.gui.StatsPanel#setElapsedGroupByContentComparisonDisplayText(java.lang.String)
	 */
	public void setElapsedGroupByContentComparisonDisplayText(String t) {
		statsPanel.setElapsedGroupByContentComparisonDisplayText(t);
	}

	/**
	 * @param t
	 * @see net.sf.cotelab.app.dupfilefinder.gui.StatsPanel#setElapsedGroupBySizeDisplayText(java.lang.String)
	 */
	public void setElapsedGroupBySizeDisplayText(String t) {
		statsPanel.setElapsedGroupBySizeDisplayText(t);
	}

	/**
	 * @param t
	 * @see net.sf.cotelab.app.dupfilefinder.gui.StatsPanel#setElapsedGroupingSubtreesDisplayText(java.lang.String)
	 */
	public void setElapsedGroupingSubtreesDisplayText(String t) {
		statsPanel.setElapsedGroupingSubtreesDisplayText(t);
	}

	/**
	 * @param t
	 * @see net.sf.cotelab.app.dupfilefinder.gui.StatsPanel#setFilesByContentCountDisplayText(java.lang.String)
	 */
	public void setFilesByContentCountDisplayText(String t) {
		statsPanel.setFilesByContentCountDisplayText(t);
	}

	/**
	 * @param t
	 * @see net.sf.cotelab.app.dupfilefinder.gui.StatsPanel#setNbrGroupsByChecksumDisplayText(java.lang.String)
	 */
	public void setNbrGroupsByChecksumDisplayText(String t) {
		statsPanel.setNbrGroupsByChecksumDisplayText(t);
	}

	/**
	 * @param t
	 * @see net.sf.cotelab.app.dupfilefinder.gui.StatsPanel#setNbrGroupsBySizeDisplayText(java.lang.String)
	 */
	public void setNbrGroupsBySizeDisplayText(String t) {
		statsPanel.setNbrGroupsBySizeDisplayText(t);
	}

	/**
	 * @param newValue
	 * @see javax.swing.JProgressBar#setIndeterminate(boolean)
	 */
	public void setPhaseProgressBarIndeterminate(boolean newValue) {
		progressBarsPanel.setPhaseProgressBarIndeterminate(newValue);
	}

	/**
	 * @param s
	 * @see javax.swing.JProgressBar#setString(java.lang.String)
	 */
	public void setPhaseProgressBarString(String s) {
		progressBarsPanel.setPhaseProgressBarString(s);
	}

	/**
	 * @param b
	 * @see javax.swing.JProgressBar#setStringPainted(boolean)
	 */
	public void setPhaseProgressBarStringPainted(boolean b) {
		progressBarsPanel.setPhaseProgressBarStringPainted(b);
	}
	
	/**
	 * @param n
	 * @see javax.swing.JProgressBar#setValue(int)
	 */
	public void setPhaseProgressBarValue(int n) {
		progressBarsPanel.setPhaseProgressBarValue(n);
	}

	/**
	 * @param newValue
	 * @see net.sf.cotelab.app.dupfilefinder.gui.ProgressBarsPanel#setSearchProgressBarIndeterminate(boolean)
	 */
	public void setSearchProgressBarIndeterminate(boolean newValue) {
		progressBarsPanel.setSearchProgressBarIndeterminate(newValue);
	}

	/**
	 * @param s
	 * @see net.sf.cotelab.app.dupfilefinder.gui.ProgressBarsPanel#setSearchProgressBarString(java.lang.String)
	 */
	public void setSearchProgressBarString(String s) {
		progressBarsPanel.setSearchProgressBarString(s);
	}

	/**
	 * @param b
	 * @see net.sf.cotelab.app.dupfilefinder.gui.ProgressBarsPanel#setSearchProgressBarStringPainted(boolean)
	 */
	public void setSearchProgressBarStringPainted(boolean b) {
		progressBarsPanel.setSearchProgressBarStringPainted(b);
	}

	/**
	 * @param n
	 * @see net.sf.cotelab.app.dupfilefinder.gui.ProgressBarsPanel#setSearchProgressBarValue(int)
	 */
	public void setSearchProgressBarValue(int n) {
		progressBarsPanel.setSearchProgressBarValue(n);
	}

	/**
	 * @param t
	 * @see net.sf.cotelab.app.dupfilefinder.gui.StatsPanel#setSearchRootFileListCountDisplayText(java.lang.String)
	 */
	public void setSearchRootFileListCountDisplayText(String t) {
		statsPanel.setSearchRootFileListCountDisplayText(t);
	}

	/**
	 * @param text
	 * @see net.sf.cotelab.app.dupfilefinder.gui.ButtonAndMssgsPanel#setSummaryMessageText(java.lang.String)
	 */
	public void setSummaryMessageText(String text) {
		buttonAndMssgsPanel.setSummaryMessageText(text);
	}

	/**
	 * @param t
	 * @see net.sf.cotelab.app.dupfilefinder.gui.StatsPanel#setAllFilesFileListCountDisplayText(java.lang.String)
	 */
	public void setTotalFileCountDisplayText(String t) {
		statsPanel.setTotalFileCountDisplayText(t);
	}

	/**
	 * @param t
	 * @see net.sf.cotelab.app.dupfilefinder.gui.StatsPanel#setUniqueFileListCountDisplayText(java.lang.String)
	 */
	public void setUniqueFileListCountDisplayText(String t) {
		statsPanel.setUniqueFileListCountDisplayText(t);
	}

	/**
	 * @param t
	 * @see net.sf.cotelab.app.dupfilefinder.gui.StatsPanel#setUnreadableFilesListCountDisplayText(java.lang.String)
	 */
	public void setUnreadableFilesListCountDisplayText(String t) {
		statsPanel.setUnreadableFilesListCountDisplayText(t);
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

	/** This method is called from within the constructor to
     * initialize the form.
     */
    protected void initComponents() {
        setLayout(new java.awt.BorderLayout());

        add(statsPanel, BorderLayout.CENTER);
        add(buttonAndMssgsPanel, BorderLayout.SOUTH);

        progressBarsPanel.reset();
        add(progressBarsPanel, BorderLayout.NORTH);
    }
}
