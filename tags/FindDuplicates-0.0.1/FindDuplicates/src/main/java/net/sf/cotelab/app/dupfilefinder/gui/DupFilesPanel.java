/**
 * 
 */
package net.sf.cotelab.app.dupfilefinder.gui;

import java.awt.BorderLayout;
import java.awt.LayoutManager;

import javax.swing.JPanel;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/**
 * @author US80653H
 *
 */
public class DupFilesPanel extends JPanel implements ResettableObject {
	private static final long serialVersionUID = 1L;
	
	protected ResettableJLabel dupFilesSummaryLabel = new ResettableJLabel();
	protected JTreeScrollPanel resultsScrollPanel = new JTreeScrollPanel();

	/**
	 * 
	 */
	public DupFilesPanel() {
		super();
		
		initComponents();
	}

	/**
	 * @param isDoubleBuffered
	 */
	public DupFilesPanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		
		initComponents();
	}

	/**
	 * @param layout
	 */
	public DupFilesPanel(LayoutManager layout) {
		super(layout);
		
		initComponents();
	}

	/**
	 * @param layout
	 * @param isDoubleBuffered
	 */
	public DupFilesPanel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		
		initComponents();
	}

	/**
	 * @param path
	 * @see net.sf.cotelab.app.dupfilefinder.gui.JTreeScrollPanel#makeVisible(javax.swing.tree.TreePath)
	 */
	public void makeVisible(TreePath path) {
		resultsScrollPanel.makeVisible(path);
	}

	/* (non-Javadoc)
	 * @see net.cote.app.dupfilefinder.gui.ResettableObject#reset()
	 */
	@Override
	public void reset() {
		dupFilesSummaryLabel.reset();
		resultsScrollPanel.reset();
	}

	/**
	 * @param x
	 * @see net.sf.cotelab.app.dupfilefinder.gui.JTreeScrollPanel#setCellRenderer(javax.swing.tree.TreeCellRenderer)
	 */
	public void setCellRenderer(TreeCellRenderer x) {
		resultsScrollPanel.setCellRenderer(x);
	}

	/**
	 * @param defaultRootNodeText
	 * @see net.sf.cotelab.app.dupfilefinder.gui.JTreeScrollPanel#setDefaultRootNodeText(java.lang.String)
	 */
	public void setDefaultRootNodeText(String defaultRootNodeText) {
		resultsScrollPanel.setDefaultRootNodeText(defaultRootNodeText);
	}
	
	/**
	 * @param defaultText
	 * @see net.sf.cotelab.app.dupfilefinder.gui.ResettableJLabel#setDefaultText(java.lang.String)
	 */
	public void setDefaultSummaryLabelText(String defaultText) {
		dupFilesSummaryLabel.setDefaultText(defaultText);
	}

	/**
	 * @param text
	 * @see javax.swing.JLabel#setText(java.lang.String)
	 */
	public void setDupFilesSummaryLabelText(String text) {
		dupFilesSummaryLabel.setText(text);
	}

	/**
	 * @param newModel
	 * @see net.sf.cotelab.app.dupfilefinder.gui.JTreeScrollPanel#setTreeModel(javax.swing.tree.TreeModel)
	 */
	public void setTreeModel(TreeModel newModel) {
		resultsScrollPanel.setTreeModel(newModel);
	}

	protected void initComponents() {
		setLayout(new BorderLayout());
		
		add(resultsScrollPanel, BorderLayout.CENTER);
		add(dupFilesSummaryLabel, BorderLayout.SOUTH);
	}
}
