/**
 * 
 */
package net.sf.cotelab.app.dupfilefinder.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * @author US80653H
 */
public class StopButtonPanel extends JPanel implements ResettableObject {
	public static final Color BUTTON_DISABLED_FOREGROUND =
    		new Color(64, 64, 64);
	public static final Color BUTTON_ENABLED_FOREGROUND =
            new Color(128, 0, 0);
	public static final boolean DEFAULT_ENABLED = false;
	public static final String DEFAULT_TEXT = "Stop Search";

	private static final long serialVersionUID = 1L;
	
	protected JButton stopSearchButton = new JButton();

	/**
	 * 
	 */
	public StopButtonPanel() {
		super();

		initComponents();
		reset();
	}

	/**
	 * @param isDoubleBuffered
	 */
	public StopButtonPanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);

		initComponents();
		reset();
	}

	/**
	 * @param layout
	 */
	public StopButtonPanel(LayoutManager layout) {
		super(layout);

		initComponents();
		reset();
	}

	/**
	 * @param layout
	 * @param isDoubleBuffered
	 */
	public StopButtonPanel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);

		initComponents();
		reset();
	}

	/**
	 * @param l
	 * @see javax.swing.AbstractButton#addActionListener(java.awt.event.ActionListener)
	 */
	public void addActionListener(ActionListener l) {
		stopSearchButton.addActionListener(l);
	}

	/* (non-Javadoc)
	 * @see net.cote.app.dupfilefinder.gui.ResettableJComponent#reset()
	 */
	@Override
	public void reset() {
		stopSearchButton.setText(DEFAULT_TEXT);
		setButtonEnabled(DEFAULT_ENABLED);
	}
	
	/**
	 * @param b
	 * @see javax.swing.AbstractButton#setEnabled(boolean)
	 */
	public void setButtonEnabled(boolean b) {
		stopSearchButton.setEnabled(b);
		
		if (b) {
			stopSearchButton.setForeground(BUTTON_ENABLED_FOREGROUND);
		} else {
			stopSearchButton.setForeground(BUTTON_DISABLED_FOREGROUND);
		}
	}
	
	protected void initComponents() {
		setLayout(new FlowLayout(FlowLayout.RIGHT));
		
        add(stopSearchButton);
	}
}
