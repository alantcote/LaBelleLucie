/**
 * 
 */
package net.sf.cotelab.app.dupfilefinder.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

/**
 * @author US80653H
 */
public class StatsPanel extends JPanel
		implements ResettableObject {
	private static final long serialVersionUID = 1L;

    protected ResettableJTextField allDirsFileListCountDisplay =
    		createLongField();
    protected ResettableJTextField allFilesFileListCountDisplay =
    		createLongField();
    protected JLabel directoryCountJLabel =
    		new JLabel("Directory file count:", SwingConstants.TRAILING);
    protected ResettableJTextField elapsedFindingDuplicateFilesDisplay =
    		createElapsedTimeField();
    protected JLabel elapsedFindingDuplicateFilesLabel = new JLabel(
    		"Elapsed finding duplicate files:", SwingConstants.TRAILING);
    protected ResettableJTextField elapsedFindingFilesDisplay =
    		createElapsedTimeField();
    protected JLabel elapsedFindingFilesLabel =
    		new JLabel("Elapsed finding files:", SwingConstants.TRAILING);
    protected ResettableJTextField elapsedGroupByChecksumDisplay =
    		createElapsedTimeField();
    protected JLabel elapsedGroupByChecksumLabel = new JLabel(
    		"Elapsed grouping by checksum:", SwingConstants.TRAILING);
    protected ResettableJTextField elapsedGroupByContentComparisonDisplay =
    		createElapsedTimeField();
    protected JLabel elapsedGroupByContentComparisonLabel =
    		new JLabel("Elapsed grouping by content:", SwingConstants.TRAILING);
    protected ResettableJTextField elapsedGroupBySizeDisplay =
    		createElapsedTimeField();
    protected JLabel elapsedGroupBySizeLabel =
    		new JLabel("Elapsed grouping by size:", SwingConstants.TRAILING);
    protected ResettableJTextField elapsedGroupingSubtreesDisplay =
    		createElapsedTimeField();
    protected JLabel elapsedGroupingSubtreesLabel = new JLabel(
    		"Elapsed grouping subtrees:", SwingConstants.TRAILING);
    protected ResettableJTextField filesByContentCountDisplay =
    		createLongField();
    protected JLabel filesByContentCountJLabel =
    		new JLabel("Groups by content:", SwingConstants.TRAILING);
    protected JLabel groupsByChecksumJLabel =
    		new JLabel("Groups by checksum:", SwingConstants.TRAILING);
    protected JLabel jLabel1 =
    		new JLabel("     ", SwingConstants.TRAILING);
    protected JLabel jLabel2 =
    		new JLabel("     ", SwingConstants.TRAILING);
    protected ResettableJTextField nbrGroupsByChecksumDisplay =
    		createLongField();
    protected ResettableJTextField nbrGroupsBySizeDisplay = createLongField();
    protected JLabel nbrGroupsBySizeJLabel =
    		new JLabel("Groups by size:", SwingConstants.TRAILING);
    protected JLabel regFileCountJLabel =
    		new JLabel("Regular file count:", SwingConstants.TRAILING);
    protected ResettableJTextField searchRootFileListCountDisplay =
    		createLongField();
    protected JLabel subtreeCountJLabel =
    		new JLabel("Subtree count:", SwingConstants.TRAILING);
    protected ResettableJTextField totalFileCountDisplay = createLongField();
    protected JLabel totalFileCountJLabel =
    		new JLabel("Total file count:", SwingConstants.TRAILING);
    protected ResettableJTextField uniqueFileListCountDisplay =
    		createLongField();
    protected JLabel uniqueFilesJLabel =
    		new JLabel("Unique file count:", SwingConstants.TRAILING);
    protected JLabel unreadableFileCountJLabel =
    		new JLabel("Unreadable file count:", SwingConstants.TRAILING);
    protected ResettableJTextField unreadableFilesListCountDisplay =
    		createLongField();

	/**
	 * 
	 */
	public StatsPanel() {
		super();

		initComponents();
		reset();
	}

	/**
	 * @param isDoubleBuffered
	 */
	public StatsPanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);

		initComponents();
		reset();
	}

	/**
	 * @param layout
	 */
	public StatsPanel(LayoutManager layout) {
		super(layout);

		initComponents();
		reset();
	}

	/**
	 * @param layout
	 * @param isDoubleBuffered
	 */
	public StatsPanel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);

		initComponents();
		reset();
	}

	@Override
	public void reset() {
        allDirsFileListCountDisplay.reset();
        allFilesFileListCountDisplay.reset();
        elapsedFindingDuplicateFilesDisplay.reset();
        elapsedFindingFilesDisplay.reset();
        elapsedGroupByChecksumDisplay.reset();
        elapsedGroupByContentComparisonDisplay.reset();
        elapsedGroupBySizeDisplay.reset();
        filesByContentCountDisplay.reset();
        nbrGroupsByChecksumDisplay.reset();
        nbrGroupsBySizeDisplay.reset();
        searchRootFileListCountDisplay.reset();
        totalFileCountDisplay.reset();
        uniqueFileListCountDisplay.reset();
        unreadableFilesListCountDisplay.reset();
	}

	/**
	 * @param t
	 * @see javax.swing.text.JTextComponent#setText(java.lang.String)
	 */
	public void setAllDirsFileListCountDisplayText(String t) {
		allDirsFileListCountDisplay.setText(t);
	}

	/**
	 * @param t
	 * @see javax.swing.text.JTextComponent#setText(java.lang.String)
	 */
	public void setAllFilesFileListCountDisplayText(String t) {
		allFilesFileListCountDisplay.setText(t);
	}

	/**
	 * @param t
	 * @see javax.swing.text.JTextComponent#setText(java.lang.String)
	 */
	public void setElapsedFindingDuplicateFilesDisplayText(String t) {
		elapsedFindingDuplicateFilesDisplay.setText(t);
	}

	/**
	 * @param t
	 * @see javax.swing.text.JTextComponent#setText(java.lang.String)
	 */
	public void setElapsedFindingFilesDisplayText(String t) {
		elapsedFindingFilesDisplay.setText(t);
	}

	/**
	 * @param t
	 * @see javax.swing.text.JTextComponent#setText(java.lang.String)
	 */
	public void setElapsedGroupByChecksumDisplayText(String t) {
		elapsedGroupByChecksumDisplay.setText(t);
	}

	/**
	 * @param t
	 * @see javax.swing.text.JTextComponent#setText(java.lang.String)
	 */
	public void setElapsedGroupByContentComparisonDisplayText(String t) {
		elapsedGroupByContentComparisonDisplay.setText(t);
	}

	/**
	 * @param t
	 * @see javax.swing.text.JTextComponent#setText(java.lang.String)
	 */
	public void setElapsedGroupBySizeDisplayText(String t) {
		elapsedGroupBySizeDisplay.setText(t);
	}

	/**
	 * @param t
	 * @see javax.swing.text.JTextComponent#setText(java.lang.String)
	 */
	public void setElapsedGroupingSubtreesDisplayText(String t) {
		elapsedGroupingSubtreesDisplay.setText(t);
	}

	/**
	 * @param t
	 * @see javax.swing.text.JTextComponent#setText(java.lang.String)
	 */
	public void setFilesByContentCountDisplayText(String t) {
		filesByContentCountDisplay.setText(t);
	}

	/**
	 * @param t
	 * @see javax.swing.text.JTextComponent#setText(java.lang.String)
	 */
	public void setNbrGroupsByChecksumDisplayText(String t) {
		nbrGroupsByChecksumDisplay.setText(t);
	}

	/**
	 * @param t
	 * @see javax.swing.text.JTextComponent#setText(java.lang.String)
	 */
	public void setNbrGroupsBySizeDisplayText(String t) {
		nbrGroupsBySizeDisplay.setText(t);
	}

	/**
	 * @param t
	 * @see javax.swing.text.JTextComponent#setText(java.lang.String)
	 */
	public void setSearchRootFileListCountDisplayText(String t) {
		searchRootFileListCountDisplay.setText(t);
	}

	/**
	 * @param t
	 * @see javax.swing.text.JTextComponent#setText(java.lang.String)
	 */
	public void setTotalFileCountDisplayText(String t) {
		totalFileCountDisplay.setText(t);
	}
    
    /**
	 * @param t
	 * @see javax.swing.text.JTextComponent#setText(java.lang.String)
	 */
	public void setUniqueFileListCountDisplayText(String t) {
		uniqueFileListCountDisplay.setText(t);
	}

	/**
	 * @param t
	 * @see javax.swing.text.JTextComponent#setText(java.lang.String)
	 */
	public void setUnreadableFilesListCountDisplayText(String t) {
		unreadableFilesListCountDisplay.setText(t);
	}
    
    protected ResettableJTextField createElapsedTimeField() {
    	ResettableJTextField result =
    			createResettableJTextField(
    					18, false, JTextField.TRAILING,
    					"0d 00h 00m 00.000s", null);
    	
    	return result;
    }
    
    protected GridBagConstraints createGridBagConstraintsXYAI(
    		int gridx, int gridy, int anchor) {
    	GridBagConstraints gridBagConstraints =
    			createGridBagConstraintsXYI(gridx, gridy);

        gridBagConstraints.anchor = anchor;
        
        return gridBagConstraints;
    }
    
    protected GridBagConstraints createGridBagConstraintsXYFI(
    		int gridx, int gridy, int fill) {
    	GridBagConstraints gridBagConstraints =
    			createGridBagConstraintsXYI(gridx, gridy);

        gridBagConstraints.fill = fill;
        
        return gridBagConstraints;
    }
    
    protected GridBagConstraints createGridBagConstraintsXYI(
    		int gridx, int gridy) {
    	GridBagConstraints gridBagConstraints = new GridBagConstraints();
    	
        gridBagConstraints.gridx = gridx;
        gridBagConstraints.gridy = gridy;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        
        return gridBagConstraints;
    }
    
    protected GridBagConstraints createGridBagConstraintsXYWAI(
    		int gridx, int gridy, int width, int anchor) {
    	GridBagConstraints gridBagConstraints =
    			createGridBagConstraintsXYAI(gridx, gridy, anchor);
    	
        gridBagConstraints.gridwidth = width;
        
        return gridBagConstraints;
    }
    
    protected ResettableJTextField createLongField() {
    	ResettableJTextField result =
    			createResettableJTextField(
    					10, false, JTextField.TRAILING, "0", null);
    	
    	return result;
    }

	protected ResettableJTextField createResettableJTextField(
    		int columns, boolean editable, int horizontalAlignment,
    		String text, Border border) {
    	ResettableJTextField result = new ResettableJTextField();

    	result.setDefaultColumns(columns);
    	result.setDefaultEditable(editable);
    	result.setDefaultHorizontalAlignment(horizontalAlignment);
    	result.setDefaultText(text);
    	result.setDefaultBorder(border);
    	
    	result.reset();
    	
    	return result;
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     */
    protected void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        setLayout(new GridBagLayout());

        gridBagConstraints = createGridBagConstraintsXYAI(
        		0, 2, GridBagConstraints.EAST);
        add(subtreeCountJLabel, gridBagConstraints);

        gridBagConstraints = createGridBagConstraintsXYAI(
        		1, 2, GridBagConstraints.EAST);
        add(searchRootFileListCountDisplay, gridBagConstraints);

        gridBagConstraints = createGridBagConstraintsXYAI(
        		2, 1, GridBagConstraints.EAST);
        add(directoryCountJLabel, gridBagConstraints);

        gridBagConstraints = createGridBagConstraintsXYAI(
        		3, 1, GridBagConstraints.EAST);
        add(allDirsFileListCountDisplay, gridBagConstraints);

        gridBagConstraints = createGridBagConstraintsXYAI(
        		2, 3, GridBagConstraints.EAST);
        add(regFileCountJLabel, gridBagConstraints);

        gridBagConstraints = createGridBagConstraintsXYAI(
        		3, 3, GridBagConstraints.EAST);
        add(allFilesFileListCountDisplay, gridBagConstraints);

        gridBagConstraints = createGridBagConstraintsXYAI(
        		2, 8, GridBagConstraints.EAST);
        add(jLabel1, gridBagConstraints);

        gridBagConstraints = createGridBagConstraintsXYAI(
        		2, 2, GridBagConstraints.EAST);
        add(unreadableFileCountJLabel, gridBagConstraints);

        gridBagConstraints = createGridBagConstraintsXYAI(
        		3, 2, GridBagConstraints.EAST);
        add(unreadableFilesListCountDisplay, gridBagConstraints);

        gridBagConstraints = createGridBagConstraintsXYAI(
        		2, 1, GridBagConstraints.EAST);
        add(jLabel2, gridBagConstraints);

        gridBagConstraints = createGridBagConstraintsXYAI(
        		2, 5, GridBagConstraints.EAST);
        add(totalFileCountJLabel, gridBagConstraints);

        gridBagConstraints = createGridBagConstraintsXYAI(
        		3, 5, GridBagConstraints.EAST);
        add(totalFileCountDisplay, gridBagConstraints);

        gridBagConstraints = createGridBagConstraintsXYAI(
        		0, 4, GridBagConstraints.EAST);
        add(nbrGroupsBySizeJLabel, gridBagConstraints);

        gridBagConstraints = createGridBagConstraintsXYAI(
        		1, 4, GridBagConstraints.EAST);
        add(nbrGroupsBySizeDisplay, gridBagConstraints);

        gridBagConstraints = createGridBagConstraintsXYAI(
        		2, 7, GridBagConstraints.EAST);
        add(uniqueFilesJLabel, gridBagConstraints);

        gridBagConstraints = createGridBagConstraintsXYAI(
        		3, 7, GridBagConstraints.EAST);
        add(uniqueFileListCountDisplay, gridBagConstraints);

        gridBagConstraints = createGridBagConstraintsXYAI(
        		0, 5, GridBagConstraints.EAST);
        add(groupsByChecksumJLabel, gridBagConstraints);

        gridBagConstraints = createGridBagConstraintsXYAI(
        		1, 5, GridBagConstraints.EAST);
        add(nbrGroupsByChecksumDisplay, gridBagConstraints);

        gridBagConstraints = createGridBagConstraintsXYAI(
        		0, 6, GridBagConstraints.EAST);
        add(filesByContentCountJLabel, gridBagConstraints);

        gridBagConstraints = createGridBagConstraintsXYAI(
        		1, 6, GridBagConstraints.EAST);
        add(filesByContentCountDisplay, gridBagConstraints);

        gridBagConstraints = createGridBagConstraintsXYWAI(
        		0, 9, 2, GridBagConstraints.EAST);
        add(elapsedFindingFilesLabel, gridBagConstraints);

        gridBagConstraints = createGridBagConstraintsXYFI(
        		2, 9, GridBagConstraints.HORIZONTAL);
        add(elapsedFindingFilesDisplay, gridBagConstraints);

        gridBagConstraints = createGridBagConstraintsXYWAI(
        		0, 10, 2, GridBagConstraints.EAST);
        add(elapsedGroupBySizeLabel, gridBagConstraints);

        gridBagConstraints = createGridBagConstraintsXYFI(
        		2, 10, GridBagConstraints.HORIZONTAL);
        add(elapsedGroupBySizeDisplay, gridBagConstraints);

        gridBagConstraints = createGridBagConstraintsXYWAI(
        		0, 11, 2, GridBagConstraints.EAST);
        add(elapsedGroupByChecksumLabel, gridBagConstraints);

        gridBagConstraints = createGridBagConstraintsXYFI(
        		2, 11, GridBagConstraints.HORIZONTAL);
        add(elapsedGroupByChecksumDisplay, gridBagConstraints);

        gridBagConstraints = createGridBagConstraintsXYWAI(
        		0, 12, 2, GridBagConstraints.EAST);
        add(elapsedGroupByContentComparisonLabel, gridBagConstraints);

        gridBagConstraints = createGridBagConstraintsXYFI(
        		2, 12, GridBagConstraints.HORIZONTAL);
        add(elapsedGroupByContentComparisonDisplay, gridBagConstraints);

        gridBagConstraints = createGridBagConstraintsXYWAI(
        		0, 13, 2, GridBagConstraints.EAST);
        add(elapsedGroupingSubtreesLabel, gridBagConstraints);

        gridBagConstraints = createGridBagConstraintsXYFI(
        		2, 13, GridBagConstraints.HORIZONTAL);
        add(elapsedGroupingSubtreesDisplay, gridBagConstraints);

        gridBagConstraints = createGridBagConstraintsXYWAI(
        		0, 15, 2, GridBagConstraints.EAST);
        add(elapsedFindingDuplicateFilesLabel, gridBagConstraints);

        gridBagConstraints = createGridBagConstraintsXYFI(
        		2, 15, GridBagConstraints.HORIZONTAL);
        add(elapsedFindingDuplicateFilesDisplay, gridBagConstraints);
    }
}
