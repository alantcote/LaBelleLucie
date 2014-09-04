/**
 * 
 */
package net.sf.cotelab.app.dupfilefinder.gui;

import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

/**
 * @author US80653H
 */
public class ButtonAndMssgsPanel extends JPanel
		implements ResettableObject {
    private static final long serialVersionUID = 1L;
    
	protected StopButtonPanel button = new StopButtonPanel();
	protected ProgressMessagesPanel messages = new ProgressMessagesPanel();

	/**
	 * 
	 */
	public ButtonAndMssgsPanel() {
		super();

		initComponents();
		reset();
	}

	/**
	 * @param isDoubleBuffered
	 */
	public ButtonAndMssgsPanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);

		initComponents();
		reset();
	}

	/**
	 * @param layout
	 */
	public ButtonAndMssgsPanel(LayoutManager layout) {
		super(layout);

		initComponents();
		reset();
	}

	/**
	 * @param layout
	 * @param isDoubleBuffered
	 */
	public ButtonAndMssgsPanel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);

		initComponents();
		reset();
	}
	
	/**
	 * @param l
	 * @see net.sf.cotelab.app.dupfilefinder.gui.StopButtonPanel#addActionListener(java.awt.event.ActionListener)
	 */
	public void addActionListener(ActionListener l) {
		button.addActionListener(l);
	}
	
	public void reset() {
		button.reset();
		messages.reset();
	}
	
	/**
	 * @param b
	 * @see net.sf.cotelab.app.dupfilefinder.gui.StopButtonPanel#setButtonEnabled(boolean)
	 */
	public void setButtonEnabled(boolean b) {
		button.setButtonEnabled(b);
	}

	/**
	 * @param text
	 * @see net.sf.cotelab.app.dupfilefinder.gui.ProgressMessagesPanel#setDetailMessageText(java.lang.String)
	 */
	public void setDetailMessageText(String text) {
		messages.setDetailMessageText(text);
	}

	/**
	 * @param text
	 * @see net.sf.cotelab.app.dupfilefinder.gui.ProgressMessagesPanel#setSummaryMessageText(java.lang.String)
	 */
	public void setSummaryMessageText(String text) {
		messages.setSummaryMessageText(text);
	}

	protected void initComponents() {
		setLayout(new BorderLayout());
		
        add(button, BorderLayout.WEST);
        add(messages, BorderLayout.CENTER);
	}
}
