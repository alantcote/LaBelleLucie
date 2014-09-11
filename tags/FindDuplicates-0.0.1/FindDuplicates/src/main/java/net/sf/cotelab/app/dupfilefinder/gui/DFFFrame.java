/**
 * 
 */
package net.sf.cotelab.app.dupfilefinder.gui;

import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.IndexedPropertyChangeEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;

import net.sf.cotelab.app.dupfilefinder.beans.PhaseStats;
import net.sf.cotelab.app.dupfilefinder.hunter.Hunter;
import net.sf.cotelab.app.dupfilefinder.hunter.Phase;
import net.sf.cotelab.app.dupfilefinder.tree.CachedFileTreeCellRenderer;
import net.sf.cotelab.app.dupfilefinder.tree.DuplicateHighlightingTreeCellRenderer;

/**
 * @author US80653H
 */
public class DFFFrame extends JFrame implements ResettableObject {
	
	public class HunterListener implements PropertyChangeListener {
	    public static final long HRS_PER_DAY = 24;
	    public static final long MILLIS_PER_MIN = 60 * 1000;
	    public static final long MILLIS_PER_SEC = 1000;
	    public static final long MINS_PER_HR = 60;
	    public static final String PN_PROGRESS = "progress";
	    public static final String PN_STATE = "state";

	    protected NumberFormat hrsFormat = NumberFormat.getInstance();
		protected Date huntEntryStamp = new Date();
		protected boolean meaningfulProgress = false;
		protected NumberFormat minsFormat = NumberFormat.getInstance();
		protected int nbrDirs = 0;
		protected int nbrRegFiles = 0;
		protected int nbrUnreadableFiles = 0;
		protected NumberFormat NUMBER_FORMAT = NumberFormat.getNumberInstance();
		protected Date phaseEntryStamp = new Date();
		protected String presentationName = "";
		protected NumberFormat secsFormat = NumberFormat.getInstance();
		
		public HunterListener() {
			super();
			
			hrsFormat.setMaximumFractionDigits(0);
			hrsFormat.setMaximumIntegerDigits(2);
			hrsFormat.setMinimumFractionDigits(0);
			hrsFormat.setMinimumIntegerDigits(2);
			
			minsFormat.setMaximumFractionDigits(0);
			minsFormat.setMaximumIntegerDigits(2);
			minsFormat.setMinimumFractionDigits(0);
			minsFormat.setMinimumIntegerDigits(2);
			
			secsFormat.setMaximumFractionDigits(3);
			secsFormat.setMaximumIntegerDigits(2);
			secsFormat.setMinimumFractionDigits(3);
			secsFormat.setMinimumIntegerDigits(2);

			tabbedPanel.setPhaseProgressBarIndeterminate(!meaningfulProgress);
		}
		
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			String propName = evt.getPropertyName();
			Object newValue = evt.getNewValue();
			IndexedPropertyChangeEvent ievt = null;
			int index = -1;
			int pct;
			String pctString;
			
			if (evt instanceof IndexedPropertyChangeEvent) {
				ievt = (IndexedPropertyChangeEvent) evt;
				index = ievt.getIndex();
			}
			
