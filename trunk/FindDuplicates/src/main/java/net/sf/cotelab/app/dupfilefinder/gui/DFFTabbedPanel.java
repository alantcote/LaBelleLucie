/**
 * 
 */
package net.sf.cotelab.app.dupfilefinder.gui;

import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.JTabbedPane;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/**
 * @author US80653H
 */
public class DFFTabbedPanel extends JTabbedPane implements ResettableObject {
	public static final String DEFAULT_DUP_FILES_ROOT_NODE_TEXT =
			"Matched Sets (0 sets)";
	public static final String DEFAULT_DUP_FILES_SUMMARY_LABEL_TEXT =
			"No entries.";
	public static final String DEFAULT_SELECT_SUBTREES_ROOT_NODE_TEXT =
			"Tree View";
//	public static final String DEFAULT_UNIQUE_FILES_ROOT_NODE_TEXT =
//			"Unique Files";
//	public static final String DEFAULT_UNREADABLE_FILES_ROOT_NODE_TEXT =
//			"Unreadable Files";
	
	private static final long serialVersionUID = 1L;
	
	protected DupFilesPanel dupFilesPanel = new DupFilesPanel();
	protected ProgressPanel progressPanel = new ProgressPanel();
	protected SelectSubtreesPanel selectSubtreesPanel =
			new SelectSubtreesPanel();
//	protected JTreeScrollPanel uniqueFilesPanel =
//			new JTreeScrollPanel();
//	protected JTreeScrollPanel unreadableFilesPanel =
//			new JTreeScrollPanel();

	public DFFTabbedPanel() {
		super();
		
		initComponents();
		reset();
	}

	/**
	 * @param tabPlacement
	 */
	public DFFTabbedPanel(int tabPlacement) {
		super(tabPlacement);
		
		initComponents();
		reset();
	}

	/**
	 * @param tabPlacement
	 * @param tabLayoutPolicy
	 */
	public DFFTabbedPanel(int tabPlacement, int tabLayoutPolicy) {
		super(tabPlacement, tabLayoutPolicy);
		
		initComponents();
		reset();
	}

	public void addStartButtonActionListener(ActionListener listener) {
		selectSubtreesPanel.addStartButtonActionListener(listener);
	}

	/**
	 * @param l
	 * @see net.sf.cotelab.app.dupfilefinder.gui.ProgressPanel#addActionListener(java.awt.event.ActionListener)
	 */
	public void addStopButtonActionListener(ActionListener l) {
		progressPanel.addActionListener(l);
	}

	/**
	 * @return
	 * @see net.sf.cotelab.app.dupfilefinder.gui.SelectSubtreesPanel#getSelectedPaths()
	 */
	public TreePath[] getSelectedPaths() {
		return selectSubtreesPanel.getSelectedPaths();
	}

	/**
	 * @return
	 * @see net.sf.cotelab.app.dupfilefinder.gui.SelectSubtreesPanel#getTreeModel()
	 */
	public TreeModel getSelectSubtreesTreeModel() {
		return selectSubtreesPanel.getTreeModel();
	}

	/**
	 * @return
	 * @see net.sf.cotelab.app.dupfilefinder.gui.SelectSubtreesPanel#isSpecialHandling()
	 */
	public boolean isSpecialHandling() {
		return selectSubtreesPanel.isSpecialHandling();
	}

	/**
	 * @return
	 * @see net.sf.cotelab.app.dupfilefinder.gui.SelectSubtreesPanel#listSelectedFiles()
	 */
	public List<File> listSelectedFiles() {
		return selectSubtreesPanel.listSelectedFiles();
	}

	/**
	 * @param path
	 * @see net.sf.cotelab.app.dupfilefinder.gui.DupFilesPanel#makeVisible(javax.swing.tree.TreePath)
	 */
	public void makeVisible(TreePath path) {
		dupFilesPanel.makeVisible(path);
	}

	/* (non-Javadoc)
	 * @see net.cote.app.dupfilefinder.gui.ResettableObject#reset()
	 */
	@Override
	public void reset() {
		dupFilesPanel.reset();
		progressPanel.reset();
		selectSubtreesPanel.reset();
//		unreadableFilesPanel.reset();
//		uniqueFilesPanel.reset();
	}

	public void resetDupFilesPanel() {
		dupFilesPanel.reset();
	}

