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
public class StartButtonPanel extends JPanel implements ResettableObject {
	public static final Color BUTTON_DISABLED_FOREGROUND =
    		new Color(64, 64, 64);
	public static final Color BUTTON_ENABLED_FOREGROUND =
            new Color(0, 128, 0);
    public static final boolean DEFAULT_ENABLED = true;
    public static final String DEFAULT_TEXT = "Start Search";

	private static final long serialVersionUID = 1L;
	
	protected JButton startSearchButton = new JButton();

	/**
	 * 
	 */
	public StartButtonPanel() {
		super();

		initComponents();
		reset();
	}

	/**
	 * @param isDoubleBuffered
	 */
	public StartButtonPanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);

		initComponents();
		reset();
	}

	/**
	 * @param layout
	 */
	public StartButtonPanel(LayoutManager layout) {
		super(layout);

		initComponents();
		reset();
	}

	/**
	 * @param layout
	 * @param isDoubleBuffered
	 */
	public StartButtonPanel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);

		initComponents();
		reset();
	}

	public void addStartButtonActionListener(ActionListener listener) {
		startSearchButton.addActionListener(listener);
	}
	
	/* (non-Javadoc)
	 * @see net.cote.app.dupfilefinder.gui.ResettableJComponent#reset()
	 */
	@Override
	public void reset() {
		startSearchButton.setText(DEFAULT_TEXT);
		setButtonEnabled(DEFAULT_ENABLED);
	}

	/**
	 * @param b
	 * @see javax.swing.AbstractButton#setEnabled(boolean)
	 */
	public void setButtonEnabled(boolean b) {
		startSearchButton.setEnabled(b);
		
		if (b) {
			startSearchButton.setForeground(BUTTON_ENABLED_FOREGROUND);
		} else {
			startSearchButton.setForeground(BUTTON_DISABLED_FOREGROUND);
		}
	}

	protected void initComponents() {
		setLayout(new FlowLayout(FlowLayout.RIGHT));
		
        add(startSearchButton);
	}
}