			switch(propName) {
			case PN_PROGRESS:
                pct = (Integer) newValue;
                pctString = makePctString(pct, 100, huntEntryStamp);
                
                pct = Math.max(pct, 0);
                pct = Math.min(pct, 100);

//				System.out.println(
//						"propertyChange(): progress = " + newValue);

				tabbedPanel.setSearchProgressBarIndeterminate(false);
                tabbedPanel.setSearchProgressBarValue(pct);
                tabbedPanel.setSearchProgressBarString(pctString);
                tabbedPanel.setSearchProgressBarStringPainted(true);
				break;
			case PN_STATE:
				if (SwingWorker.StateValue.DONE == newValue) {
					String phase = hunter.getPhase();

					hunter.removePropertyChangeListener(this);
					tabbedPanel.resetPhaseProgressBar();
					tabbedPanel.setDetailMessageText("");
					
					if (Hunter.PHASE_FINISHED.equals(phase)) {
						tabbedPanel.setSummaryMessageText("Finished.");
				        tabbedPanel.setStartButtonEnabled(true);
				        tabbedPanel.setStopButtonEnabled(false);
				        reportDuplicates();
				        reportUnreadables();
//				        reportUniques();
				        tabbedPanel.selectDuplicateFilesTab();
					} else if (Hunter.PHASE_STOPPED.equals(phase)) {
						tabbedPanel.setSummaryMessageText("Cancelled.");
				        tabbedPanel.resetDupFilesPanel();
//				        tabbedPanel.resetUnreadableFilesPanel();
				        tabbedPanel.setStartButtonEnabled(true);
				        tabbedPanel.setStopButtonEnabled(false);
					}
				}
				break;
			case PhaseStats.PN_CANCELLED:
				tabbedPanel.setDetailMessageText(newValue.toString());
				break;
			case PhaseStats.PN_DIRECTORIES:
				if (ievt != null) {
					nbrDirs = index + 1;
					
					tabbedPanel.setAllDirsFileListCountDisplayText(
							Integer.toString(nbrDirs));

					int totalFiles = nbrDirs + nbrRegFiles + nbrUnreadableFiles;
					tabbedPanel.setTotalFileCountDisplayText(
							Integer.toString(totalFiles));
				}
				break;
			case PhaseStats.PN_GROUPS:
				if (ievt != null) {
					String t = Integer.toString(index + 1);
					
					switch(presentationName) {
					case Hunter.PHASE_FINDING_FILES:
						// NOTHING
						break;
					case Hunter.PHASE_GROUP_BY_CHECKSUM:
						tabbedPanel.setNbrGroupsByChecksumDisplayText(t);
						break;
					case Hunter.PHASE_GROUP_SUBTREES:
						// FALLTHROUGN
					case Hunter.PHASE_GROUP_BY_CONTENT_COMPARISON:
						tabbedPanel.setFilesByContentCountDisplayText(t);
						break;
					case Hunter.PHASE_GROUP_BY_SIZE:
						tabbedPanel.setNbrGroupsBySizeDisplayText(t);
						break;
					}
				}
				break;
			case PhaseStats.PN_MEANINGFUL_PROGRESS:
				boolean newMeaningfulProgress = (Boolean) newValue;
				
				if (meaningfulProgress && !newMeaningfulProgress) {
	                tabbedPanel.setPhaseProgressBarValue(0);
	                tabbedPanel.setPhaseProgressBarStringPainted(false);
				}
				
				meaningfulProgress = newMeaningfulProgress;
				
				tabbedPanel.setPhaseProgressBarIndeterminate(!meaningfulProgress);
				break;
			case PhaseStats.PN_NBR_BYTES_CONSIDERED:
				// NOTHING
				break;
			case PhaseStats.PN_NBR_FILE_GROUPS_CONSIDERED:
				// NOTHING
				break;
			case PhaseStats.PN_PERCENT_COMPLETE:
	            if (meaningfulProgress) {
	                pct = (Integer) newValue;
	                pctString = makePctString(pct, 100, phaseEntryStamp);

					tabbedPanel.setPhaseProgressBarIndeterminate(false);
	                tabbedPanel.setPhaseProgressBarValue(pct);
	                tabbedPanel.setPhaseProgressBarString(pctString);
	                tabbedPanel.setPhaseProgressBarStringPainted(true);
	            }
				break;
			case PhaseStats.PN_PHASE_ENTRY_STAMP:
				phaseEntryStamp = (Date) newValue;
				break;
			case PhaseStats.PN_PHASE_EXIT_STAMP:
				Date exitStamp = (Date) newValue;
				String elapsedInPhase =
						formatElapsed(phaseEntryStamp, exitStamp);
				
				switch(presentationName) {
				case Hunter.PHASE_FINDING_FILES:
					tabbedPanel.setElapsedFindingFilesDisplayText(
							elapsedInPhase);
					break;
				case Hunter.PHASE_GROUP_BY_CHECKSUM:
					tabbedPanel.setElapsedGroupByChecksumDisplayText(
							elapsedInPhase);
					break;
				case Hunter.PHASE_GROUP_BY_CONTENT_COMPARISON:
					tabbedPanel.setElapsedGroupByContentComparisonDisplayText(
							elapsedInPhase);
					break;
				case Hunter.PHASE_GROUP_BY_SIZE:
					tabbedPanel.setElapsedGroupBySizeDisplayText(
							elapsedInPhase);
					break;
				case Hunter.PHASE_GROUP_SUBTREES:
					tabbedPanel.setElapsedGroupingSubtreesDisplayText(
							elapsedInPhase);
					break;
				}
				
				elapsedInPhase = formatElapsed(huntEntryStamp, exitStamp);
				tabbedPanel.setElapsedFindingDuplicateFilesDisplayText(
						elapsedInPhase);
				break;
			case PhaseStats.PN_PRESENTATION_NAME:
				presentationName = newValue.toString();
				tabbedPanel.setSummaryMessageText(presentationName);
				break;
			case PhaseStats.PN_PHASE_PROGRESS_MESSAGE:
				tabbedPanel.setDetailMessageText(newValue.toString());
				break;
			case PhaseStats.PN_REGULAR_FILES:
				if (ievt != null) {
					nbrRegFiles = index + 1;
					
					tabbedPanel.setAllFilesFileListCountDisplayText(
							Integer.toString(nbrRegFiles));

					int totalFiles = nbrDirs + nbrRegFiles + nbrUnreadableFiles;
					tabbedPanel.setTotalFileCountDisplayText(
							Integer.toString(totalFiles));
				}
				break;
			case PhaseStats.PN_UNIQUE_FILES:
				if (ievt != null) {
					tabbedPanel.setUniqueFileListCountDisplayText(
							Integer.toString(index + 1));
				}
				break;
			case PhaseStats.PN_UNREADABLE_FILES:
				if (ievt != null) {
					nbrUnreadableFiles = index + 1;
					
					tabbedPanel.setUnreadableFilesListCountDisplayText(
							Integer.toString(nbrUnreadableFiles));

					int totalFiles = nbrDirs + nbrRegFiles + nbrUnreadableFiles;
					tabbedPanel.setTotalFileCountDisplayText(
							Integer.toString(totalFiles));
				}
				break;
			default:
				System.err.println("Unknown property in event: " + propName);
				System.err.println("    evt = " + evt);
				break;
			}
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
	                + hrsFormat.format(hrs) + "h "
	                + minsFormat.format(mins) + "m "
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
	        String retValue = NUMBER_FORMAT.format(pct) + "%";