	/**
	 * 
	 * @see net.sf.cotelab.app.dupfilefinder.gui.ProgressPanel#resetPhaseProgressBar()
	 */
	public void resetPhaseProgressBar() {
		progressPanel.resetPhaseProgressBar();
	}
	
	public void selectDuplicateFilesTab() {
		setSelectedComponent(dupFilesPanel);
	}
	
//	public void resetUnreadableFilesPanel() {
//		unreadableFilesPanel.reset();
//	}
	
	public void selectProgressTab() {
		setSelectedComponent(progressPanel);
	}

	/**
	 * @param t
	 * @see net.sf.cotelab.app.dupfilefinder.gui.ProgressPanel#setAllDirsFileListCountDisplayText(java.lang.String)
	 */
	public void setAllDirsFileListCountDisplayText(String t) {
		progressPanel.setAllDirsFileListCountDisplayText(t);
	}

	/**
	 * @param t
	 * @see net.sf.cotelab.app.dupfilefinder.gui.ProgressPanel#setAllFilesFileListCountDisplayText(java.lang.String)
	 */
	public void setAllFilesFileListCountDisplayText(String t) {
		progressPanel.setAllFilesFileListCountDisplayText(t);
	}

	/**
	 * @param text
	 * @see net.sf.cotelab.app.dupfilefinder.gui.ProgressPanel#setDetailMessageText(java.lang.String)
	 */
	public void setDetailMessageText(String text) {
		progressPanel.setDetailMessageText(text);
	}

	/**
	 * @param x
	 * @see net.sf.cotelab.app.dupfilefinder.gui.DupFilesPanel#setCellRenderer(javax.swing.tree.TreeCellRenderer)
	 */
	public void setDupFilesCellRenderer(TreeCellRenderer x) {
		dupFilesPanel.setCellRenderer(x);
	}
	
	/**
	 * @param text
	 * @see net.sf.cotelab.app.dupfilefinder.gui.DupFilesPanel#setDupFilesSummaryLabelText(java.lang.String)
	 */
	public void setDupFilesSummaryLabelText(String text) {
		dupFilesPanel.setDupFilesSummaryLabelText(text);
	}

	/**
	 * @param newModel
	 * @see net.sf.cotelab.app.dupfilefinder.gui.DupFilesPanel#setTreeModel(javax.swing.tree.TreeModel)
	 */
	public void setDupFilesTreeModel(TreeModel newModel) {
		dupFilesPanel.setTreeModel(newModel);
	}

	/**
	 * @param t
	 * @see net.sf.cotelab.app.dupfilefinder.gui.ProgressPanel#setElapsedFindingDuplicateFilesDisplayText(java.lang.String)
	 */
	public void setElapsedFindingDuplicateFilesDisplayText(String t) {
		progressPanel.setElapsedFindingDuplicateFilesDisplayText(t);
	}

	/**
	 * @param t
	 * @see net.sf.cotelab.app.dupfilefinder.gui.ProgressPanel#setElapsedFindingFilesDisplayText(java.lang.String)
	 */
	public void setElapsedFindingFilesDisplayText(String t) {
		progressPanel.setElapsedFindingFilesDisplayText(t);
	}

	/**
	 * @param t
	 * @see net.sf.cotelab.app.dupfilefinder.gui.ProgressPanel#setElapsedGroupByChecksumDisplayText(java.lang.String)
	 */
	public void setElapsedGroupByChecksumDisplayText(String t) {
		progressPanel.setElapsedGroupByChecksumDisplayText(t);
	}

	/**
	 * @param t
	 * @see net.sf.cotelab.app.dupfilefinder.gui.ProgressPanel#setElapsedGroupByContentComparisonDisplayText(java.lang.String)
	 */
	public void setElapsedGroupByContentComparisonDisplayText(String t) {
		progressPanel.setElapsedGroupByContentComparisonDisplayText(t);
	}

	/**
	 * @param t
	 * @see net.sf.cotelab.app.dupfilefinder.gui.ProgressPanel#setElapsedGroupBySizeDisplayText(java.lang.String)
	 */
	public void setElapsedGroupBySizeDisplayText(String t) {
		progressPanel.setElapsedGroupBySizeDisplayText(t);
	}

	/**
	 * @param t
	 * @see net.sf.cotelab.app.dupfilefinder.gui.ProgressPanel#setElapsedGroupingSubtreesDisplayText(java.lang.String)
	 */
	public void setElapsedGroupingSubtreesDisplayText(String t) {
		progressPanel.setElapsedGroupingSubtreesDisplayText(t);
	}

