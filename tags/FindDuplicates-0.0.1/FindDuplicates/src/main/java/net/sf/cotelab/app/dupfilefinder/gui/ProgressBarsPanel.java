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

/**
 * @author US80653H
 */
public class ProgressBarsPanel extends JPanel implements ResettableObject {
	private static final long serialVersionUID = 1L;

    protected ResettableJProgressBar phaseProgressBar =
    		new ResettableJProgressBar();
	protected JLabel phaseProgressBarLabel = new JLabel("Phase progress:");
    protected ResettableJProgressBar searchProgressBar =
    		new ResettableJProgressBar();
	protected JLabel searchProgressBarLabel = new JLabel("Search progress:");

	/**
	 * 
	 */
	public ProgressBarsPanel() {
		super();

		initComponents();
		reset();
	}

	/**
	 * @param isDoubleBuffered
	 */
	public ProgressBarsPanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);

		initComponents();
		reset();
	}

	/**
	 * @param layout
	 */
	public ProgressBarsPanel(LayoutManager layout) {
		super(layout);

		initComponents();
		reset();
	}

	/**
	 * @param layout
	 * @param isDoubleBuffered
	 */
	public ProgressBarsPanel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);

		initComponents();
		reset();
	}

	/* (non-Javadoc)
	 * @see net.cote.app.dupfilefinder.gui.ResettableJComponent#reset()
	 */
	@Override
	public void reset() {
		phaseProgressBar.reset();
		searchProgressBar.reset();
	}

	/**
	 * @param newValue
	 * @see javax.swing.JProgressBar#setIndeterminate(boolean)
	 */
	public void setPhaseProgressBarIndeterminate(boolean newValue) {
		phaseProgressBar.setIndeterminate(newValue);
	}

	/**
	 * @param s
	 * @see javax.swing.JProgressBar#setString(java.lang.String)
	 */
	public void setPhaseProgressBarString(String s) {
		phaseProgressBar.setString(s);
	}
	
	/**
	 * @param b
	 * @see javax.swing.JProgressBar#setStringPainted(boolean)
	 */
	public void setPhaseProgressBarStringPainted(boolean b) {
		phaseProgressBar.setStringPainted(b);
	}

	/**
	 * @param n
	 * @see javax.swing.JProgressBar#setValue(int)
	 */
	public void setPhaseProgressBarValue(int n) {
		phaseProgressBar.setValue(n);
	}

	/**
	 * @param newValue
	 * @see javax.swing.JProgressBar#setIndeterminate(boolean)
	 */
	public void setSearchProgressBarIndeterminate(boolean newValue) {
		searchProgressBar.setIndeterminate(newValue);
	}

	/**
	 * @param s
	 * @see javax.swing.JProgressBar#setString(java.lang.String)
	 */
	public void setSearchProgressBarString(String s) {
		searchProgressBar.setString(s);
	}
	
	/**
	 * @param b
	 * @see javax.swing.JProgressBar#setStringPainted(boolean)
	 */
	public void setSearchProgressBarStringPainted(boolean b) {
		searchProgressBar.setStringPainted(b);
	}

	/**
	 * @param n
	 * @see javax.swing.JProgressBar#setValue(int)
	 */
	public void setSearchProgressBarValue(int n) {
		searchProgressBar.setValue(n);
	}
    
    protected GridBagConstraints createGridBagConstraintsXYAFI(
    		int gridx, int gridy, int anchor, int fill) {
    	GridBagConstraints gridBagConstraints =
    			createGridBagConstraintsXYAI(gridx, gridy, anchor);

        gridBagConstraints.fill = fill;
        gridBagConstraints.weightx = 1;
        
        return gridBagConstraints;
    }
    
    protected GridBagConstraints createGridBagConstraintsXYAI(
    		int gridx, int gridy, int anchor) {
    	GridBagConstraints gridBagConstraints =
    			createGridBagConstraintsXYI(gridx, gridy);

        gridBagConstraints.anchor = anchor;
        
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
	
	protected void initComponents() {
		GridBagConstraints gridBagConstraints;
		
		setLayout(new GridBagLayout());

        gridBagConstraints = createGridBagConstraintsXYAI(
        		0, 0, GridBagConstraints.EAST);
        add(searchProgressBarLabel, gridBagConstraints);

        gridBagConstraints = createGridBagConstraintsXYAFI(
        		1, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
        add(searchProgressBar, gridBagConstraints);

        gridBagConstraints = createGridBagConstraintsXYAI(
        		0, 1, GridBagConstraints.EAST);
        add(phaseProgressBarLabel, gridBagConstraints);

        gridBagConstraints = createGridBagConstraintsXYAFI(
        		1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
        add(phaseProgressBar, gridBagConstraints);
	}
}