	        if (pct > 5) {
	            retValue = retValue + " (~"
	                    + formatMillis((long) millisRemain) + " remaining)";
	        }

	        return retValue;
	    }

	    protected void reportAncestors(File dupFile) {
	    	File absDupFile = dupFile.getAbsoluteFile();
	    	File ancestor = absDupFile.getParentFile();
	    	
	    	while (ancestor != null) {
//	    		ancestorsOfDups.add(ancestor);
	    		Integer count = ancestorsOfDups.get(ancestor);
	    		
	    		if (count == null) {
	    			ancestorsOfDups.put(ancestor, 1);
	    		} else {
	    			ancestorsOfDups.put(ancestor, 1 + count);
	    		}
	    		
	    		ancestor = ancestor.getParentFile();
	    	}
	    }

	    protected void reportDuplicates() {
	        LinkedList<Phase> pipeline = hunter.getPipeline();
	        Collection<Collection<File>> filesByContent =
	                pipeline.getLast().getOutput().getGroups();
//	        int setNbr = 1;
	        long redundantBytes = 0;
	        int redundantFiles = 0;
//	        DefaultMutableTreeNode rootDMTN = new DefaultMutableTreeNode(
//	                "Matched Sets", true);
//	        DefaultTreeModel tm = new DefaultTreeModel(rootDMTN);
//	        FileComparator fileComparator = new FileComparator();
	        NumberFormat numberFormat = NumberFormat.getInstance();

	        numberFormat.setGroupingUsed(true);
	        ancestorsOfDups.clear();

//	        ArrayList<Collection<File>> al =
//	                new ArrayList<Collection<File>>(filesByContent);
//	        Collections.<Collection<File>>sort(
//	                al, new Comparator<Collection<File>>() {
//
//	            @Override
//	            public int compare(Collection<File> o1, Collection<File> o2) {
//	                int result = 0;
//	                long magnitude1 = 0;
//	                long magnitude2 = 0;
//
//	                if (!o1.isEmpty()) {
//	                    long fileLength = o1.iterator().next().length();
//
//	                    magnitude1 = fileLength * o1.size();
//	                }
//
//	                if (!o2.isEmpty()) {
//	                    long fileLength = o2.iterator().next().length();
//
//	                    magnitude2 = fileLength * o2.size();
//	                }
//
//	                if (magnitude1 < magnitude2) {
//	                    result = 1;
//	                }
//
//	                if (magnitude1 > magnitude2) {
//	                    result = -1;
//	                }
//
//	                return result;
//	            }
//	        });
//	        filesByContent = al;

	        for (Collection<File> fc : filesByContent) {
	            long fileLength = 0;
	            int size = fc.size();
//	            DefaultMutableTreeNode fcDMTN = new DefaultMutableTreeNode(
//	                    "Matched Set " + setNbr, true);
//
//	            rootDMTN.add(fcDMTN);
//	            ++setNbr;

//	            if (fc instanceof List) {
//	                List<File> fList = (List<File>) fc;
//
//	                Collections.<File>sort(fList, fileComparator);
//	            }

	            for (File f : fc) {
//	                LazyFileTreeNode lftn = new LazyFileTreeNode(tm, f);
//
//	                fcDMTN.add(lftn);
	            	if (f.isFile()) {
		                fileLength = f.length();
	            	}

	                file2EquivSetMap.put(f, fc);
	                reportAncestors(f);
	            }

	            redundantBytes += (fileLength * (size - 1));
	            redundantFiles += (size - 1);
	        }

//	        tabbedPanel.setDupFilesTreeModel(tm);
	        tabbedPanel.setDupFilesTreeModel(
	        		tabbedPanel.getSelectSubtreesTreeModel());
	        
	        TreePath[] selectedPaths = tabbedPanel.getSelectedPaths();
	        
	        tabbedPanel.setSelectionPaths(selectedPaths);
	        
	        for (TreePath selectedPath : selectedPaths) {
	        	tabbedPanel.makeVisible(selectedPath);
	        }
	        
	        tabbedPanel.setDupFilesSummaryLabelText("Found "
	                + numberFormat.format(redundantBytes) + " bytes in "
	                + numberFormat.format(redundantFiles)
	                + " redundant files.");
	    }