	/**
	 * @param t
	 * @see net.sf.cotelab.app.dupfilefinder.gui.ProgressPanel#setFilesByContentCountDisplayText(java.lang.String)
	 */
	public void setFilesByContentCountDisplayText(String t) {
		progressPanel.setFilesByContentCountDisplayText(t);
	}

	/**
	 * @param t
	 * @see net.sf.cotelab.app.dupfilefinder.gui.ProgressPanel#setNbrGroupsByChecksumDisplayText(java.lang.String)
	 */
	public void setNbrGroupsByChecksumDisplayText(String t) {
		progressPanel.setNbrGroupsByChecksumDisplayText(t);
	}

	/**
	 * @param t
	 * @see net.sf.cotelab.app.dupfilefinder.gui.ProgressPanel#setNbrGroupsBySizeDisplayText(java.lang.String)
	 */
	public void setNbrGroupsBySizeDisplayText(String t) {
		progressPanel.setNbrGroupsBySizeDisplayText(t);
	}

	/**
	 * @param newValue
	 * @see net.sf.cotelab.app.dupfilefinder.gui.ProgressPanel#setPhaseProgressBarIndeterminate(boolean)
	 */
	public void setPhaseProgressBarIndeterminate(boolean newValue) {
		progressPanel.setPhaseProgressBarIndeterminate(newValue);
	}

	/**
	 * @param s
	 * @see net.sf.cotelab.app.dupfilefinder.gui.ProgressPanel#setPhaseProgressBarString(java.lang.String)
	 */
	public void setPhaseProgressBarString(String s) {
		progressPanel.setPhaseProgressBarString(s);
	}

	/**
	 * @param b
	 * @see net.sf.cotelab.app.dupfilefinder.gui.ProgressPanel#setPhaseProgressBarStringPainted(boolean)
	 */
	public void setPhaseProgressBarStringPainted(boolean b) {
		progressPanel.setPhaseProgressBarStringPainted(b);
	}

	/**
	 * @param n
	 * @see net.sf.cotelab.app.dupfilefinder.gui.ProgressPanel#setPhaseProgressBarValue(int)
	 */
	public void setPhaseProgressBarValue(int n) {
		progressPanel.setPhaseProgressBarValue(n);
	}

	/**
	 * @param newValue
	 * @see net.sf.cotelab.app.dupfilefinder.gui.ProgressPanel#setSearchProgressBarIndeterminate(boolean)
	 */
	public void setSearchProgressBarIndeterminate(boolean newValue) {
		progressPanel.setSearchProgressBarIndeterminate(newValue);
	}

	/**
	 * @param s
	 * @see net.sf.cotelab.app.dupfilefinder.gui.ProgressPanel#setSearchProgressBarString(java.lang.String)
	 */
	public void setSearchProgressBarString(String s) {
		progressPanel.setSearchProgressBarString(s);
	}

	/**
	 * @param b
	 * @see net.sf.cotelab.app.dupfilefinder.gui.ProgressPanel#setSearchProgressBarStringPainted(boolean)
	 */
	public void setSearchProgressBarStringPainted(boolean b) {
		progressPanel.setSearchProgressBarStringPainted(b);
	}

	/**
	 * @param n
	 * @see net.sf.cotelab.app.dupfilefinder.gui.ProgressPanel#setSearchProgressBarValue(int)
	 */
	public void setSearchProgressBarValue(int n) {
		progressPanel.setSearchProgressBarValue(n);
	}

	/**
	 * @param t
	 * @see net.sf.cotelab.app.dupfilefinder.gui.ProgressPanel#setSearchRootFileListCountDisplayText(java.lang.String)
	 */
	public void setSearchRootFileListCountDisplayText(String t) {
		progressPanel.setSearchRootFileListCountDisplayText(t);
	}

	/**
	 * @param paths
	 * @see net.sf.cotelab.app.dupfilefinder.gui.SelectSubtreesPanel#setSelectionPaths(javax.swing.tree.TreePath[])
	 */
	public void setSelectionPaths(TreePath[] paths) {
		selectSubtreesPanel.setSelectionPaths(paths);
	}

	/**
	 * @param x
	 * @see net.sf.cotelab.app.dupfilefinder.gui.SelectSubtreesPanel#setCellRenderer(javax.swing.tree.TreeCellRenderer)
	 */
	public void setSelectSubtreesCellRenderer(TreeCellRenderer x) {
		selectSubtreesPanel.setCellRenderer(x);
	}

