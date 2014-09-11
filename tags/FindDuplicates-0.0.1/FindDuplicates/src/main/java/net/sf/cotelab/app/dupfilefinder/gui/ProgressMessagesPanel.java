/**
 * 
 */
package net.sf.cotelab.app.dupfilefinder.gui;

import java.awt.BorderLayout;
import java.awt.LayoutManager;

import javax.swing.JPanel;

/**
 * @author US80653H
 */
public class ProgressMessagesPanel extends JPanel
		implements ResettableObject {
	public static final String DEFAULT_DETAILS_TEXT = "No detail available.";
	public static final String DEFAULT_SUMMARY_TEXT = "Idle.";
	
	private static final long serialVersionUID = 1L;

	protected ResettableJLabel details = new ResettableJLabel();
	protected ResettableJLabel summary = new ResettableJLabel();

	/**
	 * 
	 */
	public ProgressMessagesPanel() {
		super();

		initComponents();
		reset();
	}

	/**
	 * @param isDoubleBuffered
	 */
	public ProgressMessagesPanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);

		initComponents();
		reset();
	}

	/**
	 * @param layout
	 */
	public ProgressMessagesPanel(LayoutManager layout) {
		super(layout);

		initComponents();
		reset();
	}
	
	/**
	 * @param layout
	 * @param isDoubleBuffered
	 */
	public ProgressMessagesPanel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);

		initComponents();
		reset();
	}
	
	@Override
	public void reset() {
		details.reset();
		summary.reset();
	}
	
	/**
	 * @param text
	 * @see javax.swing.JLabel#setText(java.lang.String)
	 */
	public void setDetailMessageText(String text) {
		details.setText(text);
	}
	
	/**
	 * @param text
	 * @see javax.swing.JLabel#setText(java.lang.String)
	 */
	public void setSummaryMessageText(String text) {
		summary.setText(text);
	}

	protected void initComponents() {
		setLayout(new BorderLayout());

		details.setDefaultText(DEFAULT_DETAILS_TEXT);
        add(details, BorderLayout.SOUTH);

		summary.setDefaultText(DEFAULT_SUMMARY_TEXT);
        add(summary, BorderLayout.NORTH);
	}
}