	    protected void reportUnreadables() {
	        LinkedList<Phase> pipeline = hunter.getPipeline();
	        List<File> unreadables =
	                pipeline.getLast().getOutput().getUnreadableFilesIdentified();
//	        DefaultMutableTreeNode rootDMTN = new DefaultMutableTreeNode(
//	                Integer.toString(unreadables.size()) + " Unreadable Files",
//	                true);
//	        DefaultTreeModel tm = new DefaultTreeModel(rootDMTN);
//	        NumberFormat numberFormat = NumberFormat.getInstance();
//
//	        numberFormat.setGroupingUsed(true);
//	        
//	        ArrayList<String> names =
//	                new ArrayList<String>(unreadables.size());
//	        for (File f : unreadables) {
//	            names.add(f.toString());
//	        }
//	        Collections.<String>sort(names);
//	        for (String n : names) {
//	            File f = new File(n);
//	            LazyFileTreeNode lftn = new LazyFileTreeNode(tm, f);
//
//	            lftn.setAllowsChildren(false);
//	            rootDMTN.add(lftn);
//	        }
//
//	        tabbedPanel.setUnreadableFilesTreeModel(tm);
	        
	        accessDeniedFiles.clear();
	        
	        for (File f : unreadables) {
	        	accessDeniedFiles.add(f.getAbsoluteFile());
	        }
	    }
	}
	public class StartButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
//			System.out.println(" e.getSource() = " + e.getSource());
//			System.out.println(" e.getID() = " + e.getID());
			
			if ((e.getID() == ActionEvent.ACTION_PERFORMED) &&
					(e.getSource() instanceof JButton)) {
				// here should start up a hunter
		        List<File> subtreeRoots =
		        		filterRedundantEntries(tabbedPanel.listSelectedFiles());

		        hunter = new Hunter(
		        		subtreeRoots, tabbedPanel.isSpecialHandling());
		        hunter.addPropertyChangeListener(new HunterListener());

		        tabbedPanel.reset();
				tabbedPanel.setSearchRootFileListCountDisplayText(
						Integer.toString(subtreeRoots.size()));
		        
		        tabbedPanel.setStartButtonEnabled(false);
		        tabbedPanel.setStopButtonEnabled(true);
		        tabbedPanel.selectProgressTab();
		        
//		        file2EquivSetMap = new HashMap<File, Collection<File>>();
		        file2EquivSetMap.clear();

		        hunter.execute();
			}
		}
	}
	
	public class StopButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