	/**
	 * @param b
	 * @see net.sf.cotelab.app.dupfilefinder.gui.SelectSubtreesPanel#setButtonEnabled(boolean)
	 */
	public void setStartButtonEnabled(boolean b) {
		selectSubtreesPanel.setButtonEnabled(b);
	}

	/**
	 * @param b
	 * @see net.sf.cotelab.app.dupfilefinder.gui.ProgressPanel#setButtonEnabled(boolean)
	 */
	public void setStopButtonEnabled(boolean b) {
		progressPanel.setButtonEnabled(b);
	}

	/**
	 * @param text
	 * @see net.sf.cotelab.app.dupfilefinder.gui.ProgressPanel#setSummaryMessageText(java.lang.String)
	 */
	public void setSummaryMessageText(String text) {
		progressPanel.setSummaryMessageText(text);
	}

	/**
	 * @param t
	 * @see net.sf.cotelab.app.dupfilefinder.gui.ProgressPanel#setAllFilesFileListCountDisplayText(java.lang.String)
	 */
	public void setTotalFileCountDisplayText(String t) {
		progressPanel.setTotalFileCountDisplayText(t);
	}

	/**
	 * @param t
	 * @see net.sf.cotelab.app.dupfilefinder.gui.ProgressPanel#setUniqueFileListCountDisplayText(java.lang.String)
	 */
	public void setUniqueFileListCountDisplayText(String t) {
		progressPanel.setUniqueFileListCountDisplayText(t);
	}

//	/**
//	 * @param x
//	 * @see net.cote.app.dupfilefinder.gui.JTreeScrollPanel#setCellRenderer(javax.swing.tree.TreeCellRenderer)
//	 */
//	public void setUniqueFilesCellRenderer(TreeCellRenderer x) {
//		uniqueFilesPanel.setCellRenderer(x);
//	}

//	/**
//	 * @param newModel
//	 * @see net.cote.app.dupfilefinder.gui.JTreeScrollPanel#setTreeModel(javax.swing.tree.TreeModel)
//	 */
//	public void setUniqueFilesTreeModel(TreeModel newModel) {
//		uniqueFilesPanel.setTreeModel(newModel);
//	}

//	/**
//	 * @param x
//	 * @see net.cote.app.dupfilefinder.gui.JTreeScrollPanel#setCellRenderer(javax.swing.tree.TreeCellRenderer)
//	 */
//	public void setUnreadableFilesCellRenderer(TreeCellRenderer x) {
//		unreadableFilesPanel.setCellRenderer(x);
//	}

	/**
	 * @param t
	 * @see net.sf.cotelab.app.dupfilefinder.gui.ProgressPanel#setUnreadableFilesListCountDisplayText(java.lang.String)
	 */
	public void setUnreadableFilesListCountDisplayText(String t) {
		progressPanel.setUnreadableFilesListCountDisplayText(t);
	}

//	/**
//	 * @param newModel
//	 * @see net.cote.app.dupfilefinder.gui.JTreeScrollPanel#setTreeModel(javax.swing.tree.TreeModel)
//	 */
//	public void setUnreadableFilesTreeModel(TreeModel newModel) {
//		unreadableFilesPanel.setTreeModel(newModel);
//	}

	protected void initComponents() {
		selectSubtreesPanel.setDefaultRootNodeText(
				DEFAULT_SELECT_SUBTREES_ROOT_NODE_TEXT);
		selectSubtreesPanel.setRootVisible(false);
		addTab("Select Subtrees", selectSubtreesPanel);

		addTab("Progress", progressPanel);
		
//		unreadableFilesPanel.setDefaultRootNodeText(
//				DEFAULT_UNREADABLE_FILES_ROOT_NODE_TEXT);
//		addTab("Unreadable Files", unreadableFilesPanel);
		
//		uniqueFilesPanel.setDefaultRootNodeText(
//				DEFAULT_UNIQUE_FILES_ROOT_NODE_TEXT);
//		addTab("Unique Files", uniqueFilesPanel);
		
		dupFilesPanel.setDefaultRootNodeText(DEFAULT_DUP_FILES_ROOT_NODE_TEXT);
		dupFilesPanel.setDefaultSummaryLabelText(
				DEFAULT_DUP_FILES_SUMMARY_LABEL_TEXT);
		addTab("Results", dupFilesPanel);
	}
}