//			System.out.println(" e.getSource() = " + e.getSource());
//			System.out.println(" e.getID() = " + e.getID());
			
			if ((e.getID() == ActionEvent.ACTION_PERFORMED) &&
					(e.getSource() instanceof JButton)) {
				// here should cancel the hunter, if there is one.
				if (hunter != null) {
					hunter.cancel(true);
				}
			}
		}
	}

	private static final long serialVersionUID = 1L;
	
	/**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
			public void run() {
            	final DFFFrame dff = new DFFFrame();
            	
                dff.setVisible(true);
                dff.pack();
            }
        });
        
    }
	
    protected HashSet<File> accessDeniedFiles = new HashSet<File>();

    protected HashMap<File, Integer> ancestorsOfDups = new HashMap<File, Integer>();
    protected HashMap<File, String> file2DisplayNameCache =
			new HashMap<File, String>();
	protected HashMap<File, Collection<File>> file2EquivSetMap =
			new HashMap<File, Collection<File>>();
	protected HashMap<File, Icon> file2IconCache = new HashMap<File, Icon>();
	protected JMenuItem fileExitMenuItem = null;
	protected JMenu fileMenu = null;
	protected CachedFileTreeCellRenderer fileTreeCellRenderer =
			new CachedFileTreeCellRenderer(
					file2DisplayNameCache, file2IconCache,
					file2EquivSetMap, ancestorsOfDups);
	protected HeapMonitorPanel heapMonitor = new HeapMonitorPanel();
	protected CachedFileTreeCellRenderer highlightingTreeCellRenderer =
			new DuplicateHighlightingTreeCellRenderer(
					file2DisplayNameCache, file2IconCache,
					file2EquivSetMap, ancestorsOfDups, accessDeniedFiles);
	protected Hunter hunter = null;
	protected JMenuBar menuBar = null;
	protected DFFTabbedPanel tabbedPanel = null;

	/**
	 * @throws HeadlessException
	 */
	public DFFFrame() throws HeadlessException {
		super();
		
		highlightingTreeCellRenderer.setUseFullPathnames(true);
		
		initComponents();
		
		pack();
	}

    /**
	 * @param gc
	 */
	public DFFFrame(GraphicsConfiguration gc) {
		super(gc);
		
		initComponents();
	}
	
	/**
	 * @param title
	 * @throws HeadlessException
	 */
	public DFFFrame(String title) throws HeadlessException {
		super(title);
		
		initComponents();
	}
	
	/**
	 * @param title
	 * @param gc
	 */
	public DFFFrame(String title, GraphicsConfiguration gc) {
		super(title, gc);
		
		initComponents();
	}
	
	/* (non-Javadoc)
	 * @see net.cote.app.dupfilefinder.gui.ResettableObject#reset()
	 */
	public void reset() {
		tabbedPanel.reset();
	}
	
	/**
     * Get a new <tt>List</tt> of <tt>File</tt>s from a given one.
     * The new list contains all of the entries from the given list except for
     * any which include any of the others as ancestors.
     * @param rawList the given <tt>List</tt> of <tt>File</tt>s.
     * @return the filtered <tt>List</tt> of <tt>File</tt>s.
     */
    protected List<File> filterRedundantEntries(List<File> rawList) {
        List<File> filteredList = new ArrayList<File>();
        File[] raw = new File[rawList.size()];
        int rawIndex = 0;

        for (File rawFile : rawList) {
            raw[rawIndex++] = rawFile;
        }

        for (int outerIndex = 0; outerIndex < raw.length; ++outerIndex) {
            File outerFile = raw[outerIndex];
            boolean hasAncestorInRawList = false;

            for (File ancestor = outerFile; ancestor != null;
                    ancestor = ancestor.getParentFile()) {
                for (int innerIndex = 0; innerIndex < raw.length;
                        ++innerIndex) {
                    if (innerIndex != outerIndex) {
                        if (ancestor.equals(raw[innerIndex])) {
                            hasAncestorInRawList = true;
                        }
                    }
                }
            }

            if (!hasAncestorInRawList) {
                filteredList.add(outerFile);
            }
        }

        return filteredList;
    }
	
	protected void initComponents() {
		setLayout(new BorderLayout());

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DupFileFinder");

        tabbedPanel = new DFFTabbedPanel();
        
        tabbedPanel.setDupFilesCellRenderer((TreeCellRenderer) highlightingTreeCellRenderer);
        tabbedPanel.setSelectSubtreesCellRenderer((TreeCellRenderer) fileTreeCellRenderer);
//        tabbedPanel.setUnreadableFilesCellRenderer(highlightingTreeCellRenderer);
//        tabbedPanel.setUniqueFilesCellRenderer(pathTreeCellRenderer);
        
        tabbedPanel.addStartButtonActionListener(
        		new StartButtonActionListener());
        tabbedPanel.addStopButtonActionListener(new StopButtonActionListener());
        
        getContentPane().add(tabbedPanel, BorderLayout.CENTER);
        getContentPane().add(heapMonitor, BorderLayout.SOUTH);
        
        fileExitMenuItem = new JMenuItem("Exit");
        fileExitMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
        });
        
        fileMenu = new JMenu("File");
        fileMenu.add(fileExitMenuItem);
        
        menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        
        this.setJMenuBar(menuBar);
	}
}
